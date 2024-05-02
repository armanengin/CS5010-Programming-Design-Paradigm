package problem2;

import java.util.NoSuchElementException;


/**
 * Represents a priority queue where elements are ordered based on their natural ordering
 * or by a Comparator provided at queue construction time. Elements are inserted into the queue
 * in an order determined by their priority, and the queue supports retrieving and removing
 * the element with the highest priority.
 *
 * @param <E> the type of elements held in this priority queue, which must be comparable
 */
public interface PriorityQueue<E extends Comparable<E>> {

  /**
   * Inserts the specified element into this priority queue.
   *
   * @param e the element to add
   */
  void insert(E e);

  /**
   * Removes and returns the highest priority element from this priority queue.
   * If multiple elements have the same priority, it follows the FIFO order.
   *
   * @return the highest priority element in this priority queue
   * @throws NoSuchElementException if this priority queue is empty
   */
  E remove();

  /**
   * Retrieves, but does not remove, the highest priority element from this priority queue.
   * If multiple elements have the same priority, it follows the FIFO order.
   *
   * @return the highest priority element in this priority queue
   * @throws NoSuchElementException if this priority queue is empty
   */
  E front();

  /**
   * Returns {@code true} if this priority queue contains no elements.
   *
   * @return {@code true} if this priority queue is empty; {@code false} otherwise
   */
  boolean isEmpty();

  /**
   * Returns a string representation of this priority queue. The string representation
   * consists of a list of the priority queue's elements in the order they are stored,
   * enclosed in braces ("{}"). Adjacent elements are separated by the characters ", "
   * (comma and space).
   *
   * @return a string representation of this priority queue
   */
  String toString();
}
