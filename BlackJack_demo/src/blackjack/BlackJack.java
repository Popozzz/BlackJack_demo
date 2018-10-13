package blackjack;

import gui.GameRoom;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BlackJack {
	
	private Dealer dealer;
	private ArrayList<Player> players;
	private GameRoom room;
	
	public BlackJack(){
		dealer = new Dealer();
		players = new ArrayList<Player>();
		Player west = new Player("west",5000);
		Player south = new Player("Administrator",5000);
		Player east = new Player("east",5000);
		
		players.add(west);
		players.add(south);
		players.add(east);
		room = new GameRoom(dealer,players);
	}
	
	public void init(){ // ��Ϸ��ʼ����ָׯ��ϴ���Լ���ǰ��������
		room.makeBet();
		dealer.washCard();		
		dealer.firstTwoCard(players);	
		room.addFirstTwoCard(dealer, players);
	}
	
	public boolean isBlackJack(){ //�ж�ׯ���Ƿ���BlackJack?

		return dealer.isBlackJack();
	}
	
	public void gameOver(){
		dealer.toTheEnd(players);
		room.postResult();
	}
	
	public void regularPostCard(){
		
		Timer timer = new Timer();// ʵ����Timer��  
        timer.schedule(new TimerTask() {  
            public void run() {
            	
    			if(dealer.postToSelf())
    			{
    				room.addCard(1, "");	
    			}
    			else
    			{
    				room.changeState(1);
    			}
    			if(dealer.postCardToComputerPlayer(players.get(0)))
    			{
    				room.addCard(2, "");
    			}
    			else
    			{
    				room.changeState(2);
    			}
    			room.showSouth();
                this.cancel();  
            }  
        }, 3000);// ����ٺ���  
 
	}
	
	public void play(){
		if(isBlackJack())
		{
			gameOver();
		}
		else
		{
			regularPostCard();
		}
	}	
}
