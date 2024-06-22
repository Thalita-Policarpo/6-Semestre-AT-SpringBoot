package br.com.edu.infnet.assessementspringboot.repository;

import br.com.edu.infnet.assessementspringboot.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}