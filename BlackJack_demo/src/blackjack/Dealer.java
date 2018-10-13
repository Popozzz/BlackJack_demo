package blackjack;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dealer {
	
	private int money; // 庄家现有金钱
	private HandCard handCard; // 庄家手牌
	private int result; // 庄家的结果：-1为输，0为平局，1为赢
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
	
	public void washCard() // 庄家洗牌
	{
		deck.init();
	}
	
	public void firstTwoCard(ArrayList<Player> players) // 庄家发前面两张牌
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
	
	public boolean isBlackJack() // 判断庄家是否是BlackJack
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
	
	public void postCardToPlayer(Player player) // 庄家常规发牌
	{
		if(player.getStand()=="") // 玩家状态不是站立
		{
			Card c = deck.popCard();
			player.receiveCard(c);
		}
	}
	
	public boolean rejectCard() // 庄家拒绝要牌
	{
		if(this.getMaxPoint()>=17)
		{
			this.setStand("stand"); // 修改庄家状态，改为站立状态
			return true;
		}
		return false;
	}
	
	public void receiveCard(Card c)
	{
		this.handCard.addCardToHand(c);
	}
	
	public int getPoint() // 结算庄家点数
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
	
	public void toTheEnd(ArrayList<Player> players) // 最后，判断胜负
	{
		int dealerCount = getPoint();
		int dealerMoney = this.getMoney();
		for(int i=0,len=players.size(); i<len; i++)
		{
			int playerCount = players.get(i).getPoint();
			if(dealerCount<=21) // 庄家小于21点
			{
				if(playerCount<dealerCount||playerCount>21) // 玩家比庄家小或者玩家大于21点，玩家输
				{
					dealerMoney = dealerMoney + players.get(i).lost();
				}else if(playerCount>dealerCount) // 玩家大于庄家且玩家小于等于21点，玩家赢
				{
					dealerMoney = dealerMoney - players.get(i).win();
				}else // 玩家与庄家相等，平局
				{
					players.get(i).setResult(0);
				}
			}
			else // 庄家大于21点，即爆掉
			{
				if(playerCount>21) // 玩家大于21点，爆掉，平局
				{
					players.get(i).setResult(0);
				}else // 玩家小于等于21点，玩家赢
				{
					dealerMoney = dealerMoney - players.get(i).win();
				}
			}
		}
		
		if(dealerMoney>this.getMoney()) // 根据庄家的赢钱情况判断庄家是赢还是输
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
		
		this.setMoney(dealerMoney); // 更新庄家的现有金钱
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
