package blackjack;

import java.util.LinkedList;

public class Player {
	
	private String id; // 玩家id，唯一标志
	private int bet; // 玩家下的赌注，讲倍，一倍200
	private int money; // 玩家现有金钱
	private HandCard handCard; // 玩家手牌
	private String stand; // 站立状态布尔值，用于判断是否是站立状态
	private int result; // 玩家的结果：-1为输，0为平局，1为赢
	
	public Player(String id,int money){ // 玩家初始化，id和现有金钱
		this.setId(id);
		this.setMoney(money);
		this.handCard = new HandCard();
		this.stand = "";
		this.bet = 1;
	}
	
	public void makeBet(int bet) // 玩家下注
	{
		this.setBet(bet);
	}
	
	public boolean chanceToWin(){ // 估计要牌后的点数会爆掉的概率，大于0.5就拒绝要牌
		int chance = 21 - this.handCard.getIdeaCount();
		if(13-chance>7)
		{
			return true;
		}
		return false;
	}
	
	public void receiveCard(Card c)	// 玩家接受发牌，也就是玩家要牌
	{
		this.handCard.addCardToHand(c);
	}
	
	public boolean rejectCard() // 玩家拒绝要牌
	{
		if(this.handCard.getMaxCount()>=17)
		{
			this.setStand("stand"); // 修改玩家状态，改为站立状态
			return true;
		}
		return false;
	}
	
	public int getPoint() // 结算玩家点数
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
	
	public int win() // 玩家获胜
	{
		int win;
		win = this.getBet()*200;
		int money = this.getMoney() + win;
		this.setMoney(money);
		this.setResult(1);
		return win;
	}
	
	public int lost() // 玩家输
	{
		int lost;
		lost = this.getBet()*200;
		int money = this.getMoney() - lost;
		this.setMoney(money);
		this.setResult(-1);
		return lost;
	}
	
	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getStand() {
		return stand;
	}

	public void setStand(String stand) {
		this.stand = stand;
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

}
