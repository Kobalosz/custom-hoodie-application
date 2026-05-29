# "HOODI"

A console-based custom apparel ordering system built in Java. HOODI allows users to create custom hoodies, purchase accessories, generate receipts, and save order history through receipt persistence.

## Features

### Custom Hoodie Builder

Users can fully customize hoodies by selecting:

* Hoodie type

    * Pullover
    * Zip-up
    * Quarter zip
* Size

    * Small
    * Medium
    * Large
* Material

    * Cotton
    * Polyester
    * Heavyweight
    * Fleece
    * Wool
* Optional design customizations

    * Printed
    * Rhinestone
    * Raised
    * Embroidery
* Design placement

    * Front
    * Back
    * Left sleeve
    * Right sleeve
    * Hood

### Prebuilt Hoodie Options

For faster ordering, users can choose from curated hoodie presets instead of building a hoodie from scratch.

### Accessories

Users can also add:

* Beanies

    * Material customization
    * Optional design
* Tote bags

    * Fixed product option

### Order Management

Users can:

* Add items to an order
* Remove individual items
* View an order summary
* Clear an order
* Checkout and generate a receipt


## Project Structure

```text
src
├── models
│   ├── Hoodie
│   ├── Beanie
│   ├── ToteBag
│   ├── Order
│   └── Receipt
│
├── DTOs
│   ├── HoodieDTO
│   └── ReceiptItemDTO
│
├── enumerations
│   ├── Size
│   ├── Material
│   ├── Type
│   ├── Design
│   └── DesignLocation
│
├── screens
│   ├── HomeScreen
│   └── OrderScreen
│
├── storage
│   └── ReceiptWriter
│
├── ui
│   ├── Display
│   └── UI
│
└── tests
```

---

## OOP Concepts Demonstrated

This project demonstrates several software engineering concepts:

### Inheritance & Polymorphism

Products inherit from a shared `OrderItem` abstraction and implement pricing behavior consistently.

### Interfaces

The `Priceable` interface ensures all purchasable products implement pricing logic.

### Enums for Business Logic

Pricing modifiers and customization behavior are embedded directly into enums to reduce conditional logic and improve scalability.

### Generics

Reusable generic methods are used to dynamically handle enum-based menu selection.

### DTOs & Records

Immutable DTOs are used to separate business models from display logic.

### Streams

Streams are used for aggregation, filtering, and pricing calculations.

### Separation of Concerns

Application responsibilities are divided between:

* Models (business logic)
* Screens (workflow)
* Display/UI (rendering and input)
* Storage (receipt persistence)

---

## Running the Application


Example flow:

```text
Home Screen
    → Start Order
        → Add Hoodie / Accessories
        → Customize
        → Review Order
        → Checkout
        → Receipt Generated
```

---
