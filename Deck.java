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
		usedCard = new ArrayList<Card>();//�إߤ@��arraylist
		openCard = new ArrayList<Card>();//�إߤ@��arraylist
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int i=1;i<=nDeck;i++)
		{
			 for (Card.Suit s : Card.Suit.values())//�N��⴫��enum�̪���� 
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
		openCard.clear();//�N���}���d�P���d��M���A���s�}�l�O�ιL����
		usedCard.clear();//�N�ιL���d�P���d��M���A���s�}�l�O�ιL����
		nUsed=0;//�ιL���ƪ��ƶq�k0
		int x = cards.size();
		for(int i=0;i<x;i++)		
		{			
			Random rnd = new Random();//���üƭ�
			int j = rnd.nextInt(x);//j�@�����X���üƭ�
			Card temp = cards.get(i);//�Ntemp = ��mi��card
			cards.set(i, cards.get(j));//�N��mi���a��אּ��m��j��card
			cards.set(j, temp);//�N��m��j���a��令���s��temp�̪�card
		}
	}
	public Card getOneCard(boolean isOpened){		
		if(nUsed == cards.size())//���p nUsed = �P�ժ��j�p(�d�P�Υ��F) 
			{
			shuffle();//�~�P
			}
		Card send = cards.get(nUsed);//�@�i�@�i��
		if(isOpened == true)
			{
				openCard.add(send);
			}
		nUsed++;//�ϥιL���d�P�ƶq+1
		usedCard.add(send);//�b�ϥιL���d�PARRAYLIST�̭��[�J�o���ǭn�o�X�h���P		
		return send;//�^�ǥd��
		
	}
	 public ArrayList<Card> getOpenedCard(){
		 return openCard;
	 }
}
