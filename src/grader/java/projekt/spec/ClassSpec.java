package projekt.spec;

import org.junit.jupiter.params.provider.Arguments;
import org.opentest4j.AssertionFailedError;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static projekt.utils.TutorAssertions.*;

public class ClassSpec {

    private final Set<String> interfacesToImplement = new HashSet<>();

    private final Set<FieldSpec> fieldSpecs = new HashSet<>();

    private final Set<MethodSpec> methodSpecs = new HashSet<>();

    private String className;
    private Class<?> clazz;

    public ClassSpec requireClass(String className) {
        this.className = className;
        return this;
    }

    public ClassSpec requireImplements(String interfaceName) {
        interfacesToImplement.add(interfaceName);
        return this;
    }

    public FieldSpec requireField(String name) {
        var spec = new FieldSpecImpl(this, name);
        fieldSpecs.add(spec);
        return spec;
    }


    public MethodSpec requireMethod(String name) {
        var spec = new MethodSpecImpl(this, name);
        methodSpecs.add(spec);
        return spec;
    }

    public Class<?> getClassToSpec() {
        if (clazz == null) {
            throw new IllegalStateException(
                "Class to spec not set. Did you call findClass()?");
        }

        return clazz;
    }

    public Stream<Arguments> provideFieldTesters() {
        var testers = fieldSpecs.isEmpty()
            ? Stream.of(new EmptyFieldTester())
            : fieldSpecs.stream()
                .map(FieldSpec::getTester);

        return testers.map(Arguments::of);
    }


    public Stream<Arguments> provideMethodTesters() {
        var testers = methodSpecs.isEmpty()
            ? Stream.of(new EmptyMethodTester())
            : methodSpecs.stream()
            .map(MethodSpec::getTester);

        return testers.map(Arguments::of);
    }

    public void assertClass() {
        assertClassImplements(clazz, interfacesToImplement.stream());
        assertClassNotGeneric(clazz);
    }

    public void findClass() {
        if (className == null) {
            throw new IllegalStateException(
                "Class name not set. Did you call requireClass()?");
        }

        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new AssertionFailedError("Class %s could not be found".formatted(className), e);
        }
    }

    @Override
    public String toString() {
        return "ClassSpec(%s)".formatted(className);
    }
}
