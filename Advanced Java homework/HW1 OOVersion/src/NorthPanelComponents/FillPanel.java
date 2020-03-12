package NorthPanelComponents;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class FillPanel extends NorthPanelModel 
{
	private static FillPanel instance = null;
	private JLabel label;
	private String fill = "填滿";
	private JCheckBox filled;
	
	private FillPanel()
	{
		setLayout(new GridLayout(2,1));
		
		label = new JLabel(fill);
		add(label);
		
		filled = new JCheckBox();
		add(filled);
		
	}
	
	public static FillPanel getInstance()
	{
		if (instance == null)
		{
			instance = new FillPanel();
		}
		return instance;
	}
}
