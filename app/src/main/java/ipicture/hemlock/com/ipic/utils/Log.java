package ipicture.hemlock.com.ipic.utils;

/**
 * Created by me866chuan on 1/21/15.
 */
public final class Log{
    private static boolean flagLog = true;
    public static void d(String tags, String message){
        if(flagLog) android.util.Log.d(tags, message);
    }
    public static void d(String tags, String message, Exception e){
        if(flagLog) android.util.Log.i(tags, message);
    }
    public static void e(String tags, String message){
        if(flagLog) android.util.Log.e(tags, message);
    }
    public static void e(String tags, String message, Exception e){
        if(flagLog) android.util.Log.e(tags, message, e);
    }
    public static void e(String tags, String message, Throwable e){
        if(flagLog) android.util.Log.e(tags, message, e);
    }
    public static void v(String tags, String message){
        if(flagLog) android.util.Log.v(tags, message);
    }
    public static void v(String tags, String message, Exception e){
        if(flagLog) android.util.Log.i(tags, message);
    }
    public static void i(String tags, String message){
        if(flagLog) android.util.Log.i(tags, message);
    }
    public static void i(String tags, String message, Exception e){
        if(flagLog) android.util.Log.i(tags, message, e);
    }
    public static void w(String tags, String message){
        if(flagLog) android.util.Log.w(tags, message);
    }
    public static void w(String tags, String message, Exception e){
        if(flagLog) android.util.Log.w(tags, message, e);
    }
}
