package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import blackjack.Player;

public class MakeBetDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton makeBet;
	
	public MakeBetDialog(JFrame f,Player player){
		super(f,"ÏÂ×¢");
		setModal(true);
		setSize(200,100);
		setVisible(false);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setBackground(new Color(255,255,255));
		
		GridBagConstraints i = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());
		i.insets=new Insets(0,20,0,20);
		
		i.gridx=0;i.gridy=0;
		String[] bet={"1×¢","2×¢","3×¢","4×¢","5×¢","6×¢","7×¢","8×¢","9×¢","10×¢"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox betList=new JComboBox(bet);
		betList.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,14));
		this.add(betList,i);
		
		makeBet = new JButton("ÏÂ×¢");
		i.gridx=1;i.gridy=0;
		this.add(makeBet,i);
		
		JDialog j = this;		
		makeBet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				String str = betList.getSelectedItem().toString();
				player.setBet(Integer.parseInt(str.substring(0,str.length()-1)));
				j.dispose();
				
			}
		});	
		setVisible(true);
	}
}
