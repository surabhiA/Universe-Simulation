import java.awt.Color;

public class Planet {
	
	// Instance variables	
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double xAccel;
	public double yAccel;
	
	//Constructor
	public Planet(double xpos,double ypos,double xVel,double yVel,double planetMass,String image){
		x = xpos;
		y = ypos;
		xVelocity = xVel;
		yVelocity = yVel;
		mass = planetMass;
		img = image;
	}
	
	//Calculate distance between two planets---r
	public double calcDistance(Planet p2){
		double rsq = (this.x - p2.x)*(this.x - p2.x) + (this.y - p2.y)*(this.y - p2.y);
		return Math.sqrt(rsq);
	}
	
	//Calculate Pairwise force---F
	public double calcPairwiseForce(Planet p2){
		double F;
		double G = 6.67e-11;
		double m1 = this.mass;
		double m2 = p2.mass;
		double r = this.calcDistance(p2);
		double d = r*r;
		
		F = (G*m1*m2)/d;
		return F;
	}
	
	//Cal Fx and Fy
	public double calcPairwiseForceX(Planet p2){
		double F = this.calcPairwiseForce(p2);
		double dx = p2.x - this.x;
		double r = this.calcDistance(p2);
		
		double Fx = (F*dx)/r;
		return Fx;
	}
	
	public double calcPairwiseForceY(Planet p2){
		double F = this.calcPairwiseForce(p2);
		double dy = p2.y - this.y;
		double r = this.calcDistance(p2);
		
		double Fy = (F*dy)/r;
		return Fy;
	}
	
	//Cal net force on a planet ie Fx and Fy of all planets added to Fx and Fy of planet
	public void setNetForce(Planet[] p){
		xNetForce = 0;
		yNetForce = 0;
		for(int i = 0; i < p.length; i++){
			if(p[i] == this){
				continue;
			}
			xNetForce += this.calcPairwiseForceX(p[i]);
			yNetForce += this.calcPairwiseForceY(p[i]);
		}
	}
	
	/*Update universe positions:
	 * Cal acceleration using net force function
	 * cal new velocity
	 * cal new position*/
	public void update(double dt){
		
		xAccel = this.xNetForce/this.mass;
		yAccel = this.yNetForce/this.mass;
		
		xVelocity = xVelocity + dt * xAccel;
		yVelocity = yVelocity + dt * yAccel;
		
		x = x + dt * xVelocity;
		y = y + dt * yVelocity;
	}

	//draw the planet at coordinates
	public void draw(){
		String image = "images/" + this.img;
		//StdDraw.setScale(0, 100);
		StdDraw.picture(this.x, this.y, image);
	}
	
}
