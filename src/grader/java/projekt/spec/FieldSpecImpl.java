package projekt.spec;

import projekt.utils.TutorAssertions;
import projekt.utils.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Predicate;

import static projekt.utils.TutorAssertions.assertClassHasField;

public class FieldSpecImpl implements FieldSpec {

    private final ClassSpec classSpec;
    private final String name;

    private Predicate<Type> typePredicate;

    private int modifiers = -1;

    FieldSpecImpl(ClassSpec classSpec, String name) {
        this.classSpec = classSpec;
        this.name = name;
    }

    @Override
    public FieldSpecImpl requireType(Class<?> expectedType) {
        typePredicate = TypeUtils.hasType(expectedType);
        return this;
    }

    @Override
    public FieldSpecImpl requireModifiers(int modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    @Override
    public FieldTester getAsserter() {
        return new FieldTester() {
            @Override
            public void testField() {
                Field field = assertClassHasField(classSpec.getClassToSpec(), name);
                TutorAssertions.assertField(field, modifiers, typePredicate);
            }

            @Override
            public String toString() {
                return "Test for field %s".formatted(name);
            }
        };
    }
}
