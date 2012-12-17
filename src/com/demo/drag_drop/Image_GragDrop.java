package com.demo.drag_drop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ImageView;

public class Image_GragDrop extends Activity
{
	ImageView dragImage = null;
	
	AbsoluteLayout layout = null;		
	int status = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_dragdrop);
		
		layout = (AbsoluteLayout)findViewById(R.id.dragLayout);
		dragImage = (ImageView)findViewById(R.id.imgDrag);
		
		dragImage.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				status = 1;
				return false;
			}
		});
		
		layout.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if(status == 1)
				{
					LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, (int)event.getX()-dragImage.getWidth()/2,(int)event.getY()-dragImage.getHeight()/2);
					dragImage.setLayoutParams(layoutParams);
				}
				
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					status=0;
					dragImage.setBackgroundColor(Color.TRANSPARENT);
				}
				return true;
			}
		});
	}
}
