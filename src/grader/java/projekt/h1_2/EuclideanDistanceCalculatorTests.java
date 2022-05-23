package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import projekt.rwap.ReflectTestUtils;
import projekt.rwap.ReflectedInstance;
import projekt.utils.ClassName;
import projekt.utils.TestClass;

import java.lang.reflect.Modifier;

import static projekt.utils.TutorAssertions.*;

@DisplayName("projekt.base.EuclideanDistanceCalculator tests")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class EuclideanDistanceCalculatorTests extends TestClass {

    private static final double EPSILON = 1e-6;

    public static final String CONSTRUCTOR_SIGNATURE = "%s()".formatted(ClassName.EUCLIDEAN_DISTANCE_CALCULATOR);
    public static final String METHOD_CALCULATE_DISTANCE_SIGNATURE = "calculateDistance(%s, %s)"
        .formatted(ClassName.LOCATION, ClassName.LOCATION);

    public EuclideanDistanceCalculatorTests() {
        super(
            ClassName.EUCLIDEAN_DISTANCE_CALCULATOR,
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
    @CsvSource({
        "5, 5, 8, 9, 5",
        "-5, -5, -8, -9, 5",
        "1, 1, 2, 2, 1.41421356237",
        "-1, -1, -2, -2, 1.41421356237",
    })
    @DisplayName("3 | calculateDistance(Location, Location)")
    public void testCalculateDistance(int x1, int y1, int x2, int y2, double expected) throws ReflectiveOperationException {
        var locationClass = ReflectTestUtils.getClassForName(ClassName.LOCATION);
        var instance = ReflectedInstance
            .getConstructor(clazz)
            .newInstance();

        var calculateDistance = instance.getMethod("calculateDistance", locationClass, locationClass);
        var loc1 = ReflectedInstance
            .getConstructor(locationClass, int.class, int.class)
            .newInstance(x1, y1);
        var loc2 = ReflectedInstance
            .getConstructor(locationClass, int.class, int.class)
            .newInstance(x2, y2);

        var result = calculateDistance.invoke(loc1.getInstance(), loc2.getInstance());

        assertEquals(expected, (double) result.getInstance(), EPSILON);
    }
}
