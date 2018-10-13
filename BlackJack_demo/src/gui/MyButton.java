package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyButton(String str){
		
		super(str);

		this.setBackground(new Color(0,112,26));
		this.setBorder(null);
		this.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,16));
		this.setForeground(new Color(255,255,255));

	}
	
	public MyButton init(String s){
		
		String fileName = "images/" + s + ".png";
		/*String path = MyButton.class.getResource(fileName).getPath(); 
		System.out.println( MyButton.class.getResource(fileName).getPath());*/
		File f = new File(fileName);
		System.out.println(f.getAbsolutePath());
		ImageIcon icon = new ImageIcon(f.getPath());
		this.setIcon(icon);
		
		return this;
	}

}
