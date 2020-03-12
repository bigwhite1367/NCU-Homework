package NorthPanelComponents;

import javax.swing.JButton;

public class ClearButtonPanel extends NorthPanelModel
{
	private static ClearButtonPanel instance = null;
	private JButton clearBtn;
	private String btnStr = "消除畫面";
	
	private ClearButtonPanel()
	{
		clearBtn =  new JButton(btnStr);
		add(clearBtn);
	}
	
	public static ClearButtonPanel getInstance()
	{
		if (instance == null)
		{
			instance = new ClearButtonPanel();
		}
		return instance;
	}
}
