package com.example.infraestructure.events;

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
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: SensoresSupervisorListener
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationLookup", propertyValue = "jms/sensoresTopic"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "jms/sensoresTopic")
public class SensoresSupervisorListener implements MessageListener {
    @Resource(lookup = "jms/cursoConnectionFactory")
    private ConnectionFactory connectionFactory;
	
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
				System.out.println("Evento " + message.getJMSMessageID() + " procesado por " + getClass().getSimpleName() 
						+ ": " + ((TextMessage) message).getText());
            } else {
                System.out.println(getClass().getSimpleName() + ": Recibido un evento no de texto: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            System.err.println(getClass().getSimpleName() + ": Error al procesar/reenviar evento: " + e.getMessage());
            e.printStackTrace();
		}
    }

}
