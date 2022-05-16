package projekt.h2_5;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

public class PastaImplConfigTest extends ClassSpecTestCase {

    protected PastaImplConfigTest() {
        spec.requireClass(ClassName.PIZZA_IMPL_CONFIG)
            .requireImplements(ClassName.PIZZA_CONFIG);
    }
}
