package tools;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BenchmarkInitializer {

	private File benchDir;
	private boolean initialized = false;
	
	public BenchmarkInitializer(String dirName) throws SuperException{
		benchDir = new File(dirName);
		if(!benchDir.isDirectory()){
			throw new SuperException(dirName + " is not a directory");
		}
		initialized = true;
	}
	
	public ArrayList<Point> initializeBenchmark() {
		if(!initialized){
			return new ArrayList<Point>();
		}
		String[] benchFilesNames = getFilesNames(benchDir);
		return getPointsFromAll(benchFilesNames);
	}

	private String[] getFilesNames(File benchDir2) {
		return benchDir2.list();
	}
	
	private ArrayList<Point> getPointsFromAll(String[] benchFilesNames) {
		ArrayList<Point> points = new ArrayList<Point>();
		for(String s : benchFilesNames){
			if(getFileNameExtension(s).equals("points")){
				points.addAll(getPointsFromFile(benchDir+"/"+s));
			}
		}
		return points;
	}
	
	private ArrayList<Point> getPointsFromFile(String fileName){
		File testFile = new File(fileName);
		return parsePoints(testFile);
	}
	
	private ArrayList<Point> parsePoints(File testFile) {
		ArrayList<Point> points = new ArrayList<Point>();
		if(testFile.isDirectory()){
			return points;
		}
		try {
			FileReader reader = new FileReader(testFile);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			while (line != null) {
				String[] pointsCoordonnees = line.split(" ");
				int x = Integer.parseInt(pointsCoordonnees[0]);
				int y = Integer.parseInt(pointsCoordonnees[1]);
				points.add(new Point(x, y));
				line = br.readLine();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return points;
	}
	
	private String getFileNameExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
}
