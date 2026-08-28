package br.com.pos.tech.challenge.app3.Domain.entities;

import br.com.pos.tech.challenge.app3.Domain.enums.StatusConsulta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Consulta {
    private UUID id;
    private UUID pacienteId;
    private UUID profissionalId; // ID do médico responsável
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private String observacoes;

    public Consulta(UUID pacienteId, UUID profissionalId, LocalDateTime dataHora) {
        this.id = UUID.randomUUID();
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
        this.dataHora = dataHora;
        this.status = StatusConsulta.AGENDADA; // Regra de negócio: toda consulta nasce agendada
    }

    // Regras de negócio encapsuladas no domínio
    public void cancelar() {
        this.status = StatusConsulta.CANCELADA;
    }

    public void concluir(String observacoes) {
        this.status = StatusConsulta.REALIZADA;
        this.observacoes = observacoes;
    }
}