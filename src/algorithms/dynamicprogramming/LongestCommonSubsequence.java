package algorithms.dynamicprogramming;

/** @author David Winton (david.a.winton@gmail.com) */
public class LongestCommonSubsequence {

  /**
   * Computes and returns the largest common subsequence of two strings using the following DP
   * algorithm:
   *
   * We create an NxM table where N = first.length, M = second.length. In position[i,j] of this
   * table,
   * we store the LCS of the first.substring(0,i) and second.substring(0, j)
   *
   * Given the LCS of (A,B) we can find LCS(A
   */
  public static String calculate(String first, String second) {
    if (first.length() == 0 || second.length() == 0) {
      return "";
    }

    String[][] answerTable = new String[first.length() + 1][second.length() + 1];
    for (int i = 0; i <= first.length(); i++) {
      for (int j = 0; j <= second.length(); j++) {
        if (i == 0 || j == 0) {
          answerTable[i][j] = new String("");
        } else {
          if (first.charAt(i - 1) == second.charAt(j - 1)) {
            answerTable[i][j] = (answerTable[i - 1][j - 1]) + first.charAt(i - 1);
          } else {
            answerTable[i][j] = getLonger(answerTable[i - 1][j], answerTable[i][j - 1]);
          }
        }
      }
    }
    return answerTable[first.length()][second.length()].toString();
  }

  private static String getLonger(String first, String second) {
    if (first.length() > second.length()) {
      return first;
    }
    return second;
  }
}
