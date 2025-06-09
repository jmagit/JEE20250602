package com.example;

import java.math.BigDecimal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.contracts.distributed.services.ConverterBeanLocal;
import com.example.contracts.distributed.services.ConverterBeanRemote;
import com.example.contracts.distributed.services.CounterBean;
import com.example.contracts.distributed.services.LikesBeanRemote;
import com.example.soap.client.Calculadora;
import com.example.soap.client.CalculadoraService;

public class App {
	public static void main(String[] args) {
//		System.out.println("Peticion SOAP");
//		Calculadora calculadora = new CalculadoraService().getCalculadoraPort();
//		System.out.println("Suma: " + calculadora.suma(5, 3)); // Suma: 8.0
		
		System.out.println("Peticiones EJB"); 
		try {
			InitialContext ctx = new InitialContext();
			System.out.println("Environment" + ctx.getNameInNamespace());
			ctx.getEnvironment().forEach((k,v) -> System.out.println(k + ": " + v));
			String jndi = "";
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
			LikesBeanRemote like = (LikesBeanRemote) ctx.lookup("com.example.contracts.distributed.services.LikesBeanRemote");
			for(int i = 0; i < 10; i++) {
				System.out.println("Like (stateful): You has send " + like.getHits() + " like(s)."); 
			}
//			System.out.println("Peticion CounterBean"); 
//			CounterBean cont = (CounterBean) ctx.lookup("com.example.presentation.services.enterprise.CounterBean");
//			System.out.println("Cont: " + cont.getHits());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
