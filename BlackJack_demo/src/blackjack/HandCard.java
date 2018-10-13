package blackjack;

import java.util.LinkedList;

public class HandCard {
	
	private LinkedList<Card> handcard; // Ҫ�ģ����ڲ�������϶࣬ʹ��LinkedList�����
	
	public HandCard(){
		this.handcard = new LinkedList<Card>();
	}
	
	public void addCardToHand(Card c) // ���Ҫ�ƣ��ѷ����Ƽӵ��Լ������ƣ��ӵ��Լ��������и���أ�A�����
	{
		this.handcard.add(c);
	}
	
	public void sort() // �������򣬰�A��������
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
		int sum = 0;// ���ݲ��Ƶ�˳�򣨴�С��������涼��A���������Ȱ�ǰ��ĵ�����ӣ������A�������ȡֵ
		int i;
		int last = 0;
		int len = this.handcard.size();
		for(i=0; i<len; i++)
		{
			//System.out.println("�ƣ�"+this.handcard.get(i).getFaceValue());
			if(this.handcard.get(i).getFaceValue()!="A") // �жϵ�ǰ��������Ƿ���A���ѳ����������ǰ��Ч�ʸ��ߣ�
			{
				sum = sum + this.handcard.get(i).getPoint(); // ����������
			}
			else
			{
				if(sum+11>21) // sum+11��ĵ����Ƿ񳬹�21�㣿
				{
					last = sum;
					sum = sum + 1; // ����21��ᱬ����ѡ���A����1
				}
				else
				{
					last = sum;
					sum = sum + 11;	// ������21�㣬ѡ���A����11
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
		int sum = 0;// ���ݲ��Ƶ�˳�򣨴�С��������涼��A���������Ȱ�ǰ��ĵ�����ӣ������A�������ȡֵ
		
		for(int i=0,len=this.handcard.size(); i<len; i++)
		{
			if(this.handcard.get(i).getFaceValue()!="A") // �жϵ�ǰ��������Ƿ���A���ѳ����������ǰ��Ч�ʸ��ߣ�
			{
				sum = sum + this.handcard.get(i).getPoint(); // ����������
			}
			else
			{
				sum = sum + 11;	// ��A����11
			}
		}
		return sum;
	}
	
	public int getMinCount() {
		int sum = 0;// ���ݲ��Ƶ�˳�򣨴�С��������涼��A���������Ȱ�ǰ��ĵ�����ӣ������A�������ȡֵ
		
		for(int i=0,len=this.handcard.size(); i<len; i++)
		{
			if(this.handcard.get(i).getFaceValue()!="A") // �жϵ�ǰ��������Ƿ���A���ѳ����������ǰ��Ч�ʸ��ߣ�
			{
				sum = sum + this.handcard.get(i).getPoint(); // ����������
			}
			else
			{
				sum = sum + 1;	// ��A����1
			}
		}
		return sum;
	}
	
	public LinkedList<Card> getHandCard(){
		return this.handcard;
	}

}
