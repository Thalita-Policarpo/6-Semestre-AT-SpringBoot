package br.com.edu.infnet.assessementspringboot.repository;


import br.com.edu.infnet.assessementspringboot.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByNome(String nome);
}