package projekt.spec;

public class EmptySpecTester implements SpecTester {

    private final String type;

    public EmptySpecTester(String type) {
        this.type = type;
    }

    @Override
    public void runTest() {}

    @Override
    public String toString() {
        return "No %s tests specified".formatted(type);
    }
}
