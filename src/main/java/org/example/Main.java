package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Datos iniciales de hoteles
        String[] hotelNombres = {"Hotel Sol", "Hotel Luna"};
        String[] hotelCiudades = {"Cartagena", "Medellín"};
        float[] hotelCalificaciones = {4.5f, 5.0f};
        double[] hotelPreciosPorNoche = {200000, 350000};
        boolean[] hotelDiaDeSol = {true, false};
        String[] hotelActividades = {"Natación, Paseos en bote", "Spa, Caminatas"};
        boolean[] hotelIncluyeAlmuerzo = {true, false};
        boolean[] hotelIncluyeRefrigerio = {false, true};

        String[][] habitacionesTipos = {
                {"Sencilla", "Doble", "Suite"},
                {"Sencilla", "Suite"}
        };
        String[][] habitacionesDescripciones = {
                {
                        "1 cama, vista al mar, aire acondicionado, TV",
                        "2 camas dobles, vista al mar, aire acondicionado, cafetera",
                        "Cama king, jacuzzi, TV pantalla plana, minibar"
                },
                {
                        "1 cama, aire acondicionado, escritorio, ducha",
                        "Cama king, jacuzzi, TV pantalla plana, minibar"
                }
        };
        double[][] habitacionesPrecios = {
                {100000, 150000, 300000},
                {120000, 500000}
        };
        int[][] habitacionesDisponibles = {
                {10, 5, 3},
                {8, 2}
        };

        // Datos de reservas
        String[] reservasClientes = new String[100];
        String[] reservasHoteles = new String[100];
        String[] reservasTiposHabitacion = new String[100];
        int[] reservasCantidadHabitaciones = new int[100];
        LocalDate[] reservasFechaInicio = new LocalDate[100];
        LocalDate[] reservasFechaFin = new LocalDate[100];
        int reservaIndex = 0;

        int opcion = 0;
        do {
            try {
                System.out.println("\n¡Bienvenido a Booking Hoteles!");
                System.out.println("1. Buscar hoteles");
                System.out.println("2. Crear reserva");
                System.out.println("3. Ver reservas");
                System.out.println("4. Buscar hoteles con más detalles");
                System.out.println("5. Confirmar disponibilidad de habitaciones");
                System.out.println("6. Realizar reserva personalizada");
                System.out.println("7. Actualizar reserva");
                System.out.println("8. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer

                switch (opcion) {
                    case 1:
                        buscarHoteles(scanner, hotelNombres, hotelCiudades, hotelCalificaciones, hotelPreciosPorNoche, hotelDiaDeSol, hotelActividades, hotelIncluyeAlmuerzo, hotelIncluyeRefrigerio);
                        break;
                    case 2:
                        reservaIndex = crearReserva(scanner, hotelNombres, habitacionesTipos, habitacionesDescripciones, habitacionesDisponibles, habitacionesPrecios, reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservasFechaInicio, reservasFechaFin, reservaIndex);
                        break;
                    case 3:
                        verReservas(reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservasFechaInicio, reservasFechaFin, reservaIndex);
                        break;
                    case 4:
                        buscarHotelesConParametros(scanner, hotelNombres, hotelCiudades, hotelCalificaciones, hotelPreciosPorNoche, hotelDiaDeSol, hotelActividades, hotelIncluyeAlmuerzo, hotelIncluyeRefrigerio, habitacionesTipos, habitacionesPrecios, habitacionesDisponibles);
                        break;
                    case 5:
                        confirmarDisponibilidad(scanner, hotelNombres, habitacionesTipos, habitacionesDescripciones, habitacionesDisponibles, habitacionesPrecios);
                        break;
                    case 6:
                        reservaIndex = realizarReserva(scanner, hotelNombres, habitacionesTipos, habitacionesDisponibles, habitacionesPrecios, reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservaIndex);
                        break;
                    case 7:
                        actualizarReserva(scanner, hotelNombres, habitacionesTipos, habitacionesDisponibles, habitacionesPrecios, reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservasFechaInicio, reservasFechaFin, reservaIndex);
                        break;
                    case 8:
                        System.out.println("¡Gracias por usar Booking Hoteles!");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido. Intente de nuevo.");
                scanner.nextLine(); // Limpia el buffer incorrecto
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Use YYYY-MM-DD.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 8);
    }

    public static void buscarHoteles(Scanner scanner, String[] nombres, String[] ciudades, float[] calificaciones, double[] preciosPorNoche,
                                     boolean[] diaDeSol, String[] actividades, boolean[] incluyeAlmuerzo, boolean[] incluyeRefrigerio) {
        try {
            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.next();

            if (ciudad.trim().isEmpty()) {
                System.out.println("Error: La ciudad no puede estar vacía.");
                return;
            }

            System.out.print("¿Desea día de sol? (true/false): ");
            boolean deseaDiaDeSol = false;

            // Validación para asegurar entrada booleana
            if (scanner.hasNextBoolean()) {
                deseaDiaDeSol = scanner.nextBoolean();
            } else {
                System.out.println("Error: Debe ingresar 'true' o 'false' para día de sol.");
                scanner.next(); // Limpia la entrada inválida
                return;
            }

            boolean hotelEncontrado = false;

            System.out.println("\nHoteles encontrados:");
            for (int i = 0; i < nombres.length; i++) {
                if (ciudades[i].equalsIgnoreCase(ciudad) && diaDeSol[i] == deseaDiaDeSol) {
                    System.out.printf("Hotel: %s, Calificación: %.1f, Precio: %.2f%n", nombres[i], calificaciones[i], preciosPorNoche[i]);
                    System.out.printf("Actividades: %s, Almuerzo: %b, Refrigerio: %b%n", actividades[i], incluyeAlmuerzo[i], incluyeRefrigerio[i]);
                    hotelEncontrado = true;
                }
            }

            if (!hotelEncontrado) {
                System.out.println("No se encontraron hoteles que coincidan con los criterios ingresados.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Intente de nuevo.");
            scanner.nextLine(); // Limpia el buffer del Scanner
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static int crearReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, String[][] habitacionesDescripciones,
                                   int[][] habitacionesDisponibles, double[][] habitacionesPrecios, String[] reservasClientes,
                                   String[] reservasHoteles, String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones,
                                   LocalDate[] reservasFechaInicio, LocalDate[] reservasFechaFin, int reservaIndex) {
        try {
            // Mostrar hoteles disponibles
            System.out.println("Hoteles disponibles:");
            for (String hotel : hotelNombres) {
                System.out.println(hotel);
            }

            // Validación del nombre del hotel
            System.out.print("Ingrese el nombre del hotel: ");
            scanner.nextLine(); // Limpia el buffer
            String hotelNombre = scanner.nextLine().trim();

            if (hotelNombre.isEmpty()) {
                System.out.println("Error: El nombre del hotel no puede estar vacío.");
                return reservaIndex;
            }

            int hotelIndex = -1;
            for (int i = 0; i < hotelNombres.length; i++) {
                if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                    hotelIndex = i;
                    break;
                }
            }

            if (hotelIndex == -1) {
                System.out.println("Error: El hotel no existe.");
                return reservaIndex;
            }

            // Solicitar nombre del cliente
            System.out.print("Ingrese su nombre: ");
            String cliente = scanner.nextLine().trim();
            if (cliente.isEmpty()) {
                System.out.println("Error: El nombre del cliente no puede estar vacío.");
                return reservaIndex;
            }

            // Solicitar fechas
            LocalDate inicio, fin;
            try {
                System.out.print("Fecha de inicio (YYYY-MM-DD): ");
                inicio = LocalDate.parse(scanner.nextLine());

                System.out.print("Fecha de fin (YYYY-MM-DD): ");
                fin = LocalDate.parse(scanner.nextLine());

                if (inicio.isAfter(fin)) {
                    System.out.println("Error: La fecha de inicio debe ser anterior o igual a la fecha de fin.");
                    return reservaIndex;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Use el formato YYYY-MM-DD.");
                return reservaIndex;
            }

            // Solicitar cantidad de habitaciones
            System.out.print("Cantidad de habitaciones: ");
            int cantidadHabitaciones;
            if (scanner.hasNextInt()) {
                cantidadHabitaciones = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer
                if (cantidadHabitaciones <= 0) {
                    System.out.println("Error: La cantidad de habitaciones debe ser mayor a 0.");
                    return reservaIndex;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido para la cantidad de habitaciones.");
                scanner.nextLine(); // Limpia entrada inválida
                return reservaIndex;
            }

            // Mostrar habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            boolean habitacionDisponible = false;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesDisponibles[hotelIndex][i] >= cantidadHabitaciones) {
                    System.out.printf("%s: %s, Precio: %.2f\n", habitacionesTipos[hotelIndex][i],
                            habitacionesDescripciones[hotelIndex][i], habitacionesPrecios[hotelIndex][i]);
                    habitacionDisponible = true;
                }
            }

            if (!habitacionDisponible) {
                System.out.println("Error: No hay habitaciones disponibles para la cantidad solicitada.");
                return reservaIndex;
            }

            // Seleccionar tipo de habitación
            System.out.print("Seleccione el tipo de habitación: ");
            String tipoHabitacion = scanner.nextLine().trim();

            int habitacionIndex = -1;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(tipoHabitacion)) {
                    habitacionIndex = i;
                    break;
                }
            }

            if (habitacionIndex == -1) {
                System.out.println("Error: El tipo de habitación ingresado no es válido.");
                return reservaIndex;
            }

            if (habitacionesDisponibles[hotelIndex][habitacionIndex] < cantidadHabitaciones) {
                System.out.println("Error: No hay suficientes habitaciones disponibles para el tipo seleccionado.");
                return reservaIndex;
            }

            // Confirmar reserva
            habitacionesDisponibles[hotelIndex][habitacionIndex] -= cantidadHabitaciones;
            reservasClientes[reservaIndex] = cliente;
            reservasHoteles[reservaIndex] = hotelNombre;
            reservasTiposHabitacion[reservaIndex] = tipoHabitacion;
            reservasCantidadHabitaciones[reservaIndex] = cantidadHabitaciones;
            reservasFechaInicio[reservaIndex] = inicio;
            reservasFechaFin[reservaIndex] = fin;

            System.out.println("¡Reserva realizada con éxito!");
            return reservaIndex + 1;

        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return reservaIndex;
        }
    }


    public static void verReservas(String[] reservasClientes, String[] reservasHoteles, String[] reservasTiposHabitacion,
                                   int[] reservasCantidadHabitaciones, LocalDate[] reservasFechaInicio, LocalDate[] reservasFechaFin,
                                   int reservaIndex) {
        try {
            // Validación si no hay reservas
            if (reservaIndex == 0) {
                System.out.println("No hay reservas registradas.");
                return;
            }

            System.out.println("\nReservas registradas:");

            for (int i = 0; i < reservaIndex; i++) {
                // Validar que los datos no sean nulos
                if (reservasClientes[i] == null || reservasHoteles[i] == null || reservasTiposHabitacion[i] == null ||
                        reservasFechaInicio[i] == null || reservasFechaFin[i] == null || reservasCantidadHabitaciones[i] <= 0) {
                    System.out.printf("Reserva #%d tiene datos inconsistentes y no será mostrada.\n", i + 1);
                    continue; // Salta a la siguiente reserva
                }

                // Imprimir información de la reserva
                System.out.printf("Cliente: %s, Hotel: %s, Tipo: %s, Cantidad: %d, Fechas: %s a %s\n",
                        reservasClientes[i], reservasHoteles[i], reservasTiposHabitacion[i],
                        reservasCantidadHabitaciones[i], reservasFechaInicio[i], reservasFechaFin[i]);
            }
        } catch (Exception e) {
            System.out.println("Error inesperado al mostrar las reservas: " + e.getMessage());
        }
    }

    public static void buscarHotelesConParametros(Scanner scanner, String[] nombres, String[] ciudades, float[] calificaciones, double[] preciosPorNoche,
                                                  boolean[] diaDeSol, String[] actividades, boolean[] incluyeAlmuerzo, boolean[] incluyeRefrigerio,
                                                  String[][] habitacionesTipos, double[][] habitacionesPrecios, int[][] habitacionesDisponibles) {
        try {
            // Entrada de la ciudad
            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.next();
            if (ciudad == null || ciudad.trim().isEmpty()) {
                System.out.println("La ciudad no puede estar vacía.");
                return;
            }

            // Tipo de alojamiento
            System.out.print("Tipo de alojamiento (Hotel, Apartamento, Finca, Día de Sol): ");
            String tipoAlojamiento = scanner.next();
            if (tipoAlojamiento == null || tipoAlojamiento.trim().isEmpty()) {
                System.out.println("El tipo de alojamiento no puede estar vacío.");
                return;
            }

            // Fechas de hospedaje
            System.out.print("Fecha de inicio (YYYY-MM-DD): ");
            LocalDate inicio = LocalDate.parse(scanner.next());
            System.out.print("Fecha de fin (YYYY-MM-DD): ");
            LocalDate fin = LocalDate.parse(scanner.next());
            if (inicio.isAfter(fin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            // Cantidad de adultos y niños
            System.out.print("Cantidad de adultos: ");
            int adultos = scanner.nextInt();
            System.out.print("Cantidad de niños: ");
            int niños = scanner.nextInt();
            if (adultos <= 0 && niños <= 0) {
                System.out.println("Debe haber al menos un adulto o un niño.");
                return;
            }

            // Cantidad de habitaciones
            System.out.print("Cantidad de habitaciones: ");
            int cantidadHabitaciones = scanner.nextInt();
            if (cantidadHabitaciones <= 0) {
                System.out.println("La cantidad de habitaciones debe ser mayor a 0.");
                return;
            }

            // Búsqueda de hoteles que cumplen con los parámetros
            System.out.println("\nHoteles que cumplen con los parámetros:");
            boolean hotelesEncontrados = false;

            for (int i = 0; i < nombres.length; i++) {
                boolean cumpleCiudad = ciudades[i].equalsIgnoreCase(ciudad);
                boolean cumpleDiaDeSol = tipoAlojamiento.equalsIgnoreCase("Día de Sol") ? diaDeSol[i] : true;

                boolean habitacionesDisponiblesSuficientes = false;
                double precioHabitacionMasBarata = Double.MAX_VALUE;

                for (int j = 0; j < habitacionesTipos[i].length; j++) {
                    if (habitacionesDisponibles[i][j] >= cantidadHabitaciones) {
                        habitacionesDisponiblesSuficientes = true;
                        precioHabitacionMasBarata = Math.min(precioHabitacionMasBarata, habitacionesPrecios[i][j]);
                    }
                }

                if (cumpleCiudad && cumpleDiaDeSol && habitacionesDisponiblesSuficientes) {
                    hotelesEncontrados = true;

                    int diasHospedaje = (int) (fin.toEpochDay() - inicio.toEpochDay() + 1);
                    double precioBase = precioHabitacionMasBarata * cantidadHabitaciones * diasHospedaje;

                    double ajuste = 0.0;
                    if (inicio.getDayOfMonth() >= 5 && fin.getDayOfMonth() <= 10) {
                        ajuste = -0.08 * precioBase; // Descuento del 8%
                    } else if (inicio.getDayOfMonth() >= 10 && fin.getDayOfMonth() <= 15) {
                        ajuste = 0.10 * precioBase; // Aumento del 10%
                    } else if (fin.getDayOfMonth() >= 26) {
                        ajuste = 0.15 * precioBase; // Aumento del 15%
                    }

                    double precioFinal = precioBase + ajuste;

                    System.out.printf("Hotel: %s\n", nombres[i]);
                    System.out.printf("Calificación: %.1f, Precio por noche: %.2f\n", calificaciones[i], preciosPorNoche[i]);
                    System.out.printf("Precio base: %.2f, Ajuste: %.2f, Precio final: %.2f\n", precioBase, ajuste, precioFinal);
                    if (diaDeSol[i]) {
                        System.out.printf("Actividades: %s, Almuerzo: %b, Refrigerio: %b\n", actividades[i], incluyeAlmuerzo[i], incluyeRefrigerio[i]);
                    }
                    System.out.println();
                }
            }

            if (!hotelesEncontrados) {
                System.out.println("No se encontraron hoteles que cumplan con los parámetros.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar hoteles: " + e.getMessage());
        }
    }

    public static void confirmarDisponibilidad(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos,
                                               String[][] habitacionesDescripciones, int[][] habitacionesDisponibles,
                                               double[][] habitacionesPrecios) {
        try {
            // Validar nombre del hotel
            System.out.print("Ingrese el nombre del hotel: ");
            scanner.nextLine(); // Limpiar buffer
            String hotelNombre = scanner.nextLine();

            if (hotelNombre == null || hotelNombre.trim().isEmpty()) {
                System.out.println("El nombre del hotel no puede estar vacío.");
                return;
            }

            // Buscar el índice del hotel
            int hotelIndex = -1;
            for (int i = 0; i < hotelNombres.length; i++) {
                if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                    hotelIndex = i;
                    break;
                }
            }

            if (hotelIndex == -1) {
                System.out.println("El hotel ingresado no existe.");
                return;
            }

            // Validar fechas
            System.out.print("Fecha de inicio (YYYY-MM-DD): ");
            String fechaInicioStr = scanner.nextLine();
            System.out.print("Fecha de fin (YYYY-MM-DD): ");
            String fechaFinStr = scanner.nextLine();

            LocalDate inicio, fin;
            try {
                inicio = LocalDate.parse(fechaInicioStr);
                fin = LocalDate.parse(fechaFinStr);
            } catch (Exception e) {
                System.out.println("Error: Las fechas deben tener el formato correcto (YYYY-MM-DD).");
                return;
            }

            if (inicio.isAfter(fin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            // Validar cantidades de adultos, niños y habitaciones
            System.out.print("Cantidad de adultos: ");
            int adultos = scanner.nextInt();
            if (adultos <= 0) {
                System.out.println("Debe ingresar al menos un adulto.");
                return;
            }

            System.out.print("Cantidad de niños: ");
            int niños = scanner.nextInt();
            if (niños < 0) {
                System.out.println("La cantidad de niños no puede ser negativa.");
                return;
            }

            System.out.print("Cantidad de habitaciones: ");
            int cantidadHabitaciones = scanner.nextInt();
            if (cantidadHabitaciones <= 0) {
                System.out.println("La cantidad de habitaciones debe ser mayor a 0.");
                return;
            }

            // Mostrar habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            boolean disponibilidad = false;

            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesDisponibles[hotelIndex][i] >= cantidadHabitaciones) {
                    disponibilidad = true;
                    System.out.printf("Tipo: %s\nDescripción: %s\nPrecio por noche: %.2f\n",
                            habitacionesTipos[hotelIndex][i],
                            habitacionesDescripciones[hotelIndex][i],
                            habitacionesPrecios[hotelIndex][i]);
                }
            }

            if (!disponibilidad) {
                System.out.println("No hay habitaciones disponibles que cumplan con los criterios.");
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Por favor, verifique los datos ingresados.");
        }
    }


    public static int realizarReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, int[][] habitacionesDisponibles,
                                      double[][] habitacionesPrecios, String[] reservasClientes, String[] reservasHoteles,
                                      String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones, int reservaIndex) {
        try {
            // Mostrar hoteles disponibles
            System.out.println("Hoteles disponibles:");
            for (String hotel : hotelNombres) {
                System.out.println(hotel);
            }

            // Validar el nombre del hotel
            System.out.print("Ingrese el nombre del hotel: ");
            scanner.nextLine(); // Limpiar buffer
            String hotelNombre = scanner.nextLine();
            if (hotelNombre == null || hotelNombre.trim().isEmpty()) {
                System.out.println("El nombre del hotel no puede estar vacío.");
                return reservaIndex;
            }

            int hotelIndex = -1;
            for (int i = 0; i < hotelNombres.length; i++) {
                if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                    hotelIndex = i;
                    break;
                }
            }

            if (hotelIndex == -1) {
                System.out.println("El hotel ingresado no existe.");
                return reservaIndex;
            }

            // Validar los datos del cliente
            System.out.print("Ingrese su nombre: ");
            String nombre = scanner.nextLine();
            if (nombre.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return reservaIndex;
            }

            System.out.print("Ingrese su apellido: ");
            String apellido = scanner.nextLine();
            if (apellido.trim().isEmpty()) {
                System.out.println("El apellido no puede estar vacío.");
                return reservaIndex;
            }

            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();
            if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("El email ingresado no es válido.");
                return reservaIndex;
            }

            System.out.print("Ingrese su nacionalidad: ");
            String nacionalidad = scanner.nextLine();
            if (nacionalidad.trim().isEmpty()) {
                System.out.println("La nacionalidad no puede estar vacía.");
                return reservaIndex;
            }

            System.out.print("Ingrese su número de teléfono: ");
            String telefono = scanner.nextLine();
            if (!telefono.matches("^\\d{7,15}$")) {
                System.out.println("El número de teléfono debe contener entre 7 y 15 dígitos.");
                return reservaIndex;
            }

            System.out.print("Ingrese su hora aproximada de llegada (HH:MM): ");
            String horaLlegada = scanner.nextLine();
            if (!horaLlegada.matches("^([01]?\\d|2[0-3]):[0-5]\\d$")) {
                System.out.println("La hora de llegada debe estar en el formato HH:MM (24 horas).");
                return reservaIndex;
            }

            // Validar cantidad de habitaciones
            System.out.print("Cantidad de habitaciones: ");
            int cantidadHabitaciones = scanner.nextInt();
            if (cantidadHabitaciones <= 0) {
                System.out.println("La cantidad de habitaciones debe ser mayor a 0.");
                return reservaIndex;
            }

            // Mostrar habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            boolean hayDisponibles = false;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesDisponibles[hotelIndex][i] >= cantidadHabitaciones) {
                    hayDisponibles = true;
                    System.out.printf("%s, Precio por noche: %.2f\n", habitacionesTipos[hotelIndex][i], habitacionesPrecios[hotelIndex][i]);
                }
            }

            if (!hayDisponibles) {
                System.out.println("No hay habitaciones disponibles que cumplan con los criterios.");
                return reservaIndex;
            }

            // Seleccionar tipo de habitación
            System.out.print("Seleccione el tipo de habitación: ");
            scanner.nextLine(); // Limpiar buffer
            String tipoHabitacion = scanner.nextLine();

            int habitacionIndex = -1;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(tipoHabitacion)) {
                    habitacionIndex = i;
                    break;
                }
            }

            if (habitacionIndex == -1 || habitacionesDisponibles[hotelIndex][habitacionIndex] < cantidadHabitaciones) {
                System.out.println("No hay disponibilidad para el tipo de habitación seleccionado.");
                return reservaIndex;
            }

            // Actualizar disponibilidad y registrar la reserva
            habitacionesDisponibles[hotelIndex][habitacionIndex] -= cantidadHabitaciones;
            reservasClientes[reservaIndex] = nombre + " " + apellido;
            reservasHoteles[reservaIndex] = hotelNombre;
            reservasTiposHabitacion[reservaIndex] = tipoHabitacion;
            reservasCantidadHabitaciones[reservaIndex] = cantidadHabitaciones;

            System.out.println("¡Se ha realizado la reserva con éxito!");
            return reservaIndex + 1;

        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Verifique los datos ingresados.");
            return reservaIndex;
        }
    }

    public static void actualizarReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, int[][] habitacionesDisponibles,
                                         double[][] habitacionesPrecios, String[] reservasClientes, String[] reservasHoteles,
                                         String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones, LocalDate[] reservasFechaInicio,
                                         LocalDate[] reservasFechaFin, int reservaIndex) {
        try {
            if (reservaIndex == 0) {
                System.out.println("No hay reservas registradas.");
                return;
            }

            // Solicitar email y validar entrada
            System.out.print("Ingrese su email: ");
            scanner.nextLine(); // Limpiar buffer
            String email = scanner.nextLine();
            if (!email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("El email ingresado no es válido.");
                return;
            }

            // Solicitar fecha de nacimiento y validar formato
            System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
            LocalDate fechaNacimiento;
            try {
                fechaNacimiento = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("La fecha ingresada no es válida. Use el formato YYYY-MM-DD.");
                return;
            }

            // Buscar la reserva
            int reservaEncontrada = -1;
            for (int i = 0; i < reservaIndex; i++) {
                if (reservasClientes[i] != null && reservasClientes[i].contains(email)) {
                    reservaEncontrada = i;
                    break;
                }
            }

            if (reservaEncontrada == -1) {
                System.out.println("No se encontró ninguna reserva asociada al email proporcionado.");
                return;
            }

            // Mostrar la reserva encontrada
            System.out.printf("Reserva encontrada:\nCliente: %s\nHotel: %s\nHabitación: %s\nCantidad: %d\nFechas: %s a %s\n",
                    reservasClientes[reservaEncontrada],
                    reservasHoteles[reservaEncontrada],
                    reservasTiposHabitacion[reservaEncontrada],
                    reservasCantidadHabitaciones[reservaEncontrada],
                    reservasFechaInicio[reservaEncontrada],
                    reservasFechaFin[reservaEncontrada]);

            // Opciones de actualización
            System.out.println("¿Qué desea hacer?\n1. Cambiar habitación\n2. Cambiar alojamiento\n3. Cancelar");
            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("La opción ingresada no es válida.");
                scanner.nextLine(); // Limpiar buffer
                return;
            }

            switch (opcion) {
                case 1:
                    cambiarHabitacion(scanner, reservasHoteles[reservaEncontrada], habitacionesTipos, habitacionesDisponibles,
                            habitacionesPrecios, hotelNombres, reservasTiposHabitacion, reservasCantidadHabitaciones, reservaEncontrada);
                    break;
                case 2:
                    cambiarAlojamiento(scanner, reservaEncontrada, reservasClientes, reservasHoteles, reservasTiposHabitacion,
                            reservasCantidadHabitaciones, reservasFechaInicio, reservasFechaFin, habitacionesDisponibles,
                            hotelNombres, habitacionesTipos, habitacionesPrecios);
                    break;
                case 3:
                    System.out.println("Operación cancelada.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al actualizar la reserva. Verifique sus datos e inténtelo nuevamente.");
        }
    }

    public static void cambiarHabitacion(Scanner scanner, String hotelNombre, String[][] habitacionesTipos, int[][] habitacionesDisponibles,
                                         double[][] habitacionesPrecios, String[] hotelNombres, String[] reservasTiposHabitacion,
                                         int[] reservasCantidadHabitaciones, int reservaIndex) {
        try {
            // Validar que el hotel exista
            int hotelIndex = -1;
            for (int i = 0; i < hotelNombres.length; i++) {
                if (hotelNombre.equalsIgnoreCase(hotelNombres[i])) {
                    hotelIndex = i;
                    break;
                }
            }

            if (hotelIndex == -1) {
                System.out.println("Error: No se encontró el hotel especificado.");
                return;
            }

            // Mostrar habitaciones disponibles
            System.out.println("Habitaciones disponibles:");
            boolean disponibilidad = false;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesDisponibles[hotelIndex][i] > 0) {
                    System.out.printf("Tipo: %s, Precio: %.2f, Disponibles: %d\n",
                            habitacionesTipos[hotelIndex][i],
                            habitacionesPrecios[hotelIndex][i],
                            habitacionesDisponibles[hotelIndex][i]);
                    disponibilidad = true;
                }
            }

            if (!disponibilidad) {
                System.out.println("No hay habitaciones disponibles en este hotel.");
                return;
            }

            // Solicitar nueva habitación
            System.out.print("Seleccione el nuevo tipo de habitación: ");
            scanner.nextLine(); // Limpiar buffer
            String nuevaHabitacion = scanner.nextLine();

            // Validar la nueva habitación
            int habitacionIndex = -1;
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(nuevaHabitacion)) {
                    habitacionIndex = i;
                    break;
                }
            }

            if (habitacionIndex == -1) {
                System.out.println("Error: La habitación seleccionada no existe.");
                return;
            }

            // Verificar disponibilidad de la nueva habitación
            if (habitacionesDisponibles[hotelIndex][habitacionIndex] < reservasCantidadHabitaciones[reservaIndex]) {
                System.out.println("No hay suficiente disponibilidad para la nueva habitación.");
                return;
            }

            // Actualizar la disponibilidad y el tipo de habitación en la reserva
            habitacionesDisponibles[hotelIndex][habitacionIndex] -= reservasCantidadHabitaciones[reservaIndex];
            reservasTiposHabitacion[reservaIndex] = nuevaHabitacion;

            System.out.println("La habitación ha sido actualizada con éxito.");
        } catch (Exception e) {
            System.out.println("Error inesperado al cambiar la habitación. Por favor, inténtelo de nuevo.");
        }
    }

    public static void cambiarAlojamiento(Scanner scanner, int reservaIndex, String[] reservasClientes, String[] reservasHoteles,
                                          String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones, LocalDate[] reservasFechaInicio,
                                          LocalDate[] reservasFechaFin, int[][] habitacionesDisponibles, String[] hotelNombres,
                                          String[][] habitacionesTipos, double[][] habitacionesPrecios) {
        try {
            // Validar que la reserva exista
            if (reservaIndex < 0 || reservasClientes[reservaIndex] == null) {
                System.out.println("Error: No se encontró una reserva válida para actualizar.");
                return;
            }

            // Encontrar el índice del hotel actual
            int hotelIndex = -1;
            for (int i = 0; i < hotelNombres.length; i++) {
                if (reservasHoteles[reservaIndex] != null && reservasHoteles[reservaIndex].equalsIgnoreCase(hotelNombres[i])) {
                    hotelIndex = i;
                    break;
                }
            }

            if (hotelIndex != -1) {
                // Revertir disponibilidad de la habitación asociada a la reserva
                for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                    if (reservasTiposHabitacion[reservaIndex] != null &&
                            habitacionesTipos[hotelIndex][i].equalsIgnoreCase(reservasTiposHabitacion[reservaIndex])) {
                        habitacionesDisponibles[hotelIndex][i] += reservasCantidadHabitaciones[reservaIndex];
                        break;
                    }
                }
            } else {
                System.out.println("Advertencia: No se pudo revertir la disponibilidad porque el hotel asociado no se encontró.");
            }

            // Borrar los datos de la reserva
            reservasClientes[reservaIndex] = null;
            reservasHoteles[reservaIndex] = null;
            reservasTiposHabitacion[reservaIndex] = null;
            reservasCantidadHabitaciones[reservaIndex] = 0;
            reservasFechaInicio[reservaIndex] = null;
            reservasFechaFin[reservaIndex] = null;

            System.out.println("La reserva ha sido eliminada. Redirigiendo a crear una nueva reserva...");

            // Redirigir a crear una nueva reserva
            realizarReserva(scanner, hotelNombres, habitacionesTipos, habitacionesDisponibles, habitacionesPrecios,
                    reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservaIndex);

        } catch (Exception e) {
            System.out.println("Error inesperado al cambiar el alojamiento. Por favor, inténtelo de nuevo.");
        }
    }
}
