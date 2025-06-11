package com.example.infraestructure.events;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: SensoresSupervisorListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/sensoresTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "jms/sensoresTopic")
public class SensoresProcesadorListener implements MessageListener {
	@Resource(lookup = "jms/cursoConnectionFactory")
	private ConnectionFactory connectionFactory;

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				try {
					Thread.sleep(1000);
					System.out.println("Evento " + message.getJMSMessageID() + " procesado por "
							+ getClass().getSimpleName() + ": " + ((TextMessage) message).getText());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(getClass().getSimpleName() + ": Recibido un evento no de texto: "
						+ message.getClass().getName());
			}
		} catch (JMSException e) {
			System.err.println(getClass().getSimpleName() + ": Error al procesar/reenviar evento: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
