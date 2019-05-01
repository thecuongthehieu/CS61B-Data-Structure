import java.lang.Math;

public class Planet {
	/**
	* Planet Object
	*/
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = Math.pow((dx * dx + dy * dy), 0.5);
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double f = G * this.mass * p.mass / (r * r);
		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double f = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double fx = f * dx / r;
		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double f = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double fy = f * dy / r;
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netFx = 0;
		for (int i = 0; i < allPlanets.length; ++i) {
			if (!this.equals(allPlanets[i])) {
				netFx += this.calcForceExertedByX(allPlanets[i]);	
			}
		}
		return netFx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netFy = 0;
		for (int i = 0; i < allPlanets.length; ++i) {
			if (!this.equals(allPlanets[i])) {
				netFy += this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return netFy;
	}

	public void update(double dt, double fX, double fY) {
		double aNetX = fX / this.mass;
		double aNetY = fY / this.mass;
		this.xxVel += aNetX * dt;
		this.yyVel += aNetY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void draw() {
		String imageFolder = "images/";
		String imageToDraw = imageFolder + this.imgFileName;

		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
	}
}