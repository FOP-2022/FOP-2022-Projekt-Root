package projekt.rwap;

public interface ConstructorWrapper {

    ReflectedInstance newInstance(Object... initargs) throws ReflectiveOperationException;
}
