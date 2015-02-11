

public class Piece {
	public boolean isFire;
	public Board b;
	public int x;
	public int y;
	public String type;
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
			return 0;
		}
		else{
			return 1;
		}
	}
	
	public String type(){
		return type;
	}

	public boolean isKing(){
		return king;
		// if (isFire() && x==7 && y ==7) {
		// 	king = true;
		// 	return true;
		// }
		// else if (isFire() && x==5 && y ==7) {
		// 	type = "king";
		// 	return true;
		// }
		// else if (isFire() && x==3 && y ==7) {
		// 	return true;
		// }
		// else if (isFire() && x==1 && y ==7) {
		// 	return true;
		// }
		// else if (isFire() != true && x==0 && y ==0) {
		// 	return true;
		// }
		// else if (isFire() != true && x==2 && y ==0) {
		// 	return true;
		// }
		// else if (isFire() != true && x==4 && y ==0) {
		// 	return true;
		// }
		// else if (isFire() != true && x==6 && y ==0) {
		// 	return true;
		// }
		// else {
		// 	return false;
		// }
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
		x = xx; //needs to check for kings & capturing, call upon the board
		y = yy;
	}

	public boolean hasCaptured(){

		if (Math.abs(b.removed.x - x) == 2){
			if (Math.abs(b.removed.y -y)==2) {
				return true;
			}
		}
		return false;
	}

	public void doneCapturing(){
		while (hasCaptured()){
			hasCaptured();
		}

	}
}