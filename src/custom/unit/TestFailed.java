package custom.unit;

public class TestFailed extends RuntimeException {
    public TestFailed(String message) {
        super(message);
    }
}
