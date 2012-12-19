package com.demo.drag_drop;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
	//Mandar github changes
	//Mandar github 2nd changes
	//Mandar web changes
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.myimage3).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.myimage4).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.topleft).setOnDragListener(new MyDragListener());
	    findViewById(R.id.topright).setOnDragListener(new MyDragListener());
	    findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener());
	    findViewById(R.id.bottomright).setOnDragListener(new MyDragListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private final class MyTouchListener implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				ClipData clipData = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
				v.startDrag(clipData, shadowBuilder, v, 0);
				v.setVisibility(View.INVISIBLE);
				return true;
			}
			else
				return false;
		}
		
	}
	
	class MyDragListener implements OnDragListener
	{
		Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		Drawable normalShaape = getResources().getDrawable(R.drawable.shape);
				
		@Override
		public boolean onDrag(View v, DragEvent event)
		{
			int action = event.getAction();
			
			switch(event.getAction())
			{
				case DragEvent.ACTION_DRAG_STARTED:
					break;
					
				case DragEvent.ACTION_DRAG_ENTERED:
					v.setBackgroundDrawable(enterShape);
					break;
					
				case DragEvent.ACTION_DRAG_EXITED:
					v.setBackgroundDrawable(normalShaape);
					break;
					
				case DragEvent.ACTION_DROP:
					View view = (View) event.getLocalState();
					ViewGroup viewGroup = (ViewGroup)view.getParent();
					viewGroup.removeView(view);
					
					LinearLayout linearLayout = (LinearLayout)v;
					linearLayout.addView(view);
					view.setVisibility(View.VISIBLE);
					break;
					
				case DragEvent.ACTION_DRAG_ENDED:
					v.setBackgroundDrawable(normalShaape);
					break;
				default:
					break;
			}
			return true;
		}
		
	}

}
