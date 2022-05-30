package projekt.h2_11;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class FoodBuilderTest extends ClassSpecTestCase {

    FoodBuilderTest() {
        spec.requireClass(ClassName.FOOD_BUILDER)
            .requireTypeParameterBoundBy(ClassName.FOOD)
            .requireTypeParameterBoundBy(ClassName.FOOD_CONFIG)
            .requireTypeParameterBoundBy(ClassName.FOOD_VARIANT);

        spec.requireMethod("build(C, V, java.util.List<? extends projekt.food.Extra<? super C>>)")
            .requireType(Object.class)
            .requireType("F");
    }
}
