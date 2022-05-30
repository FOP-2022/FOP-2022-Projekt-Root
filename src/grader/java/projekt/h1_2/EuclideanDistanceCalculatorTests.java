package projekt.h1_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class EuclideanDistanceCalculatorTests extends DistanceCalculatorImplTests {

    public EuclideanDistanceCalculatorTests() {
        super(ClassName.EUCLIDEAN_DISTANCE_CALCULATOR);
    }

    @Override
    protected double getExpected(int x1, int y1, int x2, int y2) {
        var dx = x1 - x2;
        var dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Test
    @DisplayName("1 | Class and methods")
    @Override
    public void testDefinition() {
        super.testDefinition();
    }

    @Test
    @DisplayName("2 | Instance")
    @Override
    public void testInstance() {
        super.testInstance();
    }

    @ParameterizedTest
    @CsvSource({
        "5, 5, 8, 9",
        "-5, -5, -8, -9",
        "1, 1, 2, 2",
        "-1, -1, -2, -2",
    })
    @DisplayName("3 | calculateDistance(Location, Location)")
    @Override
    public void testCalculateDistance(final int x1, final int y1, final int x2, final int y2) throws ReflectiveOperationException {
        super.testCalculateDistance(x1, y1, x2, y2);
    }
}
