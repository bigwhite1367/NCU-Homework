package Factories;

import NorthPanelComponents.BrushColorButtonPanel;
import NorthPanelComponents.BrushPanel;
import NorthPanelComponents.ClearButtonPanel;
import NorthPanelComponents.FillPanel;
import NorthPanelComponents.NorthPanelModel;
import NorthPanelComponents.PaintToolPanel;

public class PanelFactory 
{
	private NorthPanelModel model;
	
	public NorthPanelModel createPanel(String name)
	{
		if(name == "PaintToolPanel")
		{
			return model = PaintToolPanel.getInstance();
		}
		else if(name == "BrushPanel")
		{
			return model = BrushPanel.getInstance();
		}
		else if(name == "FillPanel")
		{
			return model = FillPanel.getInstance();
		}
		else if(name == "BrushColorButtonPanel")
		{
			return model = BrushColorButtonPanel.getInstance();
		}
		else if(name == "ClearButtonPanel")
		{
			return model = ClearButtonPanel.getInstance();
		}
		else
		{
			return null;
		}
	
	}
	
}
