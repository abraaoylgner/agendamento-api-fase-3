package br.com.pos.tech.challenge.agendamento.Application.ports;

import br.com.pos.tech.challenge.agendamento.Domain.entities.Consulta;

public interface NotificacaoPublisherPort {
    void enviarNotificacao(Consulta consulta);
}