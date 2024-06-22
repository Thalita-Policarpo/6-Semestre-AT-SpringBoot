package br.com.edu.infnet.assessementspringboot.repository;

import br.com.edu.infnet.assessementspringboot.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}