package br.com.pos.tech.challenge.agendamento.Infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_NOTIFICACOES = "notificacoes.fila";
    public static final String EXCHANGE_NOTIFICACOES = "notificacoes.exchange";
    public static final String ROUTING_KEY_NOTIFICACOES = "notificacoes.routing.key";

    @Bean
    public Queue filaNotificacoes() {
        return QueueBuilder.durable(FILA_NOTIFICACOES).build();
    }

    @Bean
    public DirectExchange exchangeNotificacoes() {
        return new DirectExchange(EXCHANGE_NOTIFICACOES);
    }

    @Bean
    public Binding bindingNotificacoes(Queue filaNotificacoes, DirectExchange exchangeNotificacoes) {
        return BindingBuilder.bind(filaNotificacoes).to(exchangeNotificacoes).with(ROUTING_KEY_NOTIFICACOES);
    }

    // Conversor para enviar a mensagem em formato JSON
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}