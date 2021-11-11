package algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class A_starTests extends A_star {

	@Test
	void heuristic1Tests() {
		assertEquals(manhattanDistance("123456780", "123456780"), 0);
		assertEquals(manhattanDistance("123045678", "123456780"), 10);
		assertEquals(manhattanDistance("421357806", "123456780"), 12);
		assertEquals(manhattanDistance("123456780", "087654321"), 24);
		assertEquals(manhattanDistance("214536078", "087654321"), 20);
		assertEquals(manhattanDistance("087654321", "087654321"), 0);

	}

	@Test
	void heuristic2Tests() {
		assertEquals(euclideanDistance("123456780", "123456780"), 0);
		assertEquals(euclideanDistance("123045678", "123456780"), 8.47213595499958);
		assertEquals(euclideanDistance("421357806", "123456780"), 10.47213595499958);
		assertEquals(euclideanDistance("123456780", "087654321"), 19.31370849898476);
		assertEquals(euclideanDistance("214536078", "087654321"), 16.358485472372255);
		assertEquals(euclideanDistance("087654321", "087654321"), 0);
	}

}
