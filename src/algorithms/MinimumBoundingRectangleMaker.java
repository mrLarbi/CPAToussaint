package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import supportGUI.Line;
import tools.EuclidianLine;
import tools.PointTools;

public class MinimumBoundingRectangleMaker {

	public static ArrayList<Point> getMinimumBoundingRectangle(ArrayList<Point> points){
		ArrayList<Point> enveloppe = ConvexHullMaker.getConvexHull(points);
		Point westernMostPoint = PointTools.getWesternMostPoint(enveloppe);
		Point easternMostPoint = PointTools.getEasternMostPoint(enveloppe);
		EuclidianLine leftLine = new EuclidianLine(0, westernMostPoint);
		EuclidianLine rightLine = new EuclidianLine(0, easternMostPoint);
		boolean isPassedLeft = false;
		boolean isPassedRight = false;
		ArrayList<Point> rectMin = null;
		int sizeEnveloppe = enveloppe.size();
		int currentPointIndex = enveloppe.indexOf(westernMostPoint);
		Line next = new Line(null, westernMostPoint);
		while(!isPassedLeft || !isPassedRight){
			//calcul minima
			//turn left and right
		}
		//return rect min
		return enveloppe;
	}
	
}
