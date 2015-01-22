package ipicture.hemlock.com.ipic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import ipicture.hemlock.com.ipic.utils.Log;
import ipicture.hemlock.com.ipic.utils.multitouch.RotateGestureDetector;

/**
 * Created by me866chuan on 1/21/15.
 */
public class EditPicture extends Activity implements RotateGestureDetector.OnRotateGestureListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getImageFromIntent();
    }

    protected void getImageFromIntent(){
        Intent intent = getIntent();
        ((ImageView) findViewById(R.id.origin_photo)).setImageURI(intent.getData());
    }

    @Override
    public boolean onRotate(RotateGestureDetector detector) {
        Log.e("Rotate", detector.toString());
        return false;
    }

    @Override
    public boolean onRotateBegin(RotateGestureDetector detector) {
        Log.e("Rotate", detector.toString());
        return false;
    }

    @Override
    public void onRotateEnd(RotateGestureDetector detector) {
        Log.e("Rotate", detector.toString());
    }
}
