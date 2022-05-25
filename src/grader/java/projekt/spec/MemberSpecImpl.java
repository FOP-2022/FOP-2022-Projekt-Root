package projekt.spec;

import projekt.utils.TypeUtils;

import java.lang.reflect.Type;
import java.util.function.Predicate;

public abstract class MemberSpecImpl<T extends MemberSpec<T>> implements MemberSpec<T> {
    protected final ClassSpec classSpec;
    protected final String name;
    protected Predicate<Type> typePredicate;
    protected int modifiers = -1;

    public MemberSpecImpl(ClassSpec classSpec, String name) {
        this.classSpec = classSpec;
        this.name = name;
    }

    @Override
    public T requireType(Class<?> expectedType) {
        typePredicate = TypeUtils.hasType(expectedType);
        return self();
    }

    @Override
    public T requireModifiers(int modifiers) {
        this.modifiers = modifiers;
        return self();
    }

    protected abstract T self();
}
