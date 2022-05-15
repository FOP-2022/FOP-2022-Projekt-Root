package projekt.h2_4;

import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

public class IceCreamImplTest extends AbstractFoodImpTest {

    IceCreamImplTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL)
            .requireImplementing(ClassName.ICE_CREAM);

        spec.requireField("flavor")
            .requireType(String.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
