package com.example.infraestructure.messages;

import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ContableListener
 */
//ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") // Modo de reconocimiento de mensaje
@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/peticionesQueue"), 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		})
public class ContableListener implements MessageListener {
    @Resource(lookup = "jms/cursoConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/respuestasQueue")
    private Queue outputQueue;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String body = textMessage.getText();
				Thread.sleep(1000);
				body = "Factura de ContableListener a las " + LocalDateTime.now() + " para el " + body.toLowerCase();

                // --- Envío del mensaje a OutputQueue ---
                try (JMSContext context = connectionFactory.createContext()) {
                    JMSProducer producer = context.createProducer();
                    producer.send(outputQueue, body);
//					System.out.println("Nueva factura enviada por "+ getClass().getSimpleName() + ": " + body);
                }

            } else {
                System.out.println(getClass().getSimpleName() + ": Recibido un mensaje no de texto: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println(getClass().getSimpleName() + ": Error al procesar/reenviar mensaje: " + e.getMessage());
            e.printStackTrace();
         } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
