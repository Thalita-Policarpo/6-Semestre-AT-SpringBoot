package br.com.edu.infnet.assessementspringboot.controller;

import br.com.edu.infnet.assessementspringboot.model.Usuario;
import br.com.edu.infnet.assessementspringboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        Optional<Usuario> updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return updatedUsuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
