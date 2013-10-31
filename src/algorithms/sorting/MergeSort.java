package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

/** @author David Winton (david.a.winton@gmail.com) */
public class MergeSort {

  /**
   * Uses the mergesort algorithm to sort a list in n log n time and n log n additional space
   * @param input - list to be sorted. Note, list must be comprised of comparable elements
   * @return - a sorted version of the input list. This is not performed in place and does not alter
   * input
   */
  public static List<? extends Comparable> mergeSort(List<? extends Comparable> input) {

    if (input.size() == 1 || input.size() == 0) {
      return input;
    }
    List<Comparable> left = new ArrayList<Comparable>(copyOfRange(input, 0, input.size() / 2));
    List<Comparable> right =
        new ArrayList<Comparable>(copyOfRange(input, input.size() / 2, input.size()));
    return merge(mergeSort(left), mergeSort(right));
  }

  /**
   * Takes two sorted lists and combines them into a single sorted list in linear time relative to
   * the sum of the sizes of the lists
   */
  private static List<Comparable> merge(List<? extends Comparable> first,
      List<? extends Comparable> second) {
    int firstIndex = 0;
    int secondIndex = 0;
    List<Comparable> result = new ArrayList<Comparable>(first.size() + second.size());
    while (firstIndex < first.size() || secondIndex < second.size()) {

      /*
       * We use the item in the first list if we have items left in that list that are smaller than
       * the next item in the second list, or we've run out of items in the second list
       */
      if ((secondIndex == second.size() || firstIndex < first.size() &&
          first.get(firstIndex).compareTo(second.get(secondIndex)) < 0)) {
        result.add(firstIndex + secondIndex, first.get(firstIndex));
        firstIndex++;
      } else {
        result.add(firstIndex + secondIndex, second.get(secondIndex));
        secondIndex++;
      }
    }
    return result;
  }


  static List copyOfRange(List input, int start, int end) {
    List<Object> ret = new ArrayList<Object>();

    for (int i = start; i < end; i++) {
      ret.add(input.get(i));
    }

    return ret;
  }
}
