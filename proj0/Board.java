

public class Board {

	private  Piece p;
	private  Piece prev;
	private  Piece[][] players;
	private  Piece removed;
	private  boolean moved;
	private  boolean captured;
	private  int turn;
	private int prevX;
	private int prevY;


	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        boolean check = false;
       	Board b = new Board(check);

       	if (check){
       		b.drawBoard(8);
       	}
       	else{
       	
       	b.drawOriginal(8);

        while(true) {

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.p = b.pieceAt((int) x, (int) y);
                if (b.turn % 2 ==0 && !b.moved){
                	System.out.println("Fire's turn");
	               	 if (b.p==null && b.prev == null){
	                	System.out.println("invalid move");
	               	 } else if (b.p==null && b.prev.isFire() && b.canSelect((int) x, (int) y)){
	                	b.select((int) x, (int) y); 
	                	b.drawUpdate();
	                	b.captured = b.prev.hasCaptured();
	                	b.moved = true;
	                	b.prev = null;
	                	b.p = null;
	                	
	                } else if (b.p != null && b.p.isFire() && b.canSelect((int) x, (int) y)){
	                	b.prev = b.p;
	                	b.storeXY((int) x, (int) y);
	                	}
	            } else if (!b.moved){ 
	            	System.out.println("Water's turn");	
	                	if (b.p==null && b.prev == null){
	                	System.out.println("invalid move");
	               	 } else if (b.p==null && !b.prev.isFire() && b.canSelect((int) x, (int) y)){
	                	b.select((int) x, (int) y); 
	                	b.drawUpdate();
	                   	b.captured = b.prev.hasCaptured();
	                	b.moved = true;
	                	b.prev = null;
	                	b.p = null;
	                } else if (b.p != null && !b.p.isFire() && b.canSelect((int) x, (int) y)){
	                	b.prev = b.p;
	                	b.storeXY((int) x, (int) y);
	                	
	                	}
	                }
	            } else if (StdDrawPlus.isSpacePressed()){
	            	         if (b.canEndTurn()){
            				b.endTurn();
            				} 

	            }
            
            StdDrawPlus.show(100);
        }
    }
        
}


	private void storeXY(int x, int y){
		prevX =x;
		prevY =y;
	}

	// private void canCapture(){ //checks double jump

	// }



    private void drawOriginal(int N){
    	Piece a;
    	drawBoard(8);
    	    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            a = players[i][j];
            if (a == null){

            } else if (j == 0){ 
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png",1,1);
			}
				else if (j == 1){
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png",1,1);
			}
				else if (j == 2){
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png",1,1);
			}
				else if (j == 5){
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png",1,1);
			}
				else if (j == 6){
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png",1,1);
			}
				else if (j == 7){
					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png",1,1);
			}
            }
            }
    }

    private void drawBoard(int N) {
     	    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        
    }
	

	public Board (boolean shouldBeEmpty){
		players = new Piece[8][8];	
		if (!shouldBeEmpty){

		    for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
		        if(y==0 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (true, this, (int) x,(int) y,"pawn");
            	}
            	else if (y == 1 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (true, this, (int) x, (int) y,"shield");            		
            	}
            	else if (y == 2 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (true, this, (int) x, (int) y,"bomb");
            	}
            	else if (y == 5 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (false, this, (int) x, (int) y,"bomb");            		
            	}
            	else if (y == 6 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (false, this, (int) x, (int) y,"shield");            		
            	}
            	else if (y == 7 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (false, this, (int) x, (int) y,"pawn");            		
            	}
            }
        }
    }

	}


	public Piece pieceAt(int x, int y){
		Piece pp;
		for(int i = 0; i < 8; ++i){
			for (int j =0; j < 8; ++j){
				pp = players[i][j];
			if (pp != null && i == x && j ==y){
				 return pp;
			}
		}
		}
		return null;
	}
	


	public boolean canSelect(int x, int y){
		Piece select = players[x][y];
		if (select == null){
			if (prev != null){
				if (x>=0 && y>=0 && x<8 && y<8){
					if (prev.isKing()){
						return true;
					} else if (prev.isFire()){
						if(prevX +1 == x || prevX -1 ==x){
							 if(prevY +1 == y){
								return true;
							}
						} else if(prevX +2 == x && prevY +2 == y  && pieceAt(prevX +1 ,prevY +1) != null && !pieceAt(prevX+1,prevY+1).isFire()){
							return true;
						} else if (prevX -2== x && prevY +2 == y && pieceAt(prevX -1 ,prevY +1) != null && !pieceAt(prevX-1,prevY+1).isFire()){
							return true;
					}
						return false;
					} else if (!prev.isFire()){
						if (prevX == x-1 || prevX -1 == x){
							 if(prevY -1 == y){ 
								return true;
							}
						} else if (prevX -2 == x && prevY -2 == y && pieceAt(prevX-1 ,prevY-1) != null && pieceAt(prevX-1,prevY-1).isFire()){

							return true;
						} else if (prevX +2== x && prevY -2== y  && pieceAt(prevX+1 ,prevY-1) != null && pieceAt(prevX+1,prevY-1).isFire()){

							return true;
					}
						return false;
					} 
				}

			}
		} else {
			if (prev == null) {
				return true;
			}else if (!prev.hasCaptured()){
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		return canSelect(xi,yi);
	}

	public void select(int x, int y){//check for clicking teammate
		
		if (pieceAt(x,y) != null){
			prev = players[x][y];
		} else{
			if (prev != null){
				prev.move(x,y);
			}
		}
		
	}

	public void place(Piece p, int x, int y){
		players[x][y] = p;
	}
	private void drawUpdate(){
		drawBoard(8);
		for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
        	Piece dPiece = players[i][j];
        	if (dPiece != null){

			 if (dPiece.isShield() && dPiece.isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(i +.5, j +.5, "img/shield-fire-crowned.png",1,1);
				} else {
					StdDrawPlus.picture(i +.5, j+.5, "img/shield-fire.png",1,1);
				}
			}
			else if (dPiece.isBomb() && dPiece.isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(i +.5, j +.5, "img/bomb-fire-crowned.png",1,1);
				} else {
					StdDrawPlus.picture(i + .5, j +.5, "img/bomb-fire.png",1,1);
				}
			} else if (dPiece.isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(i + .5, j +.5, "img/pawn-fire-crowned.png",1,1);
				} else { StdDrawPlus.picture(i +.5, j + .5, "img/pawn-fire.png",1,1);
				}
			}
			else if (dPiece.isBomb()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water.png",1,1);
			}
			}
			else if (dPiece.isShield()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(i+.5, j + .5, "img/shield-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(i +.5, j + .5, "img/shield-water.png",1,1);
			}
			}
			else {
				if (dPiece.isKing()){
					StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water.png",1,1);
			}
			}
		}
		}
		}
	}
	

	public Piece remove(int x, int y){

		Piece pp = pieceAt(x,y);
		if (pp == null){
			return null;
		} else if (x>7 || x<0 || y>7 || y<0){
			return null;
		} else{
		players[x][y] = null;
		return pp;
	}
	}

	public boolean canEndTurn(){

		if (moved && captured){
			return true;
		}else if (moved){
			return true;
		} else {
			return false;
		}
		
		
	}

	public void endTurn(){
		if (canEndTurn()){
		System.out.println("next turn");
		winner();
		prev.doneCapturing();
		p = null;
		prev = null;
		turn += 1;
		moved = false;
		captured = false;
	}
	}

	public String winner(){
		String win = "";
		int countFire =0;
		int countWater =0;
		for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
        	if (players[i][j] != null){
        		if (players[i][j].isFire()){
        			countFire +=1;
        		} else{
        			countWater +=1;
        		}
        	}
        }
    }
    	if (countWater == 0){
		win = "Fire";
	} else if (countFire == 0){
		win = "Water";
	} else if (countWater == countFire){
		win = "No one";
	} else{
		return null;
	}
	return win;
	}
}