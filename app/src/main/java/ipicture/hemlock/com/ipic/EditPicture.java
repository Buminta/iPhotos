package ipicture.hemlock.com.ipic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import ipicture.hemlock.com.ipic.widget.GestureImageView;


/**
 * Created by me866chuan on 1/21/15.
 */
public class EditPicture extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_pictures);
        getImageFromIntent();
    }

    protected void getImageFromIntent(){
        Intent intent = getIntent();
        ((GestureImageView) findViewById(R.id.origin_photo)).setImageURI(intent.getData());
    }
}
