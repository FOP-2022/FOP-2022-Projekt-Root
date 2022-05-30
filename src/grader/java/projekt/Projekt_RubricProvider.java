package projekt;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import projekt.h1_1.LocationTests;

@RubricForSubmission("projekt")
public class Projekt_RubricProvider implements RubricProvider {

    public static final Criterion H1_1_1 = Criterion.builder()
        .shortDescription("Die Klasse Location existiert und der Konstruktor ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1_2 = Criterion.builder()
        .shortDescription("Die Klasse Location ist vollständig korrekt")
        .build();

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1")
        .addChildCriteria(H1_1_1, H1_1_2)
        .build();

    public static final Criterion H1_2_1 = Criterion.builder()
        .shortDescription("Das Interface DistanceCalculator existiert und die Methoden sind korrekt deklariert")
        .build();

    public static final Criterion H1_2_2 = Criterion.builder()
        .shortDescription("Die Implementation EuclideanDistanceCalculator ist korrekt")
        .build();

    public static final Criterion H1_2_3 = Criterion.builder()
        .shortDescription("Die Implementation ManhattanDistanceCalculator ist korrekt")
        .build();

    public static final Criterion H1_2_4 = Criterion.builder()
        .shortDescription("Die Implementation ChessboardDistanceCalculator ist korrekt")
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2")
        .addChildCriteria(H1_2_1, H1_2_2, H1_2_3, H1_2_4)
        .build();

    public static final Criterion H1_3_1 = Criterion.builder()
        .shortDescription("Die Klasse TimeInterval existiert und die Attribute + Konstruktor ist korrekt deklariert")
        .build();

    public static final Criterion H1_3_2 = Criterion.builder()
        .shortDescription("Die Logik des Konstruktors ist richtig")
        .build();

    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3")
        .addChildCriteria(H1_3_1, H1_3_2)
        .build();

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1")
        .addChildCriteria(H1_1, H1_2, H1_3)
        .build();

    public static final Criterion H2_1_1 = Criterion.builder()
        .shortDescription("Das Interface Saucable und Methode getSauce sind korrekt deklariert")
        .build();

    public static final Criterion H2_1_2 = Criterion.builder()
        .shortDescription("Das Interface Pizza und Methode getDiameter sind korrekt deklariert")
        .build();

    public static final Criterion H2_1_3 = Criterion.builder()
        .shortDescription("Das Interface Pasta und Methode getThickness sind korrekt deklariert")
        .build();

    public static final Criterion H2_1_4 = Criterion.builder()
        .shortDescription("Das Interface IceCream und Methode getFlavor sind korrekt deklariert")
        .build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1")
        .addChildCriteria(H2_1_1, H2_1_2, H2_1_3, H2_1_4)
        .build();

    public static final Criterion H2_2_1 = Criterion.builder()
        .shortDescription("Die Interfaces **.Config sind alle korrekt definiert")
        .maxPoints(2)
        .build();

    public static final Criterion H2_2_2 = Criterion.builder()
        .shortDescription("Die Methoden **.Config#**(T) sind alle korrekt definiert")
        .maxPoints(2)
        .build();

    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2")
        .addChildCriteria(H2_2_1, H2_2_2)
        .build();

    public static final Criterion H2_3_1 = Criterion.builder()
        .shortDescription("Die Interfaces **.Variant existieren alle")
        .build();

    public static final Criterion H2_3_2 = Criterion.builder()
        .shortDescription("Die Methoden **.Variant#**() sind alle korrekt definiert")
        .build();

    public static final Criterion H2_3_3 = Criterion.builder()
        .shortDescription("Die Interfaces **.Variant sind korrekt deklariert, insbesondere korrekt abgeleitet")
        .build();

    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3")
        .addChildCriteria(H2_3_1, H2_3_2, H2_3_3)
        .build();

    public static final Criterion H2_4_1 = Criterion.builder()
        .shortDescription("Für jedes Interface existiert eine implementierende Klasse")
        .build();

    public static final Criterion H2_4_2 = Criterion.builder()
        .shortDescription("Für jede Klasse existiert ein sinnvoller Konstruktor (ohne Prüfung Generics)")
        .build();

    public static final Criterion H2_4_3 = Criterion.builder()
        .shortDescription("Gemeinsame Eigenschafen (price, weight, variant, extras) und Getter-Methoden sind korrekt")
        .build();

    public static final Criterion H2_4_4 = Criterion.builder()
        .shortDescription("Nicht-gemeinsame Eigenschaften und Getter-Methoden sind korrekt")
        .build();

    public static final Criterion H2_4_5 = Criterion.builder()
        .shortDescription("Die Aufgabe ist vollständig korrekt umgesetzt")
        .build();

    public static final Criterion H2_4 = Criterion.builder()
        .shortDescription("H2.4")
        .addChildCriteria(H2_4_1, H2_4_2, H2_4_3, H2_4_4, H2_4_5)
        .build();

    public static final Criterion H2_5_1 = Criterion.builder()
        .shortDescription("Für jede Food-Klasse existiert die jeweilige Config-Implementation")
        .build();

    public static final Criterion H2_5_2 = Criterion.builder()
        .shortDescription("Es gibt einen sinnvollen Weg, um di eaktuellen Mutators zu speichern")
        .build();

    public static final Criterion H2_5_3 = Criterion.builder()
        .shortDescription("get**() ohne Modifikation liefert Identity zurück")
        .build();

    public static final Criterion H2_5_4 = Criterion.builder()
        .shortDescription("Setter setzt Attribut (muss nur für erste Zuweisung funktionieren)")
        .build();

    public static final Criterion H2_5_5 = Criterion.builder()
        .shortDescription("get**() mit einmaliger Modifikation liefert gegebene Modifikationsfunktion zurück")
        .build();

    public static final Criterion H2_5_6 = Criterion.builder()
        .shortDescription("get**() und Setter-Methoden funktionieren immer korrekt")
        .build();

    public static final Criterion H2_5 = Criterion.builder()
        .shortDescription("H2.5")
        .addChildCriteria(H2_5_1, H2_5_2, H2_5_3, H2_5_4, H2_5_5, H2_5_6)
        .build();

    public static final Criterion H2_6_1 = Criterion.builder()
        .shortDescription("Die Klasse ExtraImpl ist korrekt deklariert")
        .build();

    public static final Criterion H2_6_2 = Criterion.builder()
        .shortDescription("Der konstruktor von ExtraImpl is korrekt")
        .build();

    public static final Criterion H2_6_3 = Criterion.builder()
        .shortDescription("Die Attribute und Getter-Methoden von ExtraImpl sind korrekt")
        .build();

    public static final Criterion H2_6 = Criterion.builder()
        .shortDescription("H2.6")
        .addChildCriteria(H2_6_1, H2_6_2, H2_6_3)
        .build();

    public static final Criterion H2_7_1 = Criterion.builder()
        .shortDescription(">= 4 Attribute sind korrekt")
        .build();

    public static final Criterion H2_7_2 = Criterion.builder()
        .shortDescription(">= 8 Attribute und ALL sind korrekt")
        .build();

    public static final Criterion H2_7_3 = Criterion.builder()
        .shortDescription("Die eigene Extras sind valide")
        .hiddenNotes("Min. 2, Generics, Sind in ALL enthalten")
        .build();

    public static final Criterion H2_7 = Criterion.builder()
        .shortDescription("H2.7")
        .addChildCriteria(H2_7_1, H2_7_2, H2_7_3)
        .build();

    public static final Criterion H2_8_1 = Criterion.builder()
        .shortDescription("Die Methode Extra.writeToConfig ist korrekt deklariert")
        .build();

    public static final Criterion H2_8_2 = Criterion.builder()
        .shortDescription("Die Sortierung erfolgt nach dem Attribut 'priority'")
        .build();

    public static final Criterion H2_8_3 = Criterion.builder()
        .shortDescription("Die Sortierung erfolgt zunächst nach Attribut 'priority', dann nach Attribut 'name'")
        .build();

    public static final Criterion H2_8 = Criterion.builder()
        .shortDescription("H2.8 - Extra.writeToConfig")
        .addChildCriteria(H2_8_1, H2_8_2, H2_8_3)
        .build();

    public static final Criterion H2_9_1 = Criterion.builder()
        .shortDescription("Die Klasse FoodTypeImpl und Attribute sind korrekt deklariert")
        .build();

    public static final Criterion H2_9_2 = Criterion.builder()
        .shortDescription("Der Konstruktur und Getter-Methoden sind korrekt")
        .build();

    public static final Criterion H2_9_3 = Criterion.builder()
        .shortDescription("Die Methoden 'addFoodVariant' und 'getFoodVariants' sind korrekt")
        .build();

    public static final Criterion H2_9 = Criterion.builder()
        .shortDescription("H2.9 - FoodTypeImpl")
        .addChildCriteria(H2_9_1, H2_9_2, H2_9_3)
        .build();

    public static final Criterion H2_10_1 = Criterion.builder()
        .shortDescription("Die Konstanten 'PIZZA', 'PASTA' und 'ICE_CREAM' sind korrekt")
        .build();

    public static final Criterion H2_10_2 = Criterion.builder()
        .shortDescription("Die Food-Variants sind richtig gesetzt")
        .build();

    public static final Criterion H2_10_3 = Criterion.builder()
        .shortDescription("Die Konstante 'ALL' ist korrekt")
        .build();

    public static final Criterion H2_10 = Criterion.builder()
        .shortDescription("H2.10 - FoodTypes")
        .addChildCriteria(H2_10_1, H2_10_2, H2_10_3)
        .build();

    public static final Criterion H2_11_1 = Criterion.builder()
        .shortDescription("Das Interface FoodBuilder ist korrekt deklariert")
        .build();

    public static final Criterion H2_11_2 = Criterion.builder()
        .shortDescription("Die Methode 'build' is korrekt deklariert")
        .build();

    public static final Criterion H2_11 = Criterion.builder()
        .shortDescription("H2.11 - FoodBuilder")
        .addChildCriteria(H2_11_1, H2_11_2)
        .build();

    public static final Criterion H2_12_1 = Criterion.builder()
        .shortDescription("'PizzaImpl.Variant' ist korrekt außer 'create'")
        .build();

    public static final Criterion H2_12_2 = Criterion.builder()
        .shortDescription("'PizzaImpl.Variant.create' ist korrekt")
        .build();

    public static final Criterion H2_12_3 = Criterion.builder()
        .shortDescription("'PastaImpl.Variant' ist korrekt außer 'create'")
        .build();

    public static final Criterion H2_12_4 = Criterion.builder()
        .shortDescription("'PastaImpl.Variant.create' ist korrekt")
        .build();

    public static final Criterion H2_12_5 = Criterion.builder()
        .shortDescription("'IceCreamImpl.Variant' ist korrekt außer 'create'")
        .build();

    public static final Criterion H2_12_6 = Criterion.builder()
        .shortDescription("'IceCreamImpl.Variant.create' ist korrekt")
        .build();

    public static final Criterion H2_12 = Criterion.builder()
        .shortDescription("H2.12 - **Impl.Variant")
        .addChildCriteria(H2_12_1, H2_12_2, H2_12_3, H2_12_4, H2_12_5, H2_12_6)
        .build();

    public static final Criterion H2_13_1 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ Pizza sind korrekt")
        .build();

    public static final Criterion H2_13_2 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ Pasta sind korrekt")
        .build();

    public static final Criterion H2_13_3 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ IceCream sind korrekt")
        .build();

    public static final Criterion H2_13 = Criterion.builder()
        .shortDescription("H2.13 - Speisekarte")
        .addChildCriteria(H2_13_1, H2_13_2, H2_13_3)
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2")
        .addChildCriteria(H2_1, H2_2, H2_3, H2_4, H2_5, H2_6, H2_7, H2_8, H2_9, H2_10, H2_11, H2_11, H2_12, H2_13)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("FOP Projekt 21/22")
        .addChildCriteria(H1, H2)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
