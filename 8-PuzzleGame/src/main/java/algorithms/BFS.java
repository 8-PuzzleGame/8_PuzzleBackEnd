package algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends Solver {
	
	// calling function.
	@Override
	public Solution Solve(String state, String goal) {
		return Bfs(state, goal);
	}
	
	// BFS algorithm
	private Solution Bfs(String start_state, String goal) {
		HashMap<String, String> seen = new HashMap<String, String>(); 		// store all frontier and explored state here to check in O(1).
		Queue<String> frontier = new LinkedList<String>();					// Queue to store frontier.
		HashMap<String,Integer> Depth = new HashMap<String,Integer >();		// hash map to get depth of tree.
		
		seen.put(start_state, null);
		frontier.add(start_state);
		Depth.put(start_state, 0);
		
		int nodes_expanded=1;												// number of nodes expanded.
		long start = System.nanoTime();										// start timer.
		List<String> path = new LinkedList<String>();						// list to store path from the return function solve_path.
		
		while (!frontier.isEmpty()) {
			String state = frontier.remove();								// the current state.
			if (state.equals(goal)) {										// check if reach to the goal state.
				long end = System.nanoTime();								// end timer.
				long elapsedTime = end - start;								// compute time.
				
				path = solve_path(start_state, goal, seen);					// get path.
				return new Solution(path,path.size(),nodes_expanded,path.size(),elapsedTime);
			}
			
			List<String> moves = make_move(state);							// list of the available moves from the current state.
			
			/* add moves to the frontier stack and seen and increase the expanded nodes
			 * and add the move to depth hash map with its depth (parent depth + 1).
			 * */
			for (String move : moves) {
				if (!seen.containsKey(move)) {								// check if the move is explored or frontier
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
