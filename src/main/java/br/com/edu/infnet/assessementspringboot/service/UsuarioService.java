package br.com.edu.infnet.assessementspringboot.service;

import br.com.edu.infnet.assessementspringboot.model.Usuario;
import br.com.edu.infnet.assessementspringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario addUsuario(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> updateUsuario(String id, Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario existingUsuario = optionalUsuario.get();
            existingUsuario.setNome(usuario.getNome());
            existingUsuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            existingUsuario.setPapel(usuario.getPapel());
            usuarioRepository.save(existingUsuario);
            return Optional.of(existingUsuario);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean deleteUsuario(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
