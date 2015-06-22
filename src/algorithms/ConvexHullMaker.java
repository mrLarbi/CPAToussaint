package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import tools.PointTools;

public class ConvexHullMaker {

	public static ArrayList<Point> getConvexHull(ArrayList<Point> points){
		HashMap<Boolean, ArrayList<Point>> usefulPoints = filtrageAbscisse(points);
		return algoGraham(usefulPoints);
	}
	
	private static ArrayList<Point> algoGraham(HashMap<Boolean, ArrayList<Point>> usefulPoints) {
		ArrayList<Point> enveloppe = new ArrayList<Point>();
		enveloppe.addAll(reverseList(algoGrahamMax(usefulPoints.get(true))));
		enveloppe.addAll(algoGrahamMin(usefulPoints.get(false)));
		return enveloppe;
		
	}
	
	private static ArrayList<Point> algoGrahamMax(ArrayList<Point> usefulPoints) {
		ArrayList<Point> enveloppe = new ArrayList<Point>();
		enveloppe.add(usefulPoints.get(0));
		enveloppe.add(usefulPoints.get(1));
		int size = usefulPoints.size();
		for(int i = 2 ; i < size ; i++){
			Point p = usefulPoints.get(i);
			while(enveloppe.size() >= 2 ? produitVectoriel(enveloppe.get(enveloppe.size()-2)
																		, enveloppe.get(enveloppe.size()-1)
																		, p) >= 0
																		: enveloppe.get(enveloppe.size()-1)
																		== p){
				enveloppe.remove(enveloppe.size()-1);
			}
			enveloppe.add(p);
		}
		return enveloppe;
	}
	
	private static ArrayList<Point> algoGrahamMin(ArrayList<Point> usefulPoints) {
		ArrayList<Point> enveloppe = new ArrayList<Point>();
		enveloppe.add(usefulPoints.get(0));
		enveloppe.add(usefulPoints.get(1));
		int size = usefulPoints.size();
		for(int i = 2 ; i < size ; i++){
			Point p = usefulPoints.get(i);
			while(enveloppe.size() >= 2 ? produitVectoriel(enveloppe.get(enveloppe.size()-2)
																		, enveloppe.get(enveloppe.size()-1)
																		, p) <= 0
																		: enveloppe.get(enveloppe.size()-1)
																		== p){
				enveloppe.remove(enveloppe.size()-1);
			}
			enveloppe.add(p);
		}
		return enveloppe;
	}
	
	private static int produitVectoriel(Point a, Point b, Point c){
		Point v1 = new Point((int)(b.getX()-a.getX()), (int)(b.getY() - a.getY()));
		Point v2 = new Point((int)(c.getX()-b.getX()), (int)(c.getY() - b.getY()));
		int pv = (int) (v1.getX() * v2.getY() - v2.getX() * v1.getY());
		return pv;
	}
	
	private static HashMap<Boolean, ArrayList<Point>> filtrageAbscisse(ArrayList<Point> points){
		ArrayList<Point> filtre = (ArrayList<Point>) points.clone();
		HashMap<Integer , ArrayList<Point>> sortedPoints = toHashMapByAbscissa(filtre);
		HashMap<Boolean, ArrayList<Point>> filtreMap = removeUselessPoints(sortedPoints);
		return filtreMap;
	}
	
	private static HashMap<Integer , ArrayList<Point>> toHashMapByAbscissa(ArrayList<Point> points){
		HashMap<Integer, ArrayList<Point>> hashPoints = new HashMap<Integer, ArrayList<Point>>();
		for(Point p1 : points){
			if(!hashPoints.containsKey((int)p1.getX())){
				ArrayList<Point> temp = new ArrayList<Point>();
				temp.add(p1);
				hashPoints.put((int)p1.getX(), temp);
				
			}
			else{
				ArrayList<Point> temp = hashPoints.get((int)p1.getX());
				temp.add(p1);
			}
		}
		
		return hashPoints;
	}
	

	private static HashMap<Boolean, ArrayList<Point>> removeUselessPoints(HashMap<Integer, ArrayList<Point>> sortedPoints) {
		
		HashMap<Boolean, ArrayList<Point>> usefulPoints = new HashMap<Boolean, ArrayList<Point>>();
		Set<Integer> abscissas = sortedPoints.keySet();
		ArrayList<Integer> orderedKeys = new ArrayList<Integer>(abscissas);
		Collections.sort(orderedKeys);
		usefulPoints.put(true, new ArrayList<Point>());
		usefulPoints.put(false, new ArrayList<Point>());
		for(Integer i : orderedKeys){
			Point tempMin = PointTools.getSouthernMostPoint(sortedPoints.get(i));
			usefulPoints.get(false).add(tempMin);
			Point tempMax = PointTools.getNorthernMostPoint(sortedPoints.get(i));
			usefulPoints.get(true).add(tempMax);
		}

		return usefulPoints;
	}
	
	private static ArrayList<Point> reverseList(ArrayList<Point> points){
		ArrayList<Point> reverse = new ArrayList<Point>();
		int size = points.size();
		for(int i = size - 1 ; i >= 0 ; i--){
			reverse.add(points.get(i));
		}
		return reverse;
	}
	
}
