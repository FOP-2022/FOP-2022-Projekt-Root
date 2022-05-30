package projekt.h1_2;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ManhattanDistanceCalculatorTests extends DistanceCalculatorImplTests {

    public ManhattanDistanceCalculatorTests() {
        super(ClassName.MANHATTAN_DISTANCE_CALCULATOR);
    }

    @Override
    protected double getExpected(int x1, int y1, int x2, int y2) {
        var dx = x1 - x2;
        var dy = y1 - y2;
        return Math.abs(dx) + Math.abs(dy);
    }
}
