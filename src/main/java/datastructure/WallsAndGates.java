package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WallsAndGates {
	static boolean[][] visited;

	static List<Point> dirs = new ArrayList<>();

	static List<Point> points = new ArrayList<>();
	static class Point {
		int x, y;
		int value;
		String name;
		Point(int x, int y, int value, String name) {
			this.x = x;
			this.y = y;
			this.value = value;
			this.name = name;
		}
	}

	// static List<Point> dist = List.of();
	static Map<String, Point> info = new HashMap<>();
	static int n, m;
	static int[][] board = new int[4][4];
	static Queue<Point> treasure = new LinkedList<>();

	public static void main(String[] args) {
		board = new int[][] {{2147483647, -1, 0, 2147483647},
			{2147483647, 2147483647, 2147483647, -1},
			{2147483647, -1, 2147483647, -1},
			{0, -1, 2147483647, 2147483647}};

		n = board.length;
		m = board[0].length;
			for(int i=0; i<n; i++){
				for(int j=0; j<m; j++){
					String name = "%s-%s".formatted(i,j);
					info.put(name, new Point(i,j,board[i][j],name));
					if(board[i][j] == 0){
						treasure.add(new Point(i,j, board[i][j], name));
					}
				}
			}


		islandsAndTreasure(board);
	}

	public static void islandsAndTreasure(int[][] grid) {

	}

	boolean isWall(Point node) {
		int x = node.x;
		int y = node.y;
		int value = board[x][y];
		if (value == -1)
			return true;
		return false;
	}

	boolean isTreasure(Point node) {
		int x = node.x;
		int y = node.y;
		int value = board[x][y];
		if (value == 0)
			return true;
		return false;
	}

	boolean canBeTraversed(Point node) {
		int x = node.x;
		int y = node.y;
		int value = board[x][y];
		if (value > 0)
			return true;
		return false;
	}
}
