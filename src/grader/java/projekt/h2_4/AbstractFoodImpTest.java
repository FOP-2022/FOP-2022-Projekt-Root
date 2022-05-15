package projekt.h2_4;

import org.junit.jupiter.api.BeforeAll;
import projekt.food.Food;
import projekt.spec.ClassSpecTestCase;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractFoodImpTest extends ClassSpecTestCase {

    @BeforeAll
    static void setupForFood() {
        spec.requireField("price")
            .requireType(BigDecimal.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);

        spec.requireField("weight")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);

        spec.requireField("variant")
            .requireType(Food.Variant.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);

        spec.requireField("extras")
            .requireType(List.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
