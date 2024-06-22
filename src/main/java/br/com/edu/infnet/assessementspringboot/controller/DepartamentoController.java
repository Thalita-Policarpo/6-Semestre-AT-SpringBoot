package br.com.edu.infnet.assessementspringboot.controller;


import br.com.edu.infnet.assessementspringboot.model.Departamento;
import br.com.edu.infnet.assessementspringboot.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable Long id) {
        return departamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        return departamentoService.findById(id)
                .map(existingDepartamento -> {
                    departamento.setId(existingDepartamento.getId());
                    return ResponseEntity.ok(departamentoService.save(departamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        if (departamentoService.findById(id).isPresent()) {
            departamentoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}