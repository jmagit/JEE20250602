package com.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.contracts.distributed.services.ConverterBeanRemote;
import com.example.contracts.distributed.services.CounterBean;
import com.example.contracts.distributed.services.LikesBeanRemote;
import com.example.contracts.distributed.services.SaludoBeanRemote;
import com.example.soap.client.Calculadora;
import com.example.soap.client.CalculadoraService;

public class App {
	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println(args[0] + " " + args[1]);
			switch (args[0]) {
			case "tienda":
				recibeFacturas(args[1]);
				mandaPedidos(args[1]);
				break;
			case "oficina":
				procesaPedidos(args[1]);
				break;
			case "sensor":
				sensor(args[1]);
				break;
			default:
				break;
			}
		}
//		consumirSOAP();
//		peticionesEJB();
		System.err.println("------------------------------- FIN");
	}

	private static InitialContext getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
		props.put(Context.PROVIDER_URL, "iiop://localhost:3700");
		return new InitialContext(props);
	}

	private static ConnectionFactory getConnection(InitialContext ctx) throws NamingException {
		return (ConnectionFactory) getInitialContext().lookup("jms/cursoConnectionFactory");
	}

	private static JMSContext getJMSContext(ConnectionFactory conn) throws NamingException {
		return conn.createContext();
	}

	private static void mandaPedidos(String id) {
		InitialContext ctx;
		ConnectionFactory conn;
		try {
			ctx = getInitialContext();
			conn = getConnection(ctx);
			try (JMSContext context = getJMSContext(conn)) {
				Queue queue = (Queue) ctx.lookup("jms/peticionesQueue");
				JMSProducer producer = context.createProducer();
				for (int i = 0; i < 10; i++) {
					String body = "Pedido de las " + LocalDateTime.now() + " de " + id;
					producer.send(queue, body);
					System.out.println("Pedido enviado por " + id + ": " + body);
					Thread.sleep(10000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void procesaPedidos(String id) {
		InitialContext ctx;
		ConnectionFactory conn;
		Queue facturas = null;
		try {
			ctx = getInitialContext();
			conn = getConnection(ctx);
			try (JMSContext context = getJMSContext(conn)) {
				Queue pedios = (Queue) ctx.lookup("jms/peticionesQueue");
				JMSConsumer consumer = context.createConsumer(pedios);
				while (true) {
					String body = consumer.receiveBody(String.class, 30000);
					if (body == null)
						break;
					Thread.sleep(1000);
					body = "Factura de " + id + " para el " + body.toLowerCase();
					if (facturas == null)
						facturas = (Queue) ctx.lookup("jms/respuestasQueue");
					context.createProducer().send(facturas, body);
					System.out.println("Factura enviada por " + id + ": " + body);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	MessageConsumer consumerListener;

	private static void recibeFacturas(String id) {
		InitialContext ctx;
		ConnectionFactory conn;
		try {
			ctx = getInitialContext();
			conn = getConnection(ctx);
			Connection connection = conn.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue facturas = (Queue) ctx.lookup("jms/respuestasQueue");
			MessageConsumer consumerListener = session.createConsumer(facturas);
			consumerListener.setMessageListener(message -> {
				try {
					System.out.println("Factura recibida por " + id + ": " + ((TextMessage) message).getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			});
			connection.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sensor(String nombre) {
		List<Integer> peloton = new ArrayList<Integer>();
		for (int i = 1; i <= 100; peloton.add(i++))
			;
		Collections.shuffle(peloton);
		Random rnd = new Random();
		InitialContext ctx;
		ConnectionFactory conn;
		try {
			ctx = getInitialContext();
			conn = getConnection(ctx);
			try (JMSContext context = getJMSContext(conn)) {
				Topic topic = (Topic) ctx.lookup("jms/sensoresTopic");
				JMSProducer producer = context.createProducer();
				for (int dorsal : peloton) {
					String body = nombre +  ": " + dorsal;
					producer.send(topic, body);
					System.out.println(body);
					Thread.sleep(rnd.nextInt(5)*500);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void peticionesEJB() {
		System.out.println("Peticiones EJB");
		String jndi = "";
		try {
			InitialContext ctx = new InitialContext();
			System.out.println("Environment" + ctx.getNameInNamespace());
			ctx.getEnvironment().forEach((k, v) -> System.out.println(k + ": " + v));
			System.out.println("Peticion Saluda");
			SaludoBeanRemote saluda = (SaludoBeanRemote) ctx
					.lookup("com.example.contracts.distributed.services.SaludoBeanRemote");
//			SaludoBeanLocal saluda = (SaludoBeanLocal) ctx.lookup("com.example.contracts.distributed.services.SaludoBeanLocal");
			System.out.println(saluda.getSaludo());
//			System.out.println(saluda.getSaludoFormal()); 
//			System.out.println(saluda.getSaludoInformal()); 

			System.out.println("Peticion ConverterBeanRemote");
			jndi = "com.example.contracts.distributed.services.ConverterBeanRemote";
			jndi = "java:global/demos-app/demos-ejb/ConverterBean!com.example.contracts.distributed.services.ConverterBeanRemote";
			ConverterBeanRemote conv = (ConverterBeanRemote) ctx.lookup(jndi);
			System.out.println("Cambio: " + conv.yenToEuro(new BigDecimal(10)));

//			System.out.println("Peticion ConverterBeanLocal"); 
//			jndi = "java:global/demos-app/demos-ejb/ConverterBean!com.example.contracts.distributed.services.ConverterBeanLocal";
//			ConverterBeanLocal convL = (ConverterBeanLocal) ctx.lookup(jndi);
//			System.out.println("Cambio: " + convL.dollarToYenLocal(new BigDecimal(10)));

			System.out.println("Peticiones LikesBeanRemote");
			LikesBeanRemote like = (LikesBeanRemote) ctx
					.lookup("com.example.contracts.distributed.services.LikesBeanRemote");
			for (int i = 0; i < 10; i++) {
				System.out.println("Like (stateful): You has send " + like.getHits() + " like(s).");
			}

			System.out.println("Peticion CounterBean");
			CounterBean cont = (CounterBean) ctx.lookup("com.example.presentation.services.enterprise.CounterBean");
			System.out.println("Cont: " + cont.getHits());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static void consumirSOAP() {
		try {
			System.out.println("Peticion SOAP");
			Calculadora calculadora = new CalculadoraService().getCalculadoraPort();
			System.out.println("Suma: " + calculadora.suma(0.1, 0.2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
