package br.com.edu.infnet.assessementspringboot.controller;


import br.com.edu.infnet.assessementspringboot.model.Funcionario;
import br.com.edu.infnet.assessementspringboot.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return funcionarioService.findById(id)
                .map(existingFuncionario -> {
                    funcionario.setId(existingFuncionario.getId());
                    return ResponseEntity.ok(funcionarioService.save(funcionario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        if (funcionarioService.findById(id).isPresent()) {
            funcionarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}