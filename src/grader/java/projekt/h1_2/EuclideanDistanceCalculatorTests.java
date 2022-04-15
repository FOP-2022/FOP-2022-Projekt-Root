package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import projekt.utils.TestClass;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import static projekt.utils.TutorAssertions.*;

@DisplayName("projekt.base.EuclideanDistanceCalculator tests")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class EuclideanDistanceCalculatorTests extends TestClass {

    public static final String CONSTRUCTOR_SIGNATURE = "projekt.base.EuclideanDistanceCalculator()";
    public static final String METHOD_CALCULATE_DISTANCE_SIGNATURE = "calculateDistance(%s, %s)"
        .formatted("projekt.base.Location", "projekt.base.Location"); // TODO: replace string literals

    public EuclideanDistanceCalculatorTests() {
        super(
            "projekt.base.EuclideanDistanceCalculator",
            predicatesFromSignatures(CONSTRUCTOR_SIGNATURE),
            predicatesFromSignatures(METHOD_CALCULATE_DISTANCE_SIGNATURE)
        );
    }

    @Test
    @DisplayName("1 | Class and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC);
        assertFalse(Modifier.isAbstract(clazz.getModifiers()), "Class %s must not be abstract".formatted(className));
        assertClassImplements(clazz, new DistanceCalculatorTests().getTestedClass().getName());
    }

    @Test
    @DisplayName("2 | Instance")
    public void testInstance() {
        assertDoesNotThrow(() -> newInstance(getConstructor(CONSTRUCTOR_SIGNATURE)),
            "Could not create an instance of class %s".formatted(className));
    }

    @ParameterizedTest
    @ArgumentsSource(LocationsProvider.class)
    @DisplayName("3 | calculateDistance(Location, Location)")
    public void testCalculateDistance(Object location1, Object location2) {
        Object instance = newInstance(getConstructor(CONSTRUCTOR_SIGNATURE));
        Method calculateDistance = getMethod(METHOD_CALCULATE_DISTANCE_SIGNATURE);


    }
}
