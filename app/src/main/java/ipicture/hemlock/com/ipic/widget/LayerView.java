package ipicture.hemlock.com.ipic.widget;

import android.view.View;

/**
 * Created by me866chuan on 1/23/15.
 */
public interface LayerView{
    public boolean isShow();
    public void setShow(boolean show);
    public void dropTo(int top, int left);
    public void alignCenter();
    public void alignCenterH();
    public void alignCenterV();
    public void alignBottom();
    public void alignTop();
    public void alignLeft();
    public void alignRight();
    public void saveCache();
    public void backToCache();
}
