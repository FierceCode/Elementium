package com.fiercecode.elementium.tasks;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;

import com.fiercecode.elementium.entities.Elemental;

public class LoadElementalsTask extends AsyncTask<Void, Void, List<Elemental>>
{
	private InputStreamReader mRead;
	private List<Elemental> mList;

	public LoadElementalsTask(InputStreamReader iRead)
	{
		super();
		this.mRead = iRead;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}

	@Override
	protected List<Elemental> doInBackground(Void... params)
	{

		this.mList = new ArrayList<Elemental>();

		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(this.mRead);
			int eventType = xpp.getEventType();
			Elemental temp_elemental = new Elemental();
			while (eventType != XmlPullParser.END_DOCUMENT)
			{
				switch (eventType)
				{
					case XmlPullParser.START_TAG:
					{
						if (xpp.getName().contentEquals("element"))
						{
							temp_elemental = new Elemental();
						}
						else if (xpp.getName().contentEquals("atomic_number"))
						{
							Integer atom_num = Integer.parseInt(xpp.nextText());
							if (atom_num != null)
							{
								temp_elemental.setAtomicNum(atom_num);
							}
						}
						else if (xpp.getName().contentEquals("symbol"))
						{
							String symbol = xpp.nextText();
							temp_elemental.setSymbol(symbol);
						}
						else if (xpp.getName().contentEquals("name"))
						{
							String name = xpp.nextText();
							temp_elemental.setName(name);
						}
						else if (xpp.getName().contentEquals("phase"))
						{
							String phase = xpp.nextText();
							temp_elemental.setPhase(phase);
						}
						else if (xpp.getName().contentEquals("category"))
						{
							String category = xpp.nextText();
							temp_elemental.setCategory(category);
						}
						else if (xpp.getName().contentEquals("atomic_weight"))
						{
							String atom_weight = xpp.nextText();
							temp_elemental.setAtomicWeight(atom_weight);
						}
						else if (xpp.getName().contentEquals("boiling_point"))
						{
							String boiling_point = xpp.nextText();
							temp_elemental.setBoilingPoint(boiling_point);
						}
						else if (xpp.getName().contentEquals("melting_point"))
						{
							String melting_point = xpp.nextText();
							temp_elemental.setMeltingPoint(melting_point);
						}
						else if (xpp.getName().contentEquals("density"))
						{
							String density = xpp.nextText();
							temp_elemental.setDensity(density);
						}
						else if (xpp.getName().contentEquals("density_tag"))
						{
							String density_tag = xpp.nextText();
							temp_elemental.setDensityTag(density_tag);
						}
						break;
					}
					case XmlPullParser.END_TAG:
					{
						if (xpp.getName().contentEquals("element"))
						{
							this.mList.add(temp_elemental);
						}
					}
					default:
						break;
				}
				xpp.next();
				eventType = xpp.getEventType();
			}
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return this.mList;
	}

	@Override
	protected void onPostExecute(List<Elemental> the_list)
	{
		super.onPostExecute(the_list);
	}
}
