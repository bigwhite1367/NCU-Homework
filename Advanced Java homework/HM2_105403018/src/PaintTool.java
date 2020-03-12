/*
康瀚中
105403018
資管2A
 */
public enum PaintTool 
{
	筆刷("筆刷"),
	直線("直線"),
	橢圓形("橢圓形"),
	矩形("矩形"),
	圓角矩形("圓角矩形");
	
	private String name;
	
	private PaintTool(String name) 
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name ;
	}
}
