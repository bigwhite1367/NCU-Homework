package View;


import javax.swing.JPanel;

import Factories.PanelFactory;
import NorthPanelComponents.PaintToolPanel;
import NorthPanelComponents.PanelList;

public class NorthPanel extends JPanel 
{	
	private static NorthPanel instance = null;
	private PanelFactory pFactory;
	private NorthPanel()
	{
		//add(PaintToolPanel.getInstance());
		pFactory = new PanelFactory();
		for(PanelList panel: PanelList.values())
		{
			add(pFactory.createPanel(panel.getDescription()));
		}
	}
	public static NorthPanel getInstance()
	{
		if(instance == null)
		{
			return instance = new NorthPanel();
		}
		
		return instance;
		
	}
}
