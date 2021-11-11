package algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DFS extends Solver {
	
	
	@Override
	public Solution Solve(String state, String goal) {
		
		return Dfs(state,goal);
	}
 
	private Solution Dfs(String start_state, String goal) {
		HashMap<String, String> seen = new HashMap<String, String>();
		Stack<String> frontier = new Stack<String>();
		HashMap<String,Integer> Depth = new HashMap<String,Integer >();
		 
		seen.put(start_state, null);
		frontier.add(start_state);
		Depth.put(start_state, 0);
		
		int nodes_expanded=1;
		long start = System.nanoTime();
		List<String> path = new Linkedlist<String>();
		while (!frontier.isEmpty()) {
			String state = frontier.pop();			
			if (state.equals(goal)) {
				long end = System.nanoTime();
				long elapsedTime = end - start;
				
//				System.out.println("Goal");
				path = solve_path(start_state, goal, seen);
//				for(String p : path)
//					System.out.print(p + " ");
				int max_depth=Collections.max(Depth.values());
//				System.out.print("\n" + max_depth+"   "+nodes_expanded);
				return new Solution(path,path.size(),nodes_expanded,max_depth,elapsedTime);
			}
			 
			List<String> moves = make_move(state);
			
			for (String move : moves) {
				if (!seen.containsKey(move)) {
					Depth.put(move, Depth.get(state)+1);
					nodes_expanded++;
					seen.put(move, state);
					frontier.push(move);
				}
			} 
		}
		long end = System.nanoTime();
		long elapsedTime = end - start;
		int max_depth=Collections.max(Depth.values());
		return new Solution(null,path.size(),nodes_expanded,max_depth,elapsedTime);
	}

	public static void main(String[] args) {
		Solver s = new DFS();
		// ['Up', 'Right', 'Down', 'Right', 'Up', 'Left', 'Left', 'Down', 'Right',
		// 'Right']
		s.Solve("1234580676", "1234567808");
	}
}
