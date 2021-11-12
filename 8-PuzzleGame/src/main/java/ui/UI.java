package ui;

import java.util.Scanner;
import java.util.function.Function;

import algorithms.*;

public class UI {
	private static Scanner sc = new Scanner(System.in);
	private static Function<?, ?> func;
	private static Solver solver;
	private static String state, goal;

	public static void main(String[] args) {
//		("123458067", "123456780")
// 		no solution for this: 812043765 
		//867254301
		try {
			func = (Void) -> chooseAlgorithm();
			while (true) {
				func.apply(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Void chooseAlgorithm() {
		while (true) {
			System.out.println("========================================");
			System.out.println("Choose Algorithm:\n1- BFS\n2- DFS\n3- A*\n4- Compare");
			System.out.print("> ");

			int input = sc.nextInt();

			if (input == 1) {
				solver = new BFS();
				func = (Void) -> enterGoal();
			} else if (input == 2) {
				solver = new DFS();
				func = (Void) -> enterGoal();
			} else if (input == 3) {
				solver = new A_star(1);
				func = (Void) -> enterGoal();
			} else if (input == 4)
				func = (Void) -> compareMode();
			else
				continue;

			return null;
		}
	}

	private static Void enterGoal() {
		String default_goal = "1234567808";
		while (true) {
			System.out.println("========================================");
			System.out.println("Enter the goal ('d' for default):");
			System.out.print("> ");

			goal = sc.next();

			if (goal.equals("d")) {
				goal = default_goal;
				func = (Void) -> enterPuzzle();
				return null;
			}

			goal = validate_state(goal);

			if (goal != null) {
				func = (Void) -> enterPuzzle();
				return null;
			} else
				guid();
		}
	}

	private static Void enterPuzzle() {
		while (true) {
			System.out.println("========================================");
			System.out.println("Enter the puzzle: ");
			System.out.print("> ");

			state = sc.next();

			state = validate_state(state);

			if (state != null) {
				solve(solver, state, goal, true);
				func = (Void) -> make_choice();
				return null;
			} else
				guid();
		}
	}

	private static Void make_choice() {
		while (true) {
			System.out.println("========================================");
			System.out.println("Choose:\n1- Solve another puzzle\n2- Change Algorithm\n3- Change goal");
			System.out.print("> ");

			int input = sc.nextInt();

			if (input == 1)
				func = (Void) -> enterPuzzle();
			else if (input == 2)
				func = (Void) -> chooseAlgorithm();
			else if (input == 3)
				func = (Void) -> enterGoal();
			else
				continue;

			return null;
		}

	}

	private static Void compareMode() {
		String default_goal = "1234567808";
		System.out.println("========================================");
		while (true) {
			System.out.println("Enter the goal ('d' for default):");
			System.out.print("> ");

			goal = sc.next();
			if (goal.equals("d"))
				goal = default_goal;
			else
				goal = validate_state(goal);

			if (goal == null) {
				guid();
				System.out.println("----------------------------------------");
				continue;
			}

			System.out.println("Enter the puzzle: ");
			System.out.print("> ");

			state = sc.next();
			state = validate_state(state);

			if (state == null) {
				guid();
				System.out.println("----------------------------------------");
				continue;
			}
			System.out.println("\n=== A* ===");
			Solver solver = new A_star(1);
			solve(solver, state, goal, false);

			System.out.println("\n=== BFS ===");
			solver = new BFS();
			solve(solver, state, goal, false);

			System.out.println("\n=== DFS ===");
			solver = new DFS();
			solve(solver, state, goal, false);

			chooseAlgorithm();
			return null;
		}
	}

	private static String validate_state(String State) {
		if (State.length() != 9)
			return null;

		if (!State.matches("\\d+"))
			return null;

		int zero_index = -1;
		for (int i = 0; i < State.length(); i++) {
			if (State.charAt(i) == '0')
				zero_index = i;
		}

		return (zero_index == -1) ? null : State + zero_index;
	}

	private static void solve(Solver Solver, String State, String Goal, boolean printBoard) {
		Formatter f = new Formatter();
		f.printSoluion(Solver.decode(State), Solver.Solve(State, Goal), printBoard);
	}

	private static void guid() {
		System.out.println("# If you don't know how to represent a state look at this example\n"
				+ "# The state of this board is as follows.\n" + "# +-----------+\n" + "# | 6 | 8 | 3 |\n"
				+ "# +---+---+---+\n" + "# | 4 | 2 | 5 |\n" + "# +---+---+---+\n" + "# | 7 | 1 |   |\n"
				+ "# +-----------+\n" + "# board state: 683425710");
	}
}
