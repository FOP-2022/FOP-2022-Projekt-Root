package projekt.spec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ClassSpecTestCase {

    protected final ClassSpec spec = new ClassSpec();

    @BeforeEach
    public void findClass() {
        spec.findClass();
    }

    @Test
    public void testClass() {
        spec.assertClass();
    }

    @ParameterizedTest
    @MethodSource("provideForTestFields")
    public void testFields(SpecTester tester) {
        tester.runTest();
    }

    Stream<Arguments> provideForTestFields() {
        return spec.provideFieldTesters();
    }

    @ParameterizedTest
    @MethodSource("provideForTestMethods")
    public void testMethods(SpecTester tester) {
        tester.runTest();
    }

    Stream<Arguments> provideForTestMethods() {
        return spec.provideMethodTesters();
    }

    @ParameterizedTest
    @MethodSource("provideForTestConstructors")
    public void testConstructors(SpecTester tester) {
        tester.runTest();
    }

    Stream<Arguments> provideForTestConstructors() {
        return spec.provideConstructorTesters();
    }
}
