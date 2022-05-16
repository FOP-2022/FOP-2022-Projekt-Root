package projekt.spec;

public class EmptyFieldTester implements FieldTester {

    @Override
    public void testField() {}

    @Override
    public String toString() {
        return "No field tests specified";
    }
}
