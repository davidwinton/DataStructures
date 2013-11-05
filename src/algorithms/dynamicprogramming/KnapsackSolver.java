package algorithms.dynamicprogramming;

import java.util.List;

/** @author David Winton (david.a.winton@gmail.com) */
public class KnapsackSolver {
  private List<KnapsackItem> items;
  private int maxWeight;

  private double[][] valueMatrix;

  public KnapsackSolver(List<KnapsackItem> items, int maxWeight) {
    this.items = items;
    this.maxWeight = maxWeight;

    valueMatrix = new double[items.size()][maxWeight];
  }

  public List<KnapsackItem> solve(boolean allowRepeats) {
    return allowRepeats ? solveWithRepeats() : solveWithoutRepeats();
  }

  /**
   * 0/1 Knapsack Problem solver that runs in psuedo-polynomial time
   * @return the maximum value that can be attained within the given weight specification
   */
  private List<KnapsackItem> solveWithoutRepeats() {
    fillValueMatrix();
    return backtrack();

  }

  private List<KnapsackItem> backtrack() {

    return null;  //To change body of created methods use File | Settings | File Templates.
  }

  private void fillValueMatrix() {
    for(int i = 0; i < items.size(); i ++) {
      for(int j = 0; j <= maxWeight; j++) {
        KnapsackItem entry = items.get(i);
        if(j > entry.getWeight()) {
          valueMatrix[i][j] = Math.max(valueMatrix[i-1][j],
              valueMatrix[i-1][j- entry.getWeight()] + entry.getValue());
        } else {
          valueMatrix[i][j] = valueMatrix[i-1][j];
        }
      }
    }
  }

  private List<KnapsackItem> solveWithRepeats() {
    return null;
  }


  public static class KnapsackItem {
    Integer weight;
    Double value;

    public KnapsackItem(Integer weight, Double value) {
      this.weight = weight;
      this.value = value;
    }

    public Integer getWeight() {
      return weight;
    }

    public void setWeight(Integer weight) {
      this.weight = weight;
    }

    public Double getValue() {
      return value;
    }

    public void setValue(Double value) {
      this.value = value;
    }
  }
}
