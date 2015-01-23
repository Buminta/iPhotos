package ipicture.hemlock.com.ipic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.TextView;

/**
 * Created by me866chuan on 1/23/15.
 */
public class LayerText extends LayerImage {
    private Context mContext = null;
    public LayerText(Context context) {
        super(context);
        mContext = context;
    }

    public void setText(String text, Integer color){
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.layout(0, 0, 50, 200);
        textView.setTextSize(24);
        if(color != null) textView.setTextColor(color);

        Bitmap bmp = Bitmap.createBitmap(50, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        textView.draw(canvas);

        this.setImageBitmap(bmp);
        this.postInvalidate();
    }
}