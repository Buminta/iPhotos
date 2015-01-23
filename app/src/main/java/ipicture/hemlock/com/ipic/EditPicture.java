package ipicture.hemlock.com.ipic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import ipicture.hemlock.com.ipic.widget.GestureImageView;
import ipicture.hemlock.com.ipic.widget.LayerImage;
import ipicture.hemlock.com.ipic.widget.LayerText;


/**
 * Created by me866chuan on 1/21/15.
 */
public class EditPicture extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_pictures);
        getImageFromIntent();
    }

    protected void getImageFromIntent() {
        Intent intent = getIntent();
        LayerImage layerImage = new LayerImage(getBaseContext());
        layerImage.setImageURI(intent.getData());

        LayerText layerText = new LayerText(getBaseContext());
        layerText.setText("Buminta dep trai", Color.RED);

        ((ViewGroup) findViewById(R.id.origin_photo)).addView(layerImage);
        ((ViewGroup) findViewById(R.id.origin_photo)).addView(layerText);

    }

    public void saveImage(View v) {
        View v1 = findViewById(R.id.origin_photo);
        // image naming and path  to include sd card  appending name you choose for file
        File imageRoot = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), getResources().getString(R.string.app_name));

        if (!imageRoot.exists()) {
            imageRoot.mkdirs();
        }


        Date date = new Date();
        File imageFile = new File(imageRoot.toString(), date.getTime() + ".png");
        if (imageFile.exists()) {
            imageFile.delete();
        }
        Bitmap bitmap;
        v1.setDrawingCacheEnabled(true);
        v1.setDrawingCacheQuality(GestureImageView.DRAWING_CACHE_QUALITY_HIGH);
        bitmap = Bitmap.createBitmap(v1.getDrawingCache());
        v1.setDrawingCacheEnabled(false);

        OutputStream fout = null;

        try {
            fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.flush();
            fout.close();
            refreshMedia(imageFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void refreshMedia(String imagePath) {
        MediaScannerConnection.scanFile(this,
                new String[]{imagePath}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }
}
