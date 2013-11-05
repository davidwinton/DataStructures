package algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

import trees.Heap;
import trees.Heap.HeapEntry;

/**
 *
 *
 * @author David Winton
 */
public class HeapSort<T extends Comparable> {
  public static <T extends Comparable<? super T>> List<T> sort(List<T> input) {
    Heap<T,T> sortingHeap = setupHeap(input);
    List<T> ret = new ArrayList<T>();

    if(input.isEmpty()) {
      return ret;
    }
    while(!sortingHeap.isEmpty()) {
      ret.add(sortingHeap.pop().getValue());
    }
    return ret;
  }

  private static <T extends Comparable<? super T>> Heap<T,T> setupHeap(List<T> input) {
    List<HeapEntry<T,T>> heapInput = new ArrayList<HeapEntry<T, T>>(input.size());
    for(int i = 0; i < input.size(); i++) {
      heapInput.add(new HeapEntry<T, T>(input.get(i), input.get(i)));
    }
    return new Heap<T, T>(heapInput.<HeapEntry>toArray(new HeapEntry[input.size()]), false);
  }

}
