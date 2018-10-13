package gui;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

	//自定义一个带有图标的Label控件
public class CardLabel extends JLabel{
	
	private static final long serialVersionUID = -1284840782614254279L;

	public CardLabel(){
		setIcon("rear");
	}
	
	public CardLabel(String s){
		
		setIcon(s);
		
	}

	public void setIcon(String s){
		
		String fileName = "images/" + s +".gif";
		//String path = getClass().getResource(fileName).getPath(); 
		File f = new File(fileName);
		ImageIcon icon = new ImageIcon(f.getPath());
		this.setIcon(icon);
	}
}
