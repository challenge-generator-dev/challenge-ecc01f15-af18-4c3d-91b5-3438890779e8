package com.pragma.cleanbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import com.pragma.cleanbackend.application.RequestService;
import com.pragma.cleanbackend.domain.model.Request;
import com.pragma.cleanbackend.domain.dto.RequestDto;

@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("Aplicación iniciada correctamente");
    }
    
    /**
     * Runner para verificar la inicialización del contexto y realizar pruebas básicas.
     * Solo se ejecuta en el perfil 'dev'.
     */
    @Bean
    @Profile("dev")
    public CommandLineRunner init(RequestService requestService) {
        return args -> {
            logger.info("Iniciando runner de validación del contexto...");
            
            // Prueba básica de creación de solicitud
            RequestDto requestDto = new RequestDto("CLIENT_001", "Solicitud inicial", "DETALLE_PRUEBA");
            Request createdRequest = requestService.createRequest(requestDto);
            logger.info("Solicitud creada con ID: {}", createdRequest.id());
            
            // Prueba de consulta
            Request retrievedRequest = requestService.getRequestById(createdRequest.id());
            logger.info("Solicitud recuperada: cliente={}, estado={}", 
                      retrievedRequest.clientId(), retrievedRequest.status());
            
            logger.info("Runner completado con éxito");
        };
    }
    
    /**
     * Clase interna para representar DTOs simples sin anotaciones.
     * Usada únicamente para la inicialización en el runner.
     */
    public record RequestDto(String clientId, String description, String details) {}
}