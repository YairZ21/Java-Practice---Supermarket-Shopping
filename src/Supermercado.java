import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Estructura para almacenar los datos de cada artículo comprado
class ItemCarrito {
    String nombre;
    int cantidad;
    double subtotal;

    public ItemCarrito(String nombre, int cantidad, double subtotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
}

public class Supermercado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Se uso TreeMap en lugar de HashMap para que los productos
        // se muestren siempre en orden alfabético, mejorando la legibilidad.
        TreeMap<String, Double> catalogo = new TreeMap<>();
        catalogo.put("Aceite",   3.80);
        catalogo.put("Arroz",    3.25);
        catalogo.put("Azucar",   1.20);
        catalogo.put("Cafe",     6.00);
        catalogo.put("Frijoles", 2.50);
        catalogo.put("Huevos",   4.00);
        catalogo.put("Leche",    1.50);
        catalogo.put("Pan",      2.00);
        catalogo.put("Queso",    5.50);
        catalogo.put("Sal",      0.90);

        // Estructura para almacenar los artículos agregados al carrito
        ArrayList<ItemCarrito> carrito = new ArrayList<>();
        double totalGeneral = 0.0;

        // Solicitar el nombre del cliente
        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine().trim();

        // Validar que el nombre no esté vacío
        while (cliente.isEmpty()) {
            System.out.print("Error: El nombre no puede estar vacío. Ingrese el nombre del cliente: ");
            cliente = scanner.nextLine().trim();
        }

        boolean continuar = true;

        // Bucle principal: permite agregar varios productos
        while (continuar) {

            // Mostrar lista de productos y precios
            System.out.println("\nProductos disponibles:");
            for (Map.Entry<String, Double> producto : catalogo.entrySet()) {
                System.out.printf("  %-10s - $%.2f%n", producto.getKey(), producto.getValue());
            }

            // Verificar que el producto exista ──────────────
            // La variable se declara antes del bucle para poder
            // asignarle el nombre normalizado (con capitalización del catálogo)
            // y usarla después de salir del while.
            String productoSeleccionado = "";
            boolean productoValido = false;

            while (!productoValido) {
                System.out.print("\nProducto: ");
                String entrada = scanner.nextLine().trim();

                if (entrada.isEmpty()) {
                    System.out.println("Error: Debe ingresar un nombre de producto.");
                    continue;
                }

                for (String key : catalogo.keySet()) {
                    if (key.equalsIgnoreCase(entrada)) {
                        productoSeleccionado = key;   // nombre normalizado del catálogo
                        productoValido = true;
                        break;
                    }
                }

                if (!productoValido) {
                    System.out.println("Error: El producto \"" + entrada
                            + "\" no existe en el catálogo. Intente nuevamente.");
                }
            }

            // Cantidad debe ser un entero mayor que cero ────
            int cantidad = 0;
            boolean cantidadValida = false;

            while (!cantidadValida) {
                System.out.print("Cantidad: ");

                // Se lee la línea completa y se intenta parsear,
                // evitando que el buffer quede en estado inconsistente cuando
                // el usuario ingresa texto en lugar de un número.
                String lineaCantidad = scanner.nextLine().trim();

                try {
                    cantidad = Integer.parseInt(lineaCantidad);
                    if (cantidad > 0) {
                        cantidadValida = true;
                    } else {
                        System.out.println("Error: La cantidad debe ser un número entero mayor que cero.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: \"" + lineaCantidad + "\" no es un número entero válido.");
                }
            }
            // Se eliminó el 'scanner.nextLine()' residual porque
            // ahora toda la entrada se lee con nextLine(), no con nextInt().

            // Calcular subtotal y acumular al total general
            double precioUnitario = catalogo.get(productoSeleccionado);
            double subtotal = precioUnitario * cantidad;
            totalGeneral += subtotal;

            carrito.add(new ItemCarrito(productoSeleccionado, cantidad, subtotal));
            System.out.printf("Subtotal: $%.2f%n", subtotal);

            // ── Control del ciclo: continuar comprando ───────────────────────
            //Se acepta tanto 's' como 'S'; cualquier otra
            // entrada (incluyendo 'N', 'n', o Enter) finaliza la compra.
            System.out.print("¿Desea agregar otro producto? (S/N): ");
            String respuesta = scanner.nextLine().trim();
            continuar = respuesta.equalsIgnoreCase("S");
        }

        // ── Generar resumen / factura ────────────────────────────────────────
        System.out.println("\n----- FACTURA -----");
        System.out.println("Cliente: " + cliente);
        System.out.println();

        for (ItemCarrito item : carrito) {
            System.out.printf("%-10s | Cantidad: %3d | Subtotal: $%.2f%n",
                    item.nombre, item.cantidad, item.subtotal);
        }

        System.out.println();

        // Condicional: descuento del 5 % si el total supera los $50
        if (totalGeneral > 50.0) {
            double descuento  = totalGeneral * 0.05;
            double nuevoTotal = totalGeneral - descuento;
            System.out.printf("Total sin descuento:  $%.2f%n", totalGeneral);
            System.out.printf("Descuento (5%%):       $%.2f%n", descuento);
            System.out.printf("Total a pagar:        $%.2f%n", nuevoTotal);
        } else {
            System.out.printf("Total a pagar: $%.2f%n", totalGeneral);
        }

        scanner.close();
    }
}