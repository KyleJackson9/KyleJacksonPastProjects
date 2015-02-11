

public class Board {

	private static Piece p;
	private static Piece prev;
	private static Piece[][] players;
	public static boolean selection;
	private static Board b;
	public static Piece removed;
	private static boolean moved;
	private static boolean captured;
	private static Piece selected;
	private static int turn;

	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        players = new Piece[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
       drawBoard(N);
       b = new Board(false);
        while(true) {

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                p = pieceAt((int) x, (int) y);

                if (turn % 2 ==0){
	               	 if (p==null && prev == null){
	                	System.out.println("invalid choice");
	               	 } else if (p==null && prev.isFire() && canSelect((int) x, (int) y)){
	                	removed = remove(prev.x, prev.y);
	                	place(prev,(int) x,(int) y);
	                	captured = prev.hasCaptured();
	                	moved = true;
	                	prev = null;
	                } else if (p != null && p.isFire() && canSelect((int) x, (int) y)){
	                	prev = p;
	                	}
	            } else{
	                	if (p==null && prev == null){
	                	System.out.println("invalid choice");
	               	 } else if (p==null && !prev.isFire() && canSelect((int) x, (int) y)){
	                	removed = remove(prev.x, prev.y);
	                	place(prev,(int) x,(int) y);
	                	captured = prev.hasCaptured();
	                	moved = true;
	                	prev = null;
	                } else if (p != null && !p.isFire() && canSelect((int) x, (int) y)){
	                	prev = p;
	                	}
	                }          
            }
            if (canEndTurn()){
            	endturn();
            }  
            StdDrawPlus.show(100);
        }
}






    private static void drawOriginal(int N){
    	    for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            if (players[i][j] == null){

            }
            else if (players[i][j].y == 0){ 
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

    private static void drawBoard(int N) {
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
		    for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
		        if(y==0 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (true, b, (int) x,(int) y,"pawn");
            	}
            	else if (y == 1 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (true, b, (int) x, (int) y,"shield");            		
            	}
            	else if (y == 2 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (true, b, (int) x, (int) y,"bomb");
            	}
            	else if (y == 5 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (false, b, (int) x, (int) y,"bomb");            		
            	}
            	else if (y == 6 && x%2 == 0){
                players[(int) x][(int) y] = new Piece (false, b, (int) x, (int) y,"shield");            		
            	}
            	else if (y == 7 && x%2 == 1){
                players[(int) x][(int) y] = new Piece (false, b, (int) x, (int) y,"pawn");            		
            	}
            }
        }
        drawOriginal(8);
		selection = false;

	}


	public static Piece pieceAt(int x, int y){
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
	

	public static boolean canSelect(int x, int y){
		Piece select = players[x][y];
		if (select == null){
			if (prev != null){
				if (x>=0 && y>=0 && x<8 && y<8){
					if (prev.isFire()){
						if(prev.x +1 == x || prev.x -1 ==x){
							if (prev.isKing()){
								return true;
							} else if(prev.y +1 == y){
								return true;
							}
						} else if(prev.x +2 == x && prev.y +2 == y  && pieceAt(prev.x +1 ,prev.y +1) != null && !pieceAt(prev.x+1,prev.y+1).isFire()){
							remove(prev.x+1,prev.y +1);
							return true;
						} else if (prev.x -2== x && prev.y +2 == y && pieceAt(prev.x -1 ,prev.y +1) != null && !pieceAt(prev.x-1,prev.y+1).isFire()){
							remove(prev.x -1, prev.y +1);
						return true;
					}
						return false;
					} else if (!prev.isFire()){
						if (prev.x == x-1 || prev.x -1 == x){
							if(prev.isKing()){
								return true;
							} else if(prev.y -1 == y){ // add King issues
								return true;
							}
						} else if (prev.x -2 == x && prev.y -2 == y && pieceAt(prev.x-1 ,prev.y-1) != null && pieceAt(prev.x-1,prev.y-1).isFire()){
							remove(prev.x-1,prev.y-1);
							return true;
						} else if (prev.x +2== x && prev.y -2== y  && pieceAt(prev.x+1 ,prev.y-1) != null && pieceAt(prev.x+1,prev.y-1).isFire()){
							remove(prev.x+1,prev.y-1);
							return true;
					}
						return false;
					} 
				}

			}
		} else {
			if (prev == null) {
				return true;
			}else if (prev != null && !prev.hasCaptured()){
				return true;
			}
		}
		return false;
	}


	public boolean validMove(int xi, int yi, int xf, int yf){
		return canSelect(xi,yi);
	}

	public void select(int x, int y){
		if (canSelect(x,y)){
			selected = players[x][y];
		}
		
	}

	public static void place(Piece p, int x, int y){
		players[x][y] = p;
		players[x][y].move(x,y);
		drawBoard(8);
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
	

	public static Piece remove(int x, int y){
	Piece pp = pieceAt(x,y);
	players[x][y] = null;
	return pp;
}

	public static boolean canEndTurn(){

		if (moved && captured){
			return true;
		}else if (moved){
			return true;
		} else {
			return false;
		}
		
		
	}

	public static void endturn(){
		System.out.println("next turn");
		winner();
		p = null;
		prev = null;
		turn += 1;
		moved = false;
		captured = false;
	}

	public static String winner(){
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
		win = "Winner: Fire";
	} else if (countFire == 0){
		win = "Winner: Water";
	}
	return win;
	}
}