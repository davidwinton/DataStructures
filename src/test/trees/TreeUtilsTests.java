package test.trees;

import org.junit.Test;
import trees.BinarySearchTree;
import trees.TreeUtils;

public class TreeUtilsTests {
  TreeUtils treeUtils = new TreeUtils();

  private BinarySearchTree<Integer> getTestBST(int nodes) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    for (int i = 0; i < nodes; i++) {
      bst.insert((int) (Math.random() * 100 - 50));
    }
    return bst;
  }

  @Test()
  public void printTreeTest() {
    System.out.println(treeUtils.drawTree(getTestBST(8).getRoot()));
  }

  public static void main(String[] args) {
    TreeUtilsTests tut = new TreeUtilsTests();
    System.out.println(TreeUtils.drawTree(tut.getTestBST(8).getRoot()));
  }
}