package projekt.spec;

import projekt.utils.TutorAssertions;
import projekt.utils.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Predicate;

import static projekt.utils.TutorAssertions.assertClassHasField;

public class FieldSpec {

    private final ClassSpec classSpec;
    private final String name;

    private Predicate<Type> typePredicate;

    private int modifiers = -1;

    FieldSpec(ClassSpec classSpec, String name) {
        this.classSpec = classSpec;
        this.name = name;
    }

    public FieldSpec requireType(Class<?> expectedType) {
        typePredicate = TypeUtils.hasType(expectedType);
        return this;
    }

    public FieldSpec requireModifiers(int modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public void assertField() {
        Field field = assertClassHasField(classSpec.getClassToSpec(), name);
        TutorAssertions.assertField(field, modifiers, typePredicate);
    }

    @Override
    public String toString() {
        return "FieldSpec(%s)".formatted(name);
    }
}
