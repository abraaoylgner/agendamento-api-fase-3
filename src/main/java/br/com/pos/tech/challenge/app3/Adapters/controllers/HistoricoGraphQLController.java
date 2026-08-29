package br.com.pos.tech.challenge.app3.Adapters.controllers;

import br.com.pos.tech.challenge.app3.Application.ports.ConsultaRepositoryPort;
import br.com.pos.tech.challenge.app3.Domain.entities.Consulta;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class HistoricoGraphQLController {

    // Em um cenário completo, você pode criar um UseCase específico para buscar o histórico.
    // Para simplificar, estamos utilizando a porta do repositório diretamente na leitura.
    private final ConsultaRepositoryPort consultaRepositoryPort;

    public HistoricoGraphQLController(ConsultaRepositoryPort consultaRepositoryPort) {
        this.consultaRepositoryPort = consultaRepositoryPort;
    }

    @QueryMapping
    public List<Consulta> historicoPaciente(@Argument UUID pacienteId) {
        return consultaRepositoryPort.buscarPorPacienteId(pacienteId);
    }
}