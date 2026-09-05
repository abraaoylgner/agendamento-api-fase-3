package br.com.pos.tech.challenge.agendamento.Application.ports;

import br.com.pos.tech.challenge.agendamento.Domain.entities.Consulta;
import java.util.List;
import java.util.UUID;

public interface ConsultaRepositoryPort {
    Consulta salvar(Consulta consulta);
    List<Consulta> buscarPorPacienteId(UUID pacienteId);
}