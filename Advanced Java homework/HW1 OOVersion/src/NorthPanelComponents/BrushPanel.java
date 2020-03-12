package NorthPanelComponents;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BrushPanel extends NorthPanelModel
{
	private static BrushPanel instance = null;
	private JLabel label;
	private String brush = "筆刷";
	private JPanel btnPanel;
	private JRadioButton[] brushSizeBtns;
	private final String[] size = {"小","中","大"};
	private ButtonGroup btnGroup;
	
	private BrushPanel()
	{
		setLayout(new GridLayout(2,1));
		
		label = new JLabel(brush);
		add(label);
		
		btnGroup = new ButtonGroup();
		btnPanel = new JPanel();
		brushSizeBtns = new JRadioButton[3];
		for(int loopCounter=0; loopCounter<size.length; loopCounter++)
		{
			brushSizeBtns[loopCounter] = new JRadioButton(size[loopCounter],true);
			btnGroup.add(brushSizeBtns[loopCounter]);
			btnPanel.add(brushSizeBtns[loopCounter]);
		}
		brushSizeBtns[0].setSelected(true);
		add(btnPanel);
	}
	
	public static BrushPanel getInstance()
	{
		if (instance == null)
		{
			instance = new BrushPanel();
		}
		return instance;
	}

}
