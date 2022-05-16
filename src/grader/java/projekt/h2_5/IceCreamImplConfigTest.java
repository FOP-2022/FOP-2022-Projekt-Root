package projekt.h2_5;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

public class IceCreamImplConfigTest extends ClassSpecTestCase {

    protected IceCreamImplConfigTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL_CONFIG)
            .requireImplements(ClassName.ICE_CREAM_CONFIG);
    }
}
