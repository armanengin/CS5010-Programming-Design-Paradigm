package problem2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * A priority queue implementation that organizes elements based on their natural ordering.
 * Elements are inserted into the queue in a way that the element with the highest priority
 * (determined by natural ordering) is always at the front of the queue. This implementation
 * uses a min heap to efficiently manage elements.
 *
 * @param <E> the type of elements held in this priority queue, which must be Comparable
 */
public class MyPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
  private ArrayList<E> heap;

  /**
   * Constructs an empty priority queue.
   */
  public MyPriorityQueue() {
    this.heap = new ArrayList<>();
  }

  /**
   * Inserts an element into the priority queue. The element is placed in the queue based on its
   * natural ordering to ensure that the element with the highest priority is at the front.
   *
   * @param e the element to insert
   */
  @Override
  public void insert(E e) {
    heap.add(e); // Add at the end
    int currentIndex = heap.size() - 1;
    // Bubble up
    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      if (heap.get(currentIndex).compareTo(heap.get(parentIndex)) < 0) {
        E temp = heap.get(currentIndex);
        heap.set(currentIndex, heap.get(parentIndex));
        heap.set(parentIndex, temp);
      } else {
        break;
      }
      currentIndex = parentIndex;
    }
  }

  /**
   * Removes and returns the element at the front of the priority queue, which is the element
   * with the highest priority according to its natural ordering.
   *
   * @return the element with the highest priority
   * @throws NoSuchElementException if the priority queue is empty
   */
  @Override
  public E remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("Priority queue is empty.");
    }
    E removedItem = heap.get(0);
    heap.set(0, heap.get(heap.size() - 1)); // Move the last item to the root
    heap.remove(heap.size() - 1); // Remove the last item
    // Bubble down
    int currentIndex = 0;
    while (currentIndex < heap.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;
      // Find the max of the left and right child
      int minIndex = currentIndex;
      if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(minIndex)) < 0) {
        minIndex = leftChildIndex;
      }
      if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(minIndex)) < 0) {
        minIndex = rightChildIndex;
      }
      if (minIndex == currentIndex) {
        break; // The heap property is satisfied
      }
      // Swap
      E temp = heap.get(minIndex);
      heap.set(minIndex, heap.get(currentIndex));
      heap.set(currentIndex, temp);
      currentIndex = minIndex;
    }
    return removedItem;
  }

  /**
   * Retrieves, but does not remove, the element at the front of the priority queue,
   * which is the element with the highest priority.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the priority queue is empty
   */

  @Override
  public E front() {
    if (isEmpty()) {
      throw new NoSuchElementException("Priority queue is empty.");
    }
    return heap.get(0);
  }


  /**
   * Checks whether the priority queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return heap.isEmpty();
  }


  /**
   * Returns a string representation of the priority queue.
   *
   * @return a string representation of the queue
   */
  @Override
  public String toString() {
    return "MyPriorityQueue{" +
        "heap=" + heap +
        '}';
  }


  /**
   * Compares the specified object with this priority queue for equality. Returns true if
   * and only if the specified object is also a priority queue, both queues have the same
   * size, and all corresponding pairs of elements in the two queues are equal.
   *
   * @param o the object to be compared for equality with this priority queue
   * @return true if the specified object is equal to this priority queue
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MyPriorityQueue<?> that)) {
      return false;
    }
    return Objects.equals(heap, that.heap);
  }


  /**
   * Returns the hash code value for this priority queue. The hash code of a queue is
   * defined to be the result of the following calculation:
   * <code>Objects.hash(heap)</code>, where <code>heap</code> is the underlying
   * representation of the priority queue.
   *
   * @return the hash code value for this priority queue
   */
  @Override
  public int hashCode() {
    return Objects.hash(heap);
  }
}