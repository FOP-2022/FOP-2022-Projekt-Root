package projekt.spec;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.provider.Arguments;
import org.opentest4j.AssertionFailedError;

import java.util.*;
import java.util.stream.Stream;

import static projekt.utils.TutorAssertions.*;

public class ClassSpec {

    private final Set<String> interfacesToImplement = new HashSet<>();

    private final List<Set<String>> typeParameters = new ArrayList<>();

    private final Set<FieldSpec> fieldSpecs = new HashSet<>();

    private final Set<MethodSpec> methodSpecs = new HashSet<>();

    private final Set<ConstructorSpec> constructorSpecs = new HashSet<>();

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

    public ClassSpec requireTypeParameterBoundBy(String... bounds) {
        typeParameters.add(Set.of(bounds));
        return this;
    }

    public FieldSpec requireField(String name) {
        var spec = new FieldSpecImpl(this, name);
        fieldSpecs.add(spec);
        return spec;
    }

    public ConstructorSpec requireConstructor(String signatur) {
        var spec = new ConstructorSpecImpl(this, signatur);
        constructorSpecs.add(spec);
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
        return getTesterArguments("field", fieldSpecs);
    }

    public Stream<Arguments> provideConstructorTesters() {
        return getTesterArguments("constructor", constructorSpecs);
    }

    public Stream<Arguments> provideMethodTesters() {
        return getTesterArguments("method", methodSpecs);
    }

    @NotNull
    private Stream<Arguments> getTesterArguments(String type, Collection<? extends TestableSpec> specs) {
        var testers = specs.isEmpty()
            ? Stream.of(new EmptySpecTester(type))
            : specs.stream()
            .map(TestableSpec::getTester);

        return testers.map(Arguments::of);
    }

    public void assertClass() {
        assertClassImplements(clazz, interfacesToImplement.stream());

        var typeParameters = this.typeParameters.toArray(Set[]::new);
        //noinspection unchecked
        assertClassHasTypeParameters(clazz, typeParameters);
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
