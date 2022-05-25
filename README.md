# FOP Projekt 2022

## Bewertungsschema samt Tests

### H1.1

- [X] Klasse existiert + Konstruktor
- [X] Rest (Methode existieren und sind richtig)

### H1.2

- [X] Interface `DistanceCalculator` existiert und Methoden sind korrekt deklariert
- [X] Impl 1 ist korrekt
- [X] Impl 2 ist korrekt
- [X] Impl 3 ist korrekt

### H1.3

- [ ] Klasse existiert+Attribute korrekt+Konstruktor korrekt deklariert
- [ ] Logik des Konstruktors richtig

    1. beide nicht null,
    1. `start <= end`
    1. Exception vollständig korrekt
    1. Attribute korrekt zugewiesen
    1. drei Getter-Methoden funktionieren korrekt

### H2.1

- [X] Interface `Sauceable` und Methode `getSauce` korrekt deklariert
- [X] Interface `Pizza` und Methode `getDiameter` korrekt deklariert
- [X] Interface `Pasta` und Methode `getThickness` korrekt deklariert
- [X] Interface `IceCream` und Methode `getFlavor` korrekt deklariert

### H2.2

- [X] Interfaces korrekt deklariert
- [X] ^ x2
- [X] Methoden korrekt deklariert
- [X] ^ x2

### H2.3

- [X] Alle Interfaces existieren ggf. Nachkorrektur
- [X] Alle Methoden sind korrekt deklariert
- [ ] Alle Interfaces sind korrekt deklariert, insbesondere korrekt abgeleitet (Generics!)

### H2.4

- [X] Für jedes Interface existiert eine implementierende Klasse
- [ ] Für jede Klasse existiert ein sinnvoller Konstruktor (ohne Prüfung Generics)
- [X] gemeinsame Eigenschaften: `price`, `weight`, `variant`, `extras` + Methoden
- [X] Nicht-gemeinsame Eigenschaften richtig + Methoden
- [ ] Die Aufgabe ist vollständig korrekt umgesetzt ggf. Ersetzen

### H2.5

- [X] Für jede `Food`-Klasse existiert die jeweilige `Config`-Implementation
- [ ] Es gibt einen sinnvollen Weg, um die aktuellen Mutators zu speichern
- [ ] `get{…}` ohne Modifikation liefert Identity zurück
- [ ] Setter-Methode setzt Attribut (muss nur für erste Zuweisung funktionieren)
- [ ] `get{…}` mit einmaliger Modifikation liefert gegebene Modifikationsfunktion zurück
- [ ] `Config`-Methoden funktionieren immer korrekt

### H2.6

- [ ] Klasse + Generics
- [ ] Constructor
- [ ] Attribute + Getter

### H2.7

- [ ] >= 4 richtig
- [ ] >= 8 richtig und `ALL` richtig
- [ ] Eigene Extras

    1. min. 2
    1. Generics
    1. Sind in `ALL` enthalten

### H2.8 - Alex

- [ ] Korrekt deklariert
- [ ] Sortierung nach `priority`
- [ ] Sortierung genauer nach `name`

### H2.9

- [ ] Korrekt deklarierte Klasse und Attribute
- [ ] Konstruktor und Getter
- [ ] `addFoodVariant` und `getFoodVariants` korrekt

### H2.10 - Alex

- [ ] Konstanten `PIZZA`, `PASTA` und `ICE_CREAM` sind korrekt (dec. + impl.)
- [ ] Food Variants sind richtig gesetzt
- [ ] Konstanten `ALL` korrekt (dec. + impl.)

### H2.11

- [ ] Klasse korrekt deklariert
- [ ] Methode korrekt deklariert

### H2.12 - Alex

- [ ] `PizzaImpl.Variant` korrekt außer `create`
- [ ] `PizzaImpl.Variant.create`
- [ ] `PastaImpl.Variant` korrekt außer `create`
- [ ] `PastaImpl.Variant.create`
- [ ] `IceCreamImpl.Variant` korrekt außer `create`
- [ ] `IceCreamImpl.Variant.create`

### H2.13

- [ ] Gerichte vom Typ Pizza
- [ ] Gerichte vom Typ Pasta
- [ ] Gerichte vom Typ Ice Cream
