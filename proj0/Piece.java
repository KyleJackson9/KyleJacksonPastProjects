

public class Piece {
	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean king;
	private boolean capture;
	private int prevX;
	private int prevY;
	


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

	private String type(){
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
		Piece rightT = b.pieceAt(x+1,y+1);
		Piece rightB = b.pieceAt(x-1,y+1);
		Piece leftT = b.pieceAt(x+1,y-1);
		Piece leftB = b.pieceAt(x-1,y-1);
			if (rightT != null && !rightT.isShield()){
				b.remove(x+1,y+1);
			}
			if (rightB != null && !rightB.isShield()){
				b.remove(x-1,y+1);
			}
			if (leftB != null && !leftB.isShield()){
				b.remove(x-1,y-1);
			}
			if (leftT != null && !leftT.isShield()){
				b.remove(x+1,y-1);
			}

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
		int holdX = x;
		int holdY = y;
		prevX = x;
		prevY = y;
		x = xx; 
		y = yy;


				if (x>=0 && y>=0 && x<8 && y<8){
					if (isKing()){
						 killerKing(this,x,y);
					} else if (isFire()){
						 if(prevX +2 == x && prevY +2 == y  && b.pieceAt(prevX +1 ,prevY +1) != null && !b.pieceAt(prevX+1,prevY+1).isFire()){
							b.remove(prevX+1,prevY +1);
							capture =true;
							explode(x,y);
						} else if (prevX -2== x && prevY +2 == y && b.pieceAt(prevX -1 ,prevY +1) != null && !b.pieceAt(prevX-1,prevY+1).isFire()){
							b.remove(prevX -1, prevY +1);
							capture =true;
							explode(x,y);
					}
						
					} else if (!isFire()){
						
							if (prevX -2 == x && prevY -2 == y && b.pieceAt(prevX-1 ,prevY-1) != null && b.pieceAt(prevX-1,prevY-1).isFire()){
							b.remove(prevX-1,prevY-1);
							capture =true;
							explode(x,y);	
						} else if (prevX +2== x && prevY -2== y  && b.pieceAt(prevX+1 ,prevY-1) != null && b.pieceAt(prevX+1,prevY-1).isFire()){
							b.remove(prevX+1,prevY-1);
							capture =true;
							explode(x,y);
							
					}
						
					}
				
				}
		b.remove(holdX,holdY);
		if (capture && isBomb()){
			explode(x,y);
		} else{
		b.place(this,x,y);
		}

	
	}
	private void killerKing(Piece k, int x, int y){
		if (x>=0 && y>=0 && x<8 && y<8){
		 if(k.isFire() && prevX +2 == x && prevY +2 == y  && b.pieceAt(prevX +1 ,prevY +1) != null && !b.pieceAt(prevX+1,prevY+1).isFire()){
				b.remove(prevX+1,prevY +1);
				capture =true;
				explode(x,y);
		} else if (k.isFire() && prevX -2== x && prevY +2 == y && b.pieceAt(prevX -1 ,prevY +1) != null && !b.pieceAt(prevX-1,prevY+1).isFire()){
						b.remove(prevX -1, prevY +1);
						capture =true;
						explode(x,y);
		}else if (k.isFire() && prevX -2 == x && prevY -2 == y && b.pieceAt(prevX-1 ,prevY-1) != null && !b.pieceAt(prevX-1,prevY-1).isFire()){
							
							b.remove(prevX-1,prevY-1);
							capture =true;
							explode(x,y);
		} else if (k.isFire() && prevX +2== x && prevY -2== y  && b.pieceAt(prevX+1 ,prevY-1) != null && !b.pieceAt(prevX+1,prevY-1).isFire()){
							
							b.remove(prevX+1,prevY-1);
							capture =true;
							explode(x,y);
		}else if(!k.isFire() && prevX +2 == x && prevY +2 == y  && b.pieceAt(prevX +1 ,prevY +1) != null && b.pieceAt(prevX+1,prevY+1).isFire()){
							b.remove(prevX+1,prevY +1);
							capture =true;
							explode(x,y);
		} else if (!k.isFire() && prevX -2== x && prevY +2 == y && b.pieceAt(prevX -1 ,prevY +1) != null && b.pieceAt(prevX-1,prevY+1).isFire()){
						b.remove(prevX -1, prevY +1);
						capture =true;
						explode(x,y);
		}else if (!k.isFire() && prevX -2 == x && prevY -2 == y && b.pieceAt(prevX-1 ,prevY-1) != null && b.pieceAt(prevX-1,prevY-1).isFire()){
							b.remove(prevX-1,prevY-1);
							capture =true;
							explode(x,y);
		} else if (!k.isFire() && prevX +2== x && prevY -2== y  && b.pieceAt(prevX+1 ,prevY-1) != null && b.pieceAt(prevX+1,prevY-1).isFire()){
							b.remove(prevX+1,prevY-1);
							capture = true;
							explode(x,y);
		}// } if (prevX +1 == x && prevY -1 ==y || prevX -1 ==x && prevY +1 == y || prevY - 1 ==y && prevX -1 ==x || prevX +1 == x && prevY +1== y){
		// 			return true;
		// }
				}
	
}

	public boolean hasCaptured(){

		return capture;
	}

	public void doneCapturing(){ //needs work
		capture = false;

	}
}