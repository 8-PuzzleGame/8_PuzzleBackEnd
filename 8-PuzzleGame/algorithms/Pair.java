package algorithms;

public class Pair {

	String state;
	double cost;
	Pair parent;
	public Pair(String state,double cost,Pair parent) {
		this.state = state;
		this.cost = cost ;
		this.parent = parent ;
	}
	@Override
	public String toString() {
		return "Pair [state=" + state + ", cost=" + cost + ", parent=" + parent + "]";
	}
	
}
