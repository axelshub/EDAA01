package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Queue;

import queue_singlelinkedlist.FifoQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<>();
		q2 = new FifoQueue<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	void testTwoEmpty() {
		int expectedSize = q1.size() + q2.size();
	
		q1.append(q2);
		
		assertEquals(expectedSize, q1.size(), "Wrong size of empty queue");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
	}
	@Test
	void testEmptyQueueAppend() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q1.offer(4);
		q1.offer(5);
		
		q1.append(q2);
		
		int expectedSize = q1.size() + q2.size();
		
		assertEquals(expectedSize, q1.size(), "Wrong total size");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals(i, k, "poll returns incorrect element");
		}
		
	}
	
	@Test
	void testAppendToEmptyQueue() {
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		q2.offer(4);
		q2.offer(5);
		
		int expectedSize = q1.size() + q2.size();
		
		q1.append(q2);
		
		assertEquals(expectedSize, q1.size(), "Wrong total size");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals(i, k, "poll returns incorrect element");
		}	
	}
	
	@Test
	void TwoNonEmpty() {
		q1.offer(1);
		q1.offer(2);
		q2.offer(3);
		q2.offer(4);
		q2.offer(5);
		
		int expectedSize = q1.size() + q2.size();
		
		q1.append(q2);
		
		assertEquals(expectedSize, q1.size(), "Wrong total size");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		
		for (int i = 1; i <= 5; i++) {
			int k = q1.poll();
			assertEquals(i, k, "poll returns incorrect element");
		}
	}
	
	@Test
	void AppendToSelf() {
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
	}

}
