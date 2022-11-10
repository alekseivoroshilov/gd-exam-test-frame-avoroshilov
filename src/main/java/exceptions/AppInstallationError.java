package exceptions;

public class AppInstallationError extends Error {
    public AppInstallationError(String msg) {
        super(msg);
    }

    public AppInstallationError(String msg, Throwable e) {
        super(msg, e);
    }
}
