package test.algorithms.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithms.sorting.HeapSort;
import algorithms.sorting.MergeSort;
import algorithms.sorting.QuickSort;
import org.junit.Test;
import sun.jvm.hotspot.utilities.Assert;

/** @author David Winton */
public class SortingTests {

  @Test()
  public void emptyListSortTest() {
    evaluator(new ArrayList<Integer>());
  }

  @Test()
  public void oneElementSortTest() {
    ArrayList<Integer> inputList = new ArrayList<Integer>();
    inputList.add(7);
    evaluator(inputList);
  }

  @Test
  public void largeListOfDoublesTest() {
    ArrayList<Double> toSort = new ArrayList<Double>();
    for(int i = 0; i < 100000; i ++) {
      toSort.add((Math.random() - .5) * 10000000d);
    }
  }

  @Test
  public void duplicateTest() {
    ArrayList<Integer> toSort = new ArrayList<Integer>();
    for(int i = 0; i < 5; i ++) {
      toSort.add(12);
    }
  }

  private <T extends Comparable<? super T>> void evaluator(List<T> input) {
    List<T> mergeSort = MergeSort.mergeSort(new ArrayList<T>(input));
    List<T> heapSort = HeapSort.sort(new ArrayList<T>(input));
    List<T> quickSort = QuickSort.quickSort(new ArrayList<T>(input));

    List<T> sorted = new ArrayList<T>(input);
    Collections.sort(sorted);

    Assert.that(listEquals(sorted, mergeSort), "Failed mergesort" + display(mergeSort));
    Assert.that(listEquals(sorted, heapSort), "Failed heapsort" + display(heapSort));
    Assert.that(listEquals(sorted, quickSort), "Failed quicksort" + display(quickSort));
  }

  private <T extends Comparable<? super T>> boolean listEquals(List<T> first, List<T> second) {
    if(first.size() != second.size()) {
      return false;
    }

    for(int i = 0;i < first.size(); i++) {
      if(!first.get(i).equals(second.get(i))) {
        return false;
      }
    }
    return true;
  }

  private <T extends Comparable<? super T>> String display(List<T> input) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < input.size(); i++) {
      sb.append(input.get(i)).append(" | ");
    }
    return sb.toString();
  }
}
