package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends Solver {
	@Override
	public Solution Solve(String state, String goal) {
		return Bfs(state, goal);
	}

	private Solution Bfs(String start_state, String goal) {
		HashMap<String, String> seen = new HashMap<String, String>();
		Queue<String> frontier = new LinkedList<String>();
		
		seen.put(start_state, null);
		frontier.add(start_state);
		
		int nodes_expanded=1;
		long start = System.nanoTime();
		List<String> path = new LinkedList<String>();
		
		while (!frontier.isEmpty()) {
			String state = frontier.remove();			
			if (state.equals(goal)) {
				long end = System.nanoTime();
				long elapsedTime = end - start;
				
//				System.out.println("Goal");
				path = solve_path(start_state, goal, seen);
//				for(String p : path)
//					System.out.print(p + " ");
				return new Solution(path,path.size(),nodes_expanded,path.size(),elapsedTime);
			}
			
			List<String> moves = make_move(state);
			
			for (String move : moves) {
				if (!seen.containsKey(move)) {
					nodes_expanded++;
					seen.put(move, state);
					frontier.add(move);
				}
			}
		}
		long end = System.nanoTime();
		long elapsedTime = end - start;
		return new Solution(null,0,nodes_expanded,path.size(),elapsedTime);
	}

	public static void main(String[] args) {
		Solver s = new BFS();
		// ['Up', 'Right', 'Down', 'Right', 'Up', 'Left', 'Left', 'Down', 'Right',
		// 'Right']
		s.Solve("1234580676", "1234567808");
	}
}
