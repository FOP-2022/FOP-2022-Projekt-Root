package projekt.h2_4;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

@TestForSubmission("projekt")
public class PizzaImplTest extends AbstractFoodImplTest {

    PizzaImplTest() {
        spec.requireClass(ClassName.PIZZA_IMPL)
            .requireImplements(ClassName.PIZZA);

        spec.requireField("diameter")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
