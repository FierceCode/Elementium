package com.fiercecode.elementium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.show_about_btn:
		{
			Intent aboutIntent = new Intent(v.getContext(),
					AboutActivity.class);
			startActivity(aboutIntent);
			break;
		}
		case R.id.show_elem_btn:
		{ 
			Intent gridIntent = new Intent(v.getContext(), GridActivity.class);
			startActivity(gridIntent);
			break;
		}
		default:
			break;
		}
	}
}
