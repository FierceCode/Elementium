package com.fiercecode.elementium;

import java.util.concurrent.ExecutionException;

import com.fiercecode.elementium.tasks.SetColorTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailActivity extends Activity
{
	private TextView mAtomicView;
	private TextView mSymbolView;
	private TextView mNameView;
	private TextView mPhaseView;
	private TextView mCategoryView;
	private TextView mAtomicWeightView;
	private TextView mBoilingView;
	private TextView mMeltingView;
	private TextView mDensityView;
	private TextView mDensityTagView;
	private RelativeLayout mRelView;
	private String mPhase;
	private String mCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		// Populate the details data with the data from the bird.
		Intent intent = getIntent();
		if (intent.hasExtra("element_description"))
		{
			initViews();
			setViewText(intent.getStringArrayExtra("element_description"));
		}
		setColors();
	}

	private void initViews()
	{
		this.mRelView = (RelativeLayout) this
				.findViewById(R.id.detail_element_bkg);
		this.mAtomicView = (TextView) this
				.findViewById(R.id.detail_atomic_view);
		this.mSymbolView = (TextView) this
				.findViewById(R.id.detail_symbol_view);
		this.mNameView = (TextView) this.findViewById(R.id.detail_name_view);
		this.mPhaseView = (TextView) this.findViewById(R.id.detail_phase_view);
		this.mCategoryView = (TextView) this
				.findViewById(R.id.detail_category_view);
		this.mAtomicWeightView = (TextView) this
				.findViewById(R.id.detail_atomic_weight_view);
		this.mBoilingView = (TextView) this
				.findViewById(R.id.detail_boiling_point_view);
		this.mMeltingView = (TextView) this
				.findViewById(R.id.detail_melting_point_view);
		this.mDensityView = (TextView) this
				.findViewById(R.id.detail_density_view);
		this.mDensityTagView = (TextView) this
				.findViewById(R.id.detail_density_tag_view);
	}

	private void setViewText(final String[] element_text)
	{
		this.mAtomicView.setText(element_text[0]);
		this.mSymbolView.setText(element_text[1]);
		this.mNameView.setText(element_text[2]);
		this.mPhase = element_text[3];
		this.mPhaseView.setText(this.mPhase);
		this.mCategory = element_text[4];
		this.mCategoryView.setText(this.mCategory);
		this.mAtomicWeightView.setText(element_text[5]);
		this.mBoilingView.setText(element_text[6]);
		this.mMeltingView.setText(element_text[7]);
		this.mDensityView.setText(element_text[8]);
		this.mDensityTagView.setText(element_text[9]);
	}

	private void setColors()
	{
		SetColorTask sct = new SetColorTask(this, this.mPhase, true);
		SetColorTask sbt = new SetColorTask(this, this.mCategory, false);
		int t_color;
		int b_color;
		try
		{
			t_color = sct.execute().get();
			b_color = sbt.execute().get();

			this.mSymbolView.setTextColor(t_color);
			this.mPhaseView.setTextColor(t_color);
			this.mCategoryView.setTextColor(b_color);
			this.mRelView.setBackgroundColor(b_color);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
	}
}
