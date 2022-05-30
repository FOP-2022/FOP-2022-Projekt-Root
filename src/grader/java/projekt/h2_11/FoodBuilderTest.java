package projekt.h2_11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.spec.SpecTester;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class FoodBuilderTest extends ClassSpecTestCase {

    FoodBuilderTest() {
        spec.requireClass(ClassName.FOOD_BUILDER)
            .requireTypeParameterBoundBy(ClassName.FOOD)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG)
            .requireTypeParameterBoundBy(ClassName.FOOD_VARIANT);

        spec.requireMethod("build(C, V, java.util.List<? extends projekt.food.Extra<? super C>>)")
            .requireType(Object.class)
            .requireType("F");
    }

    @Test
    @Override
    public void testClass() {
        super.testClass();
    }

    @ParameterizedTest
    @MethodSource("provideForTestFields")
    @Override
    public void testFields(final SpecTester tester) {
        super.testFields(tester);
    }

    @ParameterizedTest
    @MethodSource("provideForTestMethods")
    @Override
    public void testMethods(final SpecTester tester) {
        super.testMethods(tester);
    }

    @ParameterizedTest
    @MethodSource("provideForTestConstructors")
    @Override
    public void testConstructors(final SpecTester tester) {
        super.testConstructors(tester);
    }
}
