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
    void findClass() {
        spec.findClass();
    }

    @Test
    void testClass() {
        spec.assertClass();
    }

    @ParameterizedTest
    @MethodSource("provideForTestFields")
    void testFields(FieldSpec spec) {
        spec.assertField();
    }

    Stream<Arguments> provideForTestFields() {
        return spec.provideFieldSpecs();
    }
}
