package exceptions;

public class ActionFailedError extends Error {
    public ActionFailedError(String msg) {
        super(msg);
    }

    public ActionFailedError(String msg, Throwable e) {
        super(msg, e);
    }

}
