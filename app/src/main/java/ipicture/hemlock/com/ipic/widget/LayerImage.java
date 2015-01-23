package ipicture.hemlock.com.ipic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.TableRow;

import java.util.ArrayList;

/**
 * Created by me866chuan on 1/23/15.
 */
public class LayerImage extends GestureImageView implements LayerView {
    ArrayList<Bitmap> listCache = new ArrayList<>();
    private boolean mShow = false;
    private final int NUM_CACHE_MAX = 3;


    public void saveCache(){
        if(listCache.size() >= NUM_CACHE_MAX){
            listCache.remove(0);
        }
        listCache.add(this.getDrawingCache());
    }

    public void backToCache(){
        this.setImageBitmap(listCache.remove(listCache.size() - 1));
        this.postInvalidate();
    }

    public LayerImage(Context context) {
        super(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.width = TableRow.LayoutParams.FILL_PARENT;
        lp.height = TableRow.LayoutParams.FILL_PARENT;
        this.setLayoutParams(lp);
    }

    @Override
    public boolean isShow() {
        return mShow;
    }

    @Override
    public void setShow(boolean show) {
        mShow = show;
    }

    @Override
    public void dropTo(int top, int left) {
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.setMargins(left, top, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignCenter() {
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL;
        this.setLayoutParams(lp);
        alignCenterV();
    }

    @Override
    public void alignCenterH() {
        int tmpTop = getTop();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.CENTER_HORIZONTAL;
        lp.setMargins(0, tmpTop, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignCenterV() {
        int tmpLeft = getLeft();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.CENTER_VERTICAL;
        lp.setMargins(tmpLeft, 0, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignBottom() {
        int tmpLeft = getLeft();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity = Gravity.BOTTOM;
        lp.setMargins(tmpLeft, 0, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignTop() {
        int tmpLeft = getLeft();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.TOP;
        lp.setMargins(tmpLeft, 0, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignLeft() {
        int tmpTop = getTop();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.LEFT;
        lp.setMargins(0, tmpTop, 0, 0);
        this.setLayoutParams(lp);
    }

    @Override
    public void alignRight() {
        int tmpTop = getTop();
        TableRow.LayoutParams lp = new TableRow.LayoutParams();
        lp.gravity= Gravity.RIGHT;
        lp.setMargins(0, tmpTop, 0, 0);
        this.setLayoutParams(lp);
    }
}
