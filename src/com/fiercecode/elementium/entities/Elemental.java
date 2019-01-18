package com.fiercecode.elementium.entities;

public class Elemental
{
	private int my_atomic_num;
	
	private String my_symbol;
	
	private String my_name;
	
	private String my_phase;
	
	private String my_category;
	
	private String my_atomic_weight;
	
	private String my_boiling_point;
	
	private String my_melting_point;
	
	private String my_density;
	
	private String my_density_tag;
	
	
	public Elemental()
	{
		
	}
	
	public Elemental(int atomic_num, String symbol, String name, String phase, 
			String category, String atomic_weight, String boiling_point, String melting_point, 
			String density, String density_tag)
	{
		this.my_atomic_num = atomic_num;
		this.my_symbol = symbol;
		this.my_name = name;
		this.my_phase = phase;
		this.my_category = category;
		this.my_atomic_weight = atomic_weight;
		this.my_boiling_point = boiling_point;
		this.my_melting_point = melting_point;
		this.my_density = density;
		this.my_density_tag = density_tag;
	}

	public int getAtomicNum()
	{
		return this.my_atomic_num;
	}

	public void setAtomicNum(int atomic_num)
	{
		this.my_atomic_num = atomic_num;
	}

	public String getSymbol()
	{
		return this.my_symbol;
	}

	public void setSymbol(String symbol)
	{
		this.my_symbol = symbol;
	}

	public String getName()
	{
		return this.my_name;
	}

	public void setName(String name)
	{
		this.my_name = name;
	}

	public String getPhase()
	{
		return this.my_phase;
	}

	public void setPhase(String phase)
	{
		this.my_phase = phase;
	}
	
	public String getCategory()
	{
		return this.my_category;
	}
	
	public void setCategory(String category)
	{
		this.my_category = category;
	}

	public String getAtomicWeight()
	{
		return this.my_atomic_weight;
	}

	public void setAtomicWeight(String atomic_weight)
	{
		this.my_atomic_weight = atomic_weight;
	}

	public String getBoilingPoint()
	{
		return this.my_boiling_point;
	}

	public void setBoilingPoint(String boiling_point)
	{
		this.my_boiling_point = boiling_point;
	}

	public String getMeltingPoint()
	{
		return this.my_melting_point;
	}

	public void setMeltingPoint(String melting_point)
	{
		this.my_melting_point = melting_point;
	}

	public String getDensity()
	{
		return this.my_density;
	}

	public void setDensity(String density)
	{
		this.my_density = density;
	}
	
	public String getDensityTag()
	{
		return this.my_density_tag;
	}

	public void setDensityTag(String density_tag)
	{
		this.my_density_tag = density_tag;
	}
}
