package br.com.edu.infnet.assessementspringboot;


import br.com.edu.infnet.assessementspringboot.controller.DepartamentoController;
import br.com.edu.infnet.assessementspringboot.model.Departamento;
import br.com.edu.infnet.assessementspringboot.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DepartamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartamentoService departamentoService;

    @InjectMocks
    private DepartamentoController departamentoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departamentoController).build();
    }

    @Test
    public void testGetAllDepartamentos() throws Exception {
        List<Departamento> departamentos = new ArrayList<>();
        departamentos.add(new Departamento(1L, "TI", "Bloco A", new ArrayList<>()));

        when(departamentoService.findAll()).thenReturn(departamentos);

        mockMvc.perform(get("/api/public/departamentos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("TI"));
    }

    @Test
    public void testGetDepartamentoById() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "Bloco A", new ArrayList<>());

        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));

        mockMvc.perform(get("/api/public/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("TI"));
    }

    @Test
    public void testCreateDepartamento() throws Exception {
        Departamento departamento = new Departamento(null, "TI", "Bloco A", new ArrayList<>());
        Departamento departamentoSaved = new Departamento(1L, "TI", "Bloco A", new ArrayList<>());

        when(departamentoService.save(any(Departamento.class))).thenReturn(departamentoSaved);

        mockMvc.perform(post("/api/public/departamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"TI\",\"local\":\"Bloco A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testUpdateDepartamento() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "Bloco A", new ArrayList<>());
        Departamento departamentoUpdated = new Departamento(1L, "Novo Nome", "Novo Local", new ArrayList<>());

        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));
        when(departamentoService.save(any(Departamento.class))).thenReturn(departamentoUpdated);

        mockMvc.perform(put("/api/public/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Nome\",\"local\":\"Novo Local\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Nome"))
                .andExpect(jsonPath("$.local").value("Novo Local"));
    }

    @Test
    public void testDeleteDepartamento() throws Exception {
        Departamento departamento = new Departamento(1L, "TI", "Bloco A", new ArrayList<>());

        when(departamentoService.findById(1L)).thenReturn(Optional.of(departamento));

        mockMvc.perform(delete("/api/public/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(departamentoService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteDepartamentoNotFound() throws Exception {
        when(departamentoService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/public/departamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
