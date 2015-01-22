package ipicture.hemlock.com.ipic.utils;

/**
 * Created by me866chuan on 1/21/15.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private boolean flagThrowExeption = true;
    private Thread.UncaughtExceptionHandler defaultUEH  = Thread.getDefaultUncaughtExceptionHandler();
    public void uncaughtException(Thread thread, Throwable ex) {
        if(flagThrowExeption) throwExceptionHandler(thread, ex);
        else ex.printStackTrace();
    }
    public void throwExceptionHandler(Thread thread, Throwable ex) {
        defaultUEH.uncaughtException(thread, ex);
    }
}