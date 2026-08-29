package br.com.pos.tech.challenge.app3.Infrastructure.config;

import br.com.pos.tech.challenge.app3.Application.ports.ConsultaRepositoryPort;
import br.com.pos.tech.challenge.app3.Application.ports.NotificacaoPublisherPort;
import br.com.pos.tech.challenge.app3.Application.usecases.AgendarConsultaUseCase;
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