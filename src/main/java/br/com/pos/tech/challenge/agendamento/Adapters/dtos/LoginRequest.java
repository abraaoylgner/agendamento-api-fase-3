package br.com.pos.tech.challenge.agendamento.Adapters.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String senha;
}