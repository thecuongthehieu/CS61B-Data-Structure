/**
* This is Nbody Class for simulating the Universe
*/
public class NBody {
	/**
	* NO CONSTRUCTOR
	*/
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		
		int numPlanets = in.readInt(); // Number of all the Planets
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int numPlanets = in.readInt();
		double radius = in.readDouble();

		Planet[] allPlanets = new Planet[numPlanets];
		for (int i = 0; i < numPlanets; ++i) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
		}	
		return allPlanets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] allPlanets = readPlanets(filename);
		double radius = readRadius(filename);
		String folder = "images/";

		drawBackGround(radius, folder + "starfield.jpg");
		drawAllPlanets(allPlanets);

		StdDraw.enableDoubleBuffering();

		int timer = 0;
		int numPlanets = allPlanets.length;
		while (timer != T) {
			double[] xForces = new double[numPlanets];
			double[] yForces = new double[numPlanets];

			for (int i = 0; i < numPlanets; ++i) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			for (int i = 0; i < numPlanets; ++i) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			drawBackGround(radius, folder + "starfield.jpg");
			drawAllPlanets(allPlanets);
			StdDraw.show();
			StdDraw.pause(10);

			timer += dt;	
		}

		StdOut.printf("%d\n", numPlanets);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < numPlanets; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            			allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
            			allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}

	private static void drawBackGround(double radius, String imageFimeName) {
		/* Set up the universe's scale. */
		StdDraw.setScale(-radius, radius);
		/* Clears the drawing window. */
		StdDraw.clear();
		/* Stamps copy of image in drawing window. */
		StdDraw.picture(0, 0, imageFimeName);
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();	
	}

	private static void drawAllPlanets(Planet[] allPlanets) {
		for (int i = 0; i < allPlanets.length; ++i) {
			allPlanets[i].draw();
		}
	}
}