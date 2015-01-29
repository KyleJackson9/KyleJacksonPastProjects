public class NBody{


	    public static void main(String[] args) {
	    		 double T;
	 double dt;
	 String filename;
	 int numberOfPlanets;
	 double universeRadius;
	 int i;
	 Planet[] list;
	 double time = 0;
	    	T = Double.parseDouble(args[0]);
	    	dt = Double.parseDouble(args[1]);
	    	filename = args[2];
	    	In in = new In(filename);
	    	 numberOfPlanets = in.readInt();
	    	 universeRadius = in.readDouble();
	    	 i = 0;

	    	 StdDraw.setScale(-universeRadius, universeRadius);
	    	 StdDraw.picture(0,0,"images/starfield.jpg");
	    	 list = new Planet[numberOfPlanets];
	    	 int j = 0;
	    	 while (j < numberOfPlanets){
	    	 	list[j] = getPlanet(in);
	    	 	j = j+1;
	    	 }
	    	 

	    	

	    	 while(time <= T){
				 int n = 0;
			    	 		StdDraw.setScale(-universeRadius, universeRadius);
	    	 		StdDraw.picture(0,0,"images/starfield.jpg");
	    	 	while(n < numberOfPlanets){

	    	 		list[n].setNetForce(list);
	    	 		list[n].update(dt);
	    	 		list[n].xNetForce = 0;
	    	 		list[n].yNetForce = 0; 
	    	  		list[n].draw();
	    	  		n = n+1;
	    	 }
	    	 	StdDraw.show(1);
	    	 	time = time +dt;

	    	 }

	    	 

	    	 
	    	 



	    	 
			StdOut.printf("%d\n", numberOfPlanets);
			StdOut.printf("%.2e\n", universeRadius);
				for (int q = 0; q < numberOfPlanets; q++) {
   			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",list[q].x, list[q].y, list[q].xVelocity, list[q].yVelocity, list[q].mass, list[q].img);
			}
	    	 
	    	 	
	    	 



	    }
	    public static Planet getPlanet(In a){
	    	//a.readLine();
	    	
	    	//numberOfPlanets = a.readInt();
	    	//int i = 1;
	    	//while (i < a.readInt()){
	    		//String[] arg = a.readLine();
	    		//arg = arg.split(arg,6); 
	    		Planet b = new Planet(Double.parseDouble(a.readString()),Double.parseDouble(a.readString()),Double.parseDouble(a.readString()),Double.parseDouble(a.readString()),Double.parseDouble(a.readString()),a.readString());
	    		return b;
	    	//	i = i +1;
	    	//}

	    }
}