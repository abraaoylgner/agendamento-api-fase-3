package br.com.pos.tech.challenge.app3.Adapters.mensageria;

import br.com.pos.tech.challenge.app3.Application.ports.NotificacaoPublisherPort;
import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;
import br.com.pos.tech.challenge.app3.Infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificacaoPublisherAdapter implements NotificacaoPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoPublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarNotificacao(Consulta consulta) {
        // Criamos um payload simples em Map para ser convertido em JSON pelo Jackson
        Map<String, Object> payload = new HashMap<>();
        payload.put("consultaId", consulta.getId());
        payload.put("pacienteId", consulta.getPacienteId());
        payload.put("dataHora", consulta.getDataHora().toString());
        payload.put("mensagem", "Nova consulta agendada.");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NOTIFICACOES,
                RabbitMQConfig.ROUTING_KEY_NOTIFICACOES,
                payload
        );

        System.out.println("Mensagem enviada para o RabbitMQ: " + payload);
    }
}