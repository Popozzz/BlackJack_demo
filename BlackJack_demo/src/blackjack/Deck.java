package blackjack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
	private ArrayList<Card> deck; // ���Ƶ��ƴ�deck�ã��ƶѵĽṹ����ջ�����ƵĲ��������Ƴ�ջ��Ҳ������LinkedList
	private Stack<Card> d;
	
	public Deck(){
		deck = new ArrayList<Card>();
		for(int i=0;i<4;i++)
		{
			Card c = new Card("A",i+1);
			deck.add(c);
		}
		
		for(int i=4,j=0;i<40;i++)
		{
			if(i/4==0)
			{
				j = i/4;
			}
			else
				j = i/4 +1;
			Card c = new Card(String.valueOf(j),i+1);
			deck.add(c);
		}
		
		for(int i=40;i<44;i++)
		{
			Card c = new Card("J",i+1);
			deck.add(c);
		}
		
		for(int i=44;i<48;i++)
		{
			Card c = new Card("Q",i+1);
			deck.add(c);
		}
		
		for(int i=48;i<52;i++)
		{
			Card c = new Card("K",i+1);
			deck.add(c);
		}
		
	}
	
	public void init() // �Ƶĳ�ʼ����Ҳ����ϴ��
	{
		d = new Stack<Card>();
		HashSet<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		for(int i=0; i<52; i++)
		{
			int a = random.nextInt(52);
			if(set.contains(a)) // ����������û�����������������ü�������Ԫ�ز��ظ������ԣ�
			{
				i--; // ���ڣ�i-1������ѭ��
			}
			else{
				set.add(a);	// �����ڣ���������뼯��
				d.push(deck.get(a));
			}
		}
	}
	
	public int numberOfCard() // ��ȡ�ƶ�������Ҳ����ʣ�¶�����
	{
		return this.d.size();
	}
	
	public Card popCard() // ���ƣ��ƶѳ�ջ
	{
		return d.pop();
	}
	
	public void clear(){
		d.removeAllElements();
	}
}
