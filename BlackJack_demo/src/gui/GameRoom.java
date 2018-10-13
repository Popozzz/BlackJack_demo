package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import blackjack.Card;
import blackjack.Dealer;
import blackjack.Player;

public class GameRoom extends JFrame{
	
	private static final long serialVersionUID = -6955575355605769631L;
	
	private Dealer dealer;
	private ArrayList<Player> players;
	
	private JButton star;
	private JLabel label;
	private JButton recieve;
	private JButton reject;
	
	private JPanel north;
	private GridBagConstraints n;
	private JPanel south;
	private GridBagConstraints s;
	private JPanel west;
	private GridBagConstraints w;
	private JPanel east;
	private GridBagConstraints e;
	private JPanel center;
	
	// 初始化基本布局
	public GameRoom(Dealer dealer, ArrayList<Player> players) {
		
		setTitle("BlackJack");
		setSize(1070, 840);
		
		this.getContentPane().setBackground(new Color(0,112,26)); // 设置背景颜色
		this.getContentPane().setLayout(null); 	
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	    this.setLayout(new BorderLayout());
	    this.setLocationRelativeTo(null);
	    this.dealer = dealer;
	    this.players = players;
	    ready();
	}
	
	public void north(){
		north = new JPanel();
		north.setBackground(new Color(0,112,26));
		
		n = new GridBagConstraints();
		north.setLayout(new GridBagLayout());
		
		n.insets=new Insets(10,0,0,-45);
		
		n.gridx=0;n.gridy=1;
		north.add(new MyLabel("庄家",14),n);
		n.gridx=0;n.gridy=2;
		north.add(new MyLabel("￥"+dealer.getMoney(),14),n);
		n.gridx=0;n.gridy=3;
		north.add(new MyLabel("",14),n);
		
		north.setName("north");
		this.add(north,BorderLayout.NORTH);
		north.setVisible(true);
		north.updateUI();

	}
	
	public void south(){
		
		south = new JPanel();
		south.setBackground(new Color(0,112,26));
		s = new GridBagConstraints();
		south.setLayout(new GridBagLayout());
		
		s.insets=new Insets(0,0,10,-45);
		
		s.gridx=0;s.gridy=2;
		south.add(new MyLabel(players.get(1).getId(),14),s);
		s.gridx=0;s.gridy=1;
		south.add(new MyLabel("￥"+players.get(1).getMoney(),14),s);
		s.gridx=0;s.gridy=0;
		south.add(new MyLabel("",14),s);
		
		south.setName("south");
		this.add(south,BorderLayout.SOUTH);
		south.setVisible(true);
		south.updateUI();
	}
	
	public void west(){
		
		west = new JPanel();
		west.setBackground(new Color(0,112,26));
		w = new GridBagConstraints();
		west.setLayout(new GridBagLayout());
		
		w.insets=new Insets(-65,10,0,0);
		
		w.gridx=1;w.gridy=0;
		west.add(new MyLabel(players.get(0).getId(),14),w);
		w.gridx=2;w.gridy=0;
		west.add(new MyLabel("￥"+players.get(0).getMoney(),14),w);
		w.gridx=3;w.gridy=0;
		west.add(new MyLabel("",14),w);

		west.setName("west");
		this.add(west,BorderLayout.WEST);
		west.setVisible(true);
		west.updateUI();
	}
	
	public void east(){
		east = new JPanel();
		east.setBackground(new Color(0,112,26));
		e = new GridBagConstraints();
		east.setLayout(new GridBagLayout());
		
		e.insets=new Insets(-65,0,0,10);
		e.gridx=2;e.gridy=0;
		east.add(new MyLabel(players.get(2).getId(),14),e);	
		e.gridx=1;e.gridy=0;
		east.add(new MyLabel("￥"+players.get(2).getMoney(),14),e);
		e.gridx=0;e.gridy=0;
		east.add(new MyLabel("",14),e);
		
		east.setName("east");
		this.add(east,BorderLayout.EAST);
		east.setVisible(true);
		east.updateUI();
	}
	
