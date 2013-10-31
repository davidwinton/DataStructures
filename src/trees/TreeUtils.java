package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import trees.BinarySearchTree.BSTNode;

/** @author David Winton (david.a.winton@gmail.com) */
public class TreeUtils {
  public static List<BSTNode> preorderTraversal(BSTNode root) {
    ArrayList<BSTNode> ret = new ArrayList<BSTNode>();
    if(root == null) {
      return ret;
    }

    ret.add(root);
    ret.addAll(preorderTraversal(root.getLeft()));
    ret.addAll(preorderTraversal(root.getRight()));
    return ret;
  }

  public static List<BSTNode> inOrderTraversal(BSTNode root) {
    ArrayList<BSTNode> ret = new ArrayList<BSTNode>();
    if(root == null) {
      return ret;
    }

    ret.addAll(inOrderTraversal(root.getLeft()));
    ret.add(root);
    ret.addAll(inOrderTraversal(root.getRight()));
    return ret;
  }

  public static List<BSTNode> postOrderTraversal(BSTNode root) {
    ArrayList<BSTNode> ret = new ArrayList<BSTNode>();
    if(root == null) {
      return ret;
    }

    ret.addAll(postOrderTraversal(root.getLeft()));
    ret.add(root);
    ret.addAll(postOrderTraversal(root.getRight()));
    return ret;
  }

  public static BSTNode getMax(BSTNode root) {
    if(root == null) {
      return null;
    }

    BSTNode ret = root;
    while (ret.hasRight()) {
      ret = ret.getRight();
    }
    return ret;
  }

  /**
   * We use the queue to keep track of our nodes. When a node is polled,
   * we enqueue both of its children. We use nulls when part of the tree is missing.
   * To prevent an infinite loop, we store each row in a list while it is being processed, and if it
   * has any non-null nodes, the entire row is added to our result.
   *
   * @param root
   * @return list of nodes with nulls to indicate missing nodes
   */
  public static List<BSTNode> levelOrderTraversal(BSTNode root) {
    List<BSTNode> ret = new ArrayList<BSTNode>();
    if(root == null) {
      return ret;
    }

    int nodesSeen = 0;
    int nodesInRow = 1;
    boolean hasNonNulls = false;
    List<BSTNode> currentRow = new ArrayList<BSTNode>();
    Queue<BSTNode> queue = new LinkedList<BSTNode>();
    queue.offer(root);

    BSTNode nullNode = new BSTNode(null);

    BSTNode current;
    while(!queue.isEmpty()) {
      current = queue.poll();
      currentRow.add(current);
      nodesSeen++;

      if(current.getVal() != null) {
        hasNonNulls = true;
        queue.offer(current.hasLeft() ? current.getLeft() : nullNode);
        queue.offer(current.hasRight() ? current.getRight() : nullNode);
      } else {
        queue.offer(nullNode);
        queue.offer(nullNode);
      }
      if(nodesSeen == nodesInRow) {
        if(hasNonNulls) {
          ret.addAll(currentRow);
          currentRow.clear();
          nodesInRow *= 2;
          nodesSeen = 0;
          hasNonNulls = false;
        } else {
          break;
        }
      }

    }
    return ret;
  }

  public static int getSize(BSTNode root) {
    if(root == null) {
      return 0;
    }
    return 1 + getSize(root.getLeft()) + getSize(root.getRight());
  }

  public static int getDepth(BSTNode root) {
    if(root == null) {
      return 0;
    }
    return 1 + Math.max(getDepth(root.getRight()), getDepth(root.getLeft()));
  }

  /**
   * Simple function to get a string that looks sort of like the row.
   *
   * At most number of nodes + 1 /2 nodes can be in the widest row, we give each node 4 characters
   * to be printed in, put one space between rows. At each level,
   * @param root
   * @return
   */
  public static String drawTree(BSTNode root) {
    if(root == null) {
      return "";
    }
    String nullString = "null";
    List<BSTNode> levelOrder = levelOrderTraversal(root);
    int size = levelOrder.size();
    int nameSize = 4; //arbirtrary
    int maxWidthRow = (size + 1) / 2 * (nameSize + 1);
    int lineNumber = 1;
    int count = 0;
    int nodesInRow = 1;
    int spaces = maxWidthRow / 2;

    StringBuilder lineBuilder = new StringBuilder();
    for(BSTNode node : levelOrder) {
      lineBuilder.append( new String(new char[spaces -1]).replace("\0", " "));
      if(node.getVal() == null) {
        lineBuilder.append(nullString).append(" ");
      } else {
        lineBuilder.append((node.getVal().toString() + "    ").substring(0,4)).append(" ");
      }
      lineBuilder.append( new String(new char[spaces-1]).replace("\0", " "));
      count ++;

      if(count == nodesInRow) {
        nodesInRow = nodesInRow * 2 + 1;
        lineNumber++;
        lineBuilder.append("\n");
        spaces = spaces / 2;
      }

    }
    Math.ceil(2.5);
    return lineBuilder.toString();
  }


}


