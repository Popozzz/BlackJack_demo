package blackjack;

import java.util.LinkedList;

public class HandCard {
	
	private LinkedList<Card> handcard; // 要改！由于插入操作较多，使用LinkedList会更好
	
	public HandCard(){
		this.handcard = new LinkedList<Card>();
	}
	
	public void addCardToHand(Card c) // 玩家要牌，把发的牌加到自己的手牌（加到自己的手牌有个规矩：A放最后）
	{
		this.handcard.add(c);
	}
	
	public void sort() // 手牌排序，把A放最后就行
	{

		for(int i=0;i<this.handcard.size();i++)
		{
			if(this.handcard.get(i).getFaceValue().equals("A"))
			{
				Card c = this.handcard.remove(i);
				this.handcard.addLast(c);
			}
		}
	}
	
	public int getIdeaCount()
	{
		sort();
		int sum = 0;// 根据插牌的顺序（从小到大最后面都是A），可以先把前面的点数相加，后面的A根据情况取值
		int i;
		int last = 0;
		int len = this.handcard.size();
		for(i=0; i<len; i++)
		{
			//System.out.println("牌："+this.handcard.get(i).getFaceValue());
			if(this.handcard.get(i).getFaceValue()!="A") // 判断当前计算的牌是否是A（把常见的情况放前面效率更高）
			{
				sum = sum + this.handcard.get(i).getPoint(); // 常规点数相加
			}
			else
			{
				if(sum+11>21) // sum+11后的点数是否超过21点？
				{
					last = sum;
					sum = sum + 1; // 超过21点会爆掉，选择把A当做1
				}
				else
				{
					last = sum;
					sum = sum + 11;	// 不超过21点，选择把A当做11
				}
			}
		}
		if(sum<17 && last+11>21)
		{
			if(this.getHandCard().getLast().getFaceValue()=="A")
			{
				sum = sum - 1 + 11;
			}
		}

		return sum;
	}
	
	public int getMaxCount() {
		int sum = 0;// 根据插牌的顺序（从小到大最后面都是A），可以先把前面的点数相加，后面的A根据情况取值
		
		for(int i=0,len=this.handcard.size(); i<len; i++)
		{
			if(this.handcard.get(i).getFaceValue()!="A") // 判断当前计算的牌是否是A（把常见的情况放前面效率更高）
			{
				sum = sum + this.handcard.get(i).getPoint(); // 常规点数相加
			}
			else
			{
				sum = sum + 11;	// 把A当做11
			}
		}
		return sum;
	}
	
	public int getMinCount() {
		int sum = 0;// 根据插牌的顺序（从小到大最后面都是A），可以先把前面的点数相加，后面的A根据情况取值
		
		for(int i=0,len=this.handcard.size(); i<len; i++)
		{
			if(this.handcard.get(i).getFaceValue()!="A") // 判断当前计算的牌是否是A（把常见的情况放前面效率更高）
			{
				sum = sum + this.handcard.get(i).getPoint(); // 常规点数相加
			}
			else
			{
				sum = sum + 1;	// 把A当做1
			}
		}
		return sum;
	}
	
	public LinkedList<Card> getHandCard(){
		return this.handcard;
	}

}
