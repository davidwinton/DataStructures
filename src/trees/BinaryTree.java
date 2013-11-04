package trees;

import java.util.Collection;
import java.util.Iterator;

/** @author David Winton */
public class BinaryTree<T> implements Collection<T> {
  @Override
  public int size() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isEmpty() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean contains(Object o) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Iterator<T> iterator() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object[] toArray() {
    return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean add(T t) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean remove(Object o) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void clear() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public static class BinaryTreeNode<T extends Comparable>
      implements Comparable<BinaryTreeNode<T>> {
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
    T val;

    public BinaryTreeNode(BinaryTreeNode<T> left, BinaryTreeNode<T> right, T val) {
      this.left = left;
      this.right = right;
      this.val = val;
    }

    public BinaryTreeNode(T val) {
      left = null;
      right = null;
      this.val = val;
    }

    public BinaryTreeNode<T> getLeft() {
      return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
      this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
      return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
      this.right = right;
    }

    public T getVal() {
      return val;
    }

    public void setVal(T val) {
      this.val = val;
    }

    public boolean hasLeft() {
      return left != null;
    }

    public boolean hasRight() {
      return right != null;
    }

    @Override
    public int compareTo(BinaryTreeNode<T> o) {
      return this.val.compareTo(o.val);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      BinaryTreeNode BinaryTreeNode = (BinaryTreeNode) o;

      if (val != null ? !val.equals(BinaryTreeNode.val) : BinaryTreeNode.val != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return val != null ? val.hashCode() : 0;
    }

    public void clear() {
      this.left = null;
      this.right = null;
      this.val = null;
    }
  }
}
