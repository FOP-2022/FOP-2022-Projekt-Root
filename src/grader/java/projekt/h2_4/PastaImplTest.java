package projekt.h2_4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.SpecTester;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

@TestForSubmission("projekt")
public class PastaImplTest extends AbstractFoodImplTest {

    PastaImplTest() {
        spec.requireClass(ClassName.PASTA_IMPL)
            .requireImplements(ClassName.PASTA);

        spec.requireField("thickness")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
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
