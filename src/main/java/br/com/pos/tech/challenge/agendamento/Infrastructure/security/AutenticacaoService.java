package br.com.pos.tech.challenge.agendamento.Infrastructure.security;

import br.com.pos.tech.challenge.agendamento.Infrastructure.persistence.SpringDataUsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final SpringDataUsuarioRepository repository;

    public AutenticacaoService(SpringDataUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuarioEntity = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuarioEntity.getTipoUsuario()))
        );
    }
}