package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel  extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyLabel(String a,int b)
	{
		super();
		setText(a);
		this.setForeground(new Color(255,255,255));
		setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,b));
	}

}
