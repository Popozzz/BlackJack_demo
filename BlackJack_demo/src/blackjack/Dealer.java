package blackjack;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dealer {
	
	private int money; // ׯ�����н�Ǯ
	private HandCard handCard; // ׯ������
	private int result; // ׯ�ҵĽ����-1Ϊ�䣬0Ϊƽ�֣�1ΪӮ
	private Deck deck;
	private String stand;
	private int standCount;
	
	public Dealer(){
		this.setMoney(5000);
		this.handCard = new HandCard();
		this.setStand("");
		this.standCount = 0;
		deck = new Deck();
	}
	
	public void washCard() // ׯ��ϴ��
	{
		deck.init();
	}
	
	public void firstTwoCard(ArrayList<Player> players) // ׯ�ҷ�ǰ��������
	{
		Card c = deck.popCard();
		this.receiveCard(c);
		c = deck.popCard();
		this.receiveCard(c);
		
		for(int i=0,len=players.size(); i<len; i++)
		{
			c = deck.popCard();
			players.get(i).receiveCard(c);
			c = deck.popCard();
			players.get(i).receiveCard(c);
		}
	}
	
	public boolean isBlackJack() // �ж�ׯ���Ƿ���BlackJack
	{
		if(this.getPoint()==21)
		{
			return true;
		}else 
			return false;
	}
	
	public boolean postToSelf(){
		if(this.getStand()=="")
		{
			if(this.getPoint()>=17)
			{
				this.rejectCard();
				standCount++;
				return false;
			}
			else{
				Card c = deck.popCard();
				this.receiveCard(c);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean postCardToComputerPlayer(Player player)
	{
		if(player.getStand()=="")
		{
			if(player.getPoint()>=17)
			{
				player.rejectCard();
				standCount++;
				return false;
			}
			else{
				Card c = deck.popCard();
				player.receiveCard(c);
				return true;
			}
		}
		return false;
	}
	
	public void postCardToPlayer(Player player) // ׯ�ҳ��淢��
	{
		if(player.getStand()=="") // ���״̬����վ��
		{
			Card c = deck.popCard();
			player.receiveCard(c);
		}
	}
	
	public boolean rejectCard() // ׯ�Ҿܾ�Ҫ��
	{
		if(this.getMaxPoint()>=17)
		{
			this.setStand("stand"); // �޸�ׯ��״̬����Ϊվ��״̬
			return true;
		}
		return false;
	}
	
	public void receiveCard(Card c)
	{
		this.handCard.addCardToHand(c);
	}
	
	public int getPoint() // ����ׯ�ҵ���
	{
		return this.handCard.getIdeaCount();
	}
	
	public int getMaxPoint()
	{
		return this.handCard.getMaxCount();
	}
	
	public int getMinPoint()
	{
		return this.handCard.getMinCount();
	}
	
	public void toTheEnd(ArrayList<Player> players) // ����ж�ʤ��
	{
		int dealerCount = getPoint();
		int dealerMoney = this.getMoney();
		for(int i=0,len=players.size(); i<len; i++)
		{
			int playerCount = players.get(i).getPoint();
			if(dealerCount<=21) // ׯ��С��21��
			{
				if(playerCount<dealerCount||playerCount>21) // ��ұ�ׯ��С������Ҵ���21�㣬�����
				{
					dealerMoney = dealerMoney + players.get(i).lost();
				}else if(playerCount>dealerCount) // ��Ҵ���ׯ�������С�ڵ���21�㣬���Ӯ
				{
					dealerMoney = dealerMoney - players.get(i).win();
				}else // �����ׯ����ȣ�ƽ��
				{
					players.get(i).setResult(0);
				}
			}
			else // ׯ�Ҵ���21�㣬������
			{
				if(playerCount>21) // ��Ҵ���21�㣬������ƽ��
				{
					players.get(i).setResult(0);
				}else // ���С�ڵ���21�㣬���Ӯ
				{
					dealerMoney = dealerMoney - players.get(i).win();
				}
			}
		}
		
		if(dealerMoney>this.getMoney()) // ����ׯ�ҵ�ӮǮ����ж�ׯ����Ӯ������
		{
			this.setResult(1);
		}
		else if(dealerMoney<this.getMoney())
		{
			this.setResult(-1);
		}
		else
		{
			this.setResult(0);
		}
		
		this.setMoney(dealerMoney); // ����ׯ�ҵ����н�Ǯ
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public LinkedList<Card> getCard(){
		return this.handCard.getHandCard();
	}

	public String getStand() {
		return stand;
	}

	public void setStand(String stand) {
		this.stand = stand;
	}
	
	public Deck getDeck(){
		return deck;
	}
	
	public int getStandCount(){
		return standCount;
	}
	
	public void setStandCount(int standCount){
		this.standCount = standCount;
	}
}
