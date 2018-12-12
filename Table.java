import java.util.ArrayList;

public class Table {
	static final int MAXPLAYER = 4;
	private Deck allcard;
	private Player player[];
	private Dealer boss;
	private int[] pos_betArray = new int[MAXPLAYER];
	
	Table(int nDeck){
		allcard = new Deck(nDeck);//新增一個nDeck*52的排堆
		player = new Player[MAXPLAYER];//實體化PLAYER陣列
	}
	public void set_player(int pos, Player p){
		if(pos >= MAXPLAYER)
			System.out.print("error");
		else
			player[pos] = p;//將每個玩家配到他輸入的位置上
		
	}
	
	public Player[] get_player(){
		return player;//回傳PLAYER陣列
	}
	public void set_dealer(Dealer d){
		boss = d;//設定莊家
	}
	public Card get_face_up_card_of_dealer(){
		return boss.getOneRoundCard().get(1);//回傳莊家打開的那張牌
	}
	
	private void ask_each_player_about_bets(){
		for(int i = 0;i < player.length;i++){
			player[i].sayHello();
			pos_betArray[i]=player[i].makeBet();//每個玩家打招呼，並顯示有多少本金
		}
	}
	private void distribute_cards_to_dealer_and_players(){
		for(int i = 0;i < player.length;i++){//發兩張打開的牌給玩家
			ArrayList<Card> playcard = new ArrayList<Card>(); 
			playcard.add(allcard.getOneCard(true));
			playcard.add(allcard.getOneCard(true));
			player[i].setOneRoundCard(playcard);
		}
		ArrayList<Card> bosscard = new ArrayList<Card>(); //發一張蓋的和一張開的牌給莊家
			bosscard.add(allcard.getOneCard(false));	
			bosscard.add(allcard.getOneCard(true));
			boss.setOneRoundCard(bosscard);
			System.out.print("Dealer's face up card is ");
			get_face_up_card_of_dealer().printCard();//顯示莊家打開的那張牌
	}
	private void ask_each_player_about_hits(){
		for(int i = 0;i < player.length;i++){//問每位玩家要不要再拿牌
			boolean hit=false;
			do{
				System.out.println(player[i].getName()+"'s Cards now:");
					for(Card c : player[i].getOneRoundCard()){
						c.printCard();
					}
					hit=player[i].hit_me(this);
					if(hit){
						System.out.print("Hit! ");
						player[i].getOneRoundCard().add(allcard.getOneCard(true));
					}
					else if(hit == false && player[i].getTotalValue()<=21){
						System.out.println(player[i].getName()+", Pass hit!");
					}	
					else
						System.out.println(player[i].getName()+"'s hit is over!");
			}while(hit);
		}
	}
	private void ask_dealer_about_hits(){//問莊家還要不要拿牌
		
		boolean hit=false;
		do{
			hit = boss.hit_me(this);
			if(hit){
				boss.getOneRoundCard().add(allcard.getOneCard(true));
			}
			else if(hit == false && boss.getTotalValue()<=21){
				System.out.println("Dealer's hit is over!");
			}	
			else{
				System.out.println("Dealer's hit is over!");
			}
		}while(hit);
	}
	private void calculate_chips(){//先顯示莊家的總數，和牌，再對每位玩家做比數的比較和顯示結果(玩家是輸贏還是平手)
			int i = 0;
			System.out.println("Dealer's card value is " + boss.getTotalValue()+",Cards:");
			boss.printAllCard();
			do{
			if(boss.getTotalValue()>21&&player[i].getTotalValue()<=21){
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(+1);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+", Get "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
			}
			else if(boss.getTotalValue()<=21&&player[i].getTotalValue()>21){
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(-1);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+", Loss "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
			}
			else if(boss.getTotalValue()>21&&player[i].getTotalValue()>21){
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(0);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+",chips have no change! The Chips now is: "+player[i].getCurrentChips());
			}
			else if(player[i].getTotalValue()<boss.getTotalValue()){
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(-1);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+", Loss "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
			}
			else if(player[i].getTotalValue()>boss.getTotalValue()){
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(1);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+",Get "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
			}
			else{
				System.out.println(player[i].getName()+"'s Cards:");
				player[i].printAllCard();
				player[i].increaseChips(0);
				System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue()+",chips have no change! The Chips now is: "+player[i].getCurrentChips());
			}
			i++;
			}while(i<MAXPLAYER);
	}
	public void play(){
			ask_each_player_about_bets();
			distribute_cards_to_dealer_and_players();
			ask_each_player_about_hits();
			ask_dealer_about_hits();
			calculate_chips();
		}
	public int[] get_palyers_bet() {
		return pos_betArray;//回傳陣列
	}	
}
