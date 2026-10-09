package com.pragma.cleanbackend.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.cleanbackend.application.RequestDto;
import com.pragma.cleanbackend.application.RequestService;
import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import com.pragma.cleanbackend.infrastructure.config.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Tests de integración para RequestController")
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RequestService requestService;

    private Request solicitud;
    private RequestDto requestDto;
    private static final String API_PATH = "/api/solicitudes";

    @BeforeEach
    void setUp() {
        requestDto = new RequestDto("CLIENT-001", "Solicitud de prueba", "Detalles de la solicitud");
        solicitud = new Request("CLIENT-001", "Solicitud de prueba", "Detalles de la solicitud");
    }

    @Test
    @DisplayName("POST /api/solicitudes crea solicitud exitosamente")
    void crearSolicitud_ConDatosValidos_RetornaCreated() throws Exception {
        when(requestService.crearSolicitud(any(RequestDto.class))).thenReturn(solicitud);

        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value("CLIENT-001"))
                .andExpect(jsonPath("$.description").value("Solicitud de prueba"));

        verify(requestService).crearSolicitud(any(RequestDto.class));
    }

    @Test
    @DisplayName("POST /api/solicitudes con datos inválidos retorna BadRequest")
    void crearSolicitud_ConDatosInvalidos_RetornaBadRequest() throws Exception {
        RequestDto dtoInvalido = new RequestDto(null, "", "Detalles");

        when(requestService.crearSolicitud(any(RequestDto.class)))
                .thenThrow(new InvalidRequestException("Los datos de la solicitud son inválidos"));

        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/solicitudes con solicitud duplicada retorna Conflict")
    void crearSolicitud_ConSolicitudDuplicada_RetornaConflict() throws Exception {
        UUID existingId = UUID.randomUUID();
        DuplicateRequestException ex = new DuplicateRequestException(
                "Solicitud duplicada", 
                existingId, 
                "CLIENT-001"
        );

        when(requestService.crearSolicitud(any(RequestDto.class))).thenThrow(ex);

        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("GET /api/solicitudes retorna todas las solicitudes")
    void obtenerTodasLasSolicitudes_RetornaListaOk() throws Exception {
        Request solicitud1 = new Request("CLIENT-001", "Descripcion 1", "Detalles 1");
        Request solicitud2 = new Request("CLIENT-002", "Descripcion 2", "Detalles 2");
        List<Request> solicitudes = Arrays.asList(solicitud1, solicitud2);

        when(requestService.obtenerTodasLasSolicitudes()).thenReturn(solicitudes);

        mockMvc.perform(get(API_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].clientId").value("CLIENT-001"))
                .andExpect(jsonPath("$[1].clientId").value("CLIENT-002"));
    }

    @Test
    @DisplayName("GET /api/solicitudes/{id} retorna solicitud cuando existe")
    void obtenerSolicitudPorId_CuandoExiste_RetornaOk() throws Exception {
        UUID id = UUID.randomUUID();
        
        when(requestService.obtenerSolicitudPorId(id)).thenReturn(Optional.of(solicitud));

        mockMvc.perform(get(API_PATH + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value("CLIENT-001"));
    }

    @Test
    @DisplayName("GET /api/solicitudes/{id} retorna NotFound cuando no existe")
    void obtenerSolicitudPorId_CuandoNoExiste_RetornaNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        
        when(requestService.obtenerSolicitudPorId(id)).thenReturn(Optional.empty());

        mockMvc.perform(get(API_PATH + "/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/solicitudes/{id} elimina solicitud exitosamente")
    void eliminarSolicitud_EliminaCorrectamente_RetornaNoContent() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(requestService).eliminarSolicitud(id);

        mockMvc.perform(delete(API_PATH + "/" + id))
                .andExpect(status().isNoContent());

        verify(requestService).eliminarSolicitud(id);
    }

    @Test
    @DisplayName("GET /api/solicitudes/count retorna cantidad de solicitudes")
    void contarSolicitudes_RetornaCantidadCorrecta() throws Exception {
        when(requestService.contarSolicitudes()).thenReturn(10L);

        mockMvc.perform(get(API_PATH + "/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    @DisplayName("POST con body vacío retorna BadRequest")
    void crearSolicitud_ConBodyVacio_RetornaBadRequest() throws Exception {
        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST con content-type incorrecto retorna UnsupportedMediaType")
    void crearSolicitud_ConContentTypeIncorrecto_RetornaUnsupportedMediaType() throws Exception {
        mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("invalid content"))
                .andExpect(status().isUnsupportedMediaType());
    }
}