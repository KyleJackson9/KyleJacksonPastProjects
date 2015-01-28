

	public class Planet{
		public double y;
		public double x;
		 public double xVelocity;
		 public double yVelocity;
		 public double mass;
		public String img;
		public double xAccel;
		public double yAccel;	
		public double xNetForce;
		public double yNetForce;
	 public Planet(double x1, double y1, double xVelocity1, double yVelocity1, double mass1, String img1){
	  x= x1;
	  y = y1;
	  xVelocity = xVelocity1;
	  yVelocity = yVelocity1;	
	  mass = mass1;
	  img = img1;
	  xNetForce = 0;
	  yNetForce = 0;

	}
		public double calcDistance(Planet a){ //works
		double distance;
		distance = Math.sqrt((a.x - this.x)*(a.x - this.x) + (a.y-this.y)*(a.y - this.y)); //distance formula c*c = a*a + b*b
		return distance;

	}

	public double calcPairwiseForce(Planet a){ //works
		double pairwise;

		pairwise = 6.67E-11*(a.mass*this.mass)/(this.calcDistance(a)*this.calcDistance(a));
		return pairwise;

		 
	}
		public double calcPairwiseForceX(Planet a){
		double pairwisex;
		double r = this.calcDistance(a);
		pairwisex = calcPairwiseForce(a)*(a.x-this.x)/r;
		return Math.abs(pairwisex);

		 
	}

	public double calcPairwiseForceY(Planet a){
		double pairwisey;
		double r = this.calcDistance(a);
		pairwisey = calcPairwiseForce(a)*(a.y-this.y)/r;
		return Math.abs(pairwisey);
		 
	}
		 public void setNetForce(Planet[] b){
		
		int i = 0;
		while(i < b.length){
			 if (b[i].mass != this.mass){
				xNetForce = this.xNetForce + calcPairwiseForceX(b[i]);
				yNetForce = this.yNetForce + calcPairwiseForceY(b[i]);
			}
		
			i = i + 1;
		}
	}
		 
	
			public void draw() {
				StdDraw.picture(this.x,this.y,"images/" + this.img);
		 
	}
		public void update(double d){
		double dt = d;
		

		//xAccel = calcPairwiseForceX(this)/this.mass;
		//yAccel = calcPairwiseForceY(this)/this.mass;
		xAccel = xNetForce/mass;
		yAccel = yNetForce/mass;

		xVelocity = xVelocity + dt*xAccel;
		yVelocity = yVelocity + dt*yAccel;
		x = x + dt*xVelocity;
		y = y + dt*yVelocity;

	
		 
	}

}
