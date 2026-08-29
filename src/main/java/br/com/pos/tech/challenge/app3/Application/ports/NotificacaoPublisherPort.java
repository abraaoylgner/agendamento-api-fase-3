package br.com.pos.tech.challenge.app3.Application.ports;

import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;

public interface NotificacaoPublisherPort {
    void enviarNotificacao(Consulta consulta);
}