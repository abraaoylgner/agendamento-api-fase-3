package br.com.pos.tech.challenge.app3.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "consultas")
@Getter
@Setter
public class ConsultaEntity {

    @Id
    private UUID id;

    private UUID pacienteId;
    private UUID profissionalId;
    private LocalDateTime dataHora;
    private String status;
    private String observacoes;
}