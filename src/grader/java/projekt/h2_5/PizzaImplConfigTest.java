package projekt.h2_5;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class PizzaImplConfigTest extends ClassSpecTestCase {

    protected PizzaImplConfigTest() {
        spec.requireClass(ClassName.PIZZA_IMPL_CONFIG)
            .requireImplements(ClassName.PIZZA_CONFIG);
    }
}
