package algorithms;

import java.util.PriorityQueue;

public class newPriorityQueue extends PriorityQueue<Pair>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pair containsByString(String o) {
		if (o != null) {
            final Pair[] es = (Pair[]) super.toArray();
            for (int i = 0, n = size(); i < n; i++)
                if (o.equalsIgnoreCase(es[i].state))
                    return es[i];
        }
        return null;
	}
}
