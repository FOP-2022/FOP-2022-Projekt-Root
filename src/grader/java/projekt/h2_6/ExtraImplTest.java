package projekt.h2_6;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class ExtraImplTest extends ClassSpecTestCase {

    ExtraImplTest() {
        spec.requireClass(ClassName.EXTRA_IMPL)
            .requireImplements(ClassName.EXTRA)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG);

        spec.requireField("name")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(String.class);

        spec.requireField("priority")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(int.class);

        spec.requireField("configMutator")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(Consumer.class);
    }
}
