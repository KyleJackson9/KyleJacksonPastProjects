

public class Piece {
	private boolean isFire;
	private Board b;
	public int x;
	public int y;
	private String type;
	public boolean king;
	private boolean capture;


	public Piece(boolean isFire, Board board, int xx, int yy, String typee){
		this.isFire = isFire;
		b = board;
		x = xx;
		y = yy;
		type = typee;
		king = false;


	}
	private int x(){
		return x;
	}

	private int y(){
		return y;
	}

	public boolean isFire(){
		return isFire;
	}

	public int side(){
		if (isFire() == true){
			return 1;
		}
		else{
			return 0;
		}
	}

	public String type(){
		return type;
	}

	public boolean isKing(){
		if  (isFire() && y ==7) {
			king = true;
		}
		else if (!isFire() && y ==0) {
			king = true;
		}
	
		return king;
	}
	private void explode(int x, int y){
			if (isBomb() && hasCaptured()){
				b.remove(x,y);	
			}
	}

	public  boolean isBomb(){
		if (type == "bomb"){
			return true;
		}
		else {
			return false;
		}
	}

	public  boolean isShield(){
		if (type == "shield"){
			return true;
		}
		else{
			return false;
		}
	}

	public void move(int xx, int yy){

		if (Math.abs(xx - x) == 2){
			if (Math.abs(yy -y)==2) {
				capture = true;
				}
			
		} else{
			capture = false;
		}
		
		x = xx; 
		y = yy;
		explode(x,y);

	}

	public boolean hasCaptured(){

		return capture;
	}

	public void doneCapturing(){ //needs work
		while (hasCaptured()){
			move(0,0);
		}

	}
}