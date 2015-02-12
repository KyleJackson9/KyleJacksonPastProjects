

public class Piece {
	private boolean isFire;
	private Board b;
	public int x;
	public int y;
	private String type;
	public boolean king;


	public Piece(boolean isFire, Board board, int xx, int yy, String typee){
		this.isFire = isFire;
		b = board;
		x = xx;
		y = yy;
		type = typee;
		king = false;


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
		return king;
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
		x = xx; 
		y = yy;
	}

	public boolean hasCaptured(){
		// if (b.removed != null){
		// 	if (Math.abs(b.removed.x - x) == 2){
		// 		if (Math.abs(b.removed.y -y)==2) {
		// 			return true;
		// 		}
		// 	}
		// }
		// return false;
		return b.captured;
	}

	public void doneCapturing(){
		while (hasCaptured()){
			b.captured = false;
		}

	}
}