package projekt.spec;

import java.lang.reflect.Type;
import java.util.function.Predicate;

import static projekt.utils.TutorAssertions.*;

public class ConstructorSpecImpl implements ConstructorSpec {

    private final ClassSpec classSpec;

    private final String signature;

    private int modifiers = -1;

    public ConstructorSpecImpl(ClassSpec classSpec, String signature) {
        this.classSpec = classSpec;
        this.signature = signature;
    }

    @Override
    public ConstructorSpec requireModifiers(int modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    @Override
    public SpecTester getTester() {
        return new SpecTester() {

            @Override
            public void runTest() {
                var constructor = assertClassHasConstructor(classSpec.getClassToSpec(), signature);
                assertConstructor(constructor, modifiers, (Predicate<Type>[]) null);
            }

            @Override
            public String toString() {
                return "Tests for constructor %s".formatted(signature);
            }
        };
    }
}
