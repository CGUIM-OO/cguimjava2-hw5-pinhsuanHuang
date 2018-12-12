import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	public ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed;
	
	//TODO: Please implement the constructor (30 points)
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCard = new ArrayList<Card>();//建立一個arraylist
		openCard = new ArrayList<Card>();//建立一個arraylist
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int i=1;i<=nDeck;i++)
		{
			 for (Card.Suit s : Card.Suit.values())//將花色換成enum裡的花色 
				{	
					for(int rank=1;rank <= 13;rank++)
					{	
						Card card=new Card(s,rank);
						cards.add(card);
					}
				}
		
		}
		shuffle();
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)	
		for(int i = 0;i<cards.size();i++)
		{
		cards.get(i).printCard();
		}		
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	
	public void shuffle(){
		openCard.clear();//將打開的卡牌的卡堆清除，重新開始記用過的排
		usedCard.clear();//將用過的卡牌的卡堆清除，重新開始記用過的排
		nUsed=0;//用過的排的數量歸0
		int x = cards.size();
		for(int i=0;i<x;i++)		
		{			
			Random rnd = new Random();//取亂數值
			int j = rnd.nextInt(x);//j作為取出的亂數值
			Card temp = cards.get(i);//將temp = 位置i的card
			cards.set(i, cards.get(j));//將位置i的地方改為位置為j的card
			cards.set(j, temp);//將位置為j的地方改成剛剛存於temp裡的card
		}
	}
	public Card getOneCard(boolean isOpened){		
		if(nUsed == cards.size())//假如 nUsed = 牌組的大小(卡牌用光了) 
			{
			shuffle();//洗牌
			}
		Card send = cards.get(nUsed);//一張一張拿
		if(isOpened == true)
			{
				openCard.add(send);
			}
		nUsed++;//使用過的卡牌數量+1
		usedCard.add(send);//在使用過的卡牌ARRAYLIST裡面加入這次傳要發出去的牌		
		return send;//回傳卡片
		
	}
	 public ArrayList<Card> getOpenedCard(){
		 return openCard;
	 }
}
