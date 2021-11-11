package puzzleGame;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import algorithms.A_star;
import algorithms.BFS;
import algorithms.DFS;
import algorithms.Solution;
import algorithms.Solver;
import ui.Formatter;

@CrossOrigin
@RestController
public class Controller {
//	private static Solver solver;
	@PostMapping("/Solve")
	public String Solve(@RequestBody String jsonString) {
		JSONObject obj = new JSONObject(jsonString);
		return getSolution(obj.getString("state"), obj.getString("goal"), obj.getInt("method_id"));

	}

	private String getSolution(String state, String goal, int methodId) {
		Solver s;
		state = validate_state(state);
		goal = validate_state(goal);
		switch (methodId) {
		case 1:
			s = new BFS();
			break;
		case 2:
			s = new DFS();
			break;
		case 3:
			s = new A_star();
		default:
			throw new IllegalArgumentException("Unexpected value: " + methodId);
		}
		if (state != null && goal != null) 
			return solve(s, state, goal, false);
		 else
			guid();
		return null;
	}

	private String solve(Solver Solver, String State, String Goal, boolean printBoard) {
		Formatter f = new Formatter();
		Solution s = Solver.Solve(State, Goal);
		f.printSoluion(Solver.decode(State), s, printBoard);
		return solutionAsJsonString(s);
	}

	private String solutionAsJsonString(Solution s) {
		JSONObject obj = new JSONObject();
		obj.put("cost", s.cost);
		obj.put("path", s.path);
		obj.put("nodes_expanded", s.nodes_expanded);
		obj.put("search_depth", s.search_depth);
		obj.put("time", s.time);
		return obj.toString();
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

	private void guid() {
		System.out.println("# If you don't know how to represent a state look at this example\n"
				+ "# The state of this board is as follows.\n" + "# +-----------+\n" + "# | 6 | 8 | 3 |\n"
				+ "# +---+---+---+\n" + "# | 4 | 2 | 5 |\n" + "# +---+---+---+\n" + "# | 7 | 1 |   |\n"
				+ "# +-----------+\n" + "# board state: 683425710");
	}

}
