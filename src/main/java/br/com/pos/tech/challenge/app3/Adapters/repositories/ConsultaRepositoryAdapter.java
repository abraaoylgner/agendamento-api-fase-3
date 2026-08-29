package br.com.pos.tech.challenge.app3.Adapters.repositories;

import br.com.pos.tech.challenge.app3.Application.ports.ConsultaRepositoryPort;
import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;
import br.com.pos.tech.challenge.app3.Infrastructure.persistence.ConsultaEntity;
import br.com.pos.tech.challenge.app3.Infrastructure.persistence.SpringDataConsultaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ConsultaRepositoryAdapter implements ConsultaRepositoryPort {

    private final SpringDataConsultaRepository springDataRepository;

    public ConsultaRepositoryAdapter(SpringDataConsultaRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Consulta salvar(Consulta consulta) {
        ConsultaEntity entity = new ConsultaEntity();
        entity.setId(consulta.getId());
        entity.setPacienteId(consulta.getPacienteId());
        entity.setProfissionalId(consulta.getProfissionalId());
        entity.setDataHora(consulta.getDataHora());
        entity.setStatus(consulta.getStatus().name());
        entity.setObservacoes(consulta.getObservacoes());

        springDataRepository.save(entity);
        return consulta;
    }

    @Override
    public List<Consulta> buscarPorPacienteId(UUID pacienteId) {
        // Na prática, você usará o MapStruct aqui para converter Entity de volta para Domínio
        return springDataRepository.findByPacienteId(pacienteId).stream()
                .map(entity -> new Consulta(entity.getPacienteId(), entity.getProfissionalId(), entity.getDataHora()))
                .collect(Collectors.toList());
    }
}