/**
 * 
 */
package com.gooker.dfg.utils;

/**
 * Jun 3, 2014 2:56:27 PM
 * 
 */
public class SuDoKuGame {

	// 存放初始化的数据
	private final String str = "360000000004230800000004200" + "070460003820000014500013020"
			+ "001900000007048300000000045";

	private int sudoku[] = new int[9 * 9];

	private int used[][][] = new int[9][9][];

	/**
		 * 
		 */
	public SuDoKuGame() {
		super();
		sudoku = formPuzzleStr(str);
		calculateAllUsedTiles();
	}

	/**
		 * 
		 */
	private void calculateAllUsedTiles() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				used[i][j] = calculateAllUsedTiles(i, j);
			}
		}
	}

	// 根据九宫格当中的坐标，返回该坐标所应该填写的数字
	private int getTile(int x, int y) {
		return sudoku[y * 9 + x];
	}

	public String getTileStr(int x, int y) {
		int v = getTile(x, y);
		if (v == 0) {
			return "";
		} else
			return String.valueOf(v);
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private int[] calculateAllUsedTiles(int x, int y) {
		// TODO Auto-generated method stub
		int c[] = new int[9];
		for (int i = 0; i < 9; i++) {
			if (i == y)
				continue;
			int t = getTile(x, i);
			if (t != 0)
				c[t - 1] = t;

		}
		for (int i = 0; i < 9; i++) {
			if (i == x)
				continue;
			int t = getTile(i, y);
			if (t != 0)
				c[t - 1] = t;
		}

		int startx = (x / 3) * 3;
		int starty = (y / 3) * 3;
		for (int i = startx; i < startx + 3; i++) {
			for (int j = starty; j < starty + 3; j++) {
				if (i == x && j == y)
					continue;
				int t = getTile(i, j);
				if (t != 0)
					c[t - 1] = t;
			}
		}
		//
		int nused = 0;
		for (int t : c) {
			if (t != 0)
				nused++;
		}
		int c1[] = new int[nused];
		nused = 0;
		for (int t : c) {
			if (t != 0)
				c1[nused++] = t;
		}
		return c1;
	}

	// 根据x，y坐标取出该单元格不可用的数据
	public int[] getUsedTilesByCoor(int x, int y) {

		return used[x][y];

	}

	public boolean setTileIfValid(int x, int y, int value) {
		int tiles[] = getUsedTiles(x, y);
		if (value != 0) {
			for (int tile : tiles) {
				if (tile == value)
					return false;

			}
		}
		setTile(x, y, value);
		calculateAllUsedTiles();
		
		return true;

	}

	private int[] getUsedTiles(int x, int y) {
		// TODO Auto-generated method stub
		return used[x][y];
	}

	private void setTile(int x, int y, int value) {
		// TODO Auto-generated method stub
		sudoku[y * 9 + x] = value;
	}

	/**
	 * @param str2
	 * @return
	 */
	private int[] formPuzzleStr(String src) {
		// TODO Auto-generated method stub
		int[] sudo = new int[src.length()];
		for (int i = 0; i < sudo.length; i++) {
			sudo[i] = src.charAt(i) - '0';
		}
		return sudo;
	}

}
