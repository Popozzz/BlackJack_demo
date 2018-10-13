package blackjack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
	private ArrayList<Card> deck; // 发牌的牌从deck拿，牌堆的结构类似栈，发牌的操作就类似出栈，也可以用LinkedList
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
	
	public void init() // 牌的初始化，也就是洗牌
	{
		d = new Stack<Card>();
		HashSet<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		for(int i=0; i<52; i++)
		{
			int a = random.nextInt(52);
			if(set.contains(a)) // 集合里面有没有这个随机数？（利用集合里面元素不重复的特性）
			{
				i--; // 存在，i-1，继续循环
			}
			else{
				set.add(a);	// 不存在，随机数加入集合
				d.push(deck.get(a));
			}
		}
	}
	
	public int numberOfCard() // 获取牌堆牌数，也就是剩下多少牌
	{
		return this.d.size();
	}
	
	public Card popCard() // 发牌，牌堆出栈
	{
		return d.pop();
	}
	
	public void clear(){
		d.removeAllElements();
	}
}
