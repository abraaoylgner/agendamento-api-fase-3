package br.com.pos.tech.challenge.app3.Application.usecases;

import br.com.pos.tech.challenge.app3.Application.ports.ConsultaRepositoryPort;
import br.com.pos.tech.challenge.app3.Application.ports.NotificacaoPublisherPort;
import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;

import java.time.LocalDateTime;
import java.util.UUID;

public class AgendarConsultaUseCase {

    private final ConsultaRepositoryPort consultaRepository;
    private final NotificacaoPublisherPort notificacaoPublisher;

    public AgendarConsultaUseCase(ConsultaRepositoryPort consultaRepository, NotificacaoPublisherPort notificacaoPublisher) {
        this.consultaRepository = consultaRepository;
        this.notificacaoPublisher = notificacaoPublisher;
    }

    public Consulta executar(UUID pacienteId, UUID profissionalId, LocalDateTime dataHora) {
        // 1. Cria a entidade (a regra de nascer com status "AGENDADA" está encapsulada no domínio)
        Consulta novaConsulta = new Consulta(pacienteId, profissionalId, dataHora);

        // 2. Salva no banco de dados (através da porta)
        Consulta consultaSalva = consultaRepository.salvar(novaConsulta);

        // 3. Envia a mensagem para notificar o paciente (através da porta)
        notificacaoPublisher.enviarNotificacao(consultaSalva);

        return consultaSalva;
    }
}