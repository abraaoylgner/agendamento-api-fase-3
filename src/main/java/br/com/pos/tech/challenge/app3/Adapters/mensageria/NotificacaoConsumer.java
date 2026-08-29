package br.com.pos.tech.challenge.app3.Adapters.mensageria;

import br.com.pos.tech.challenge.app3.Infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificacaoConsumer {

    @RabbitListener(queues = RabbitMQConfig.FILA_NOTIFICACOES)
    public void receberNotificacao(Map<String, Object> payload) {
        System.out.println("=========================================");
        System.out.println("🔔 SERVIÇO DE NOTIFICAÇÕES ACIONADO");
        System.out.println("Processando envio de lembrete para o paciente...");

        // Em um cenário real, aqui entraria a integração com um serviço de E-mail ou SMS
        System.out.println("ID da Consulta: " + payload.get("consultaId"));
        System.out.println("ID do Paciente: " + payload.get("pacienteId"));
        System.out.println("Data/Hora: " + payload.get("dataHora"));
        System.out.println("Mensagem original: " + payload.get("mensagem"));

        System.out.println("✅ Lembrete simulado com sucesso!");
        System.out.println("=========================================");
    }
}