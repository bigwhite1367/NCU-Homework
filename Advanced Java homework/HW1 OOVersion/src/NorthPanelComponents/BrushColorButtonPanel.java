package NorthPanelComponents;

import javax.swing.JButton;

public class BrushColorButtonPanel extends NorthPanelModel
{
	private static BrushColorButtonPanel instance = null;
	private JButton changeBrushColorBtn;
	private final String btnstr = "筆刷顏色";
	
	private BrushColorButtonPanel()
	{
		changeBrushColorBtn = new JButton(btnstr);
		add(changeBrushColorBtn);
	}
	
	public static BrushColorButtonPanel getInstance()
	{
		if (instance == null)
		{
			instance = new BrushColorButtonPanel();
		}
		return instance;
	}
}
