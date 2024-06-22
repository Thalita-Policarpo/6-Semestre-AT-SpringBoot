package br.com.edu.infnet.assessementspringboot.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String local;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;
}
