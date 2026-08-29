package br.com.pos.tech.challenge.app3.Adapters.controllers;

import br.com.pos.tech.challenge.app3.Adapters.dtos.AgendarConsultaRequest;
import br.com.pos.tech.challenge.app3.Application.usecases.AgendarConsultaUseCase;
import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;
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
