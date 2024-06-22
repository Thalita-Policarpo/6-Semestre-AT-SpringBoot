package br.com.edu.infnet.assessementspringboot;

import br.com.edu.infnet.assessementspringboot.controller.UsuarioController;
import br.com.edu.infnet.assessementspringboot.model.Usuario;
import br.com.edu.infnet.assessementspringboot.service.UsuarioService;
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


public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("1", "João", "senha", "ROLE_USER"));

        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/public/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"));
    }

    @Test
    public void testAddUsuario() throws Exception {
        Usuario usuario = new Usuario(null, "Maria", "senha123", "ROLE_ADMIN");
        Usuario usuarioSaved = new Usuario("1", "Maria", "senha123", "ROLE_ADMIN");

        when(usuarioService.addUsuario(any(Usuario.class))).thenReturn(usuarioSaved);

        mockMvc.perform(post("/api/public/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Maria\",\"senha\":\"senha123\",\"papel\":\"ROLE_ADMIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario("1", "João", "senha", "ROLE_USER");
        Usuario usuarioUpdated = new Usuario("1", "João Silva", "senha123", "ROLE_USER");

        when(usuarioService.updateUsuario(eq("1"), any(Usuario.class))).thenReturn(Optional.of(usuarioUpdated));

        mockMvc.perform(put("/api/public/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"João Silva\",\"senha\":\"senha123\",\"papel\":\"ROLE_USER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        when(usuarioService.deleteUsuario("1")).thenReturn(true);

        mockMvc.perform(delete("/api/public/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).deleteUsuario("1");
    }

    @Test
    public void testDeleteUsuarioNotFound() throws Exception {
        when(usuarioService.deleteUsuario("1")).thenReturn(false);

        mockMvc.perform(delete("/api/public/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
