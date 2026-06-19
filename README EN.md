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

