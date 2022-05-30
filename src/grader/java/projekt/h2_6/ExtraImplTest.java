package projekt.h2_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.spec.SpecTester;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.util.function.Consumer;

@TestForSubmission("projekt")
public class ExtraImplTest extends ClassSpecTestCase {

    ExtraImplTest() {
        spec.requireClass(ClassName.EXTRA_IMPL)
            .requireImplements(ClassName.EXTRA)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG);

        spec.requireField("name")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(String.class);

        spec.requireField("priority")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(int.class);

        spec.requireField("configMutator")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(Consumer.class);
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
