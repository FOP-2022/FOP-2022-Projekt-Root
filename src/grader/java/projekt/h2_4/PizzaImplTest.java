package projekt.h2_4;

import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

public class PizzaImplTest extends AbstractFoodImpTest {

   PizzaImplTest() {
        spec.requireClass(ClassName.PIZZA_IMPL)
            .requireImplements(ClassName.PIZZA);

        spec.requireField("diameter")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
