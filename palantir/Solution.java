import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter square size : ");
		String line = reader.readLine();
		int size = Integer.parseInt(line);
		int area[][] = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("(" + i + "," + j + ")" + " : ");
				line = reader.readLine();
				area[i][j] = Integer.parseInt(line.trim());
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println("");
		}
		
		compBasins(area);
		System.out.println("Complete");
	}
    
    public static boolean outOfBounds(int x, int y, int size) {
		boolean signal = false;
		if (x < 0 || x >= size) {
			signal = true;
		}
		if (y < 0 || y >= size) {
			signal = true;
		}
		return signal;
	}
    
	public static void compBasins(int area[][]) {
		int size = area.length;
		String basins[][] = new String [size][size];
		HashMap map = new HashMap();
		char uniChar = 'A';
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				basins[x][y] = lowestPlot(area, x, y, 'A');
				if(map.containsKey(basins[x][y])) {
					String valueString = (String)map.get(basins[x][y]);
					StringTokenizer tokens = new StringTokenizer(valueString, ":");
					char basinUniChar = tokens.nextToken().charAt(0);
					int basinSize = Integer.parseInt(tokens.nextToken());
					basinSize = basinSize + 1;
					map.put(basins[x][y], basinUniChar + ":" + basinSize);
				} else {
					map.put(basins[x][y], uniChar + ":" + 1);
					uniChar++;
				}
			}
		}
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				System.out.print(map.get(basins[x][y]) + " ");
			}
			System.out.println("");
		}
	}
    
    public static String lowestPlot(int area[][],int x, int y, char ch) {
		String lowPoint = x + "," + y;
		int leftX, leftY, rightX, rightY, topX, topY, bottomX, bottomY, minX, minY,minValue;
		int size = area.length;
		
		leftX = x;
		leftY = y - 1;
		
		rightX = x;
		rightY = y + 1;
		
		topX = x - 1;
		topY = y;
		
		bottomX = x + 1;
		bottomY = y;
		
		minX = x;
		minY = y;
		minValue = area[x][y];
    
        if (!outOfBounds(leftX, leftY, size)) {
			if (area[leftX][leftY] < minValue) {
				minX = leftX;
				minY = leftY;
				minValue = area[leftX][leftY];
			}
		}
		if (!outOfBounds(rightX, rightY, size)) {
			if (area[rightX][rightY] < minValue) {
				minX = rightX;
				minY = rightY;
				minValue = area[rightX][rightY];
			}
		}
		if (!outOfBounds(topX, topY, size)) {
			if (area[topX][topY] < minValue) {
				minX = topX;
				minY = topY;
				minValue = area[topX][topY];
			}
		}
		if (!outOfBounds(bottomX, bottomY, size)) {
			if (area[bottomX][bottomY] < minValue) {
				minX = bottomX;
				minY = bottomY;
				minValue = area[bottomX][bottomY];
			}
		}
        
        if(minX == x && minY == y) {
			;
		} else {
			lowPoint = lowestPlot(area, minX, minY, ch);
		}
		return lowPoint;
	}
        
}


