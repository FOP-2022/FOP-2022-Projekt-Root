package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;

import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertClassHasModifiers;
import static projekt.utils.TutorAssertions.assertMethod;

@DisplayName("projekt.base.DistanceCalculator tests")
public class DistanceCalculatorTests extends TestClass {

    public static final String METHOD_CALCULATE_DISTANCE_SIGNATURE = "calculateDistance(%s, %s)"
        .formatted("projekt.base.Location", "projekt.base.Location"); // TODO: replace string literals

    public DistanceCalculatorTests() {
        super(
            "projekt.base.DistanceCalculator",
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_CALCULATE_DISTANCE_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_CALCULATE_DISTANCE_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}
