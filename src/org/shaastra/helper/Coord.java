package org.shaastra.helper;

public class Coord {
	public String name;
	public String phone;
	public String subEvent;
	public String event;
	public String name1,name2,name3;
	
	public Coord(){
		super();
	}
	public Coord(String name, String phone,String subEvent,String event)
	{
		super();
		this.name=name;
		this.phone=phone;
		this.subEvent=subEvent;
		this.event=event;
		String[] parts =name.split("\\s");
		name1=new String();
		name2=new String();
		name3=new String();
		
		name1=parts[0];
		if(parts.length>=2)
		{
			name2=parts[1];
		}
		if(parts.length>=3)
		{
			name3 = parts[2];
		}
		
	}
	
	
	
	
}
