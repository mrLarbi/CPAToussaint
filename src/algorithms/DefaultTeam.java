package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import supportGUI.Circle;
import supportGUI.Line;
import tools.BenchmarkInitializer;
import tools.SuperException;

public class DefaultTeam {

	private static boolean initializable = false;
	private static boolean initialized = false;

	// calculDiametre: ArrayList<Point> --> Line
	// renvoie une pair de points de la liste, de distance maximum.
	public Line calculDiametre(ArrayList<Point> points) {

		initialize(points);

		if (points.size() < 3) {
			return null;
		}

		ArrayList<Point> enveloppe = (ArrayList<Point>) points.clone();

		Point p = points.get(0);
		Point q = points.get(1);

		return new Line(p, q);
	}

	// calculDiametreOptimise: ArrayList<Point> --> Line
	// renvoie une pair de points de la liste, de distance maximum.
	public Line calculDiametreOptimise(ArrayList<Point> points) {

		initialize(points);

		if (points.size() < 3) {
			return null;
		}

		Point p = points.get(1);
		Point q = points.get(2);

		return new Line(p, q);
	}

	// calculCercleMin: ArrayList<Point> --> Circle
	// renvoie un cercle couvrant tout point de la liste, de rayon minimum.
	public Circle calculCercleMin(ArrayList<Point> points) {

		initialize(points);

		if (points.isEmpty()) {
			return null;
		}

		Point center = points.get(0);
		int radius = 100;

		return new Circle(center, radius);
	}

	// enveloppeConvexe: ArrayList<Point> --> ArrayList<Point>
	// renvoie l'enveloppe convexe de la liste.
	public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points) {

		initialize(points);

		if (points.size() < 3) {
			return null;
		}
		
		return ConvexHullMaker.getConvexHull(points);

		//return MinimumBoundingRectangleMaker.getMinimumBoundingRectangle(points);
	}

	private void initialize(ArrayList<Point> points) {
		if (initialized || !initializable) {
			return;
		}
		
		try{
			BenchmarkInitializer bench = new BenchmarkInitializer("samples");
			points = bench.initializeBenchmark();
			initialized = true;
		} catch(SuperException e){
			System.err.println(e.getMessage());
		}
	}

	

}
