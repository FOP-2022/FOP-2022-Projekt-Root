package projekt.h1_2;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ChessboardDistanceCalculatorTests extends DistanceCalculatorImplTests {

    public ChessboardDistanceCalculatorTests() {
        super(ClassName.CHESSBOARD_DISTANCE_CALCULATOR);
    }

    @Override
    protected double getExpected(int x1, int y1, int x2, int y2) {
        var dx = x1 - x2;
        var dy = y1 - y2;
        return Math.max(Math.abs(dx), Math.abs(dy));
    }
}
