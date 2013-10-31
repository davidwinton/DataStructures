package trees;

/**
 * Implementation for the splay tree data structure
 *
 * Splay trees are a form of self-balancing binary search trees that "splay" a node to the root of
 * the tree whenever it is accessed
 *
 * @author David Winton (david.a.winton@gmail.com)
 */
public class SplayTree<T extends Comparable> extends BinarySearchTree<T>{

  private SplayNode<T> root;

  public SplayTree(SplayNode<T> root) {
    this.root = root;
  }

  public SplayTree() {
    this.root = null;
  }

  private void splay(SplayNode<T> node) {
    if(node == null) {
      return;
    }

    while(node.hasParent()) {
      SplayNode<T> parent = node.getParent();
      if(!parent.hasParent()) {
        zig(node);
      } else {
        SplayNode<T> grandparent = parent.getParent();
        if(node.equals(parent.getLeft()) && parent.equals(grandparent.getLeft())
            || node.equals(parent.getRight()) && parent.equals(grandparent.getRight())) {
          zigzig(node);
        } else {
          zigzag(node);
        }
      }
    }
  }

  @Override
  public void insert(T val) {
    if(val == null) {
      return;
    }
    insert(new SplayNode<T>(val));
  }

  /** Iteratively inserts entry into the tree then splays that entry to the top
   *
   * @param entry
   * @return
   */
  private void insert(SplayNode<T> entry) {
    SplayNode<T> parent = null;
    SplayNode<T> current = root;

    while(current != null) {
      parent = current;
      if(entry.compareTo(current) < 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }

    if(parent == null) {
      root = entry;
    } else if(entry.compareTo(parent) < 0) {
      parent.setLeft(entry);
    } else {
      parent.setRight(entry);
    }

    splay(entry);
  }


  @Override
  public void remove(T val) {
    SplayNode<T> node = (SplayNode)get(val);
    if(node == null) {
      return;
    }
    remove(node);
  }

  private void remove(SplayNode<T> node) {
    if(node == null) {
      return;
    }
    splay(node);
    SplayNode<T> newRoot = null;
    if(node.hasLeft() && node.hasRight()) {
      //insert maximal element from left sub-tree to root
       newRoot = (SplayNode)TreeUtils.getMax(node.getLeft());
      if(newRoot.equals(newRoot.getParent().getRight())) {
        newRoot.getParent().setRight(newRoot.getLeft());
      } else { //parent is the current root
        newRoot.getParent().setLeft(newRoot.getLeft());
      }

      newRoot.setLeft(node.getLeft());
      newRoot.setRight(node.getRight());

    } else if (!node.hasLeft()) {
      //There is only a right sub-tree so we make node.Right the root
      newRoot = node.getRight();
    } else if (!node.hasRight()) {
      //There is only a left sub-tree so we make node.Left the root
      newRoot = node.getLeft();
    }

    root = newRoot;
    if(newRoot != null) {
      newRoot.setParent(null);
    }

    node.clear();
  }

  public SplayNode<T> find(T val) {
    if(val == null) {
      return null;
    }
    SplayNode node = (SplayNode)get(val);
    splay((SplayNode<T>)get(val));
    return node;
  }

  /**
   * This step is used when the accessed node is the child of the root. If node is the right child
   * of the root (which we'll call "p"), we make node the root and make p its left child.
   * If node had a right sub-tree, we make that p's right sub-tree.
   *
   * If node is the left child, we follow the steps above but substitute left and right
   *
   * @param node - the node we are splaying to the root.
   *
   *  Invariants: Node.getParent must be the root
   */
  private void zig(SplayNode<T> node) {
    if (node == null || node.getParent() == null || node.getParent().getParent() != null) {
      //we've violated our invariant
      throw new IllegalArgumentException("zig can only be used on a child of the root");
    }

    SplayNode<T> parent = node.getParent();
    if (parent.getRight().equals(node)) {
      parent.setRight(node.getRight());
      node.setRight(parent);
    } else {
      parent.setLeft(node.getLeft());
      node.setLeft(parent);
    }

    node.setParent(null);
    root = node;
  }

  /**
   * This step is used when the accessed node and its parent ("p") are both right or both left
   * children and p is not the root
   *
   * If node is a left child, we make node the root, p its right child, node's right child p's left
   * child,
   *
   * If node is the left child, we follow the steps above but substitute left and right
   *
   * @param node - the node we are splaying to the root.
   *
   * Invariants: node must have a grandparent, node and parent must both be left children or
   *   both be right children
   */
  private void zigzig(SplayNode<T> node) {
    if (node == null || node.getParent() == null || node.getParent().getParent() == null) {
      throw new IllegalArgumentException("node didn't have a grandparent");
    }

    SplayNode parent = node.getParent();
    SplayNode grandparent = parent.getParent();

    if ((node.equals(parent.getLeft()) && parent.equals(grandparent.getRight()))
        || (node.equals(parent.getRight()) && parent.equals(grandparent.getLeft()))) {
      throw new IllegalArgumentException("node and parent weren't both left (or right) children");
    }

    node.setParent(grandparent.getParent());
    if (parent.getLeft().equals(node)) {
      parent.setLeft(node.getRight());
      grandparent.setLeft(parent.getRight());

      parent.setRight(grandparent);
      node.setRight(parent);
    } else {
      parent.setRight(node.getLeft());
      grandparent.setRight(parent.getLeft());

      parent.setLeft(grandparent);
      node.setLeft(parent);
    }
  }
  /**
   * This step is used when the accessed node is a left child and its parent ("p") is a right child
   * of vice versa
   *
   * If node is a left child, we make node the roo, p its right child, node's right child p's left
   * child,
   *
   * If node is the left child, we follow the steps above but substitute left and right
   *
   * @param node - the node we are splaying to the root.
   *
   * Invariants: node must have a grandparent, node and parent must both be left children or
   *   both be right children
   */
  private void zigzag(SplayNode<T> node) {
    if (node == null || node.getParent() == null || node.getParent().getParent() == null) {
      throw new IllegalArgumentException("node didn't have a grandparent");
    }

    SplayNode parent = node.getParent();
    SplayNode grandparent = parent.getParent();

    if ((node.equals(parent.getLeft()) && parent.equals(grandparent.getLeft()))
        || (node.equals(parent.getRight()) && parent.equals(grandparent.getRight()))) {
      throw new IllegalArgumentException("node and parent were both left (or right) children");
    }

    node.setParent(grandparent.getParent());
    if (parent.getRight().equals(node)) {
      parent.setRight(node.getLeft());
      grandparent.setLeft(node.getRight());

      node.setRight(grandparent);
      node.setLeft(parent);
    } else {
      parent.setLeft(node.getRight());
      grandparent.setRight(node.getLeft());

      node.setLeft(grandparent);
      node.setRight(parent);
    }
  }


  public static class SplayNode<T extends Comparable> extends BSTNode<T> {
    private T val;
    private SplayNode<T> left;
    private SplayNode<T> right;
    private SplayNode<T> parent;

    public SplayNode(SplayNode<T> left, SplayNode<T> right, T val, SplayNode parent) {
      super(left, right, val);
      this.parent = parent;
    }

    public SplayNode(T val) {
      super(val);
      parent = null;
    }

    public void setRight(SplayNode<T> right) {
      this.right = right;
      right.setParent(this);
    }

    public void setLeft(SplayNode<T> left) {
      this.left = left;
      left.setParent(this);
    }

    public void setParent(SplayNode<T> parent) {
      this.parent = parent;
    }

    public T getVal() {
      return val;
    }

    public SplayNode<T> getLeft() {
      return left;
    }

    public SplayNode<T> getRight() {
      return right;
    }

    public SplayNode<T> getParent() {
      return parent;
    }

    public boolean hasLeft() {
      return left != null;
    }

    public boolean hasRight() {
      return right != null;
    }

    public boolean hasParent() {
      return parent != null;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }

      SplayNode splayNode = (SplayNode) o;

      if (val != null ? !val.equals(splayNode.val) : splayNode.val != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (val != null ? val.hashCode() : 0);
      return result;
    }

    @Override
    public void clear() {
      super.clear();
      this.parent = null;
    }
  }
}
