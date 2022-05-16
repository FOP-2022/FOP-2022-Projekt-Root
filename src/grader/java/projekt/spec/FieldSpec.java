package projekt.spec;

public interface FieldSpec {
    FieldSpecImpl requireType(Class<?> expectedType);

    FieldSpecImpl requireModifiers(int modifiers);

    FieldTester getAsserter();
}
