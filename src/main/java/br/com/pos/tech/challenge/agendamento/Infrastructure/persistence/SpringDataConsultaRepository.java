package br.com.pos.tech.challenge.agendamento.Infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataConsultaRepository extends JpaRepository<ConsultaEntity, UUID> {
    List<ConsultaEntity> findByPacienteId(UUID pacienteId);
}