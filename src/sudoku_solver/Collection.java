//Amander Pears
package sudoku_solver;

import java.util.Scanner;
import java.util.Vector;

public class Collection {

	public static Vector<Cell> cells = new Vector<Cell>();
	public static Vector<Cell> noGivens = new Vector<Cell>();

	public static void main(String[] args) {

		// get input for puzzle
		System.out.print("Enter puzzle: ");
		Scanner getInput = new Scanner(System.in);
		String inputString;
		do {
			inputString = getInput.next();
		} while (inputString.length() != 81);
		System.out.println();
		//getInput.close(); // close scanner

		// setup the cells vector
		setupCells(inputString);

		// display puzzle
		System.out.println("Puzzle:");
		display();
		
		//solve the puzzle
		solve();

		// display result
		System.out.println("Solution:");
		display();

		// check
		String compare = "";
		for (Cell pp : cells) {
			compare += pp.current_value;
		}
		System.out.print("Enter solution:");
		//Scanner getInput2 = new Scanner(System.in);
		String answer = getInput.next();
		getInput.close();
		if (answer.equals(compare)) {
			System.out.println("Solution is correct!");
		}

	}// main end

	public static void setupCells(String ssstring) {
		// read from string into vector
		char inputCharArray[] = ssstring.toCharArray();
		for (char c : inputCharArray) {
			if (c != '.') {
				cells.add(new Cell(Character.getNumericValue(c), true));
			} else {
				cells.add(new Cell());
			}
		}

		// setup neighbors
		// vector<Cell*> *used;
		int nextRow = 0, nextCol = 0, grid = 0, gridr = 0, gridc = 0;
		for (int ss = 0; ss < 81; ss++) {
			// used = cells[ss].used();

			// horizontal
			if (ss != 0 && ss % 9 == 0) {
				nextRow += 9;
			}
			for (int row = 0 + nextRow; row < (9 + nextRow); row++) {
				if (row != ss) {
					// used.push_back(row);
					// cells[ss].addNeighbors(&cells[row]);
					cells.get(ss).neighbors.addElement(cells.get(row));
					// cells.get(ss).neighbors.add(cells.get(row));
				}
			}

			// vertical
			if (ss != 0 && ss % 9 == 0) {
				nextCol = 0;
			}
			for (int col = 0 + nextCol; col <= 72 + nextCol; col += 9) {
				if (col != ss) {
					// used.push_back(col);
					// cells[ss].addNeighbors(&cells[col]);
					cells.get(ss).neighbors.addElement(cells.get(col));
				}
			}
			nextCol++;

			// grid
			if (ss != 0 && ss % 3 == 0) {
				gridc += 3;
				gridc = gridc <= 6 ? gridc : 0;
			}
			if (ss != 0 && ss % 27 == 0) {
				gridr += 27;
			}
			for (int gr = 0; gr < 3; gr++) {
				for (int gc = 0 + gridc; gc < (3 + gridc); gc++) {
					grid = gr * 9 + gridr + gc;
					if (grid != ss) {
						// need to compare address
						// if (!any_of(used->begin(), used->end(), [&](Cell *i) { return i ==
						// &cells[grid]; }))
						if (!cells.get(ss).neighbors.contains(cells.get(grid))) {
							// used.push_back(grid);
							// cells[ss].addNeighbors(&cells[grid]);
							cells.get(ss).neighbors.addElement(cells.get(grid));
						}
					}
				}
			}

		} // neighbor setup end

		// set up noGivens
		noGivens = new Vector<Cell>();
		for (Cell cc : cells) {
			if (!cc.given_value) {
				noGivens.add(cc);
			}
		}

	}// setupCells end

	public static void solve() {
		// solve
		boolean error;
		for (int ii = 0; ii < noGivens.size(); ii = error ? ii - 1 : ii + 1) {
			error = !noGivens.get(ii).next_value();

		}
	}//solve end

	public static void display() {
		// display puzzle
		int c;
		for (int i = 0; i < 81; i++) {

			if (i != 0) {
				if (i % 3 == 0 && i % 9 != 0)
					System.out.print("|");
				if (i % 9 == 0)
					System.out.println();
				if (i == 27 || i == 54) {
					for (int j = 0; j < 11; j++) {
						if (j == 3 || j == 7)
							System.out.print("|");
						else
							System.out.print("-");
					}
					System.out.println();
				}
			}

			c = cells.get(i).current_value;
			System.out.print(c == 0 ? "." : Integer.toString(c));
		}
		System.out.println();
		System.out.println();
	}// display end
}// class end