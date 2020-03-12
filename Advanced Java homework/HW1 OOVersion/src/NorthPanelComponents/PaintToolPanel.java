package NorthPanelComponents;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PaintToolPanel extends NorthPanelModel 
{
	private static PaintToolPanel instance = null;
	private JLabel label;
	private JComboBox<ToolList> box;
	private final String labelStr = "繪圖工具";
	
	private PaintToolPanel()
	{
		setLayout(new GridLayout(2,1));
		label = new JLabel(labelStr);
		add(label);
		box = new JComboBox<ToolList>(ToolList.values());
		add(box);
		
	}
	public static PaintToolPanel getInstance()
	{
		if(instance == null)
		{
			return instance = new PaintToolPanel();
		}
		
		return instance;
		
	}
}
