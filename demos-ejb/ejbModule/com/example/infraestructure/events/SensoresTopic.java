package com.example.infraestructure.events;

import java.util.Optional;

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
import javax.jms.Topic;

import com.example.contracts.domain.distributed.SensoresEvent;

@Stateless
@LocalBean
public class SensoresTopic implements SensoresEvent {
    @Resource(lookup = "jms/cursoConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/sensoresTopic")
    private Topic topic;

    @Override
	public void send(String text) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            producer.send(topic, text);
            System.out.println(getClass().getSimpleName() + ": Evento '" + text + "' enviado a " + topic.getTopicName());
        } catch (JMSException e) {
            System.err.println(getClass().getSimpleName() + ": Error al enviar el evento: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
	public Optional<String> receive(long timeoutMillis) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer = context.createConsumer(topic);
            Message message = consumer.receive(timeoutMillis);

            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                return Optional.of(text);
            } else if (message == null) {
            	return Optional.empty();
            } else {
            	throw new JMSException("Received a non-text message: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(getClass().getSimpleName() + ": Error al recibir el evento: " + e.getMessage(), e);
        }
    }
}
