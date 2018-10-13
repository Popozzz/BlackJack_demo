package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import blackjack.Dealer;
import blackjack.Player;

public class ResultDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultDialog(JFrame f,ArrayList<Player> players,Dealer dealer)
	{
		super(f,"公布结果");
		setModal(true);
		setSize(300,300);
		setVisible(false);
		this.setLocationRelativeTo(null);
		
		this.getContentPane().setBackground(new Color(0,112,26));
		
		GridBagConstraints i = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());
		i.insets=new Insets(20,20,0,0);
		
		i.gridx = 0; i.gridy = 0;
		this.add(new MyLabel("人物",14),i);	
		i.gridx = 1; i.gridy = 0;
		this.add(new MyLabel("点数",14),i);			
		i.gridx = 2; i.gridy = 0;
		this.add(new MyLabel("积分",14),i);			
		i.gridx = 3; i.gridy = 0;
		this.add(new MyLabel("结果",14),i);
		
		String s = "";
		i.gridx = 0; i.gridy = 1;
		this.add(new MyLabel("庄家",14),i);		
		i.gridx = 1; i.gridy = 1;
		this.add(new MyLabel(String.valueOf(dealer.getPoint()),14),i);
		i.gridx = 2; i.gridy = 1;
		this.add(new MyLabel(String.valueOf(dealer.getMoney()),14),i);
		
		if(dealer.getResult()==1)
		{
			s = "胜";
		}
		else if(dealer.getResult()==0)
		{
			s = "平";
		}
		else if(dealer.getResult()==-1)
		{
			s = "输";
		}
		i.gridx = 3; i.gridy = 1;
		this.add(new MyLabel(s,14),i);
			
		for(int j=0,y=2;j<3;j++,y++)
		{
			i.gridx = 0; i.gridy = y;
			this.add(new MyLabel(players.get(j).getId(),14),i);		
			i.gridx = 1; i.gridy = y;
			this.add(new MyLabel(String.valueOf(players.get(j).getPoint()),14),i);
			i.gridx = 2; i.gridy = y;
			this.add(new MyLabel(String.valueOf(players.get(j).getMoney()),14),i);
			
			if(players.get(j).getResult()==1)
			{
				s = "胜";
			}
			else if(players.get(j).getResult()==0)
			{
				s = "平";
			}
			else if(players.get(j).getResult()==-1)
			{
				s = "输";
			}
			
			i.gridx = 3; i.gridy = y;
			this.add(new MyLabel(s,14),i);
		}
		
		setVisible(true);
		
	}

}
