package br.com.pos.tech.challenge.agendamento.Domain.entities;

import br.com.pos.tech.challenge.agendamento.Domain.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String documento; // Pode ser CRM, COREN ou CPF dependendo do TipoUsuario
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, String email, String senha, String documento, TipoUsuario tipoUsuario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.documento = documento;
        this.tipoUsuario = tipoUsuario;
    }
}