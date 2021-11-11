package ui;

import algorithms.Solution;

public class Formatter {
	public void printSoluion(int[] start_state, Solution sol, boolean printBoard) {
		if (printBoard && sol.path != null && sol.path.size() <= 31) {
			int[] state = start_state.clone();
			printBoard(state);
			for (String p : sol.path) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\n-- " + p + " --\n");

				int emp = state[state.length - 1];

				if (p.equals("Up"))
					swap(state, emp, emp - 3);
				else if (p.equals("Down"))
					swap(state, emp, emp + 3);
				else if (p.equals("Left"))
					swap(state, emp, emp - 1);
				else if (p.equals("Right"))
					swap(state, emp, emp + 1);

				printBoard(state);
			}
			System.out.println("");
		}

		if (sol.path != null) {
			System.out.println("Path:");
			System.out.println(sol.path);
			System.out.println("");
			System.out.println("Cost: " + sol.cost);
		} else
			System.out.println("\tUnsolvable!");

		System.out.println("Nodes Expanded: " + sol.nodes_expanded);
		System.out.println("Search Depth: " + sol.search_depth);
		System.out.println("Time: " + sol.time / 1000000 + " ms");
	}

	private void printBoard(int[] state) {
		System.out.println("+-----------+");
		System.out.println(line(state, 0, 2));
		System.out.println("+---+---+---|");
		System.out.println(line(state, 3, 5));
		System.out.println("+---+---+---|");
		System.out.println(line(state, 6, 8));
		System.out.println("+-----------+");
	}

	private String line(int[] state, int l, int r) {
		String line = "|";
		for (int i = l; i <= r; i++) {
			if (state[i] == 0)
				line = line + "   |";
			else
				line = line + " " + state[i] + " |";
		}
		return line;
	}

	private void swap(int[] arr, int emp, int new_emp) {
		int temp = arr[emp];
		arr[emp] = arr[new_emp];
		arr[new_emp] = temp;

		arr[arr.length - 1] = new_emp;
	}
}
