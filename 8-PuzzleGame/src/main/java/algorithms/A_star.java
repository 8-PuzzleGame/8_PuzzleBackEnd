package algorithms;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class A_star extends Solver {
	private int h;

	public A_star(int h) {
		this.h = h;
	}

	@Override
	public Solution Solve(String state, String goal) {
		//start time 
		long t1 = System.nanoTime();
		// call A*
		ArrayList<Pair> expanded = AStTree(state, goal);
		//end time 
		long time = System.nanoTime() - t1;
		// check that there is a solution
		if (expanded.get(expanded.size() - 1).state.equalsIgnoreCase("0000000000")) {
			// if there isn't solution return null path
			return new Solution(null, 0, expanded.size() - 1, searchDepth(expanded), time);
		}
		//get the goal
		Pair last = expanded.get(expanded.size() - 1);
		int cost = 0;
		//get the cost
		if (last.parent != null) {
			cost = (int) last.parent.cost + 1;
		}
		//return the solution
		return new Solution(getPath(last), cost, expanded.size(), searchDepth(expanded), time);
	}

	//get the path like up and down
	private List<String> getPath(Pair goal) {
		LinkedList<String> path = new LinkedList<>();
		int len = goal.state.length() - 1;

		while (goal.parent != null) {
			int emp = Character.getNumericValue(goal.state.charAt(len));
			int p_emp = Character.getNumericValue(goal.parent.state.charAt(len));

			if (emp == p_emp - 3)
				path.addFirst("Up");
			else if (emp == p_emp + 3)
				path.addFirst("Down");
			else if (emp == p_emp - 1)
				path.addFirst("Left");
			else if (emp == p_emp + 1)
				path.addFirst("Right");

			goal = goal.parent;
		}
		return path;
	}

	// get the most depth we visit in tree
	private int searchDepth(ArrayList<Pair> expanded) {
		int max = (int) expanded.get(0).cost;
		for (Pair p : expanded) {
			if (p.cost > max)
				max = (int) p.cost;
		}
		return max;
	}
	
	// A* algorithm
	private ArrayList<Pair> AStTree(String state, String goal) {
		//PriorityQueue store the node that we will visit
		PriorityQueue<Pair> frontier = new PriorityQueue<Pair>(1, new PairComparator());
		//add initial state
		frontier.add(new Pair(state, 0, null));
		// map to store expanded nodes to check in o(1)
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// map to store frontier nodes to check in o(1)
		HashMap<String, Pair> frontierMap = new HashMap<String, Pair>();
		//list to return expanded nodes
		ArrayList<Pair> expanded = new ArrayList<Pair>();
		// loop while frontier have nodes
		while (!frontier.isEmpty()) {
			//get the lowest cost node
			Pair current = frontier.poll();
			//update the cost to cost without heuristic
			if (current.parent != null) {
				current.cost = current.parent.cost + 1;
			}
			// add current state to expanded
			map.put(current.state, 1);
			expanded.add(current);
			//if goal return success
			if (current.state.equalsIgnoreCase(goal)) {
				return expanded;
			} else {
				// get the possible nodes
				List<String> list = make_move(current.state);
				for (int i = 0; i < list.size(); i++) {
					String child = list.get(i);
					//calculate the heuristic
					double heuristic = h == 1 ? manhattanDistance(child, goal) : euclideanDistance(child, goal);
					//add the new node if not expanded or in frontier
					if (!frontierMap.containsKey(child) && !map.containsKey(child)) {
						Pair pair = new Pair(child, heuristic + current.cost + 1, current);
						frontier.add(pair);
						frontierMap.put(child, pair);
						// decrease key if in frontier
					} else if (frontierMap.containsKey(child)) {
						Pair p = frontierMap.get(child);
						if ((heuristic + current.cost + 1) < p.cost) {
							frontier.remove(p);
							frontierMap.remove(child);
							Pair pair = new Pair(child, heuristic + current.cost + 1, current);
							frontier.add(pair);
							frontierMap.put(child, pair);
						}
					}
				}
			}
		}
		//add node to know that there isn't solution
		expanded.add(new Pair("0000000000", 0, null));
		return expanded;
	}

	//calculate manhattanDistance
	protected double manhattanDistance(String state, String goal) {
		state = state.substring(0, state.length() - 1);
		goal = goal.substring(0, goal.length() - 1);
		double sum = 0;
		for (char a : state.toCharArray()) {
			int curIndex = state.indexOf(a);
			int goalIndex = goal.indexOf(a);
			// Y difference + X difference
			sum += Math.abs((curIndex % 3) - (goalIndex % 3)) + Math.abs((curIndex / 3) - (goalIndex / 3));
		}
		return sum;
	}

	//calculate euclideanDistance
	protected double euclideanDistance(String state, String goal) {
		double sum = 0;
		for (char a : state.toCharArray()) {
			int curIndex = state.indexOf(a);
			int goalIndex = goal.indexOf(a);
			sum += Math.sqrt(
					Math.pow((curIndex % 3) - (goalIndex % 3), 2) + Math.pow((curIndex / 3) - (goalIndex / 3), 2));
		}
		return sum;
	}

	// Comparator to sort pair by cost
	class PairComparator implements Comparator<Pair> {
		// Overriding compare()method of Comparator
		public int compare(Pair p1, Pair p2) {
			if (p1.cost < p2.cost)
				return -1;
			else if (p1.cost > p2.cost)
				return 1;
			return 0;
		}
	}

}
