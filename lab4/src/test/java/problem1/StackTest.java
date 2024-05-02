package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {
  private Stack<Integer> stack;

  // Inline implementation of the Stack interface for testing purposes
  public class SimpleImmutableStack<E> implements Stack<E> {
    private final LinkedList<E> elements;

    public SimpleImmutableStack() {
      this.elements = new LinkedList<>();
    }

    private SimpleImmutableStack(LinkedList<E> elements) {
      this.elements = elements;
    }

    @Override
    public Stack<E> push(E element) {
      LinkedList<E> newElements = new LinkedList<>(elements);
      newElements.addFirst(element);
      return new SimpleImmutableStack<>(newElements);
    }

    @Override
    public Stack<E> remove() {
      if (elements.isEmpty()) {
        throw new IllegalStateException("Cannot remove from an empty stack.");
      }
      LinkedList<E> newElements = new LinkedList<>(elements);
      newElements.removeFirst();
      return new SimpleImmutableStack<>(newElements);
    }

    @Override
    public E top() {
      return elements.peekFirst();
    }
  }

  @BeforeEach
  public void setUp() {
    // Instantiate the inline implementation
    stack = new SimpleImmutableStack<>();
  }

  // Single assert tests
  @Test
  public void testStackIsNotEmptyAfterPush() {
    Stack<Integer> resultStack = stack.push(1);
    assertNotNull(resultStack.top());
  }

  @Test
  public void testTopEqualsLastPushedValue() {
    Integer pushedValue = 1;
    Stack<Integer> resultStack = stack.push(pushedValue);
    assertEquals(pushedValue, resultStack.top());
  }

  @Test
  public void testStackTopIsNullAfterRemovingAllElements() {
    Stack<Integer> tempStack = stack.push(1).push(2).push(3);
    Stack<Integer> resultStack = tempStack.remove().remove().remove();
    assertNull(resultStack.top());
  }

  @Test
  public void testTopAfterPushingThreeElements() {
    Integer lastPushedValue = 3;
    Stack<Integer> resultStack = stack.push(1).push(2).push(lastPushedValue);
    assertEquals(lastPushedValue, resultStack.top());
  }

  @Test
  public void testOriginalStackUnchangedAfterRemove() {
    Integer firstPushedValue = 1;
    Stack<Integer> originalStack = stack.push(firstPushedValue).push(2);
    Stack<Integer> newStack = originalStack.remove(); // Capture the returned stack to verify it separately
    assertEquals(2, originalStack.top(), "Original stack's top should remain unchanged, showing immutability.");
  }

  @Test
  public void testTopAfterRemovingOneElement() {
    Integer firstPushedValue = 1;
    Stack<Integer> resultStack = stack.push(firstPushedValue).push(2).remove();
    assertEquals(firstPushedValue, resultStack.top());
  }

  @Test
  public void testOriginalStackTopUnchangedAfterPush() {
    Integer originalTopValue = 1;
    Stack<Integer> originalStack = stack.push(originalTopValue);
    originalStack.push(2); // Perform push, but ignore the result
    assertEquals(originalTopValue, originalStack.top());
  }

  @Test
  public void testNewStackTopAfterPush() {
    Integer newTopValue = 2;
    Stack<Integer> originalStack = stack.push(1);
    Stack<Integer> newStack = originalStack.push(newTopValue);
    assertEquals(newTopValue, newStack.top());
  }

  @Test
  public void testStackTopIsNullAfterRemovingSingleElement() {
    Stack<Integer> tempStack = stack.push(1);
    Stack<Integer> resultStack = tempStack.remove();
    assertNull(resultStack.top(), "Top should be null after removing the only element, indicating an empty stack.");
  }

  @Test
  public void testStackTopAfterSequentialPushes() {
    Integer pushedValue = 4;
    Stack<Integer> resultStack = stack.push(1).push(2).push(3).push(pushedValue);
    assertEquals(pushedValue, resultStack.top(), "Top should return the last pushed value after sequential pushes.");
  }

  @Test
  public void testStackTopAfterRemoveFollowingMultiplePushes() {
    Stack<Integer> tempStack = stack.push(1).push(2).push(3);
    Stack<Integer> resultStack = tempStack.remove(); // Removing the top, which is 3
    assertNotEquals(3, resultStack.top(), "Top should not be 3 after it has been removed from the stack.");
  }

  @Test
  public void testStackImmutabilityWithMultipleOperations() {
    Stack<Integer> originalStack = stack.push(1);
    originalStack.push(2); // Push but ignore the returned stack
    originalStack.remove(); // Remove but ignore the returned stack
    assertEquals(1, originalStack.top(), "Original stack should remain unchanged after operations that are not assigned.");
  }

  @Test
  public void testNewStackTopAfterPushAndRemove() {
    Stack<Integer> tempStack = stack.push(1).push(2);
    Stack<Integer> resultStack = tempStack.remove().push(3);
    assertEquals(3, resultStack.top(), "Top should be 3 after removing the top element and then pushing 3.");
  }

  @Test
  public void testStackIsEmptyAfterRemovingAllPushedElements() {
    Stack<Integer> resultStack = stack.push(1).push(2).remove().remove();
    assertNull(resultStack.top(), "Top should return null for an empty stack after removing all elements.");
  }

  @Test
  public void testStackTopDoesNotChangeAfterUnassignedRemove() {
    Stack<Integer> tempStack = stack.push(1).push(2);
    tempStack.remove(); // Remove operation is unassigned
    assertEquals(2, tempStack.top(), "Top should still be 2, as the remove operation result was not assigned.");
  }


}