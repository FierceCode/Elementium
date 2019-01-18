package com.fiercecode.elementium;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fiercecode.elementium.entities.Elemental;
import com.fiercecode.elementium.tasks.SetColorTask;


public class ElementalViewArrayAdapter extends ArrayAdapter<Elemental>
{
	private int my_resource_id;
	private int mBkgColor;
	private int mTxtColor;
	
	public ElementalViewArrayAdapter(Context context, int resource_id,
			List<Elemental> elements)
	{
		super(context, resource_id, elements);
		this.my_resource_id = resource_id;
	}

	@Override
	public View getView(int position, View convert_view, ViewGroup parent)
	{
		RelativeLayout element_view;
		Elemental element = getItem(position);
		String atomic_num = String.valueOf(element.getAtomicNum());
		String name = element.getName();
		String symbol = element.getSymbol();
		String phase = element.getPhase();
		String category = element.getCategory();
		// if we already have a view of this type lets just use it.
		if (convert_view == null)
		{
			element_view = new RelativeLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					inflater);
			vi.inflate(this.my_resource_id, element_view, true);
		} 
		else
		{
			element_view = (RelativeLayout) convert_view;
		}

		TextView symbol_view = (TextView) element_view.findViewById(R.id.symbol_view);
		TextView atomic_view = (TextView) element_view.findViewById(R.id.atomic_view);
		TextView name_view = (TextView) element_view.findViewById(R.id.name_view);
		
		symbol_view.setText(symbol);
		atomic_view.setText(atomic_num);
		name_view.setText(name);
		
		
		try
		{
			SetColorTask sct = new SetColorTask(this.getContext(), phase, true);
			SetColorTask sbt = new SetColorTask(this.getContext(), category, false);
			this.mTxtColor = sct.execute().get();
			this.mBkgColor = sbt.execute().get();
			symbol_view.setTextColor(this.mTxtColor);
			element_view.setBackgroundColor(this.mBkgColor);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}	
		return element_view;
	}
	protected int getTxtColor()
	{
		return this.mTxtColor;
	}
	protected int getBkgColor()
	{
		return this.mBkgColor;
	}
}
