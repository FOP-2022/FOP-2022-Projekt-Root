package projekt.h2_4;

import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

public class PastaImplTest extends AbstractFoodImpTest {

    PastaImplTest() {
        spec.requireClass(ClassName.PASTA_IMPL)
            .requireImplementing(ClassName.PASTA);

        spec.requireField("thickness")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}
