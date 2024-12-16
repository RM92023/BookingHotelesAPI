package org.example;

import java.time.LocalDate;
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

        int opcion;
        do {
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
        } while (opcion != 6);
    }

    public static void buscarHoteles(Scanner scanner, String[] nombres, String[] ciudades, float[] calificaciones, double[] preciosPorNoche, boolean[] diaDeSol, String[] actividades, boolean[] incluyeAlmuerzo, boolean[] incluyeRefrigerio) {
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.next();
        System.out.print("¿Desea día de sol? (true/false): ");
        boolean deseaDiaDeSol = scanner.nextBoolean();

        System.out.println("Hoteles encontrados:");
        for (int i = 0; i < nombres.length; i++) {
            if (ciudades[i].equalsIgnoreCase(ciudad) && diaDeSol[i] == deseaDiaDeSol) {
                System.out.printf("Hotel: %s, Calificación: %.1f, Precio: %.2f\n", nombres[i], calificaciones[i], preciosPorNoche[i]);
                System.out.printf("Actividades: %s, Almuerzo: %b, Refrigerio: %b\n", actividades[i], incluyeAlmuerzo[i], incluyeRefrigerio[i]);
            }
        }
    }

    public static int crearReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, String[][] habitacionesDescripciones, int[][] habitacionesDisponibles, double[][] habitacionesPrecios,
                                   String[] reservasClientes, String[] reservasHoteles, String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones,
                                   LocalDate[] reservasFechaInicio, LocalDate[] reservasFechaFin, int reservaIndex) {
        System.out.println("Hoteles disponibles:");
        for (String hotel : hotelNombres) {
            System.out.println(hotel);
        }

        System.out.print("Ingrese el nombre del hotel: ");
        scanner.nextLine();
        String hotelNombre = scanner.nextLine();

        int hotelIndex = -1;
        for (int i = 0; i < hotelNombres.length; i++) {
            if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                hotelIndex = i;
                break;
            }
        }

        if (hotelIndex == -1) {
            System.out.println("El hotel no existe.");
            return reservaIndex;
        }

        System.out.print("Ingrese su nombre: ");
        String cliente = scanner.nextLine();

        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Fecha de fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());

        System.out.print("Cantidad de habitaciones: ");
        int cantidadHabitaciones = scanner.nextInt();

        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
            if (habitacionesDisponibles[hotelIndex][i] >= cantidadHabitaciones) {
                System.out.printf("%s: %s, Precio: %.2f\n", habitacionesTipos[hotelIndex][i], habitacionesDescripciones[hotelIndex][i], habitacionesPrecios[hotelIndex][i]);
            }
        }

        System.out.print("Seleccione el tipo de habitación: ");
        scanner.nextLine();
        String tipoHabitacion = scanner.nextLine();

        int habitacionIndex = -1;
        for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
            if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(tipoHabitacion)) {
                habitacionIndex = i;
                break;
            }
        }

        if (habitacionIndex == -1 || habitacionesDisponibles[hotelIndex][habitacionIndex] < cantidadHabitaciones) {
            System.out.println("No hay disponibilidad para este tipo de habitación.");
            return reservaIndex;
        }

        habitacionesDisponibles[hotelIndex][habitacionIndex] -= cantidadHabitaciones;

        reservasClientes[reservaIndex] = cliente;
        reservasHoteles[reservaIndex] = hotelNombre;
        reservasTiposHabitacion[reservaIndex] = tipoHabitacion;
        reservasCantidadHabitaciones[reservaIndex] = cantidadHabitaciones;
        reservasFechaInicio[reservaIndex] = inicio;
        reservasFechaFin[reservaIndex] = fin;

        System.out.println("¡Reserva realizada con éxito!");
        return reservaIndex + 1;
    }

    public static void verReservas(String[] reservasClientes, String[] reservasHoteles, String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones,
                                   LocalDate[] reservasFechaInicio, LocalDate[] reservasFechaFin, int reservaIndex) {
        if (reservaIndex == 0) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        System.out.println("Reservas registradas:");
        for (int i = 0; i < reservaIndex; i++) {
            System.out.printf("Cliente: %s, Hotel: %s, Tipo: %s, Cantidad: %d, Fechas: %s a %s\n",
                    reservasClientes[i], reservasHoteles[i], reservasTiposHabitacion[i], reservasCantidadHabitaciones[i], reservasFechaInicio[i], reservasFechaFin[i]);
        }
    }

    public static void buscarHotelesConParametros(Scanner scanner, String[] nombres, String[] ciudades, float[] calificaciones, double[] preciosPorNoche,
                                                  boolean[] diaDeSol, String[] actividades, boolean[] incluyeAlmuerzo, boolean[] incluyeRefrigerio,
                                                  String[][] habitacionesTipos, double[][] habitacionesPrecios, int[][] habitacionesDisponibles) {
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.next();

        System.out.print("Tipo de alojamiento (Hotel, Apartamento, Finca): ");
        String tipoAlojamiento = scanner.next();

        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.next());

        System.out.print("Fecha de fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.next());

        System.out.print("Cantidad de adultos: ");
        int adultos = scanner.nextInt();

        System.out.print("Cantidad de niños: ");
        int niños = scanner.nextInt();

        System.out.print("Cantidad de habitaciones: ");
        int cantidadHabitaciones = scanner.nextInt();

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

                int diasHospedaje = fin.getDayOfMonth() - inicio.getDayOfMonth() + 1;
                double precioBase = precioHabitacionMasBarata * cantidadHabitaciones * diasHospedaje;

                double ajuste = 0.0;
                if (inicio.getDayOfMonth() >= 5 && fin.getDayOfMonth() <= 10) {
                    ajuste = -0.08 * precioBase;
                } else if (inicio.getDayOfMonth() >= 10 && fin.getDayOfMonth() <= 15) {
                    ajuste = 0.10 * precioBase;
                } else if (fin.getDayOfMonth() >= 26) {
                    ajuste = 0.15 * precioBase;
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
    }

    public static void confirmarDisponibilidad(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, String[][] habitacionesDescripciones, int[][] habitacionesDisponibles, double[][] habitacionesPrecios) {
        System.out.print("Ingrese el nombre del hotel: ");
        scanner.nextLine(); // Limpiar buffer
        String hotelNombre = scanner.nextLine();

        int hotelIndex = -1;
        for (int i = 0; i < hotelNombres.length; i++) {
            if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                hotelIndex = i;
                break;
            }
        }

        if (hotelIndex == -1) {
            System.out.println("El hotel no existe.");
            return;
        }

        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Fecha de fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());

        System.out.print("Cantidad de adultos: ");
        int adultos = scanner.nextInt();

        System.out.print("Cantidad de niños: ");
        int niños = scanner.nextInt();

        System.out.print("Cantidad de habitaciones: ");
        int cantidadHabitaciones = scanner.nextInt();

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
    }

    public static int realizarReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, int[][] habitacionesDisponibles, double[][] habitacionesPrecios,
                                      String[] reservasClientes, String[] reservasHoteles, String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones,
                                      int reservaIndex) {
        System.out.println("Hoteles disponibles:");
        for (String hotel : hotelNombres) {
            System.out.println(hotel);
        }

        System.out.print("Ingrese el nombre del hotel: ");
        scanner.nextLine(); // Limpiar buffer
        String hotelNombre = scanner.nextLine();

        int hotelIndex = -1;
        for (int i = 0; i < hotelNombres.length; i++) {
            if (hotelNombres[i].equalsIgnoreCase(hotelNombre)) {
                hotelIndex = i;
                break;
            }
        }

        if (hotelIndex == -1) {
            System.out.println("El hotel no existe.");
            return reservaIndex;
        }

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese su hora aproximada de llegada (HH:MM): ");
        String horaLlegada = scanner.nextLine();

        System.out.print("Cantidad de habitaciones: ");
        int cantidadHabitaciones = scanner.nextInt();

        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
            if (habitacionesDisponibles[hotelIndex][i] >= cantidadHabitaciones) {
                System.out.printf("%s, Precio por noche: %.2f\n", habitacionesTipos[hotelIndex][i], habitacionesPrecios[hotelIndex][i]);
            }
        }

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
            System.out.println("No hay disponibilidad para este tipo de habitación.");
            return reservaIndex;
        }

        // Actualizar la disponibilidad
        habitacionesDisponibles[hotelIndex][habitacionIndex] -= cantidadHabitaciones;

        // Registrar la reserva
        reservasClientes[reservaIndex] = nombre + " " + apellido;
        reservasHoteles[reservaIndex] = hotelNombre;
        reservasTiposHabitacion[reservaIndex] = tipoHabitacion;
        reservasCantidadHabitaciones[reservaIndex] = cantidadHabitaciones;

        System.out.println("¡Se ha realizado la reserva con éxito!");
        return reservaIndex + 1;
    }

    public static void actualizarReserva(Scanner scanner, String[] hotelNombres, String[][] habitacionesTipos, int[][] habitacionesDisponibles, double[][] habitacionesPrecios,
                                         String[] reservasClientes, String[] reservasHoteles, String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones,
                                         LocalDate[] reservasFechaInicio, LocalDate[] reservasFechaFin, int reservaIndex) {
        if (reservaIndex == 0) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        System.out.print("Ingrese su email: ");
        scanner.nextLine(); // Limpiar buffer
        String email = scanner.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());

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

        System.out.println("¿Qué desea hacer?\n1. Cambiar habitación\n2. Cambiar alojamiento\n3. Cancelar");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                cambiarHabitacion(scanner, reservasHoteles[reservaEncontrada], habitacionesTipos, habitacionesDisponibles, habitacionesPrecios, hotelNombres, reservasTiposHabitacion, reservasCantidadHabitaciones, reservaEncontrada);
                break;
            case 2:
                cambiarAlojamiento(scanner, reservaEncontrada, reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones,
                        reservasFechaInicio, reservasFechaFin, habitacionesDisponibles, hotelNombres, habitacionesTipos, habitacionesPrecios);

                break;
            case 3:
                System.out.println("Operación cancelada.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public static void cambiarHabitacion(Scanner scanner, String hotelNombre, String[][] habitacionesTipos, int[][] habitacionesDisponibles,
                                         double[][] habitacionesPrecios, String[] hotelNombres, String[] reservasTiposHabitacion,
                                         int[] reservasCantidadHabitaciones, int reservaIndex) {
        int hotelIndex = -1;
        for (int i = 0; i < hotelNombres.length; i++) {
            if (hotelNombre.equalsIgnoreCase(hotelNombres[i])) {
                hotelIndex = i;
                break;
            }
        }

        if (hotelIndex == -1) {
            System.out.println("Error al identificar el hotel.");
            return;
        }

        System.out.println("Habitaciones disponibles:");
        for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
            if (habitacionesDisponibles[hotelIndex][i] > 0) {
                System.out.printf("%s: Precio: %.2f\n", habitacionesTipos[hotelIndex][i], habitacionesPrecios[hotelIndex][i]);
            }
        }

        System.out.print("Seleccione el nuevo tipo de habitación: ");
        scanner.nextLine(); // Limpiar buffer
        String nuevaHabitacion = scanner.nextLine();

        int habitacionIndex = -1;
        for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
            if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(nuevaHabitacion)) {
                habitacionIndex = i;
                break;
            }
        }

        if (habitacionIndex == -1 || habitacionesDisponibles[hotelIndex][habitacionIndex] < reservasCantidadHabitaciones[reservaIndex]) {
            System.out.println("No hay disponibilidad para la nueva habitación.");
            return;
        }

        // Actualizar habitación
        habitacionesDisponibles[hotelIndex][habitacionIndex] -= reservasCantidadHabitaciones[reservaIndex];
        reservasTiposHabitacion[reservaIndex] = nuevaHabitacion;

        System.out.println("Habitación actualizada con éxito.");
    }

    public static void cambiarAlojamiento(Scanner scanner, int reservaIndex, String[] reservasClientes, String[] reservasHoteles,
                                          String[] reservasTiposHabitacion, int[] reservasCantidadHabitaciones, LocalDate[] reservasFechaInicio,
                                          LocalDate[] reservasFechaFin, int[][] habitacionesDisponibles, String[] hotelNombres,
                                          String[][] habitacionesTipos, double[][] habitacionesPrecios) {
        int hotelIndex = -1;
        for (int i = 0; i < hotelNombres.length; i++) {
            if (reservasHoteles[reservaIndex].equalsIgnoreCase(hotelNombres[i])) {
                hotelIndex = i;
                break;
            }
        }

        if (hotelIndex != -1) {
            // Revertir la disponibilidad de habitaciones
            for (int i = 0; i < habitacionesTipos[hotelIndex].length; i++) {
                if (habitacionesTipos[hotelIndex][i].equalsIgnoreCase(reservasTiposHabitacion[reservaIndex])) {
                    habitacionesDisponibles[hotelIndex][i] += reservasCantidadHabitaciones[reservaIndex];
                    break;
                }
            }
        }

        // Borrar la reserva
        reservasClientes[reservaIndex] = null;
        reservasHoteles[reservaIndex] = null;
        reservasTiposHabitacion[reservaIndex] = null;
        reservasCantidadHabitaciones[reservaIndex] = 0;
        reservasFechaInicio[reservaIndex] = null;
        reservasFechaFin[reservaIndex] = null;

        System.out.println("La reserva ha sido eliminada. Redirigiendo a crear una nueva reserva...");
        realizarReserva(scanner, hotelNombres, habitacionesTipos, habitacionesDisponibles, habitacionesPrecios,
                reservasClientes, reservasHoteles, reservasTiposHabitacion, reservasCantidadHabitaciones, reservaIndex);

    }
}
