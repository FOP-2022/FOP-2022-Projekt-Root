package projekt.h2_4;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

public class PizzaImplTest extends ClassSpecTestCase {

    protected PizzaImplTest() {
        spec.requireClass(ClassName.PIZZA_IMPL)
            .requireImplementing(ClassName.PIZZA);

        spec.requireField("diameter")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
