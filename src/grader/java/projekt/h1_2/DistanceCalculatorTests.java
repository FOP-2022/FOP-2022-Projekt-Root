package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertClassHasModifiers;
import static projekt.utils.TutorAssertions.assertClassNotGeneric;
import static projekt.utils.TutorAssertions.assertMethod;

@DisplayName("projekt.base.DistanceCalculator tests")
public class DistanceCalculatorTests extends TestClass {

    public static final String METHOD_CALCULATE_DISTANCE_SIGNATURE = "calculateDistance(%s, %s)"
        .formatted(ClassName.LOCATION, ClassName.LOCATION);

    public DistanceCalculatorTests() {
        super(
            ClassName.DISTANCE_CALCULATOR,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_CALCULATE_DISTANCE_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassNotGeneric(clazz);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_CALCULATE_DISTANCE_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}
