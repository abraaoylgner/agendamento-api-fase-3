package br.com.pos.tech.challenge.app3.Adapters.controllers;

import br.com.pos.tech.challenge.app3.Adapters.dtos.CadastrarUsuarioRequest;
import br.com.pos.tech.challenge.app3.Adapters.dtos.LoginRequest;
import br.com.pos.tech.challenge.app3.Domain.entities.Usuario;
import br.com.pos.tech.challenge.app3.Domain.enums.TipoUsuario;
import br.com.pos.tech.challenge.app3.Infrastructure.persistence.SpringDataUsuarioRepository;
import br.com.pos.tech.challenge.app3.Infrastructure.persistence.UsuarioEntity;
import br.com.pos.tech.challenge.app3.Infrastructure.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SpringDataUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            TokenService tokenService,
            SpringDataUsuarioRepository repository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody CadastrarUsuarioRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "E-mail já cadastrado no sistema."));
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(UUID.randomUUID());
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setSenha(passwordEncoder.encode(request.getSenha())); // Criptografa a senha em BCrypt
        entity.setDocumento(request.getDocumento());
        entity.setTipoUsuario(request.getTipoUsuario().name());

        repository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", entity.getId(),
                "mensagem", "Usuário cadastrado com sucesso!"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        authenticationManager.authenticate(authenticationToken);

        var usuarioEntity = repository.findByEmail(request.getEmail()).orElseThrow();

        var usuarioDomain = new Usuario(
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                usuarioEntity.getDocumento(),
                TipoUsuario.valueOf(usuarioEntity.getTipoUsuario())
        );
        usuarioDomain.setId(usuarioEntity.getId());

        var tokenJWT = tokenService.gerarToken(usuarioDomain);

        return ResponseEntity.ok(Map.of("token", tokenJWT));
    }
}