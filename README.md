# Biblioteca

Programa en Java que administra la información de los libros de una biblioteca.
Registra el título, el autor y el año de publicación de cada ejemplar, los guarda
en un arreglo y permite mostrar el acervo completo.

Proyecto de la evidencia final de **LSTI2001**.

---

## Requisitos

| Componente | Versión mínima |
|---|---|
| JDK | 17 o superior (probado con OpenJDK 25.0.4.1 LTS) |
| Log4J2 | 2.24.3 (incluido en `lib/`) |

No se requiere Maven ni Gradle: el proyecto se compila con `javac` y las
dependencias están versionadas dentro del repositorio.

## Instalación

```bash
git clone https://github.com/JosueDev-afk/biblioteca.git
cd biblioteca
./compilar.sh
```

El script compila las clases y genera el archivo ejecutable `bin/biblioteca.jar`.

> **Entorno reproducible (opcional).** El proyecto incluye un *dev container* con
> el JDK ya instalado. Si usas Podman o Docker:
> `devcontainer up --workspace-folder .`

## Uso

```bash
java -jar bin/biblioteca.jar
```

Salida esperada:

```
=== Administrador de biblioteca ===

===================== ACERVO DE LA BIBLIOTECA =====================
#    TÍTULO                                  AUTOR                       AÑO
-------------------------------------------------------------------
1    Cien años de soledad                    Gabriel García Márquez      1967
2    El llano en llamas                      Juan Rulfo                  1953
3    La casa de los espíritus                Isabel Allende              1982
4    Rayuela                                 Julio Cortázar              1963
-------------------------------------------------------------------
Total de libros: 4 de 10
===================================================================
```

La ejecución deja un registro detallado en `logs/biblioteca.log`.

## Estructura del proyecto

```
biblioteca/
├── bin/          JAR ejecutable generado por compilar.sh
├── config/       log4j2.xml — configuración del registro de actividad
├── lib/          dependencias (Log4J2 API y Core)
├── logs/         aquí se escriben los logs en tiempo de ejecución
├── src/          código fuente
│   ├── Libro.java
│   ├── Biblioteca.java
│   └── Main.java
└── docs/         diagrama de flujo, pseudocódigo y evidencias
```

## Clases

| Clase | Responsabilidad |
|---|---|
| `Libro` | Guarda título, autor y año de publicación de un ejemplar |
| `Biblioteca` | Administra el arreglo de libros; agrega y muestra el acervo |
| `Main` | Crea la biblioteca, registra cuatro libros y muestra el resultado |

## Créditos

**Autor:** Nicholas Josué Ceballos López — matrícula AL03088786
**Materia:** LSTI2001 · Evidencia final
**Institución:** Universidad Tecmilenio

Bibliotecas de terceros utilizadas:

- [Apache Log4J2](https://logging.apache.org/log4j/2.x/) — Apache License 2.0,
  propiedad de la Apache Software Foundation.

## Licencia

Este proyecto se distribuye bajo la licencia MIT. El texto completo está en el
archivo [LICENSE](LICENSE).
