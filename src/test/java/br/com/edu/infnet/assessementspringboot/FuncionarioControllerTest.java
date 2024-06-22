package br.com.edu.infnet.assessementspringboot;

import br.com.edu.infnet.assessementspringboot.controller.FuncionarioController;
import br.com.edu.infnet.assessementspringboot.model.Funcionario;
import br.com.edu.infnet.assessementspringboot.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FuncionarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }

    @Test
    public void testGetAllFuncionarios() throws Exception {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario(1L, "João", "Rua A", "123456789", "joao@test.com", LocalDate.now(), null));

        when(funcionarioService.findAll()).thenReturn(funcionarios);

        mockMvc.perform(get("/api/public/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"));
    }

    @Test
    public void testGetFuncionarioById() throws Exception {
        Funcionario funcionario = new Funcionario(1L, "João", "Rua A", "123456789", "joao@test.com", LocalDate.now(), null);

        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));

        mockMvc.perform(get("/api/public/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    public void testCreateFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario(null, "Maria", "Rua B", "987654321", "maria@test.com", LocalDate.now(), null);
        Funcionario funcionarioSaved = new Funcionario(1L, "Maria", "Rua B", "987654321", "maria@test.com", LocalDate.now(), null);

        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionarioSaved);

        mockMvc.perform(post("/api/public/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Maria\",\"endereco\":\"Rua B\",\"telefone\":\"987654321\",\"email\":\"maria@test.com\",\"dataNascimento\":\"2023-06-21\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testUpdateFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario(1L, "João", "Rua A", "123456789", "joao@test.com", LocalDate.now(), null);
        Funcionario funcionarioUpdated = new Funcionario(1L, "João Silva", "Rua A", "123456789", "joao@test.com", LocalDate.now(), null);

        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));
        when(funcionarioService.save(any(Funcionario.class))).thenReturn(funcionarioUpdated);

        mockMvc.perform(put("/api/public/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"João Silva\",\"endereco\":\"Rua A\",\"telefone\":\"123456789\",\"email\":\"joao@test.com\",\"dataNascimento\":\"2023-06-21\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testDeleteFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario(1L, "João", "Rua A", "123456789", "joao@test.com", LocalDate.now(), null);

        when(funcionarioService.findById(1L)).thenReturn(Optional.of(funcionario));

        mockMvc.perform(delete("/api/public/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(funcionarioService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteFuncionarioNotFound() throws Exception {
        when(funcionarioService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/public/funcionarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
