package projekt.spec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import projekt.spec.ClassSpec;
import projekt.spec.FieldSpec;

import java.util.stream.Stream;

public abstract class ClassSpecTestCase {

    protected final static ClassSpec spec = new ClassSpec();

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

    static Stream<Arguments> provideForTestFields() {
        return spec.provideFieldSpecs();
    }
}
