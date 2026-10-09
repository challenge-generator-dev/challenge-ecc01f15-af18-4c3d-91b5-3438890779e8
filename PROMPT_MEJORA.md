# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/cleanbackend/Application.java` — `com.pragma.cleanbackend.domain.model.Request`: El import com.pragma.cleanbackend.domain.model.Request usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/pragma/cleanbackend/Application.java` — `com.pragma.cleanbackend.domain.dto.RequestDto`: El import com.pragma.cleanbackend.domain.dto.RequestDto usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `com.pragma.cleanbackend.application.RequestDto`: El import com.pragma.cleanbackend.application.RequestDto usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.id`: Se invoca `id` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.clientId`: Se invoca `clientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.status`: Se invoca `status` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getClientId`: Se invoca `getClientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getDescription`: Se invoca `getDescription` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getDetails`: Se invoca `getDetails` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getId`: Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/application/RequestService.java` — `Request.getId`: Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestDto.clientId`: Se invoca `clientId` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestDto.description`: Se invoca `description` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.create`: Se invoca `create` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `Request.getId`: Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `DuplicateRequestException.getMessage`: Se invoca `getMessage` sobre `DuplicateRequestException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.findAll`: Se invoca `findAll` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.findById`: Se invoca `findById` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.deleteById`: Se invoca `deleteById` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/cleanbackend/infrastructure/config/GlobalExceptionHandler.java` — `ExceptionResponse.put`: Se invoca `put` sobre `ExceptionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.clientId`: Se invoca `clientId` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.description`: Se invoca `description` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.details`: Se invoca `details` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.crearSolicitud`: Se invoca `crearSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.getClientId`: Se invoca `getClientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.getDescription`: Se invoca `getDescription` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.obtenerTodasLasSolicitudes`: Se invoca `obtenerTodasLasSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.size`: Se invoca `size` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.obtenerSolicitudPorId`: Se invoca `obtenerSolicitudPorId` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.isPresent`: Se invoca `isPresent` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.get`: Se invoca `get` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.isEmpty`: Se invoca `isEmpty` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.eliminarSolicitud`: Se invoca `eliminarSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.contarSolicitudes`: Se invoca `contarSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.crearSolicitud`: Se invoca `crearSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.obtenerTodasLasSolicitudes`: Se invoca `obtenerTodasLasSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.obtenerSolicitudPorId`: Se invoca `obtenerSolicitudPorId` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.contarSolicitudes`: Se invoca `contarSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Trainee

### Brecha de conocimiento
Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).

### Misión / candidato
Candidato Trainee en Backend con Java, iniciando su ruta de aprendizaje en Pragma.

### Reto
- Tema: aplicación de código limpio y eficiente
- Seniority: trainee-l1
- Tipo: practical
- Título: Reto de código limpio en backend
- Tiempo estimado: 3 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Implementación del servicio básico — objetivo: Crear un servicio que acepte y registre solicitudes de clientes. — entregable (NO resolver): Servicio básico que acepta y registra solicitudes únicas.
- Fase 2: Mejora de la validación y manejo de errores — objetivo: Mejorar la validación de entradas y el manejo de errores. — entregable (NO resolver): Servicio con validaciones mejoradas y manejo de errores descriptivo.
- Fase 3: Refactorización y aplicación de principios SOLID — objetivo: Refactorizar el código para aplicar los principios SOLID. — entregable (NO resolver): Servicio refactorizado aplicando los principios SOLID.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>cleanbackend</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>cleanbackend</name>
    <description>Reto de código limpio en backend</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>compile</scope>
            <optional>true</optional>
        </dependency>
        
        <!-- H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        
        <!-- SLF4J -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.12</version>
        </dependency>
        
        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/Application.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
# Configuración de la aplicación Spring Boot
spring:
  application:
    name: cleanbackend
  
  # Configuración de datasource para H2
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ""
    
  # Configuración de JPA
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  
  # Configuración de H2 Console
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: false

# Configuración de logging
logging:
  level:
    root: INFO
    com.pragma.cleanbackend: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.boot: INFO
    
# Configuración del servidor
server:
  port: 8080
  servlet:
    context-path: /api
    