	// 中间布局
	public void center() {
		center = new JPanel();

		GridBagConstraints i = new GridBagConstraints();
		center.setLayout(new GridBagLayout());
		center.setBackground(new Color(0,112,26));
		
		i.insets=new Insets(0,0,50,20);
		
		i.gridx=1;i.gridy=0;
		star = new MyButton("再来一局").init("star");
		center.add(star,i);
		
		i.gridx=1;i.gridy=1;
		label = new MyLabel("是否要牌？",16);
		center.add(label,i);
		
		i.gridx=0;i.gridy=2;
		recieve = new JButton("要");
		center.add(recieve,i);
		
		i.gridx=2;i.gridy=2;
		reject = new JButton("不要");
		center.add(reject,i);
		center.setName("center");
		this.add(center,BorderLayout.CENTER);
		center.setVisible(true);
		center.updateUI();
		
		JFrame f = this;
		
		star.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{

				clean();
				// 清空手牌和牌堆
				dealer.setStandCount(0);
				dealer.getDeck().clear();
				dealer.getCard().removeAll(dealer.getCard());
				dealer.setStand("");
				for(int i=0;i<players.size();i++)
				{
					players.get(i).getCard().removeAll(players.get(i).getCard());
					players.get(i).setStand("");
				}
				
				makeBet();				
				dealer.washCard();
				
				dealer.firstTwoCard(players);
				
				addFirstTwoCard(dealer, players);
				
				Timer timer = new Timer();// 实例化Timer类  
		        timer.schedule(new TimerTask() {  
		            public void run() {

						if(dealer.isBlackJack())
						{
							dealer.toTheEnd(players);
							postResult();
							return;
						}
		    			if(dealer.postToSelf())
		    			{
		    				addCard(1, "");	
		    			}
		    			else
		    			{
		    				changeState(1);
		    			}
		    			if(dealer.postCardToComputerPlayer(players.get(0)))
		    			{
		    				addCard(2, "");
		    			}
		    			else
		    			{
		    				changeState(2);
		    			}
		    			showSouth();
		                this.cancel();  
		            }  
		        }, 3000);// 这里百毫秒  
					
			}
		});
		
		recieve.addActionListener(new ActionListener() // 要牌事件
		{
			public void actionPerformed(ActionEvent e) 
			{
				int count = dealer.getDeck().numberOfCard();
				if(count>0)
				{
					dealer.postCardToPlayer(players.get(1));
					addCard(3,String.valueOf(players.get(1).getCard().getLast().getIndex()));
				}
				else
				{
					dealer.toTheEnd(players);
					postResult();
					return;
				}

				hideSouth();
				//System.out.println(players.get(1).getPoint());
				if(players.get(1).getMinPoint()>21)
				{			
					players.get(1).setStand("stand");
					changeState(3);
					dealer.setStandCount(dealer.getStandCount()+1);
					reject.doClick();					
					return;
				}
				if(dealer.postCardToComputerPlayer(players.get(2)))
				{
					addCard(4,"");
				}
				else
				{
					changeState(4);
				}
				
				//下一轮
				Timer timer = new Timer();// 实例化Timer类  
		        timer.schedule(new TimerTask() {  
		            public void run() {  
		            	if(dealer.getStandCount()==4)
		            	{
		            		dealer.toTheEnd(players);
		            		postResult();
		            		return;
		            	}
						if(dealer.postToSelf())
						{
							addCard(1, "");	
						}
						else
						{
							changeState(1);
						}
						if(dealer.postCardToComputerPlayer(players.get(0)))
						{
							addCard(2, "");
						}
						else
						{
							changeState(2);
						}

						showSouth();
		                this.cancel();  
		            }  
		        }, 5000);// 这里百毫秒  

			}
		});
		
		reject.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{

				if(players.get(1).getStand()=="")
				{
					if(players.get(1).getMaxPoint()<17)
					{
						JOptionPane.showMessageDialog(f,"手牌点数低于17，不能stand！","error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
					{
						changeState(3);
						players.get(1).setStand("stand");
						dealer.setStandCount(dealer.getStandCount()+1);
						hideSouth();
					}
				}

				if(dealer.postCardToComputerPlayer(players.get(2)))
				{
					addCard(4,"");
				}
				else
				{
					changeState(4);
				}

				//下一轮
				Timer timer = new Timer();// 实例化Timer类  
		        timer.schedule(new TimerTask() {  
		            public void run() { 
			          	if(dealer.getStandCount()==4)
		            	{
			          		dealer.toTheEnd(players);
		            		postResult();
		            		return;
		            	}
						if(dealer.postToSelf())
						{
							addCard(1, "");	
						}
						else
						{
							changeState(1);
						}
						if(dealer.postCardToComputerPlayer(players.get(0)))
						{
							addCard(2, "");
						}
						else
						{
							changeState(2);
						}
						
						if(dealer.postCardToComputerPlayer(players.get(2)))
						{
							addCard(4, "");
						}
						else
						{
							changeState(4);
						}
						reject.doClick();
		                this.cancel();  
		            }  
		        }, 3000);// 这里百毫秒  			
			}
		});
	}
	
	// 玩家和牌布局初始化
	public void ready(){
		north();
		south();
		west();
		east();
		center();
		init();
	}
	
	public void showSouth(){
		
		label.setVisible(true);
		recieve.setVisible(true);
		reject.setVisible(true);
	}
	
	public void hideSouth(){
		label.setVisible(false);
		recieve.setVisible(false);
		reject.setVisible(false);
	}
	
	public void init(){
		star.setVisible(false);
		hideSouth();
	}
	
	//把牌加到手上
	public void addCard(int order,String str){
		JPanel p = new JPanel();
		GridBagConstraints i = new GridBagConstraints();
		if(order==1)
		{
			p = north;
			i = n;
			if(i.gridx==0)
			{
				i.gridx = 10;
			}
			i.gridx -= 1;i.gridy=0;
		}
		else if(order==3)
		{
			p = south;
			i = s;
			if(i.gridx==0)
			{
				i.gridx = 10;
			}
			i.gridx -= 1;i.gridy=3;
		}
		else if(order==2)
		{
			p = west;
			i = w;
			if(i.gridy==0)
			{
				i.gridy=10;
			}
			i.gridx=0;i.gridy -= 1;
		}else if(order==4)
		{
			p = east;
			i = e;
			if(i.gridy==0)
			{
				i.gridy = 10;
			}
			i.gridx=3;i.gridy -= 1;
		}
		if(str!="")
		{
			p.add(new CardLabel(str), i);
		}
		else
			p.add(new CardLabel(), i);
		
		p.updateUI();
	}
	
	public JPanel getPanel(int order){
		if(order==1)
			return north;
		else if(order==2)
			return west;
		else if(order==3)
			return south;
		else
			return east;
	}
	
	public void showAllCard(int order,LinkedList<Card> handcard){
		
		JPanel p = getPanel(order);
		int len = p.getComponentCount();
		CardLabel c;

		for(int i=3,j=0;i<len;i++,j++)
		{
			c = (CardLabel) p.getComponent(i);
			c.setIcon(String.valueOf(handcard.get(j).getIndex()));
		}
		p.updateUI();
	}
	
	public void addFirstTwoCard(Dealer dealer,ArrayList<Player> players){
		
		this.addCard(1, String.valueOf(dealer.getCard().getFirst().getIndex()));
		for(int i=0;i<3;i++)
		{
			this.addCard(i+2, String.valueOf(players.get(i).getCard().getFirst().getIndex()));
		}
		
		this.addCard(1, "");
		
		for(int i=0;i<3;i++)
		{
			if(i+2==3)
			{
				this.addCard(i+2, String.valueOf(players.get(i).getCard().getLast().getIndex()));
			}
			else
			{
				this.addCard(i+2, "");
			}
		}
	}
	
	public void changeState(int order){
		
		JPanel p = this.getPanel(order);
		
		MyLabel label;
		label = (MyLabel) p.getComponent(2);
		label.setText("stand");
		p.updateUI();
	}
	
	public void makeBet()
	{
		@SuppressWarnings("unused")
		MakeBetDialog makeBet = new MakeBetDialog(this,players.get(1));
	}
	
	public void postResult(){ // 公布结果，通过对话框
		JOptionPane.showMessageDialog(this,"游戏结束！","game over", JOptionPane.INFORMATION_MESSAGE);
		showAllCard(1,dealer.getCard());
		showAllCard(2,players.get(0).getCard());
		showAllCard(4,players.get(2).getCard());
		@SuppressWarnings("unused")
		ResultDialog result = new ResultDialog(this,players,dealer);
		
		star.setVisible(true);
	}
	
	public void clean(){
		
		this.north.removeAll();
		this.south.removeAll();
		this.west.removeAll();
		this.east.removeAll();
		this.remove(north);
		this.remove(south);
		this.remove(west);
		this.remove(east);
		north();
		south();
		west();
		east();
		init();
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}

}
