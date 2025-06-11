package com.example.infraestructure.messages;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.example.contracts.domain.distributed.PedidosCommand;

@Stateless(mappedName = "ejb/pedidosSender")
@LocalBean
public class PedidosQueue implements PedidosCommand {
    @Resource(lookup = "jms/cursoConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/peticionesQueue")
    private Queue queue;

    @Override
	public void send(String text) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            producer.send(queue, text);
            System.out.println(getClass().getSimpleName() + ": Mensaje '" + text + "' enviado a " + queue.getQueueName());
        } catch (JMSException e) {
            System.err.println(getClass().getSimpleName() + ": Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
	public String receive(long timeoutMillis) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer = context.createConsumer(queue);
            Message message = consumer.receive(timeoutMillis);

            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                System.out.println(getClass().getSimpleName() + ": Mensaje recibido de OutputQueue: '" + text + "'");
                return text;
            } else if (message == null) {
            	return null;
//                System.out.println(getClass().getSimpleName() + ": No se recibió ningún mensaje dentro del tiempo de espera.");
//                return "No message received";
            } else {
                System.out.println(getClass().getSimpleName() + ": Recibido un mensaje no de texto: " + message.getClass().getName());
                return "Non-text message received";
            }
        } catch (JMSException e) {
            System.err.println(getClass().getSimpleName() + ": Error al recibir el mensaje: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
