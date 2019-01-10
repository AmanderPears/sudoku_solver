//Amander Pears
package sudoku_solver;

import java.util.Vector;
public class Cell {

	public int current_value;
	public boolean given_value;
	
	public Vector<Cell> neighbors;
	
	public Cell() {
		this(0, false);
	}
	
	public Cell(int i, boolean b) {
		current_value = i;
		given_value = b;
		neighbors = new Vector<Cell>();
	}
	
	/*public void initial_set(int i) {
		current_value = i;
		given_value = true;
	}*/
	
	public boolean next_value() {
		if (!given_value) {
			boolean match;
			do {
				match = false;
				if (current_value == 9) {
					current_value = 0;
					return false;
				}
				current_value++;
				
				for (Cell c: neighbors) {
					if (c.current_value == current_value) {
						match = true;
						break;
					}
				}
			} while (match);
			return true;
		}
		return false;
	}
	
}
