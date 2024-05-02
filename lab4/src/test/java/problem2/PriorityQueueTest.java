package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriorityQueueTest {
  private MyPriorityQueue<Integer> priorityQueue;

  @BeforeEach
  public void setUp() {
    priorityQueue = new MyPriorityQueue<>();
  }

  @Test
  public void shouldBeEmptyWhenNew() {
    assertTrue(priorityQueue.isEmpty(), "Newly created priority queue should be empty.");
  }

  @Test
  public void shouldNotBeEmptyAfterInsert() {
    priorityQueue.insert(10);
    assertFalse(priorityQueue.isEmpty(), "Priority queue should not be empty after an insert operation.");
  }

  @Test
  public void frontShouldReturnLowestElementAfterSingleInsert() {
    priorityQueue.insert(20);
    assertEquals(20, priorityQueue.front(), "Front should return the element when it is the only one inserted.");
  }

  @Test
  public void frontShouldReturnLowestElementAfterMultipleInserts() {
    priorityQueue.insert(20);
    priorityQueue.insert(10);
    assertEquals(10, priorityQueue.front(), "Front should return the lowest element after multiple inserts.");
  }

  @Test
  public void removeShouldReturnLowestElement() {
    priorityQueue.insert(15);
    priorityQueue.insert(10);
    assertEquals(10, priorityQueue.remove(), "Remove should return the lowest element.");
  }

  @Test
  public void removeShouldCorrectlyReheapify() {
    MyPriorityQueue<Integer> priorityQueue = new MyPriorityQueue<>();
    priorityQueue.insert(50); // This will be removed first.
    priorityQueue.insert(45);
    priorityQueue.insert(40);
    priorityQueue.insert(30);
    priorityQueue.insert(35);
    priorityQueue.insert(20);
    priorityQueue.insert(10);
    // Remove the root, which is 50. The last element (10) moves to the root and should bubble down.
    priorityQueue.remove();

    // The next remove should remove 20, which was one of the next highest priorities after 50.
    // This confirms that the bubbling down correctly repositioned 10 below 20 in the heap.
    Integer result = priorityQueue.remove();
    assertEquals(Integer.valueOf(20), result, "The next remove should return 20, indicating correct reheapification.");
  }

  @Test
  public void queueShouldBeEmptyAfterRemovingAllElements() {
    priorityQueue.insert(10);
    priorityQueue.remove();
    assertTrue(priorityQueue.isEmpty(), "Priority queue should be empty after all elements are removed.");
  }

  @Test
  public void frontShouldThrowExceptionOnEmptyQueue() {
    assertThrows(NoSuchElementException.class, priorityQueue::front, "Accessing front on an empty queue should throw an exception.");
  }

  @Test
  public void removeShouldThrowExceptionOnEmptyQueue() {
    assertThrows(NoSuchElementException.class, priorityQueue::remove, "Removing from an empty queue should throw an exception.");
  }

  @Test
  public void frontShouldRemainUnchangedAfterMultipleFrontCalls() {
    priorityQueue.insert(10);
    priorityQueue.front(); // Call front but do not remove
    assertEquals(10, priorityQueue.front(), "Front should remain unchanged after multiple front calls.");
  }

  @Test
  public void toStringShouldReturnCorrectFormat() {
    MyPriorityQueue<Integer> priorityQueue = new MyPriorityQueue<>();
    priorityQueue.insert(10);
    priorityQueue.insert(20);
    String expected = "MyPriorityQueue{heap=[10, 20]}";
    assertEquals(expected, priorityQueue.toString(), "toString does not match the expected format.");
  }

  @Test
  public void equalsShouldVerifyEqualityCorrectly() {
    MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
    queue1.insert(10);
    queue1.insert(20);

    MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>();
    queue2.insert(10);
    queue2.insert(20);

    assertEquals(queue1, queue2, "Two queues with the same elements should be equal.");
  }

  @Test
  public void equalsShouldFailForDifferentQueues() {
    MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
    queue1.insert(10);

    MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>();
    queue2.insert(20);

    assertNotEquals(queue1, queue2, "Two queues with different elements should not be equal.");
  }

  @Test
  public void hashCodeShouldBeConsistentWithEquals() {
    MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
    queue1.insert(10);
    queue1.insert(20);

    MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>();
    queue2.insert(10);
    queue2.insert(20);

    assertEquals(queue1.hashCode(), queue2.hashCode(), "Hash codes should be equal for equal queues.");
  }

  @Test
  public void hashCodeShouldDifferForDifferentQueues() {
    MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>();
    queue1.insert(10);

    MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>();
    queue2.insert(20);

    // It's possible (though highly unlikely) for different objects to have the same hash code,
    // so this test isn't as strong as the equals tests.
    assertNotEquals(queue1.hashCode(), queue2.hashCode(), "Hash codes should differ for non-equal queues.");
  }

}