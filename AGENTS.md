# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Reto de código limpio en backend**.

| | |
|---|---|
| Tema | aplicación de código limpio y eficiente |
| Nivel | trainee-l1 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | capas estándar |
| Tiempo estimado | 3 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.projectlombok:lombok 1.18.30
- com.h2database:h2 2.2.224
- org.slf4j:slf4j-api 2.0.12

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Implementación del servicio básico**: Servicio básico que acepta y registra solicitudes únicas.
- **Fase 2 — Mejora de la validación y manejo de errores**: Servicio con validaciones mejoradas y manejo de errores descriptivo.
- **Fase 3 — Refactorización y aplicación de principios SOLID**: Servicio refactorizado aplicando los principios SOLID.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (39)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/cleanbackend/Application.java` — `com.pragma.cleanbackend.domain.model.Request`
      El import com.pragma.cleanbackend.domain.model.Request usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/pragma/cleanbackend/Application.java` — `com.pragma.cleanbackend.domain.dto.RequestDto`
      El import com.pragma.cleanbackend.domain.dto.RequestDto usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `com.pragma.cleanbackend.application.RequestDto`
      El import com.pragma.cleanbackend.application.RequestDto usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.id`
      Se invoca `id` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.clientId`
      Se invoca `clientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/Application.java` — `Request.status`
      Se invoca `status` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getClientId`
      Se invoca `getClientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getDescription`
      Se invoca `getDescription` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getDetails`
      Se invoca `getDetails` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java` — `Request.getId`
      Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/application/RequestService.java` — `Request.getId`
      Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestDto.clientId`
      Se invoca `clientId` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestDto.description`
      Se invoca `description` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.create`
      Se invoca `create` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `Request.getId`
      Se invoca `getId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `DuplicateRequestException.getMessage`
      Se invoca `getMessage` sobre `DuplicateRequestException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.findAll`
      Se invoca `findAll` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.findById`
      Se invoca `findById` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java` — `RequestService.deleteById`
      Se invoca `deleteById` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/cleanbackend/infrastructure/config/GlobalExceptionHandler.java` — `ExceptionResponse.put`
      Se invoca `put` sobre `ExceptionResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.clientId`
      Se invoca `clientId` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.description`
      Se invoca `description` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestDto.details`
      Se invoca `details` sobre `RequestDto`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.crearSolicitud`
      Se invoca `crearSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.getClientId`
      Se invoca `getClientId` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.getDescription`
      Se invoca `getDescription` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.obtenerTodasLasSolicitudes`
      Se invoca `obtenerTodasLasSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.size`
      Se invoca `size` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.obtenerSolicitudPorId`
      Se invoca `obtenerSolicitudPorId` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.isPresent`
      Se invoca `isPresent` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.get`
      Se invoca `get` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `Request.isEmpty`
      Se invoca `isEmpty` sobre `Request`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.eliminarSolicitud`
      Se invoca `eliminarSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java` — `RequestService.contarSolicitudes`
      Se invoca `contarSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.crearSolicitud`
      Se invoca `crearSolicitud` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.obtenerTodasLasSolicitudes`
      Se invoca `obtenerTodasLasSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.obtenerSolicitudPorId`
      Se invoca `obtenerSolicitudPorId` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java` — `RequestService.contarSolicitudes`
      Se invoca `contarSolicitudes` sobre `RequestService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (13)

- `pom.xml`
- `src/main/java/com/pragma/cleanbackend/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/cleanbackend/domain/Request.java`
- `src/main/java/com/pragma/cleanbackend/domain/RequestRepository.java`
- `src/main/java/com/pragma/cleanbackend/domain/exception/DuplicateRequestException.java`
- `src/main/java/com/pragma/cleanbackend/domain/exception/InvalidRequestException.java`
- `src/main/java/com/pragma/cleanbackend/infrastructure/JpaRequestRepository.java`
- `src/main/java/com/pragma/cleanbackend/application/RequestService.java`
- `src/main/java/com/pragma/cleanbackend/infrastructure/RequestController.java`
- `src/main/java/com/pragma/cleanbackend/infrastructure/config/GlobalExceptionHandler.java`
- `src/test/java/com/pragma/cleanbackend/application/RequestServiceTest.java`
- `src/test/java/com/pragma/cleanbackend/infrastructure/RequestControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/cleanbackend`
- `src/main/java/com/pragma/cleanbackend/domain`
- `src/main/java/com/pragma/cleanbackend/application`
- `src/main/java/com/pragma/cleanbackend/infrastructure`
- `src/main/resources`
- `src/test/java/com/pragma/cleanbackend`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Trainee
- Brecha que el reto ataca: Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).
- Mision: Candidato Trainee en Backend con Java, iniciando su ruta de aprendizaje en Pragma.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
