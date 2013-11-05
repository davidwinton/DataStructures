package trees;

/**
 * An implementation of a Binary Search Tree data structure
 *
 * @author David Winton (david.a.winton@gmail.com)
 */
public class BinarySearchTree<T extends Comparable> {

  BSTNode<T> root;

  public BinarySearchTree(BSTNode<T> root) {
    this.root = root;
  }

  public BinarySearchTree() {
    root = null;
  }

  public BSTNode<T> getRoot() {
    return root;
  }

  public void insert(T val) {
    root = insert(new BSTNode<T>(val), root);
  }

  private BSTNode insert(BSTNode<T> entry, BSTNode<T> current) {
    if (current == null) {
      return entry;
    } else {
      int comparison = entry.compareTo(current);

      if (comparison < 0) { //smaller
        current.setLeft(insert(entry, current.getLeft()));
      } else { //larger
        current.setRight(insert(entry, current.getRight()));
      }
    }
    return current;
  }

  public void remove(T val) {
    root = remove(val, root);
  }

  private BSTNode<T> remove(T val, BSTNode<T> current) {
    if(current == null) {
      return current;
    }

    int comparison = val.compareTo(current.getVal());
    if(comparison < 0) {
      current.setLeft(remove(val, current.getLeft()));
    } else if(comparison > 0) {
      current.setRight(remove(val, current.getRight()));
    } else {
      if(!current.hasRight()) {
        return current.getLeft();
      } else if(!current.hasLeft()) {
        return current.getRight();
      } else {
        BSTNode<T> replacement = getMax(current.getLeft());
        replacement.setRight(current.getRight());
        replacement.setLeft(remove(replacement.getVal(), replacement.getLeft()));
        return replacement;
      }
    }

    return current;
  }

  private BSTNode<T> getMax(BSTNode<T> current) {
    if(current == null) {
      return null;
    } else if(current.hasRight()) {
      return getMax(current.getRight());
    }
    return current;
  }

  public boolean contains(T key) {
    return get(key) != null;
  }

  public BSTNode get(T key) {
    return get(key, root);
  }

  private BSTNode get(T key, BSTNode<T> current) {
    if (current == null) {
      return null;
    }

    int comparison = key.compareTo(current.getVal());

    if (comparison < 0) { //smaller
      return get(key, current.getLeft());
    } else if (comparison > 0) { //larger
      return get(key, current.getRight());
    } else {
      return current;
    }
  }

  public boolean isEmpty() {
    return root == null;
  }


  public static class BSTNode<T extends Comparable> implements Comparable<BSTNode<T>> {
    BSTNode<T> left;
    BSTNode<T> right;
    T val;

    public BSTNode(BSTNode<T> left, BSTNode<T> right, T val) {
      this.left = left;
      this.right = right;
      this.val = val;
    }

    public BSTNode(T val) {
      left = null;
      right = null;
      this.val = val;
    }

    public BSTNode<T> getLeft() {
      return left;
    }

    public void setLeft(BSTNode<T> left) {
      this.left = left;
    }

    public BSTNode<T> getRight() {
      return right;
    }

    public void setRight(BSTNode<T> right) {
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
    public int compareTo(BSTNode<T> o) {
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

      BSTNode bstNode = (BSTNode) o;

      if (val != null ? !val.equals(bstNode.val) : bstNode.val != null) {
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
