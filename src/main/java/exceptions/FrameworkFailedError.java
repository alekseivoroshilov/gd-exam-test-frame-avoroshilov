package exceptions;

public class FrameworkFailedError extends Error {
    public FrameworkFailedError() {
        super("The framework has ended its work with an error. Please, check the input config info");
    }
    public FrameworkFailedError(String msg, Throwable e) {
        super(msg, e);
    }
}
