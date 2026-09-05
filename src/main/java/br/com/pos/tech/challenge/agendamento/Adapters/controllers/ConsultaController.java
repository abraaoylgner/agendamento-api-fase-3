package br.com.pos.tech.challenge.agendamento.Adapters.controllers;

import br.com.pos.tech.challenge.agendamento.Adapters.dtos.AgendarConsultaRequest;
import br.com.pos.tech.challenge.agendamento.Application.usecases.AgendarConsultaUseCase;
import br.com.pos.tech.challenge.agendamento.Domain.entities.Consulta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendarConsultaUseCase agendarConsultaUseCase;

    public ConsultaController(AgendarConsultaUseCase agendarConsultaUseCase) {
        this.agendarConsultaUseCase = agendarConsultaUseCase;
    }

    @PostMapping
    public ResponseEntity<Consulta> agendar(@RequestBody AgendarConsultaRequest request) {
        Consulta consultaAgendada = agendarConsultaUseCase.executar(
                request.getPacienteId(),
                request.getProfissionalId(),
                request.getDataHora()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaAgendada);
    }
}
