package tools;

import java.awt.Point;

public class EuclidianLine {

	private double gradiant;
	private double interceptY;
	
	public EuclidianLine(int grad, int interY){
		gradiant = grad;
		interceptY = interY;
	}
	
	public EuclidianLine(int grad, Point p){
		gradiant = grad;
		interceptY = p.getY() - grad * p.getX();
	}
	
	public EuclidianLine(Point p1, Point p2){
		gradiant = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		interceptY = (p2.getX() * p1.getY() - p1.getX() * p2.getY())/(p2.getX() - p1.getX());
	}
	
	public double getGradiant(){
		return gradiant;
	}
	
	public double getInterceptY(){
		return interceptY;
	}
}
