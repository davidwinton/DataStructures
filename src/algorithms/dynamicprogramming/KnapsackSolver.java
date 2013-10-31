package algorithms.dynamicprogramming;

import java.util.List;

/** @author David Winton (david.a.winton@gmail.com) */
public class KnapsackSolver {
  private List<KnapsackItems> items;
  private double maxWeight;

  private double[][] valueMatrix;

  public KnapsackSolver(List<KnapsackItems> items, double maxWeight) {
    this.items = items;
    this.maxWeight = maxWeight;

    valueMatrix = new double[items.size()][(int)(Math.ceil(maxWeight))];
  }

  public List<KnapsackItems> solve(boolean allowRepeats) {
    return allowRepeats ? solveWithRepeats() : solveWithoutRepeats();
  }

  private List<KnapsackItems> solveWithoutRepeats() {
    return null;
  }

  private List<KnapsackItems> solveWithRepeats() {
    return null;
  }


  public static class KnapsackItems<V> {
    Double weight;
    V value;

    public KnapsackItems(Double weight, V value) {
      this.weight = weight;
      this.value = value;
    }

    public Double getWeight() {
      return weight;
    }

    public void setWeight(Double weight) {
      this.weight = weight;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }
}
