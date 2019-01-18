package com.fiercecode.elementium.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.fiercecode.elementium.R;

public class SetColorTask extends AsyncTask<Void, Void, Integer>
{
	private Context mContext;
	private String mType;
	private boolean mIsTextView;

	public SetColorTask(Context context, String the_type, Boolean isTextView)
	{
		this.mContext = context;
		this.mType = the_type;
		this.mIsTextView = isTextView;
	}

	@Override
	protected Integer doInBackground(Void... unused)
	{
		return getTxtColor(this.mType);
	}

	private Integer getTxtColor(String the_text_type)
	{
		if (this.mIsTextView)
		{
			if (the_text_type.contentEquals("solid"))
			{
				return this.mContext.getResources().getColor(R.color.solid);
			}
			else if (the_text_type.contentEquals("gas"))
			{
				return this.mContext.getResources().getColor(R.color.gas);
			}
			else if (the_text_type.contentEquals("liquid"))
			{
				return this.mContext.getResources().getColor(R.color.liquid);
			}
			else if (the_text_type.contentEquals("synthetic"))
			{
				return this.mContext.getResources().getColor(R.color.sythetic);
			}
			else
			{
				return this.mContext.getResources().getColor(
						R.color.unknown_text);
			}
		}
		else
		{
			if (the_text_type.contains("alkali metal"))
			{
				return this.mContext.getResources().getColor(
						R.color.alkali_metal);
			}
			else if (the_text_type.contains("alkaline earth metal"))
			{
				return this.mContext.getResources().getColor(
						R.color.alkaline_earth_metal);
			}
			else if (the_text_type.contains("halogen"))
			{
				return this.mContext.getResources().getColor(R.color.halogen);
			}
			else if (the_text_type.contains("metaloid"))
			{
				return this.mContext.getResources().getColor(R.color.metalloid);
			}
			else if (the_text_type.contains("noble gas"))
			{
				return this.mContext.getResources().getColor(R.color.noble_gas);
			}
			else if (the_text_type.contains("non metal"))
			{
				return this.mContext.getResources().getColor(R.color.non_metal);
			}
			else if (the_text_type.contains("transition metal"))
			{
				return this.mContext.getResources().getColor(
						R.color.transition_metal);
			}
			else if (the_text_type.contains("actinoid"))
			{
				return this.mContext.getResources().getColor(R.color.actinoid);
			}
			else if (the_text_type.contains("lanthanoid"))
			{
				return this.mContext.getResources()
						.getColor(R.color.lanthanoid);
			}
			else if (the_text_type.contains("post transition metal"))
			{
				return this.mContext.getResources().getColor(
						R.color.post_transition_metal);
			}
			else if (the_text_type.contains("possible solid"))
			{
				return this.mContext.getResources().getColor(
						R.color.possible_solid);
			}
			else
			{
				return this.mContext.getResources().getColor(R.color.unknown);
			}
		}
	}
}
