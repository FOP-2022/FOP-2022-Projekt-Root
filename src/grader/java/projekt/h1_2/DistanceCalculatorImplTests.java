package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.rwap.ReflectTestUtils;
import projekt.rwap.ReflectedInstance;
import projekt.utils.ClassName;
import projekt.utils.TestClass;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static projekt.utils.TutorAssertions.assertClassHasModifiers;
import static projekt.utils.TutorAssertions.assertClassImplements;

public abstract class DistanceCalculatorImplTests extends TestClass {

    private static final double EPSILON = 1e-6;

    public final String constructorSignature;

    public DistanceCalculatorImplTests(String className) {
        super(className);

        constructorSignature = "%s()".formatted(className);
        setConstructorPredicates(predicatesFromSignatures(constructorSignature));
    }

    @Test
    @DisplayName("1 | Class and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC);
        assertFalse(Modifier.isAbstract(clazz.getModifiers()), "Class %s must not be abstract".formatted(className));
        assertClassImplements(clazz, ClassName.DISTANCE_CALCULATOR);
    }

    @Test
    @DisplayName("2 | Instance")
    public void testInstance() {
        assertDoesNotThrow(() -> newInstance(getConstructor(constructorSignature)),
            "Could not create an instance of class %s".formatted(className));
    }

    @ParameterizedTest
    @CsvSource({
        "5, 5, 8, 9",
        "-5, -5, -8, -9",
        "1, 1, 2, 2",
        "-1, -1, -2, -2",
    })
    @DisplayName("3 | calculateDistance(Location, Location)")
    public void testCalculateDistance(int x1, int y1, int x2, int y2) throws ReflectiveOperationException {
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

        var expected = getExpected(x1, y1, x2, y2);
        assertEquals(expected, (double) result.getInstance(), EPSILON);
    }

    protected abstract double getExpected(int x1, int y1, int x2, int y2);
}
