package br.com.edu.infnet.assessementspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}
