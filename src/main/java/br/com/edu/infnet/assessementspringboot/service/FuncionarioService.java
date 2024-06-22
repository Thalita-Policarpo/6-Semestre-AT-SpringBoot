package br.com.edu.infnet.assessementspringboot.service;

import br.com.edu.infnet.assessementspringboot.model.Funcionario;
import br.com.edu.infnet.assessementspringboot.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

   @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteById(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
