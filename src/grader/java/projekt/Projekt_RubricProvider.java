package projekt;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import projekt.h1_1.LocationTests;
import projekt.h1_2.ChessboardDistanceCalculatorTests;
import projekt.h1_2.DistanceCalculatorTests;
import projekt.h1_2.EuclideanDistanceCalculatorTests;
import projekt.h1_2.ManhattanDistanceCalculatorTests;
import projekt.h1_3.TimeIntervalTest;
import projekt.h2_1.IceCreamTest;
import projekt.h2_1.PastaTest;
import projekt.h2_1.PizzaTest;
import projekt.h2_1.SaucableTest;
import projekt.h2_10.FoodTypesTest;
import projekt.h2_11.FoodBuilderTest;
import projekt.h2_2.IceCreamConfigTest;
import projekt.h2_2.PastaConfigTest;
import projekt.h2_2.PizzaConfigTest;
import projekt.h2_2.SaucableConfigTest;
import projekt.h2_3.IceCreamVariantTest;
import projekt.h2_3.PastaVariantTest;
import projekt.h2_3.PizzaVariantTest;
import projekt.h2_3.SaucableVariantTest;
import projekt.h2_4.IceCreamImplTest;
import projekt.h2_4.PastaImplTest;
import projekt.h2_4.PizzaImplTest;
import projekt.h2_5.IceCreamImplConfigTest;
import projekt.h2_5.PastaImplConfigTest;
import projekt.h2_5.PizzaImplConfigTest;
import projekt.h2_6.ExtraImplTest;
import projekt.h2_8.ExtraTest;
import projekt.h2_9.FoodTypeImplTest;
import projekt.spec.SpecTester;

@RubricForSubmission("projekt")
public class Projekt_RubricProvider implements RubricProvider {

