---ENGLISH---
# 🛒 Supermercado — Java Shopping Simulation

> An interactive supermarket checkout simulation built in pure Java, featuring robust input validation, a dynamic shopping cart, and automatic invoice generation.

---

## 📸 Sample Run

```
Enter customer name: Maria

Available products:
  Aceite     - $3.80
  Arroz      - $3.25
  Azucar     - $1.20
  ...

Product: Leche
Quantity: 3
Subtotal: $4.50

Would you like to add another product? (Y/N): N

----- INVOICE -----
Customer: Maria

Leche      | Quantity:   3 | Subtotal: $4.50
Pan        | Quantity:   2 | Subtotal: $4.00

Total to pay: $8.50
```

---

## ✨ Features

| Feature | Details |
|---|---|
| 📦 Product catalog | 10 products with prices, sorted alphabetically |
| 🛍️ Dynamic cart | Add unlimited products using `ArrayList` |
| ✅ Product validation | Case-insensitive search against the catalog |
| 🔢 Quantity validation | Only accepts integers greater than zero |
| 🏷️ Automatic subtotal | Calculated per item and accumulated into the total |
| 💳 Detailed invoice | Full summary printed at the end of the purchase |
| 💰 Automatic discount | 5% discount applied when the total exceeds $50.00 |

---

## 🗂️ Project Structure

```
Supermercado/
│
├── Supermercado.java      ← Main class with the full program flow
└── ItemCarrito.java       ← Helper class for each cart item
                             (name, quantity, subtotal)
```

> **Note:** Both classes live in the same `.java` file for easier submission.

---

## 🚀 How to Run

### Requirements
- Java 8 or higher installed
- Terminal / command prompt

### Steps

```bash
# 1. Compile
javac Supermercado.java

# 2. Run
java Supermercado
```

---

## 🧠 Concepts Applied

```
Variables        →  nombre, cantidad, precioUnitario, totalGeneral
Data types       →  String, int, double
Operators        →  +  -  *  /  >  ==  !=  &&  !
Conditionals     →  if / else if / else
Loops            →  while
Collections      →  TreeMap (catalog), ArrayList (cart)
Classes          →  ItemCarrito with constructor and public fields
User input       →  Scanner using nextLine() to avoid buffer issues
```

---

## 🛠️ Fixes Applied

### 🔴 Critical Bugs Fixed

**1. Undeclared variable `finalProducto`**
The variable used inside the catalog search loop was never declared, causing an immediate compilation error.

```java
// ❌ Before — does not compile
if (key.equalsIgnoreCase(finalProducto)) { ... }

// ✅ After — variable properly declared before the loop
String entrada = scanner.nextLine().trim();
if (key.equalsIgnoreCase(entrada)) { ... }
```

**2. Inconsistent `Scanner` buffer**
Using `nextInt()` left the `\n` newline character in the buffer. When the user typed text instead of a number, the exception was not handled correctly and the program could enter an infinite loop.

```java
// ❌ Before — prone to infinite loops on invalid input
if (scanner.hasNextInt()) {
    cantidad = scanner.nextInt();
} else {
    scanner.next(); // partial cleanup
}

// ✅ After — full line read + safe integer parsing
String lineaCantidad = scanner.nextLine().trim();
try {
    cantidad = Integer.parseInt(lineaCantidad);
} catch (NumberFormatException e) {
    System.out.println("Error: please enter a valid integer.");
}
```

**3. Stale `scanner.nextLine()` call removed**
There was an extra `nextLine()` call meant to consume the leftover newline from `nextInt()`. After switching to full-line reads, that call was skipping the next prompt entirely.

---

### 🟡 Optimizations

- **`HashMap` → `TreeMap`:** products now always display in alphabetical order, regardless of insertion order.
- **Empty name validation:** the program rejects a blank customer name.
- **Empty product input validation:** a clear error message is shown if the user presses Enter without typing anything.
- **Aligned columns with `%-10s`:** the product list is easier to scan at a glance.
- **`\n` → `%n`:** cross-platform line break compatible with Windows, Linux, and macOS.
- **Descriptive error messages:** each error tells the user exactly what was entered incorrectly.

---

## 📋 Product Catalog

| Product | Price |
|---|---|
| Aceite (Oil) | $3.80 |
| Arroz (Rice) | $3.25 |
| Azucar (Sugar) | $1.20 |
| Cafe (Coffee) | $6.00 |
| Frijoles (Beans) | $2.50 |
| Huevos (Eggs) | $4.00 |
| Leche (Milk) | $1.50 |
| Pan (Bread) | $2.00 |
| Queso (Cheese) | $5.50 |
| Sal (Salt) | $0.90 |

---

## 💡 Discount Logic

```
If totalGeneral > $50.00:
    discount   = totalGeneral × 0.05
    finalTotal = totalGeneral − discount

    → Three values are displayed: total before discount,
      amount saved, and total to pay.

If totalGeneral ≤ $50.00:
    → Only the total to pay is displayed.
```

---

## 🤝 Contributors

| Name | Role |
|---|---|
| [Your name] | Lead developer |
| Claude (Anthropic) | Code review & bug-fixing assistant |

---

## 👩‍💻 Author

Developed as an academic Java programming project.  
Course: Introduction to Programming · 2025
*Code revised and optimized with assistance from Claude (claude.ai) — Anthropic, 2025.*







---ESPAÑOL---
# 🛒 Supermercado — Sistema de Compras en Java

> Simulación interactiva de caja registradora para un supermercado, desarrollada en Java puro con validaciones robustas, carrito de compras dinámico y generación de factura.

