

public class Player extends Person{
	private String name;//玩家名稱
    private int chips;//現有的籌碼
    private int bet;//下注的籌碼
    
    public Player(String name, int chips){//玩家的建構式
    	this.name = name;
    	this.chips = chips;
    }
    public String getName(){
    	return name;//回傳玩家名稱
    }
    public int makeBet(){//下注
    	if(chips != 0){//假如還有籌碼
    		bet = 1;
    	}
    	return bet;
    }
    public int getCurrentChips(){
    	return chips;//回傳籌碼
    }
    public void increaseChips (int diff){
    	chips += diff;//把原本的籌碼加上這局贏或輸的錢
    }
    public void sayHello(){//跟玩家說你好
    	System.out.println("Hello, I am " + name + ".");
    	System.out.println("I have " + chips + " chips.");
    }
	@Override
	public boolean hit_me(Table table) {
		int total = getTotalValue();//取得目前拿到的點數總和
    	if(total<=16)
    		{
    			return true;//小於等於16就再要牌
    		}
    	else
    		{
    			return false;//加如大於16回傳false
    		}
	}

}
