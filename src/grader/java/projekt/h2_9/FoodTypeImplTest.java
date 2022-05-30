package projekt.h2_9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.spec.SpecTester;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.util.List;

@TestForSubmission("projekt")
public class FoodTypeImplTest extends ClassSpecTestCase {

    FoodTypeImplTest() {
        spec.requireClass(ClassName.FOOD_TYPE_IMPL)
            .requireImplements(ClassName.FOOD_TYPE)
            .requireTypeParameterBoundBy(ClassName.FOOD)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG);

        spec.requireField("name")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(String.class);

        spec.requireField("compatibleExtras")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(List.class);

        spec.requireField("foodVariants")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(List.class);
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
