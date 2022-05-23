package projekt.rwap;

public interface MethodWrapper {

    ReflectedInstance invoke(Object... args) throws ReflectiveOperationException;
}
