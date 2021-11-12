package algorithms;

import java.util.Collections;
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
		HashMap<String,Integer> Depth = new HashMap<String,Integer >();
		
		seen.put(start_state, null);
		frontier.add(start_state);
		Depth.put(start_state, 0);
		
		int nodes_expanded=1;
		long start = System.nanoTime();
		List<String> path = new LinkedList<String>();
		
		while (!frontier.isEmpty()) {
			String state = frontier.remove();			
			if (state.equals(goal)) {
				long end = System.nanoTime();
				long elapsedTime = end - start;
				
				path = solve_path(start_state, goal, seen);
				return new Solution(path,path.size(),nodes_expanded,path.size(),elapsedTime);
			}
			
			List<String> moves = make_move(state);
			
			for (String move : moves) {
				if (!seen.containsKey(move)) {
					Depth.put(move, Depth.get(state)+1);
					nodes_expanded++;
					seen.put(move, state);
					frontier.add(move);
				}
			}
		}
		long end = System.nanoTime();
		long elapsedTime = end - start;
		int max_depth=Collections.max(Depth.values());
		return new Solution(null,0,nodes_expanded,max_depth,elapsedTime);
	}
}
