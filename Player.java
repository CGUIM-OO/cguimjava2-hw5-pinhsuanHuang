

public class Player extends Person{
	private String name;//���a�W��
    private int chips;//�{�����w�X
    private int bet;//�U�`���w�X
    
    public Player(String name, int chips){//���a���غc��
    	this.name = name;
    	this.chips = chips;
    }
    public String getName(){
    	return name;//�^�Ǫ��a�W��
    }
    public int makeBet(){//�U�`
    	if(chips != 0){//���p�٦��w�X
    		bet = 1;
    	}
    	return bet;
    }
    public int getCurrentChips(){
    	return chips;//�^���w�X
    }
    public void increaseChips (int diff){
    	chips += diff;//��쥻���w�X�[�W�o��Ĺ�ο骺��
    }
    public void sayHello(){//�򪱮a���A�n
    	System.out.println("Hello, I am " + name + ".");
    	System.out.println("I have " + chips + " chips.");
    }
	@Override
	public boolean hit_me(Table table) {
		int total = getTotalValue();//���o�ثe���쪺�I���`�M
    	if(total<=16)
    		{
    			return true;//�p�󵥩�16�N�A�n�P
    		}
    	else
    		{
    			return false;//�[�p�j��16�^��false
    		}
	}

}
