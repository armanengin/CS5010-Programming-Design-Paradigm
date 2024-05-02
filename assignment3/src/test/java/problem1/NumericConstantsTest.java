package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumericConstantsTest {

  @Test
  public void testZeroValue() {
    assertEquals(0, NumericConstants.ZERO.getValue());
  }

  @Test
  public void testOneValue() {
    assertEquals(1, NumericConstants.ONE.getValue());
  }

  @Test
  public void testZeroToString() {
    assertEquals("NumericConstants{value=0}", NumericConstants.ZERO.toString());
  }

  @Test
  public void testOneToString() {
    assertEquals("NumericConstants{value=1}", NumericConstants.ONE.toString());
  }
}