# Configuración de Spring Actuator (opcional para monitoreo)
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/domain/Request.java ===
package com.pragma.cleanbackend.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Request {
    private UUID id;

    @NotNull(message = "El clientId no puede ser nulo")
    @Size(min = 3, max = 50, message = "El clientId debe tener entre 3 y 50 caracteres")
    private String clientId;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 5, max = 200, message = "La descripción debe tener entre 5 y 200 caracteres")
    private String description;

    @NotNull(message = "Los detalles no pueden ser nulos")
    @Size(min = 5, max = 500, message = "Los detalles deben tener entre 5 y 500 caracteres")
    private String details;

    private LocalDateTime createdAt;
    private RequestStatus status;

    public Request() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.PENDING;
    }

    public Request(String clientId, String description, String details) {
        this();
        this.clientId = clientId;
        this.description = description;
        this.details = details;
    }

    public enum RequestStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        REJECTED
    }

    public void validate() {
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("El clientId no puede estar vacío");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (details == null || details.trim().isEmpty()) {
            throw new IllegalArgumentException("Los detalles no pueden estar vacíos");
        }
    }

    public boolean isDuplicateOf(Request other) {
        if (other == null) {
            return false;
        }
        return this.clientId.equals(other.clientId) &&
               this.description.equals(other.description) &&
               this.details.equals(other.details);
    }
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/domain/RequestRepository.java ===
package com.pragma.cleanbackend.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestRepository {
    Request save(Request request);

    Optional<Request> findById(UUID id);

    List<Request> findAll();

    Optional<Request> findByClientIdAndDescriptionAndDetails(String clientId, String description, String details);

    void deleteById(UUID id);

    long count();
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java ===
package com.pragma.cleanbackend.domain.exception;


import com.pragma.cleanbackend.domain.Request;
import java.time.LocalDateTime;
import java.util.UUID;

public class DuplicateRequestException extends RuntimeException {
    private final UUID existingRequestId;
    private final String clientId;
    private final LocalDateTime timestamp;

    public DuplicateRequestException(String message, UUID existingRequestId, String clientId) {
        super(message);
        this.existingRequestId = existingRequestId;
        this.clientId = clientId;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getExistingRequestId() {
        return existingRequestId;
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLogMessage() {
        return String.format(
            "DuplicateRequestException: [existingRequestId=%s, clientId=%s, timestamp=%s] - %s",
            existingRequestId, clientId, timestamp, getMessage()
        );
    }

    public static DuplicateRequestException forRequest(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        String message = String.format(
            "Ya existe una solicitud con clientId '%s', descripción '%s' y detalles '%s'",
            request.getClientId(),
            request.getDescription(),
            request.getDetails()
        );
        return new DuplicateRequestException(
            message,
            request.getId(),
            request.getClientId()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/domain/exception/InvalidRequestException.java ===
package com.pragma.cleanbackend.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class InvalidRequestException extends RuntimeException {
    private final String field;
    private final Object rejectedValue;
    private final String reason;
    private final LocalDateTime timestamp;
    private final UUID requestId;

    public InvalidRequestException(String message, String field, Object rejectedValue, String reason) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
        this.requestId = UUID.randomUUID();
    }

    public InvalidRequestException(String message, String field, Object rejectedValue) {
        this(message, field, rejectedValue, "Valor inválido para el campo especificado");
    }

    public InvalidRequestException(String message) {
        this(message, null, null, "Solicitud no válida");
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvalidRequestException[")
          .append("id=").append(requestId)
          .append(", timestamp=").append(timestamp)
          .append(", message=").append(getMessage());
        if (field != null) {
            sb.append(", field=").append(field)
              .append(", rejectedValue=").append(rejectedValue)
              .append(", reason=").append(reason);
        }
        sb.append("]");
        return sb.toString();
    }

    public static InvalidRequestException forNullField(String fieldName) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' no puede ser nulo",
            fieldName,
            null,
            "Campo obligatorio no proporcionado"
        );
    }

    public static InvalidRequestException forEmptyField(String fieldName) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' no puede estar vacío",
            fieldName,
            "",
            "Campo obligatorio está vacío"
        );
    }

    public static InvalidRequestException forInvalidLength(String fieldName, int min, int max, int actual) {
        return new InvalidRequestException(
            "El campo '" + fieldName + "' debe tener entre " + min + " y " + max + " caracteres, pero tiene " + actual,
            fieldName,
            actual,
            "Longitud fuera del rango permitido"
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/infrastructure/JpaRequestRepository.java ===
package com.pragma.cleanbackend.infrastructure;

import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.RequestRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaRequestRepository implements RequestRepository {

    public JpaRequestRepository() {
    }

    @Override
    public Request save(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        return request;
    }

    @Override
    public Optional<Request> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        return List.of();
    }

    @Override
    public Optional<Request> findByClientIdAndDescriptionAndDetails(String clientId, String description, String details) {
        if (clientId == null || description == null || details == null) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
    }

    @Override
    public long count() {
        return 0L;
    }
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/application/RequestService.java ===
package com.pragma.cleanbackend.application;

import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.RequestRepository;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);
    private static final int MIN_DESCRIPTION_LENGTH = 10;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MIN_DETAILS_LENGTH = 5;
    private static final int MAX_DETAILS_LENGTH = 2000;

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        if (requestRepository == null) {
            throw new IllegalArgumentException("El repositorio de solicitudes no puede ser nulo");
        }
        this.requestRepository = requestRepository;
    }

    public Request createRequest(String clientId, String description, String details) {
        logger.info("Iniciando registro de solicitud para cliente: {}", clientId);

        validateClientId(clientId);
        validateDescription(description);
        validateDetails(details);

        Request newRequest = new Request(clientId, description, details);
        newRequest.validate();

        Optional<Request> existingRequest = requestRepository.findByClientIdAndDescriptionAndDetails(
            clientId, description, details
        );

        if (existingRequest.isPresent()) {
            logger.warn("Detectada solicitud duplicada para cliente: {}", clientId);
            throw DuplicateRequestException.forRequest(existingRequest.get());
        }

        Request savedRequest = requestRepository.save(newRequest);
        logger.info("Solicitud registrada exitosamente con ID: {}", savedRequest.getId());

        return savedRequest;
    }

    public Optional<Request> getRequestById(UUID id) {
        if (id == null) {
            logger.warn("Se intentó buscar una solicitud con ID nulo");
            return Optional.empty();
        }
        logger.debug("Buscando solicitud con ID: {}", id);
        return requestRepository.findById(id);
    }

    public List<Request> getAllRequests() {
        logger.debug("Obteniendo todas las solicitudes");
        return requestRepository.findAll();
    }

    public void deleteRequest(UUID id) {
        if (id == null) {
            throw InvalidRequestException.forNullField("id");
        }
        logger.info("Eliminando solicitud con ID: {}", id);
        requestRepository.deleteById(id);
    }

    public long getRequestCount() {
        return requestRepository.count();
    }

    private void validateClientId(String clientId) {
        if (clientId == null) {
            logger.error("Validation failed: clientId es nulo");
            throw InvalidRequestException.forNullField("clientId");
        }
        if (clientId.trim().isEmpty()) {
            logger.error("Validation failed: clientId está vacío");
            throw InvalidRequestException.forEmptyField("clientId");
        }
        if (clientId.length() > 100) {
            logger.error("Validation failed: clientId excede longitud máxima");
            throw InvalidRequestException.forInvalidLength("clientId", 1, 100, clientId.length());
        }
    }

    private void validateDescription(String description) {
        if (description == null) {
            logger.error("Validation failed: description es nulo");
            throw InvalidRequestException.forNullField("description");
        }
        if (description.trim().isEmpty()) {
            logger.error("Validation failed: description está vacío");
            throw InvalidRequestException.forEmptyField("description");
        }
        int length = description.length();
        if (length < MIN_DESCRIPTION_LENGTH || length > MAX_DESCRIPTION_LENGTH) {
            logger.error("Validation failed: description longitud inválida - {}", length);
            throw InvalidRequestException.forInvalidLength(
                "description", MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH, length
            );
        }
    }

    private void validateDetails(String details) {
        if (details == null) {
            logger.error("Validation failed: details es nulo");
            throw InvalidRequestException.forNullField("details");
        }
        if (details.trim().isEmpty()) {
            logger.error("Validation failed: details está vacío");
            throw InvalidRequestException.forEmptyField("details");
        }
        int length = details.length();
        if (length < MIN_DETAILS_LENGTH || length > MAX_DETAILS_LENGTH) {
            logger.error("Validation failed: details longitud inválida - {}", length);
            throw InvalidRequestException.forInvalidLength(
                "details", MIN_DETAILS_LENGTH, MAX_DETAILS_LENGTH, length
            );
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java ===
package com.pragma.cleanbackend.infrastructure;

import com.pragma.cleanbackend.Application.RequestDto;
import com.pragma.cleanbackend.application.RequestService;
import com.pragma.cleanbackend.domain.Request;
import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody RequestDto requestDto) {
        logger.info("Recibida solicitud de cliente: {} - Descripcion: {}", 
                    requestDto.clientId(), requestDto.description());
        try {
            Request createdRequest = requestService.create(requestDto);
            logger.info("Solicitud creada exitosamente con ID: {}", createdRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
        } catch (InvalidRequestException e) {
            logger.warn("Error de validacion al crear solicitud: {}", e.getMessage());
            throw e;
        } catch (DuplicateRequestException e) {
            logger.warn("Solicitud duplicada detectada para cliente: {}", e.getClientId());
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        logger.info("Obteniendo todas las solicitudes");
        List<Request> requests = requestService.findAll();
        logger.info("Se encontraron {} solicitudes", requests.size());
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable UUID id) {
        logger.info("Buscando solicitud con ID: {}", id);
        return requestService.findById(id)
                .map(request -> {
                    logger.info("Solicitud encontrada: {}", request.getId());
                    return ResponseEntity.ok(request);
                })
                .orElseGet(() -> {
                    logger.warn("Solicitud no encontrada con ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID id) {
        logger.info("Eliminando solicitud con ID: {}", id);
        if (requestService.deleteById(id)) {
            logger.info("Solicitud eliminada exitosamente: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("No se pudo eliminar, solicitud no encontrada: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequestException e) {
        logger.error("Excepcion de solicitud invalida: {}", e.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRequest(DuplicateRequestException e) {
        logger.error("Excepcion de solicitud duplicada: {}", e.getLogMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    public record ErrorResponse(int status, String message, long timestamp) {}
}

// === ARCHIVO: src/main/java/com/pragma/cleanbackend/infrastructure/config/GlobalExceptionHandler.java ===
package com.pragma.cleanbackend.infrastructure.config;

import com.pragma.cleanbackend.domain.exception.DuplicateRequestException;
import com.pragma.cleanbackend.domain.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequest(InvalidRequestException e) {
        logger.error("Solicitud invalida detectada - Mensaje: {} - Campo: {}", 
                     e.getMessage(), e.getClass().getSimpleName());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud invalida",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateRequest(DuplicateRequestException e) {
        logger.error("Solicitud duplicada detectada - Cliente: {} - ID existente: {}", 
                     e.getClientId(), e.getExistingRequestId());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.CONFLICT.value(),
                "Solicitud duplicada",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        logger.error("Errores de validacion en argumentos del request: {} errores encontrados", 
                     e.getBindingResult().getFieldErrors().size());
        
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
            logger.warn("Error en campo '{}': {}", fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validacion de entrada fallida");
        response.put("fieldErrors", fieldErrors);
        response.put("timestamp", LocalDateTime.now().toString());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgument(IllegalArgumentException e) {
        logger.error("Argumento ilegal proporcionado: {}", e.getMessage());
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Argumento invalido",
                e.getMessage(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        logger.error("Excepcion no manejada - Tipo: {} - Mensaje: {}", 
                     e.getClass().getName(), e.getMessage(), e);
        
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                "Ocurrio un error inesperado. Por favor contacte al administrador.",
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public record ExceptionResponse(int status, String error, String message, String timestamp) {}
}

// === ARCHIVO: src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java ===
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
```
