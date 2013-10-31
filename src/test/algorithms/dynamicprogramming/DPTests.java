package test.algorithms.dynamicprogramming;

import algorithms.dynamicprogramming.LongestCommonSubsequence;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

/** @author David Winton (david.a.winton@gmail.com) */
public class DPTests {

  @Test()
  public void LCSubsequenceTest() {
    testLCSubsequenceOnEmptyString();
    testLCSubsequenceOnOneEmptyString();
    testLCSubsequence();
  }

  private void testLCSubsequence() {
    Assert.that(LongestCommonSubsequence.calculate("ABC", "ABC").equals("ABC"), "identical words");
    Assert.that(LongestCommonSubsequence.calculate("BABCD", "ABC").equals("ABC"),
        "substring matches");
    Assert.that(LongestCommonSubsequence.calculate("EAEBETFC", "ABCD").equals("ABC"),
        "second is a subsequence of the first");
    Assert.that(LongestCommonSubsequence.calculate("EAEBETFC", "ATBTCTD").equals("ABTC"),
        "second is a subsequence of the first");
  }

  private void testLCSubsequenceOnOneEmptyString() {
    Assert.that(LongestCommonSubsequence.calculate("", "ABC").equals(""), "first word is empty");
    Assert.that(LongestCommonSubsequence.calculate("ABC", "").equals(""), "second word is empty");
  }

  public void testLCSubsequenceOnEmptyString() {
    Assert.that(LongestCommonSubsequence.calculate("", "").equals(""), "empty case");
  }
}
