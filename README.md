# Reto de código limpio en backend

Como desarrollador trainee en el equipo de backend, tu tarea es aplicar los principios de código limpio en una aplicación Java. El objetivo es escribir un servicio que maneje solicitudes de clientes, validando entradas y asegurando que el código sea legible, mantenible y eficiente. El servicio debe registrar solicitudes con un identificador único, evitando duplicados y manejando errores de forma adecuada.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | aplicación de código limpio y eficiente |
| **Nivel** | trainee-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 3 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Implementación del servicio básico

**Objetivo:** Crear un servicio que acepte y registre solicitudes de clientes.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- El servicio debe registrar cada solicitud con un identificador único.
- Prohibir solicitudes duplicadas usando el identificador único.
- Manejar errores de validación y proporcionar mensajes claros.

**Entregable:** Servicio básico que acepta y registra solicitudes únicas.

<details>
<summary>Pistas de conocimiento</summary>

- Principios de código limpio (KISS, SOLID, YAGNI, DRY).
- Manejo de identificadores únicos para evitar duplicados.

</details>

### Fase 2: Mejora de la validación y manejo de errores

**Objetivo:** Mejorar la validación de entradas y el manejo de errores.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Agregar validaciones adicionales a las solicitudes (ej. formato de datos).
- Mejorar los mensajes de error para ser más descriptivos.
- Implementar logging para rastrear las solicitudes y errores.

**Entregable:** Servicio con validaciones mejoradas y manejo de errores descriptivo.

<details>
<summary>Pistas de conocimiento</summary>

- Técnicas de validación de datos.
- Prácticas de logging para depuración y seguimiento.

</details>

### Fase 3: Refactorización y aplicación de principios SOLID

**Objetivo:** Refactorizar el código para aplicar los principios SOLID.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Refactorizar el servicio para aplicar los principios SOLID.
- Asegurar que el código sea más modular y fácil de mantener.
- Verificar que el servicio continúe funcionando correctamente después de la refactorización.

**Entregable:** Servicio refactorizado aplicando los principios SOLID.

<details>
<summary>Pistas de conocimiento</summary>

- Principios SOLID y su aplicación en el código.
- Técnicas de refactorización para mejorar la estructura del código.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el principio de responsabilidad única y cómo se aplica en este reto?
- **paraQueSirve**: ¿Para qué sirve la validación de entradas en el servicio?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar un servicio de registro de solicitudes?
- **comoSeUsa**: ¿Cómo se usa el logging para mejorar el seguimiento de las solicitudes?

## Criterios de Evaluacion

- Implementación del servicio básico que acepta y registra solicitudes únicas.
- Mejora de la validación de entradas y manejo de errores descriptivo.
- Refactorización del código para aplicar los principios SOLID.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
