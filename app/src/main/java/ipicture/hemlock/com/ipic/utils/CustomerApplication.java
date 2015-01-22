package ipicture.hemlock.com.ipic.utils;

/**
 * Created by me866chuan on 1/21/15.
 */
public class CustomerApplication extends GATraceHandler {
    @Override
    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        super.onCreate();
    }
}
