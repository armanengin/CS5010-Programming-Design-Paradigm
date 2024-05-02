package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {
  public static class SimpleImmutableQueue<E> implements Queue<E> {
    private final LinkedList<E> elements;

    public SimpleImmutableQueue() {
      this.elements = new LinkedList<>();
    }

    private SimpleImmutableQueue(LinkedList<E> elements) {
      this.elements = elements;
    }

    @Override
    public Queue<E> enqueue(E element) {
      LinkedList<E> newElements = new LinkedList<>(elements);
      newElements.addLast(element);
      return new SimpleImmutableQueue<>(newElements);
    }

    @Override
    public Queue<E> dequeue() {
      if (elements.isEmpty()) {
        return this; // or throw new NoSuchElementException("Queue is empty.");
      }
      LinkedList<E> newElements = new LinkedList<>(elements);
      newElements.removeFirst();
      return new SimpleImmutableQueue<>(newElements);
    }

    @Override
    public E front() {
      return elements.peekFirst();
    }
  }

  private Queue<Integer> queue;

  @BeforeEach
  public void setUp() {
    queue = new SimpleImmutableQueue<>();
  }

  @Test
  public void enqueueShouldAddElement() {
    Queue<Integer> newQueue = queue.enqueue(1);
    assertEquals(1, newQueue.front());
  }

  @Test
  public void dequeueShouldRemoveFirstElement() {
    Queue<Integer> newQueue = queue.enqueue(1).enqueue(2).dequeue();
    assertEquals(2, newQueue.front());
  }

  @Test
  public void frontShouldReturnFirstElementWithoutRemoving() {
    Queue<Integer> newQueue = queue.enqueue(1).enqueue(2);
    assertEquals(1, newQueue.front());
    // Ensure the element is not removed
    assertEquals(1, newQueue.front());
  }

  @Test
  public void dequeueOnEmptyQueueShouldDoNothing() {
    Queue<Integer> newQueue = queue.dequeue(); // Assuming dequeue on empty queue returns the same queue
    assertNull(newQueue.front());
  }

  @Test
  public void enqueueAndDequeueOperationsShouldMaintainCorrectOrder() {
    queue = queue.enqueue(1).enqueue(2).dequeue().enqueue(3);
    assertEquals(2, queue.front()); // After dequeuing 1, the front should be 2
    queue = queue.dequeue();
    assertEquals(3, queue.front()); // After dequeuing 2, the front should be 3
  }

  @Test
  public void enqueueIncreasesSize() {
    Queue<Integer> initialQueue = queue.enqueue(1);
    Queue<Integer> newQueue = initialQueue.enqueue(2);
    assertNotEquals(initialQueue.front(), newQueue.dequeue().front(), "Enqueue should increase the queue size.");
  }

  @Test
  public void dequeueReducesSize() {
    Queue<Integer> initialQueue = queue.enqueue(1).enqueue(2);
    Queue<Integer> afterDequeue = initialQueue.dequeue();
    assertNotEquals(initialQueue.front(), afterDequeue.front(), "Dequeue should reduce the queue size.");
  }

  @Test
  public void frontReturnsFirstEnqueuedElement() {
    Queue<Integer> newQueue = queue.enqueue(1);
    assertEquals(1, newQueue.front(), "Front should return the first enqueued element.");
  }

  @Test
  public void frontRemainsUnchangedAfterFrontCall() {
    Queue<Integer> newQueue = queue.enqueue(1).enqueue(2);
    int firstFrontCall = newQueue.front();
    int secondFrontCall = newQueue.front();
    assertEquals(firstFrontCall, secondFrontCall, "Front should remain unchanged after calling front().");
  }

  @Test
  public void frontAfterEnqueueDequeueEnqueue() {
    Queue<Integer> queueAfterOperations = queue.enqueue(1).dequeue().enqueue(2);
    assertEquals(2, queueAfterOperations.front(), "Front should reflect the most recent state after enqueue-dequeue-enqueue operations.");
  }

  @Test
  public void frontAfterMultipleDequeues() {
    Queue<Integer> initialQueue = queue.enqueue(1).enqueue(2).enqueue(3);
    Queue<Integer> queueAfterDequeues = initialQueue.dequeue().dequeue();
    assertEquals(3, queueAfterDequeues.front(), "Front should be the last remaining element after multiple dequeues.");
  }

  @Test
  public void immutabilityAfterEnqueue() {
    Queue<Integer> originalQueue = queue.enqueue(1);
    originalQueue.enqueue(2); // This operation's result is not stored
    assertEquals(1, originalQueue.front(), "Original queue should remain unchanged after an enqueue operation.");
  }

  @Test
  public void immutabilityAfterDequeue() {
    Queue<Integer> originalQueue = queue.enqueue(1).enqueue(2);
    originalQueue.dequeue(); // This operation's result is not stored
    assertEquals(1, originalQueue.front(), "Original queue should remain unchanged after a dequeue operation.");
  }
}