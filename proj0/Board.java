

public class Board {

	private  Piece p;
	private  Piece prev;
	private  Piece[][] players;
	private  boolean selection;
	private  Piece removed;
	private  boolean moved;
	private  boolean captured;
	private  Piece selected;
	private  int turn;

	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
       	Board b = new Board(true);
       	//b.makeOriginal();
       	b.drawOriginal(8);

        while(true) {

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                b.p = b.pieceAt((int) x, (int) y);
                if (b.turn % 2 ==0){
                	System.out.println("Fire's turn");
	               	 if (b.p==null && b.prev == null){
	                	System.out.println("invalid move");
	               	 } else if (b.p==null && b.prev.isFire() && b.canSelect((int) x, (int) y)){
	                	b.remove(b.prev.x, b.prev.y);
	                    b.place(b.prev,(int) x,(int) y);
	                	b.drawUpdate();
	                	b.captured = b.prev.hasCaptured();
	                	b.moved = true;
	                	b.prev = null;
	                	
	                } else if (b.p != null && b.p.isFire() && b.canSelect((int) x, (int) y)){
	                	b.prev = b.p;
	                	}
	            } else{ System.out.println("Water's turn");	
	                	if (b.p==null && b.prev == null){
	                	System.out.println("invalid move");
	               	 } else if (b.p==null && !b.prev.isFire() && b.canSelect((int) x, (int) y)){
	                	b.remove(b.prev.x, b.prev.y);
	                	b.place(b.prev,(int) x,(int) y);
	                	b.drawUpdate();
	                   	b.captured = b.prev.hasCaptured();
	                	b.moved = true;
	                	b.prev = null;
	                } else if (b.p != null && !b.p.isFire() && b.canSelect((int) x, (int) y)){
	                	b.prev = b.p;
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






    private void drawOriginal(int N){
    	    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            if (players[i][j] == null){

            } else if (players[i][j].y == 0){ 
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/pawn-fire.png",1,1);
			}
				else if (players[i][j].y == 1){
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/shield-fire.png",1,1);
			}
				else if (players[i][j].y == 2){
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/bomb-fire.png",1,1);
			}
				else if (players[i][j].y == 5){
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/bomb-water.png",1,1);
			}
				else if (players[i][j].y == 6){
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/shield-water.png",1,1);
			}
				else if (players[i][j].y == 7){
					StdDrawPlus.picture(players[i][j].x + 0.5, players[i][j].y + 0.5, "img/pawn-water.png",1,1);
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
		
		if(shouldBeEmpty) {
			drawBoard(8);
		}

			players = new Piece[8][8];
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
		selection = false;

	}


	public Piece pieceAt(int x, int y){
		Piece pp;
		for(int i = 0; i < 8; ++i){
			for (int j =0; j < 8; ++j){

			if (players[i][j] != null && players[i][j].x == x && players[i][j].y ==y){

				 pp = players[i][j];
				 return pp;
			}
		}
	}
		return null;
	}
	
	private  boolean killerKing(Piece k, int x, int y){
		if (x>=0 && y>=0 && x<8 && y<8){
		 if(k.isFire() && k.x +2 == x && k.y +2 == y  && pieceAt(k.x +1 ,k.y +1) != null && !pieceAt(k.x+1,k.y+1).isFire()){
				bomb(x,y);
				remove(k.x+1,k.y +1);
				captured = true;
				return true;
		} else if (k.isFire() && k.x -2== x && k.y +2 == y && pieceAt(k.x -1 ,k.y +1) != null && !pieceAt(k.x-1,k.y+1).isFire()){
						bomb(x,y);
						remove(k.x -1, k.y +1);
						captured = true;
						return true;
		}else if (k.isFire() && k.x -2 == x && k.y -2 == y && pieceAt(k.x-1 ,k.y-1) != null && !pieceAt(k.x-1,k.y-1).isFire()){
							bomb(x,y);
							remove(k.x-1,k.y-1);
							captured = true;
							return true;
		} else if (k.isFire() && k.x +2== x && k.y -2== y  && pieceAt(k.x+1 ,k.y-1) != null && !pieceAt(k.x+1,k.y-1).isFire()){
							bomb(x,y);
							remove(k.x+1,k.y-1);
							captured = true;
							return true;
		}else if(!k.isFire() && k.x +2 == x && k.y +2 == y  && pieceAt(k.x +1 ,k.y +1) != null && pieceAt(k.x+1,k.y+1).isFire()){
							bomb(x,y);
							remove(k.x+1,k.y +1);
							captured =true;
							return true;
		} else if (!k.isFire() && k.x -2== x && k.y +2 == y && pieceAt(k.x -1 ,k.y +1) != null && pieceAt(k.x-1,k.y+1).isFire()){
						bomb(x,y);
						remove(k.x -1, k.y +1);
						captured =true;
						return true;
		}else if (!k.isFire() && k.x -2 == x && k.y -2 == y && pieceAt(k.x-1 ,k.y-1) != null && pieceAt(k.x-1,k.y-1).isFire()){
							bomb(x,y);
							remove(k.x-1,k.y-1);
							captured = true;
							return true;
		} else if (!k.isFire() && k.x +2== x && k.y -2== y  && pieceAt(k.x+1 ,k.y-1) != null && pieceAt(k.x+1,k.y-1).isFire()){
							bomb(x,y);
							remove(k.x+1,k.y-1);
							captured =true;
							return true;
		} if (k.x +1 == x && k.y -1 ==y || k.x -1 ==x && k.y +1 == y || k.y - 1 ==y && k.x -1 ==x || k.x +1 == x && k.y +1== y){
					return true;
		}
				}
	
	return false;
}

	public boolean canSelect(int x, int y){
		Piece select = players[x][y];
		if (select == null){
			if (prev != null){
				if (x>=0 && y>=0 && x<8 && y<8){
					if (prev.isKing()){
						return killerKing(prev,x,y);
					} else if (prev.isFire()){
						if(prev.x +1 == x || prev.x -1 ==x){
							 if(prev.y +1 == y){
								return true;
							}
						} else if(prev.x +2 == x && prev.y +2 == y  && pieceAt(prev.x +1 ,prev.y +1) != null && !pieceAt(prev.x+1,prev.y+1).isFire()){
							bomb(x,y);
							remove(prev.x+1,prev.y +1);
							captured = true;
							return true;
						} else if (prev.x -2== x && prev.y +2 == y && pieceAt(prev.x -1 ,prev.y +1) != null && !pieceAt(prev.x-1,prev.y+1).isFire()){
							bomb(x,y);
							remove(prev.x -1, prev.y +1);
							captured = true;
							return true;
					}
						return false;
					} else if (!prev.isFire()){
						if (prev.x == x-1 || prev.x -1 == x){
							 if(prev.y -1 == y){ 
								return true;
							}
						} else if (prev.x -2 == x && prev.y -2 == y && pieceAt(prev.x-1 ,prev.y-1) != null && pieceAt(prev.x-1,prev.y-1).isFire()){
							bomb(x,y);
							remove(prev.x-1,prev.y-1);
							captured = true;
							return true;
						} else if (prev.x +2== x && prev.y -2== y  && pieceAt(prev.x+1 ,prev.y-1) != null && pieceAt(prev.x+1,prev.y-1).isFire()){
							bomb(x,y);
							remove(prev.x+1,prev.y-1);
							captured = true;
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
	private  void bomb(int x, int y){
		Piece rightT = pieceAt(x+1,y+1);
		Piece rightB = pieceAt(x-1,y+1);
		Piece leftT = pieceAt(x+1,y-1);
		Piece leftB = pieceAt(x-1,y-1);
		if(prev != null && prev.isBomb()){
			if (rightT != null && !rightT.isShield()){
				remove(rightT.x,rightT.y);
			}
			if (rightB != null && !rightB.isShield()){
				remove(rightB.x,rightB.y);
			}
			if (leftB != null && !leftB.isShield()){
				remove(leftB.x,leftB.y);
			}
			if (leftT != null && !leftT.isShield()){
				remove(leftT.x,leftT.y);
			}


	}

	}


	private boolean validMove(int xi, int yi, int xf, int yf){
		return canSelect(xi,yi);
	}

	public void select(int x, int y){
		if (canSelect(x,y)){
			selected = players[x][y];
		}
		
	}

	public void place(Piece p, int x, int y){
		players[x][y] = p;
		players[x][y].move(x,y);
		drawBoard(8);
	}
	private void drawUpdate(){
		for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
        	Piece dPiece = players[i][j];
        	if (dPiece != null){
			if (dPiece.type() == "pawn" && dPiece.isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/pawn-fire-crowned.png",1,1);
				} else { StdDrawPlus.picture(dPiece.x +.5, dPiece.y + .5, "img/pawn-fire.png",1,1);
				}
			}
			else if (dPiece.type() == "shield" && dPiece.isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x +.5, dPiece.y +.5, "img/shield-fire-crowned.png",1,1);
				} else {
					StdDrawPlus.picture(players[i][j].x +.5, dPiece.y+.5, "img/shield-fire.png",1,1);
				}
			}
			else if (players[i][j].type() == "bomb" && players[i][j].isFire()){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x +.5, dPiece.y +.5, "img/bomb-fire-crowned.png",1,1);
				} else {
					StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/bomb-fire.png",1,1);
				}
			}
			else if (dPiece.type() == "bomb"){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/bomb-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/bomb-water.png",1,1);
			}
			}
			else if (dPiece.type() == "shield"){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x +.5, dPiece.y + .5, "img/shield-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(dPiece.x +.5, dPiece.y + .5, "img/shield-water.png",1,1);
			}
			}
			else if (dPiece.type() == "pawn"){
				if (dPiece.isKing()){
					StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/pawn-water-crowned.png",1,1);
				}
				else{
				StdDrawPlus.picture(dPiece.x + .5, dPiece.y +.5, "img/pawn-water.png",1,1);
			}
			}
		}
	}
	}
}
	

	public Piece remove(int x, int y){
	Piece pp = pieceAt(x,y);
	players[x][y] = null;
	return pp;
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
		System.out.println("next turn");
		winner();
		p = null;
		prev = null;
		turn += 1;
		moved = false;
		captured = false;
	}

	public  String winner(){
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
    	if (countWater == 1){
		win = "Winner: Fire";
	} else if (countFire == 1){
		win = "Winner: Water";
	}
	return win;
	}
}