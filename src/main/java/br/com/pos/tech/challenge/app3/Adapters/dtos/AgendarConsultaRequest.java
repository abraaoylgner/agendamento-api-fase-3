package br.com.pos.tech.challenge.app3.Adapters.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AgendarConsultaRequest {
    private UUID pacienteId;
    private UUID profissionalId;
    private LocalDateTime dataHora;
}