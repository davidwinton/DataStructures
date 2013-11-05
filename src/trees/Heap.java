package trees;

import java.util.Arrays;

/**
 * An implementation of the heap data structure.
 *
 * This class can be configured as a max-heap or min-heap depending on the isMaxHeap parameter.
 *
 * @author David Winton (david.a.winton@gmail.com)
 */
public class Heap<KEY extends Comparable, VALUE> {

  private final int DEFAULT_SIZE = 11;
  private int size;
  private boolean isMaxHeap;
  HeapEntry<KEY, VALUE>[] data;

  public Heap() {
    this(false);
  }

  public Heap(boolean isMaxHeap) {
    this.isMaxHeap = isMaxHeap;
    size = 0;
    data = new HeapEntry[DEFAULT_SIZE];
  }

  public Heap(HeapEntry<KEY, VALUE>[] input, boolean isMaxHeap) {
    this.isMaxHeap = isMaxHeap;
    size = input.length;
    prepareArray(input);
    heapify();
  }

  private void heapify() {
    if (size == 0) {
      return;
    }

    for (int i = size / 2; i > 0; i--) {
      percolateDown(i);
    }
  }

  private void swap(int first, int second) {
    if (first >= data.length || second >= data.length) {
      throw new IllegalArgumentException("tried to swap indices that were not in the data array");
    }
    HeapEntry<KEY, VALUE> temp = data[first];
    data[first] = data[second];
    data[second] = temp;
  }

  public void insert(HeapEntry<KEY, VALUE> entry) {
    size++;
    if (isFull()) {
      doubleArray();
    }
    data[size] = entry;

    percolateUp(size);
  }

  private boolean isFull() {
    return size == data.length - 1;
  }

  private void percolateUp(int index) {
    if(index == 0) {
      return;
    }

    int parent = index / 2;

    if (getBetterMatch(index, parent) == index) {
      swap(index, parent);
      percolateUp(parent);
    }
  }

  private void percolateDown(int index) {
    if(index > size / 2) {
      return;
    }

    int child = getBetterMatch(2 * index, 2 * index + 1);
    if (child != -1 && getBetterMatch(index, child) != index) {
      swap(index, child);
      percolateDown(child);
    }
  }

  public HeapEntry<KEY, VALUE> pop() {
    if (isEmpty()) {
      return null;
    }

    HeapEntry ret = data[1];
    data[1] = data[size];
    data[size] = null;
    size--;

    if (!isEmpty()) {
      percolateDown(1);
    }
    return ret;
  }

  public HeapEntry<KEY, VALUE> peek() {
    if (isEmpty()) {
      return null;
    }
    return data[1];
  }

  private void prepareArray(HeapEntry<KEY, VALUE>[] input) {
    data = new HeapEntry[input.length + 1];
    for (int i = 0; i < input.length; i++) {
      data[i + 1] = input[i];
    }
  }

  private int getBetterMatch(int firstIndex, int secondIndex) {
    if (firstIndex > size || firstIndex == 0) {
      return secondIndex > size || secondIndex == 0? -1 : secondIndex;
    }

    if (secondIndex > size || secondIndex == 0) {
      return firstIndex > size  || firstIndex == 0? -1 : firstIndex;
    }

    HeapEntry first = data[firstIndex];
    HeapEntry second = data[secondIndex];
    if (second == null) {
      return firstIndex;
    }

    int comparison = first.getKey().compareTo(second.getKey());
    if ((comparison > 0 && isMaxHeap) || (comparison < 0 && !isMaxHeap)) {
      return firstIndex;
    } else {
      return secondIndex;
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void doubleArray() {
    data = Arrays.copyOf(data, data.length * 2);
  }

  public static class HeapEntry<KEY extends Comparable, VALUE> {
    private KEY key;
    private VALUE value;

    public HeapEntry(KEY key, VALUE value) {
      this.key = key;
      this.value = value;
    }

    public KEY getKey() {
      return key;
    }

    public void setKey(KEY key) {
      this.key = key;
    }

    public VALUE getValue() {
      return value;
    }

    public void setValue(VALUE value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      HeapEntry heapEntry = (HeapEntry) o;

      if (!key.equals(heapEntry.key)) {
        return false;
      }
      return value.equals(heapEntry.value);

    }

    @Override
    public int hashCode() {
      int result = key.hashCode();
      result = 31 * result + value.hashCode();
      return result;
    }
  }
}
