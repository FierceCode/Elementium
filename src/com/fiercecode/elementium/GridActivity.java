package com.fiercecode.elementium;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.fiercecode.elementium.dao.ElementDao;
import com.fiercecode.elementium.entities.Elemental;
import com.fiercecode.elementium.tasks.LoadElementalsTask;

public class GridActivity extends Activity
{
	private Context context = this;
	private ElementDao elementDao;
	private GridView gridview;
	private ElementalViewArrayAdapter elemental_adapter;
	private List<Elemental> elements;

	private LoadElementalsTask loadElemTask = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		initialize();
		if (this.elementDao.getAllElements().size() < 118)
		{
			populateList();
		}
		if (this.elements.size() < 118)
		{
			loadElements();
		}
		setDisplay();
	}

	@Override
	protected void onResume()
	{
		this.elementDao.open();
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		this.elementDao.close();
		super.onPause();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
			default:

				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initialize()
	{
		if (this.elementDao == null)
		{
			this.elementDao = new ElementDao(this.context);
			this.elementDao.open();
		}
		if (this.elements == null)
		{
			this.elements = new ArrayList<Elemental>();
		}
	}

	private void populateList()
	{
		Toast.makeText(this.context,
				"Bonding with elements, please wait for a reaction",
				Toast.LENGTH_SHORT).show();
		AssetManager am = this.context.getAssets();
		InputStreamReader the_iRead = null;
		try
		{
			the_iRead = new InputStreamReader(am.open("elementals.xml"));
			this.loadElemTask = new LoadElementalsTask(the_iRead);
			this.loadElemTask.execute();

			this.elementDao.insertAllElements(this.loadElemTask.get());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void loadElements()
	{
		this.elements = this.elementDao.getAllElements();
	}

	private void setDisplay()
	{
		if (this.gridview == null)
		{
			this.gridview = (GridView) findViewById(R.id.grid_view);
		}
		if (this.elemental_adapter == null)
		{
			this.elemental_adapter = new ElementalViewArrayAdapter(this,
					R.layout.grid_view, this.elements);
		}
		this.gridview.setAdapter(this.elemental_adapter);
		
		this.gridview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				String[] elements_array = convertElementalToArray(GridActivity.this.elemental_adapter.getItem(position));
				
				Intent detailIntent = new Intent(GridActivity.this.context,
						DetailActivity.class);
				detailIntent.putExtra("element_description", elements_array);
				detailIntent.putExtra("text_color", GridActivity.this.elemental_adapter.getTxtColor());
				detailIntent.putExtra("bg_color", GridActivity.this.elemental_adapter.getBkgColor());
				startActivity(detailIntent);
			}
		});
	}

	private String[] convertElementalToArray(Elemental element)
	{
		String atomic_num = String.valueOf(element.getAtomicNum());
		String symbol = element.getSymbol();
		String name = element.getName();
		String phase = element.getPhase();
		String category = element.getCategory();
		String atomic_weight = element.getAtomicWeight();
		String boiling_point = element.getBoilingPoint();
		String melting_point = element.getMeltingPoint();
		String density = element.getDensity();
		String density_tag = element.getDensityTag();

		String[] text = new String[] { atomic_num, symbol, name, phase,
				category, atomic_weight, boiling_point, melting_point, density,
				density_tag };
		return text;
	}
}
