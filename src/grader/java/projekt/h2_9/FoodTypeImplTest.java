package projekt.h2_9;

import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;
import java.util.List;

public class FoodTypeImplTest extends ClassSpecTestCase {

    FoodTypeImplTest() {
        spec.requireClass(ClassName.FOOD_TYPE_IMPL)
            .requireImplements(ClassName.FOOD_TYPE)
            .requireTypeParameterBoundBy(ClassName.FOOD)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG);

        spec.requireField("name")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(String.class);

        spec.requireField("compatibleExtras")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(List.class);

        spec.requireField("foodVariants")
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL)
            .requireType(List.class);
    }
}
