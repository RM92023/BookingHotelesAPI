# Booking Hoteles

## Descripción

Booking Hoteles es una aplicación de consola desarrollada en Java que permite gestionar reservas de hoteles de manera intuitiva. Los usuarios pueden buscar hoteles, confirmar disponibilidad de habitaciones, realizar reservas personalizadas y actualizar sus datos de reserva. Este proyecto implementa una variedad de funcionalidades con validaciones para asegurar un flujo consistente y sin errores.

### Tecnologías

Lenguaje: Java 17

IDE: IntelliJ IDEA Community Edition

Dependencias: Standard Java Libraries (Scanner, LocalDate)

## Características principales

### Búsqueda de hoteles:

* Busca hoteles por ciudad y preferencias de día de sol.

* Muestra información como calificaciones, precios y actividades ofrecidas.

* Confirmar disponibilidad de habitaciones:

* Consulta habitaciones disponibles por hotel.

* Detalla descripciones y precios por noche.

### Realizar reservas:

* Proceso guiado para registrar clientes y seleccionar habitaciones.

* Validaciones para fechas, disponibilidad y datos personales.

* Visualizar reservas:

* Lista todas las reservas con información detallada.

#### Actualizar reservas:

* Cambiar de habitación dentro del mismo hotel.

* Cambiar de hotel eliminando la reserva previa y creando una nueva.

* Validaciones robustas:

* Manejo de errores para fechas, entradas vacías y formatos de email.

## Instalación

Sigue estos pasos para clonar y ejecutar el proyecto localmente.

* Clonar el repositorio

```
https://github.com/RM92023/BookingHotelesAPI.git
```

## Abrir el proyecto

* Abre IntelliJ IDEA.

* Importa el proyecto como una carpeta existente.

### Compilar el proyecto

    ./gradlew build

### Ejecutar la aplicación

    ./gradlew run

### Flujo de funcionalidades

1. Buscar hoteles

Permite buscar hoteles por ciudad y preferencias.

Muestra calificaciones, actividades y precios.

2. Crear reserva

Solicita datos personales del cliente.

Pide fechas de hospedaje, tipo de habitación y cantidad.

Valida la disponibilidad y confirma la reserva.

3. Ver reservas

Lista todas las reservas registradas con detalles como:

Cliente

Hotel

Tipo de habitación

Cantidad de habitaciones

Fechas de hospedaje

4. Buscar hoteles con parámetros avanzados

Búsqueda avanzada por ciudad, tipo de alojamiento, fechas y personas.

Calcula precios finales con ajustes por fechas.

5. Confirmar disponibilidad

Muestra habitaciones disponibles con descripciones y precios.

6. Realizar reserva personalizada

Registra datos completos del cliente, fechas y selección de habitación.

7. Actualizar reserva

Autenticación mediante email.

Opciones para:

Cambiar de habitación.

Cambiar de hotel eliminando la reserva previa.

## Contribuciones

Este proyecto está abierto a mejoras y colaboraciones. Por favor, crea un pull request con tus cambios y sugerencias.

## Autor
![GitHub Contributors Image](https://contrib.rocks/image?repo=RM92023/BookingHotelesAPI)

Robinson Muñetón Jaramillo - <a href="https://github.com/RM92023" target="_blank"> @RM92023</a>