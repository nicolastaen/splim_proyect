# splim_proyect

trabajo de full-stack que se basa en crear un "tienda" en la cual se puedan subir proyectos de juegos en el cual la camunidad tenga protagonismo y puedan seguir proyectos y apoyarlos como una especie de startup
---

## 1) Requisitos

- Java 17
- Maven (opcional si usas `mvnw`)
- **MySQL** corriendo en `localhost:3306` (usuario `root`, sin contraseña)
- IDE recomendado: VS Code / IntelliJ / Eclipse
- Postman (opcional para probar la API)
- git

> Hibernate crea automáticamente la tabla `Juegos`, `aportes`, `usuarios`  al iniciar la aplicación (`ddl-auto=update`). No es necesario crearla manualmente.

---

## 2) Configuración de base de datos

El archivo `src/main/resources/application.properties` contiene la conexión:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/splim?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

- `createDatabaseIfNotExist=true` → crea la base de datos `splim` si no existe.

- `ddl-auto=update` → Hibernate actualiza el esquema automáticamente según la entidad `Juegos`, `aportes`, `usuarios`.

- `show-sql=true` → muestra las consultas SQL generadas en la consola.

---

## 3) ¿Cómo ejecutar el proyecto?

1- clonar el repository con el sigente comando

"git clone https://github.com/nicolastaen/splim_proyect.git"

2- ejecutar el proyecto desde un editor de codigo como vs Code o Intellij

3- probar el correcto funcionamiento con postman

## 4) URL base de la API

Por defecto Spring Boot levanta en puerto `8080`:

```text
http://localhost:8080
```

Base path del controlador:

```text
/api/v1/usuario
```

---

## 5) Endpoints disponibles

"pendiente"


## 6) Estructura del proyecto y explicación por capas

```text
src/main/java/com/duoc/splim/
├── controller/
├── dto/
├── service/
├── repository/
└── model/
```

### 6.1 `controller` (presentación / API REST)

En esta carpeta está `AportesController`, `JuegosController`, `UsuariosController`, que reciben las peticiones HTTP.

Anotaciones importantes:

- `@RestController`
	- Le dice a Spring que esta clase es un controlador REST.
	- Los métodos retornan datos (JSON) directamente.

- `@RequestMapping("/api/v1/usuario")`
	- Define la ruta base para todos los endpoints de este controlador.

- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
	- Asocian cada método Java con un verbo HTTP.

- `@PathVariable`
	- Obtiene valores de la URL, por ejemplo `{id}`.

- `@RequestBody`
	- Convierte automáticamente JSON del request a un objeto Java EJ:(`usuario`).

- `@Autowired`
	- Inyección de dependencias automática.
	- Spring inyecta una instancia de `...Service`.

### 6.2 `service` (lógica de negocio)

En esta carpeta está `UsuarioService`, `AportesService`, `JuegoService`.

Responsabilidades:
- Centralizar reglas y flujo de negocio.
- Evitar que el controlador tenga lógica compleja.
- Coordinar acceso al repositorio.

Anotación clave:

- `@Service`
	- Marca la clase como componente de la capa de servicio.
	- Spring la detecta y la gestiona como bean.

También usa `@Autowired` para inyectar `...Repository`.

### 5.3 `repository` (acceso a datos)

En esta carpeta está `UsuarioRepository`, `JuegosRepository`,`AporteRepository`.

Ahora es una **interfaz** que extiende `JpaRepository<..., String>`:


```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> { }
```

Al extender `JpaRepository`, Spring Data JPA genera automáticamente la implementación con todos los métodos CRUD:

| Método JPA | Descripción |
|---|---|
| `findAll()` | Obtiene todos los registros |
| `findById(id)` | Busca por id, retorna `Optional<Usuario>` |
| `save(Usuario)` | Inserta o actualiza |
| `existsById(id)` | Verifica si existe |
| `deleteById(id)` | Elimina por id |

> Ya no existe la lista en memoria. Los datos se persisten en MySQL y **sobreviven al reinicio** de la aplicación.

Anotación clave:

- `@Repository`
	- Indica que esta interfaz pertenece a la capa de acceso a datos.

### 5.4 `model` (entidades / estructura de datos)

En esta carpeta está `Usuario`,`Juego`,`Aportes`, que representa los datos de un libro.

tomando como ejemplo 

Campos actuales:
- `id_usuario`
- `fecha_nacimiento`
- `fecha_creacion_cuenta`
- `nombre_usuario`
- `foto_perfil`
- `descripcion_perfil`

Anotaciones de validación y persistencia usadas:

- `@Id`
	- Marca el identificador de la entidad.
	- En este proyecto sirve para señalar que `id` es la clave del libro.
	- Nota: como el repositorio actual es en memoria (`List<Usuario>`), no hay persistencia real en base de datos todavía.

- `@NotNull`
	- Exige que el valor no sea `null`.
	- Se usa en `fecha_creacion_cuenta` y `fecha_nacimiento`.

- `@NotBlank`
	- Exige que el texto no sea `null`, no esté vacío (`""`) y no tenga solo espacios.
	- Se usa en `isbn`, `titulo`, `editorial` y `autor`.

Anotaciones de Lombok usadas:

- `@Data`
	- Genera automáticamente:
		- getters y setters
		- `toString()`
		- `equals()` y `hashCode()`

- `@AllArgsConstructor`
	- Genera un constructor con **todos** los atributos.

- `@NoArgsConstructor`
	- Genera un constructor **vacío** (sin parámetros).

Esto evita escribir mucho código repetitivo (boilerplate).
