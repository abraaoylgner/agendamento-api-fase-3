package br.com.pos.tech.challenge.agendamento.Infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioEntity {

    @Id
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String documento;
    private String tipoUsuario; // MEDICO, ENFERMEIRO ou PACIENTE
}