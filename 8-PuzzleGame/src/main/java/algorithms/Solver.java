package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solver {
	public Solution Solve(String state, String goal) {
		return null;
	}

	protected List<String> make_move(String State) {
		List<String> moves = new LinkedList<String>();
		int[] state = decode(State);

		int emp = state[state.length - 1];
		int empX = emp % 3, empY = emp / 3;

		if (empY > 0) // up
			moves.add(state_make(state.clone(), emp - 3));
		if (empY < 2) // down
			moves.add(state_make(state.clone(), emp + 3));
		if (empX > 0) // left
			moves.add(state_make(state.clone(), emp - 1));
		if (empX < 2) // right
			moves.add(state_make(state.clone(), emp + 1));

		return moves;
	}

	protected List<String> solve_path(String start_state, String goal, HashMap<String, String> explored) {
		LinkedList<String> path = new LinkedList<String>();
		String state = goal;
		int len = state.length() - 1;
		
		while (!state.equals(start_state)) {
			String parent = (String) explored.get(state);
			int emp =  Character.getNumericValue(state.charAt(len));
			int p_emp = Character.getNumericValue(parent.charAt(len));
			
			if (emp == p_emp - 3)
				path.addFirst("Up");
			else if (emp == p_emp + 3)
				path.addFirst("Down");
			else if (emp == p_emp - 1)
				path.addFirst("Left");
			else if (emp == p_emp + 1)
				path.addFirst("Right");
			
			state = parent;
		}
		return path;
	}

	private String state_make(int[] state, int new_empty) {
		int emp = state[state.length - 1];
		state[state.length - 1] = new_empty;

		int temp = state[new_empty];
		state[new_empty] = state[emp];
		state[emp] = temp;

		return encode(state);
	}

	public int[] decode(String State) {
		int[] state = new int[State.length()];
		for (int i = 0; i < state.length; i++)
			state[i] = Character.getNumericValue(State.charAt(i));
		return state;
	}

	public String encode(int[] state) {
		String State = "";
		for (int i : state)
			State = State + String.valueOf(i);
		return State;
	}
}
