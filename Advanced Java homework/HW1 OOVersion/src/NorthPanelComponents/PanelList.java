package NorthPanelComponents;

public enum PanelList 
{
	PaintTool("PaintToolPanel"),
	Brush("BrushPanel"),
	Fill("FillPanel"),
	BrushColorButton("BrushColorButtonPanel"),
	ClearButton("ClearButtonPanel");
	
	private String description;
	
	private PanelList(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	
}