    public static final Criterion H1_1_1 = Criterion.builder()
        .shortDescription("Die Klasse Location existiert und der Konstruktor ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testInstance", Integer.class, Integer.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1_2 = Criterion.builder()
        .shortDescription("Die Klasse Location ist vollst??ndig korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testGetters", Integer.class, Integer.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testAdd", Integer.class, Integer.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> LocationTests.class.getMethod("testSub", Integer.class, Integer.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1 - Location")
        .addChildCriteria(H1_1_1, H1_1_2)
        .build();

    public static final Criterion H1_2_1 = Criterion.builder()
        .shortDescription("Das Interface DistanceCalculator existiert und die Methoden sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> DistanceCalculatorTests.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2_2 = Criterion.builder()
        .shortDescription("Die Implementation EuclideanDistanceCalculator ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> EuclideanDistanceCalculatorTests.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> EuclideanDistanceCalculatorTests.class.getMethod("testInstance")))
            .requirePass(JUnitTestRef.ofMethod(() -> EuclideanDistanceCalculatorTests.class.getMethod("testCalculateDistance", int.class, int.class, int.class, int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2_3 = Criterion.builder()
        .shortDescription("Die Implementation ManhattanDistanceCalculator ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ManhattanDistanceCalculatorTests.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> ManhattanDistanceCalculatorTests.class.getMethod("testInstance")))
            .requirePass(JUnitTestRef.ofMethod(() -> ManhattanDistanceCalculatorTests.class.getMethod("testCalculateDistance", int.class, int.class, int.class, int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2_4 = Criterion.builder()
        .shortDescription("Die Implementation ChessboardDistanceCalculator ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ChessboardDistanceCalculatorTests.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> ChessboardDistanceCalculatorTests.class.getMethod("testInstance")))
            .requirePass(JUnitTestRef.ofMethod(() -> ChessboardDistanceCalculatorTests.class.getMethod("testCalculateDistance", int.class, int.class, int.class, int.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 - DistanceCalculator")
        .addChildCriteria(H1_2_1, H1_2_2, H1_2_3, H1_2_4)
        .build();

    public static final Criterion H1_3_1 = Criterion.builder()
        .shortDescription("Die Klasse TimeInterval existiert und die Attribute + Konstruktor ist korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> TimeIntervalTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> TimeIntervalTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> TimeIntervalTest.class.getMethod("testConstructors", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_3_2 = Criterion.builder()
        .shortDescription("Die Logik des Konstruktors ist richtig")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3 - TimeInterval")
        .addChildCriteria(H1_3_1, H1_3_2)
        .build();

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1")
        .addChildCriteria(H1_1, H1_2, H1_3)
        .build();

    public static final Criterion H2_1_1 = Criterion.builder()
        .shortDescription("Das Interface Saucable und Methode getSauce sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1_2 = Criterion.builder()
        .shortDescription("Das Interface Pizza und Methode getDiameter sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1_3 = Criterion.builder()
        .shortDescription("Das Interface Pasta und Methode getThickness sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PastaTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1_4 = Criterion.builder()
        .shortDescription("Das Interface IceCream und Methode getFlavor sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1 - Food Interfaces")
        .addChildCriteria(H2_1_1, H2_1_2, H2_1_3, H2_1_4)
        .build();

    public static final Criterion H2_2_1 = Criterion.builder()
        .shortDescription("Die Interfaces **.Config sind alle korrekt definiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaConfigTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaConfigTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamConfigTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableConfigTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .maxPoints(2)
        .build();

    public static final Criterion H2_2_2 = Criterion.builder()
        .shortDescription("Die Methoden **.Config#**(T) sind alle korrekt definiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaConfigTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaConfigTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamConfigTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableConfigTest.class.getMethod("testMethods")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .maxPoints(2)
        .build();

    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("H2.2 - Food.Config Interfaces")
        .addChildCriteria(H2_2_1, H2_2_2)
        .build();

    public static final Criterion H2_3_1 = Criterion.builder()
        .shortDescription("Die Interfaces **.Variant existieren alle")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaVariantTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaVariantTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamVariantTest.class.getMethod("testDefinition")))
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableVariantTest.class.getMethod("testDefinition")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_3_2 = Criterion.builder()
        .shortDescription("Die Methoden **.Variant#**() sind alle korrekt definiert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaVariantTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaVariantTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamVariantTest.class.getMethod("testMethods")))
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableVariantTest.class.getMethod("testMethods")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_3_3 = Criterion.builder()
        .shortDescription("Die Interfaces **.Variant sind korrekt deklariert, insbesondere korrekt abgeleitet")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaVariantTest.class.getMethod("testDerivation")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaVariantTest.class.getMethod("testDerivation")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamVariantTest.class.getMethod("testDerivation")))
            .requirePass(JUnitTestRef.ofMethod(() -> SaucableVariantTest.class.getMethod("testDerivation")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_3 = Criterion.builder()
        .shortDescription("H2.3 - Food.Variant Interfaces")
        .addChildCriteria(H2_3_1, H2_3_2, H2_3_3)
        .build();

    public static final Criterion H2_4_1 = Criterion.builder()
        .shortDescription("F??r jedes Interface existiert eine implementierende Klasse")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplTest.class.getMethod("testClass")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4_2 = Criterion.builder()
        .shortDescription("F??r jede Klasse existiert ein sinnvoller Konstruktor (ohne Pr??fung Generics)")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_4_3 = Criterion.builder()
        .shortDescription("Gemeinsame Eigenschafen (price, weight, variant, extras) und Getter-Methoden sind korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplTest.class.getMethod("testFields", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_4_4 = Criterion.builder()
        .shortDescription("Nicht-gemeinsame Eigenschaften und Getter-Methoden sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_4_5 = Criterion.builder()
        .shortDescription("Die Aufgabe ist vollst??ndig korrekt umgesetzt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_4 = Criterion.builder()
        .shortDescription("H2.4 - Food Implementationen")
        .addChildCriteria(H2_4_1, H2_4_2, H2_4_3, H2_4_4, H2_4_5)
        .build();

    public static final Criterion H2_5_1 = Criterion.builder()
        .shortDescription("F??r jede Food-Klasse existiert die jeweilige Config-Implementation")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplConfigTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplConfigTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplConfigTest.class.getMethod("testMethods", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> IceCreamImplConfigTest.class.getMethod("testConstructors", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplConfigTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplConfigTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplConfigTest.class.getMethod("testMethods", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PizzaImplConfigTest.class.getMethod("testConstructors", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplConfigTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplConfigTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplConfigTest.class.getMethod("testMethods", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> PastaImplConfigTest.class.getMethod("testConstructors", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_5_2 = Criterion.builder()
        .shortDescription("Es gibt einen sinnvollen Weg, um die aktuellen Mutators zu speichern")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_5_3 = Criterion.builder()
        .shortDescription("get**() ohne Modifikation liefert Identity zur??ck")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_5_4 = Criterion.builder()
        .shortDescription("Setter setzt Attribut (muss nur f??r erste Zuweisung funktionieren)")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_5_5 = Criterion.builder()
        .shortDescription("get**() mit einmaliger Modifikation liefert gegebene Modifikationsfunktion zur??ck")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_5_6 = Criterion.builder()
        .shortDescription("get**() und Setter-Methoden funktionieren immer korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_5 = Criterion.builder()
        .shortDescription("H2.5 - Food.Config Implementationen")
        .addChildCriteria(H2_5_1, H2_5_2, H2_5_3, H2_5_4, H2_5_5, H2_5_6)
        .build();

    public static final Criterion H2_6_1 = Criterion.builder()
        .shortDescription("Die Klasse ExtraImpl ist korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraImplTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraImplTest.class.getMethod("testFields", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraImplTest.class.getMethod("testMethods", SpecTester.class)))
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraImplTest.class.getMethod("testConstructors", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_6_2 = Criterion.builder()
        .shortDescription("Der konstruktor von ExtraImpl is korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_6_3 = Criterion.builder()
        .shortDescription("Die Attribute und Getter-Methoden von ExtraImpl sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_6 = Criterion.builder()
        .shortDescription("H2.6 - ExtraImpl")
        .addChildCriteria(H2_6_1, H2_6_2, H2_6_3)
        .build();

    public static final Criterion H2_7_1 = Criterion.builder()
        .shortDescription(">= 4 Attribute sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_7_2 = Criterion.builder()
        .shortDescription(">= 8 Attribute und ALL sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_7_3 = Criterion.builder()
        .shortDescription("Die eigene Extras sind valide")
        .hiddenNotes("Min. 2, Generics, Sind in ALL enthalten")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_7 = Criterion.builder()
        .shortDescription("H2.7 - Extras")
        .addChildCriteria(H2_7_1, H2_7_2, H2_7_3)
        .build();

    public static final Criterion H2_8_1 = Criterion.builder()
        .shortDescription("Die Methode Extra.writeToConfig ist korrekt deklariert")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_8_2 = Criterion.builder()
        .shortDescription("Die Sortierung erfolgt nach dem Attribut 'priority'")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraTest.class.getMethod("testSortByPriorityOnly")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_8_3 = Criterion.builder()
        .shortDescription("Die Sortierung erfolgt zun??chst nach Attribut 'priority', dann nach Attribut 'name'")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraTest.class.getMethod("testSortByPriorityOnly")))
            .requirePass(JUnitTestRef.ofMethod(() -> ExtraTest.class.getMethod("testSortComplete")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_8 = Criterion.builder()
        .shortDescription("H2.8 - Extra.writeToConfig")
        .addChildCriteria(H2_8_1, H2_8_2, H2_8_3)
        .build();

    public static final Criterion H2_9_1 = Criterion.builder()
        .shortDescription("Die Klasse FoodTypeImpl und Attribute sind korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypeImplTest.class.getMethod("testClass")))
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypeImplTest.class.getMethod("testFields", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_9_2 = Criterion.builder()
        .shortDescription("Der Konstruktur und Getter-Methoden sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_9_3 = Criterion.builder()
        .shortDescription("Die Methoden 'addFoodVariant' und 'getFoodVariants' sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_9 = Criterion.builder()
        .shortDescription("H2.9 - FoodTypeImpl")
        .addChildCriteria(H2_9_1, H2_9_2, H2_9_3)
        .build();

    public static final Criterion H2_10_1 = Criterion.builder()
        .shortDescription("Die Konstanten 'PIZZA', 'PASTA' und 'ICE_CREAM' sind korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("pizzaTest")))
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("pastaTest")))
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("iceCreamTest")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_10_2 = Criterion.builder()
        .shortDescription("Die Food-Variants sind richtig gesetzt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("pizzaVariantsTest")))
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("pastaVariantsTest")))
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("iceCreamVariantsTest")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_10_3 = Criterion.builder()
        .shortDescription("Die Konstante 'ALL' ist korrekt")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodTypesTest.class.getMethod("allTest")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_10 = Criterion.builder()
        .shortDescription("H2.10 - FoodTypes")
        .addChildCriteria(H2_10_1, H2_10_2, H2_10_3)
        .build();

    public static final Criterion H2_11_1 = Criterion.builder()
        .shortDescription("Das Interface FoodBuilder ist korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodBuilderTest.class.getMethod("testClass")))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_11_2 = Criterion.builder()
        .shortDescription("Die Methode 'build' is korrekt deklariert")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofMethod(() -> FoodBuilderTest.class.getMethod("testMethods", SpecTester.class)))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2_11 = Criterion.builder()
        .shortDescription("H2.11 - FoodBuilder")
        .addChildCriteria(H2_11_1, H2_11_2)
        .build();

    public static final Criterion H2_12_1 = Criterion.builder()
        .shortDescription("'PizzaImpl.Variant' ist korrekt au??er 'create'")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12_2 = Criterion.builder()
        .shortDescription("'PizzaImpl.Variant.create' ist korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12_3 = Criterion.builder()
        .shortDescription("'PastaImpl.Variant' ist korrekt au??er 'create'")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12_4 = Criterion.builder()
        .shortDescription("'PastaImpl.Variant.create' ist korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12_5 = Criterion.builder()
        .shortDescription("'IceCreamImpl.Variant' ist korrekt au??er 'create'")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12_6 = Criterion.builder()
        .shortDescription("'IceCreamImpl.Variant.create' ist korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_12 = Criterion.builder()
        .shortDescription("H2.12 - **Impl.Variant")
        .addChildCriteria(H2_12_1, H2_12_2, H2_12_3, H2_12_4, H2_12_5, H2_12_6)
        .build();

    public static final Criterion H2_13_1 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ Pizza sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_13_2 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ Pasta sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_13_3 = Criterion.builder()
        .shortDescription("Die Gerichte vom Typ IceCream sind korrekt")
        .grader(Grader.descendingPriority())
        .build();

    public static final Criterion H2_13 = Criterion.builder()
        .shortDescription("H2.13 - Speisekarte")
        .addChildCriteria(H2_13_1, H2_13_2, H2_13_3)
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2")
        .addChildCriteria(H2_1, H2_2, H2_3, H2_4, H2_5, H2_6, H2_7, H2_8, H2_9, H2_10, H2_11, H2_12, H2_13)
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