---

## 📸 Ejemplo de ejecución

```
Ingrese el nombre del cliente: María

Productos disponibles:
  Aceite     - $3.80
  Arroz      - $3.25
  Azucar     - $1.20
  ...

Producto: Leche
Cantidad: 3
Subtotal: $4.50

¿Desea agregar otro producto? (S/N): N

----- FACTURA -----
Cliente: María

Leche      | Cantidad:   3 | Subtotal: $4.50
Pan        | Cantidad:   2 | Subtotal: $4.00

Total a pagar: $8.50
```

---

## ✨ Características

| Característica | Detalle |
|---|---|
| 📦 Catálogo de productos | 10 productos con precios, ordenados alfabéticamente |
| 🛍️ Carrito dinámico | Agrega productos sin límite usando `ArrayList` |
| ✅ Validación de producto | Búsqueda sin distinción de mayúsculas/minúsculas |
| 🔢 Validación de cantidad | Solo acepta enteros mayores que cero |
| 🏷️ Subtotal automático | Calculado por producto y acumulado al total |
| 💳 Factura detallada | Resumen completo al finalizar la compra |
| 💰 Descuento automático | 5 % de descuento si el total supera los $50.00 |

---

## 🗂️ Estructura del proyecto

```
Supermercado/
│
├── Supermercado.java      ← Clase principal con el flujo del programa
└── ItemCarrito.java       ← Clase auxiliar para cada ítem del carrito
                             (nombre, cantidad, subtotal)
```

> **Nota:** Ambas clases están en el mismo archivo `.java` para simplificar la entrega.

---

## 🚀 Cómo ejecutar

### Requisitos
- Java 8 o superior instalado
- Terminal / símbolo del sistema

### Pasos

```bash
# 1. Compilar
javac Supermercado.java

# 2. Ejecutar
java Supermercado
```

---

## 🧠 Conceptos aplicados

```
Variables           →  nombre, cantidad, precioUnitario, totalGeneral
Tipos de datos      →  String, int, double
Operadores          →  +  -  *  /  >  ==  !=  &&  !
Estructuras         →  if / else if / else
Ciclos              →  while
Colecciones         →  TreeMap (catálogo), ArrayList (carrito)
Clases              →  ItemCarrito con constructor y atributos públicos
Entrada de datos    →  Scanner con nextLine() para evitar errores de buffer
```

---

## 🛠️ Correcciones aplicadas

### 🔴 Errores críticos resueltos

**1. Variable `finalProducto` inexistente**
La variable usada en la búsqueda del catálogo no había sido declarada, lo que causaba un error de compilación inmediato.

```java
// ❌ Antes — no compila
if (key.equalsIgnoreCase(finalProducto)) { ... }

// ✅ Después — variable correctamente declarada antes del bucle
String entrada = scanner.nextLine().trim();
if (key.equalsIgnoreCase(entrada)) { ... }
```

**2. Buffer de `Scanner` inconsistente**
El uso de `nextInt()` dejaba el salto de línea `\n` en el buffer. Si el usuario ingresaba texto, la excepción no era manejada correctamente y el programa podía entrar en un bucle infinito.

```java
// ❌ Antes — propenso a bucles infinitos con entrada inválida
if (scanner.hasNextInt()) {
    cantidad = scanner.nextInt();
} else {
    scanner.next(); // limpieza parcial
}

// ✅ Después — lectura total de la línea + parseo seguro
String lineaCantidad = scanner.nextLine().trim();
try {
    cantidad = Integer.parseInt(lineaCantidad);
} catch (NumberFormatException e) {
    System.out.println("Error: ingrese un número entero válido.");
}
```

**3. `scanner.nextLine()` residual eliminado**
Existía una llamada extra a `nextLine()` para consumir el salto de línea de `nextInt()`. Al migrar a lectura completa por línea, esa llamada extra omitía la siguiente pregunta al usuario.

---

### 🟡 Optimizaciones

- **`HashMap` → `TreeMap`:** los productos ahora se muestran siempre en orden alfabético, sin importar el orden de inserción.
- **Validación de nombre vacío:** el programa no acepta un nombre de cliente en blanco.
- **Validación de entrada de producto vacía:** muestra un mensaje claro si el usuario presiona Enter sin escribir nada.
- **Formato de tabla con `%-10s`:** las columnas de la lista de productos quedan alineadas y son más fáciles de leer.
- **`\n` → `%n`:** salto de línea multiplataforma compatible con Windows, Linux y macOS.
- **Mensajes de error descriptivos:** cada error indica exactamente qué fue lo que se ingresó incorrectamente.

---

## 📋 Catálogo de productos

| Producto | Precio |
|---|---|
| Aceite | $3.80 |
| Arroz | $3.25 |
| Azucar | $1.20 |
| Cafe | $6.00 |
| Frijoles | $2.50 |
| Huevos | $4.00 |
| Leche | $1.50 |
| Pan | $2.00 |
| Queso | $5.50 |
| Sal | $0.90 |

---

## 💡 Lógica del descuento

```
Si totalGeneral > $50.00:
    descuento  = totalGeneral × 0.05
    totalFinal = totalGeneral − descuento

    → Se muestran los tres valores: total sin descuento,
      monto descontado y total a pagar.

Si totalGeneral ≤ $50.00:
    → Se muestra solo el total a pagar directamente.
```

---

## 👩‍💻 Autor

Desarrollado como proyecto académico de programación en Java.  
Curso: Introducción a la Programación · 2025
---
*Código revisado y optimizado con asistencia de Claude (claude.ai) — Anthropic, 2025.* 
