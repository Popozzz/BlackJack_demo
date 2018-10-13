package blackjack;

import java.util.LinkedList;

public class Player {
	
	private String id; // ���id��Ψһ��־
	private int bet; // ����µĶ�ע��������һ��200
	private int money; // ������н�Ǯ
	private HandCard handCard; // �������
	private String stand; // վ��״̬����ֵ�������ж��Ƿ���վ��״̬
	private int result; // ��ҵĽ����-1Ϊ�䣬0Ϊƽ�֣�1ΪӮ
	
	public Player(String id,int money){ // ��ҳ�ʼ����id�����н�Ǯ
		this.setId(id);
		this.setMoney(money);
		this.handCard = new HandCard();
		this.stand = "";
		this.bet = 1;
	}
	
	public void makeBet(int bet) // �����ע
	{
		this.setBet(bet);
	}
	
	public boolean chanceToWin(){ // ����Ҫ�ƺ�ĵ����ᱬ���ĸ��ʣ�����0.5�;ܾ�Ҫ��
		int chance = 21 - this.handCard.getIdeaCount();
		if(13-chance>7)
		{
			return true;
		}
		return false;
	}
	
	public void receiveCard(Card c)	// ��ҽ��ܷ��ƣ�Ҳ�������Ҫ��
	{
		this.handCard.addCardToHand(c);
	}
	
	public boolean rejectCard() // ��Ҿܾ�Ҫ��
	{
		if(this.handCard.getMaxCount()>=17)
		{
			this.setStand("stand"); // �޸����״̬����Ϊվ��״̬
			return true;
		}
		return false;
	}
	
	public int getPoint() // ������ҵ���
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
	
	public int win() // ��һ�ʤ
	{
		int win;
		win = this.getBet()*200;
		int money = this.getMoney() + win;
		this.setMoney(money);
		this.setResult(1);
		return win;
	}
	
	public int lost() // �����
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
