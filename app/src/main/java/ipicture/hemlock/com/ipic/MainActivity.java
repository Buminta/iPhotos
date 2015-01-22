package ipicture.hemlock.com.ipic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.abc_fade_out, R.anim.abc_fade_in);
    }


    private Intent pictureActionIntent = null;
    public void takePhoto(){
        Intent pictureActionIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pictureActionIntent,
                iPicApplication.CAMRERA_FLAG);
    }
    public void selectPhotos(){
        pictureActionIntent = new Intent(
                Intent.ACTION_GET_CONTENT, null);
        pictureActionIntent.setType("image/*");
        pictureActionIntent.putExtra("return-data", true);
        startActivityForResult(pictureActionIntent,
                iPicApplication.GALLERY_FLAG);
    }

    AlertDialog choseDialog = null;
    public void chosePhotos(View v){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linearLayout = inflater.inflate(R.layout.layout_dialog, null);
        final ListView listViewReport = (ListView) linearLayout.findViewById(R.id.list_view);
        final PhotoAdapter adapter = new PhotoAdapter(getApplicationContext());
        listViewReport.setAdapter(adapter);

        listViewReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) takePhoto();
                else selectPhotos();
                if(choseDialog != null) choseDialog.hide();
            }
        });
        choseDialog = new AlertDialog.Builder(this)
                .setView(linearLayout)
                .setTitle("Select picture or take a new")
                .show();
    }

    protected class PhotoAdapter extends BaseAdapter {
        Context mContext;
        private int[] list = {
                R.drawable.ic_camera_btn,
                R.drawable.ic_camera_btn
        };
        private String[] title = {
                "Take a photo",
                "Chose a photo"
        };

        public PhotoAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_item, null);
            ((ImageView) layout.findViewById(R.id.icon)).setImageResource(list[position]);
            ((TextView) layout.findViewById(R.id.title)).setText(title[position]);
            return layout;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if ((requestCode == iPicApplication.CAMRERA_FLAG || requestCode == iPicApplication.GALLERY_FLAG) && resultCode == RESULT_OK) {
            if (data != null) {
                Intent i = new Intent(this, EditPicture.class);
                i.setData(data.getData());
                startActivityForResult(i, requestCode);
            }
        }
    }

}