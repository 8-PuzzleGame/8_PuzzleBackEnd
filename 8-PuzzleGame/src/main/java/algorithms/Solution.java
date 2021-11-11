package algorithms;

import java.util.List;

public class Solution {
	public List<String> path = null;
	public int cost, nodes_expanded, search_depth;
	public long time; // I am not sure about the type :/
	
	public Solution() {}
	
	public Solution(List<String> path, int cost, int nodes_expanded, int search_depth, long time) {
		this.path = path;
		this.cost = cost;
		this.nodes_expanded = nodes_expanded;
		this.search_depth = search_depth;
		this.time = time;
	}
}
