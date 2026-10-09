package com.pragma.cleanbackend.application;


import com.pragma.cleanbackend.RequestDto;
import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.RequestRepository;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para RequestService")
class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestService requestService;

    private Request requestValida;
    private RequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new RequestDto("CLIENT-001", "Solicitud de prueba", "Detalles de la solicitud");
        requestValida = new Request("CLIENT-001", "Solicitud de prueba", "Detalles de la solicitud");
    }

    @Test
    @DisplayName("Crear solicitud exitosamente cuando no existe duplicado")
    void crearSolicitud_CuandoNoExisteDuplicado_RetornaSolicitudCreada() {
        when(requestRepository.findByClientIdAndDescriptionAndDetails(
                requestDto.clientId(), 
                requestDto.description(), 
                requestDto.details()))
                .thenReturn(Optional.empty());
        
        when(requestRepository.save(any(Request.class))).thenAnswer(invocation -> {
            Request r = invocation.getArgument(0);
            return r;
        });

        Request resultado = requestService.crearSolicitud(requestDto);

        assertNotNull(resultado);
        assertEquals("CLIENT-001", resultado.getClientId());
        assertEquals("Solicitud de prueba", resultado.getDescription());
        verify(requestRepository).save(any(Request.class));
    }

    @Test
    @DisplayName("Crear solicitud lanza DuplicateRequestException cuando existe duplicado")
    void crearSolicitud_CuandoExisteDuplicado_LanzaExcepcion() {
        when(requestRepository.findByClientIdAndDescriptionAndDetails(
                requestDto.clientId(), 
                requestDto.description(), 
                requestDto.details()))
                .thenReturn(Optional.of(requestValida));

        DuplicateRequestException ex = assertThrows(
                DuplicateRequestException.class,
                () -> requestService.crearSolicitud(requestDto)
        );

        assertNotNull(ex.getExistingRequestId());
        assertEquals("CLIENT-001", ex.getClientId());
        verify(requestRepository, never()).save(any(Request.class));
    }

    @Test
    @DisplayName("Obtener todas las solicitudes retorna lista de solicitudes")
    void obtenerTodasLasSolicitudes_RetornaListaDeSolicitudes() {
        Request solicitud1 = new Request("CLIENT-001", "Descripcion 1", "Detalles 1");
        Request solicitud2 = new Request("CLIENT-002", "Descripcion 2", "Detalles 2");
        List<Request> solicitudes = Arrays.asList(solicitud1, solicitud2);

        when(requestRepository.findAll()).thenReturn(solicitudes);

        List<Request> resultado = requestService.obtenerTodasLasSolicitudes();

        assertEquals(2, resultado.size());
        verify(requestRepository).findAll();
    }

    @Test
    @DisplayName("Obtener solicitud por ID retorna solicitud cuando existe")
    void obtenerSolicitudPorId_CuandoExiste_RetornaSolicitud() {
        UUID id = UUID.randomUUID();
        Request solicitud = new Request("CLIENT-001", "Descripcion", "Detalles");
        
        when(requestRepository.findById(id)).thenReturn(Optional.of(solicitud));

        Optional<Request> resultado = requestService.obtenerSolicitudPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("CLIENT-001", resultado.get().getClientId());
    }

    @Test
    @DisplayName("Obtener solicitud por ID retorna vacio cuando no existe")
    void obtenerSolicitudPorId_CuandoNoExiste_RetornaVacio() {
        UUID id = UUID.randomUUID();
        
        when(requestRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Request> resultado = requestService.obtenerSolicitudPorId(id);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Eliminar solicitud llama al repositorio")
    void eliminarSolicitud_LlamaAlRepositorio() {
        UUID id = UUID.randomUUID();

        requestService.eliminarSolicitud(id);

        verify(requestRepository).deleteById(id);
    }

    @Test
    @DisplayName("Contar solicitudes retorna cantidad correcta")
    void contarSolicitudes_RetornaCantidadCorrecta() {
        when(requestRepository.count()).thenReturn(5L);

        long cantidad = requestService.contarSolicitudes();

        assertEquals(5L, cantidad);
        verify(requestRepository).count();
    }

    @Test
    @DisplayName("Validar solicitud lanza excepcion cuando clientId es nulo")
    void crearSolicitud_ConClientIdNulo_LanzaExcepcion() {
        RequestDto dtoInvalido = new RequestDto(null, "Descripcion", "Detalles");

        assertThrows(InvalidRequestException.class, 
                () -> requestService.crearSolicitud(dtoInvalido));
    }

    @Test
    @DisplayName("Validar solicitud lanza excepcion cuando description esta vacia")
    void crearSolicitud_ConDescriptionVacia_LanzaExcepcion() {
        RequestDto dtoInvalido = new RequestDto("CLIENT-001", "", "Detalles");

        assertThrows(InvalidRequestException.class, 
                () -> requestService.crearSolicitud(dtoInvalido));
    }

    @Test
    @DisplayName("Validar solicitud lanza excepcion cuando description excede limite")
    void crearSolicitud_ConDescriptionExcedeLimite_LanzaExcepcion() {
        String descripcionLarga = "A".repeat(256);
        RequestDto dtoInvalido = new RequestDto("CLIENT-001", descripcionLarga, "Detalles");

        assertThrows(InvalidRequestException.class, 
                () -> requestService.crearSolicitud(dtoInvalido));
    }
}