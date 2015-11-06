import java.*;

public class NBody {

	public static Planet getPlanet(In file){
		String nextPlanet = file.readLine();
		//System.out.println(nextPlanet);
		String[] splitPlanet = nextPlanet.split(" ");
		Planet p = new Planet(Double.parseDouble(splitPlanet[0]), Double.parseDouble(splitPlanet[1]), Double.parseDouble(splitPlanet[2]), Double.parseDouble(splitPlanet[3]), Double.parseDouble(splitPlanet[4]), splitPlanet[5]);
		return p;

		// for(int j=0 ; j < 6 ; j++){
		//  	System.out.println(splitPlanet[j]);
		//  }
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		In i = new In(filename);
		int numPlanets = i.readInt();
		double radWindow = i.readDouble();
		i.readLine();

		//call getPlanet
		Planet[] p = new Planet[numPlanets];
		for(int j = 0; j < numPlanets; j++){
			p[j] = getPlanet(i);
		}
		// Planet p1 = getPlanet(i);
		// Planet p2 = getPlanet(i);
		// Planet p3 = getPlanet(i);
		// Planet p4 = getPlanet(i);
		// Planet p5 = getPlanet(i);
		
		//String[] allPlanets = new String[numPlanets+1];		
		//allPlanets = i.readAllLines();

		StdDraw.setScale(-radWindow,radWindow);
		String image = "images/starfield.jpg";
		StdDraw.picture(0, 0, image);
	
		for(int k = 0; k < numPlanets; k++){
			p[k].draw();
		}
		// p1.draw();
		// p2.draw();
		// p3.draw();
		// p4.draw();
		// p5.draw();

		//Planet[] p = new Planet[]{p1,p2,p3,p4,p5};
		double t = 0.0;
		while(t < T) {
			for(int l = 0; l < numPlanets; l++){
				p[l].setNetForce(p);
			}
			// p1.setNetForce(p);
			// p2.setNetForce(p);
			// p3.setNetForce(p);
			// p4.setNetForce(p);
			// p5.setNetForce(p);

			for(int l = 0; l < numPlanets; l++){
				p[l].update(dt);
			}

			// p1.update(dt);
			// p2.update(dt);
			// p3.update(dt);
			// p4.update(dt);
			// p5.update(dt);

			StdDraw.picture(0, 0, image);

			for(int k = 0; k < numPlanets; k++){
				p[k].draw();
			}
			// p1.draw();
			// p2.draw();
			// p3.draw();
			// p4.draw();
			// p5.draw();

			StdDraw.show(10);
			t = t + dt;
		}


		// for(int j=0 ; j < numPlanets ; j++){
		// 	System.out.println(p[j]);
		// }
		//System.out.println(numPlanets);
		//System.out.println(radWindow);
	}

}
