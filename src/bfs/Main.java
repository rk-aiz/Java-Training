package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) {
		
		//0の箇所が通行可能マス、1の箇所が通行不可能マス
		int[][] meiro = {
	            {0, 1, 0, 0, 0, 0},	//[0][0]がスタート地点
	            {0, 0, 0, 1, 1, 0},
	            {1, 1, 0, 1, 0, 0},
	            {0, 0, 0, 0, 1, 0},
	            {1, 0, 1, 0, 1, 1},
	            {0, 0, 1, 0, 0, 0}	//[5][5]がゴール地点
	        };	
		
		bfs(meiro);
	}
	
	public static void bfs(int[][] meiro) {
		
		Queue<Point> queue = new ArrayDeque<>();
		
		int width = meiro[0].length;
		int height = meiro.length;
		
		boolean[][] grid = new boolean[height][width];
		
		Point start = new Point(0, 0);
		Point goal = new Point(5, 5);
		
		int[][] distance = new int[height][width];
		
		// gridの初期化
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = switch(meiro[i][j]) {
					case 0 -> true;
					default -> false;
				};
			}
		}
		
		// distanceの初期化
		for(int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[0][0] = 0;
		
		Point[] around = {
				new Point(1, 0),
				new Point(0, 1),
				new Point(-1, 0),
				new Point(0, -1)
		};
		
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Point cursor = queue.poll();
			
			int nextDist = distance[cursor.getY()][cursor.getX()] + 1;
			
			//4方向の隣のマス全て探索
			for(Point side : around) {
				Point next = new Point(
						cursor.getX() + side.getX(),
						cursor.getY() + side.getY());
				
				//エリア外の場合はスキップ
				if (next.getX() < 0 || width <= next.getX() ||
					next.getY() < 0 || height<= next.getY()) { 
					continue;
				}
				
				//通行可能の場合
				if (grid[next.getY()][next.getX()]) {
					
					//より短い距離で到達できたらその距離を登録した上で、探索キューに追加
					if (distance[next.getY()][next.getX()] > nextDist) {
						distance[next.getY()][next.getX()] = nextDist;
						queue.add(next);
					}
				}
			}
		}
		
		System.out.println(
				"ゴールまでの距離 : " + distance[height - 1][width - 1]);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j])
					System.out.printf("%2d", distance[i][j]);
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		
	}
}

