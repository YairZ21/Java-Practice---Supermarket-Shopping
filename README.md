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
