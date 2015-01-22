package ipicture.hemlock.com.ipic.utils.multitouch;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;


public class MoveGestureDetector extends BaseGestureDetector {

	/**
	 * Listener which must be implemented which is used by MoveGestureDetector
	 * to perform callbacks to any implementing class which is registered to a
	 * MoveGestureDetector via the constructor.
	 * 
	 * @see ipicture.hemlock.com.ipic.utils.multitouch.MoveGestureDetector.SimpleOnMoveGestureListener
	 */
	public interface OnMoveGestureListener {
		public boolean onMove(MoveGestureDetector detector);
		public boolean onMoveBegin(MoveGestureDetector detector);
		public void onMoveEnd(MoveGestureDetector detector);
	}

	/**
	 * Helper class which may be extended and where the methods may be
	 * implemented. This way it is not necessary to implement all methods
	 * of OnMoveGestureListener.
	 */
	public static class SimpleOnMoveGestureListener implements OnMoveGestureListener {
	    public boolean onMove(MoveGestureDetector detector) {
	        return false;
	    }

	    public boolean onMoveBegin(MoveGestureDetector detector) {
	        return true;
	    }

	    public void onMoveEnd(MoveGestureDetector detector) {
	    	// Do nothing, overridden implementation may be used
	    }
	}

    private static final PointF FOCUS_DELTA_ZERO = new PointF();
    
    private final OnMoveGestureListener mListener;
    
    private PointF mCurrFocusInternal;
    private PointF mPrevFocusInternal;  
    private PointF mFocusExternal = new PointF();
    private PointF mFocusDeltaExternal = new PointF();
    private int NumOfPoints =0;

    public MoveGestureDetector(Context context, OnMoveGestureListener listener) {
    	super(context);
        mListener = listener;
    }

    @Override
    protected void handleStartProgressEvent(int actionCode, MotionEvent event){
        switch (actionCode) { 
            case MotionEvent.ACTION_DOWN: 
                resetState(); // In case we missed an UP/CANCEL event
                
                mPrevEvent = MotionEvent.obtain(event);
                mTimeDelta = 0;

                updateStateByEvent(event);
                break;
            
            case MotionEvent.ACTION_MOVE:
                mGestureInProgress = mListener.onMoveBegin(this);
                break;
        }
    }
    
    public void InvokeMoveEnd(){
    	 mListener.onMoveEnd(this);
         resetState();
    }
    
    
    @Override
    protected void handleInProgressEvent(int actionCode, MotionEvent event){ 	
        switch (actionCode) {
        	case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mListener.onMoveEnd(this);
                resetState();
                break;

            case MotionEvent.ACTION_MOVE:
            	try{
                updateStateByEvent(event);

				// Only accept the event if our relative pressure is within
				// a certain limit. This can help filter shaky data as a
				// finger is lifted.
                
                NumOfPoints = event.getPointerCount();
                Log.d("hhr","num of points: " + String.valueOf(NumOfPoints));
                if (mCurrPressure / mPrevPressure > PRESSURE_THRESHOLD ) {
                    final boolean updatePrevious = mListener.onMove(this);
                    if (updatePrevious) {
                        mPrevEvent.recycle();
                        mPrevEvent = MotionEvent.obtain(event);
                    }
                }
                }catch(Exception e){
                	Log.d("hhr","the exception catched");
                }
                
                break;
        }
	}
    
    protected void updateStateByEvent(MotionEvent curr) {
    	super.updateStateByEvent(curr);

    	final MotionEvent prev = mPrevEvent;
    	Log.d("hhr serial","1");
        // Focus intenal
        mCurrFocusInternal = determineFocalPoint(curr);
        mPrevFocusInternal = determineFocalPoint(prev);
        Log.d("hhr serial","2");
        // Focus external
        // - Prevent skipping of focus delta when a finger is added or removed
        boolean mSkipNextMoveEvent = prev.getPointerCount() != curr.getPointerCount();
        mFocusDeltaExternal = mSkipNextMoveEvent ? FOCUS_DELTA_ZERO : new PointF(mCurrFocusInternal.x /*- mPrevFocusInternal.x*/,  mCurrFocusInternal.y /*- mPrevFocusInternal.y*/);
        Log.d("hhr serial","3");
        // - Don't directly use mFocusInternal (or skipping will occur). Add 
        // 	 unskipped delta values to mFocusExternal instead.
        mFocusExternal.x = mFocusDeltaExternal.x;
        mFocusExternal.y = mFocusDeltaExternal.y;
        Log.d("hhr serial","4");
    }

	/**
	 * Determine (multi)finger focal point (a.k.a. center point between all
	 * fingers)
	 * 
	 * @param MotionEvent e
	 * @return PointF focal point
	 */
    private PointF determineFocalPoint(MotionEvent e){
    	// Number of fingers on screen
    	
    	//if (e == null)  return new PointF(0,0);
        final int pCount = e.getPointerCount(); 
        float x = 0f;
        float y = 0f;
        
        for(int i = 0; i < pCount; i++){
        	x += e.getX(i);
        	y += e.getY(i);
        }
        
        return new PointF(x/pCount, y/pCount);
    }

    public float getFocusX() {
        return mFocusExternal.x;
    }

    public float getFocusY() {
        return mFocusExternal.y;
    }

    public PointF getFocusDelta() {
		return mFocusDeltaExternal;
    }
    public int getNumberOfPoints(){
    	return NumOfPoints;
    }
    

}