package projekt.h2_5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.spec.SpecTester;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class IceCreamImplConfigTest extends ClassSpecTestCase {

    protected IceCreamImplConfigTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL_CONFIG)
            .requireImplements(ClassName.ICE_CREAM_CONFIG);
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
