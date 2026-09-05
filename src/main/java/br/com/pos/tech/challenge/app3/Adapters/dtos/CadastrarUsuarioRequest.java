package br.com.pos.tech.challenge.app3.Adapters.dtos;

import br.com.pos.tech.challenge.app3.Domain.enums.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarUsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private String documento;
    private TipoUsuario tipoUsuario; // MEDICO, ENFERMEIRO ou PACIENTE
}