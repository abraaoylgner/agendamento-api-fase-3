package br.com.pos.tech.challenge.agendamento.Infrastructure.config;

import br.com.pos.tech.challenge.agendamento.Application.ports.ConsultaRepositoryPort;
import br.com.pos.tech.challenge.agendamento.Application.ports.NotificacaoPublisherPort;
import br.com.pos.tech.challenge.agendamento.Application.usecases.AgendarConsultaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AgendarConsultaUseCase agendarConsultaUseCase(
            ConsultaRepositoryPort consultaRepositoryPort,
            NotificacaoPublisherPort notificacaoPublisherPort) {
        return new AgendarConsultaUseCase(consultaRepositoryPort, notificacaoPublisherPort);
    }
}