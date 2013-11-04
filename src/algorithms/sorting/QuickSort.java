package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

/** @author David Winton (david.a.winton@gmail.com) */
public class QuickSort {

  /**
   * Performs quicksort to sort the input list. Note: this is not the in place version of the
   * quicksort algorithm and instead uses an expected n log n additional space
   * @param input
   * @return
   */
  public static  <T extends Comparable<? super T>> List<T> quickSort(List<T> input) {
    if (input.size() == 0 || input.size() == 1) {
      return input;
    }

    T pivot = input.get((int) (Math.random() * input.size()));
    List<T> less = new ArrayList<T>();
    List<T> greater = new ArrayList<T>();
    List<T> pivotList = new ArrayList<T>();

    for (T entry : input) {
      int comparison = entry.compareTo(pivot);
      if (comparison < 0) {
        less.add(entry);
      } else if (comparison > 0) {
        greater.add(entry);
      } else {
        pivotList.add(entry);
      }
    }

    List<T> result = quickSort(less);
    result.addAll(pivotList);
    result.addAll(quickSort(greater));
    return result;
  }

  /**
   *
   * Partitions the input into sections that are respectively less than the pivot and greater than
   * or equal to the pivot.
   *
   * @param input - list to be partitioned
   * @param left - first element to be considered in the partition
   * @param right - last element to be considered in the partition
   * @param index - the location of the pivot element. As an invariant, this must be between left
   * and right
   * @return - the index of the final location of the pivot or -1 if there is invalid input
   */
  public static <T extends Comparable<? super T>> int partition(List<T> input, int left,
      int right, int index) {

    if(index < left || index > right) {
      //should throw an exception
      return -1;
    }

    T pivot = input.get(index);
    swap(input, right, index);

    int less = left;
    for (int i = left; i < right; i++) {
      if (input.get(i).compareTo(pivot) < 0) {
        swap(input, i, less);
        less++;
      }
    }
    swap(input, less, right);
    return less;
  }

  public static void swap(List input, int first, int second) {
    if (first > input.size() - 1 || second > input.size() - 1 || first < 0 || second < 0) {
      return; //would like to throw an exception but who needs the hassle
    }

    Object temp = input.get(first);
    input.set(first, input.get(second));
    input.set(second, temp);
  }
}
