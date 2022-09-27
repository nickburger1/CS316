

import java.util.*;
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}
public class BinaryTreeTraversal {
    Node root;

    private Node addNode(Node current, int value) { //method to add node to binary tree
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) { //if the new node is less than current node, add as left child
            current.left = addNode(current.left, value);
        } else if (value > current.value) { //if the new node is greater than the current, add as right child
            current.right = addNode(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    public void add(int value) {
        root = addNode(root, value); //assign the root
    }

    public static void LVR(Node node){ //method to find LVR order
        if (node != null) {
            LVR(node.left); //left
            System.out.print(" " + node.value); //value
            LVR(node.right); //right
        }
    }

    public static void RVL(Node node){//method to find RVL order
        if (node != null) {
            RVL(node.right); //right
            System.out.print(" " + node.value); //value
            RVL(node.left); //left
        }
    }

    public static void VLR(Node node) { //method to find VLR order
        if (node != null) {
            System.out.print(" " + node.value); //value
            VLR(node.left); //left
            VLR(node.right); //right
        }
    }

    public static void VRL(Node node) { //method to find VRL order
        if (node != null) {
            System.out.print(" " + node.value); //value
            VRL(node.right); //right
            VRL(node.left); //left
        }
    }

    public static void LRV(Node node) { //method to find LRV order
        if (node != null) {
            LRV(node.left);  //left
            LRV(node.right); //right
            System.out.print(" " + node.value); //value
        }
    }

    public static void RLV(Node node) { //method to find RLV order
        if (node != null) {
            RLV(node.right); //right
            RLV(node.left); //left
            System.out.print(" " + node.value); //value
        }
    }

    private static BinaryTreeTraversal createBinaryTree() { //method to create the binary tree
        BinaryTreeTraversal bt = new BinaryTreeTraversal(); //create new tree
        Scanner s = new Scanner(System.in); //open scanner to get user input
        System.out.print("Enter Nodes Seperated by Spaces: ");
        String nodes[] = s.nextLine().split("\\s+"); //split user input by the spaces and enter them into an array
        for(int i = 0; i< nodes.length; i++){
            bt.add(Integer.parseInt(nodes[i])); //add all nodes into tree
        }

        return bt; //return tree
    }


    public static void main(String[] args){
        BinaryTreeTraversal binaryTree = createBinaryTree(); //call function and put tree into function

        //call all functions to output desired orders
        System.out.print("LVR->" );
        LVR(binaryTree.root);
        System.out.println(" ");

        System.out.print("RVL->" );
        RVL(binaryTree.root);
        System.out.println(" ");

        System.out.print("VLR->" );
        VLR(binaryTree.root);
        System.out.println(" ");

        System.out.print("VRL->" );
        VRL(binaryTree.root);
        System.out.println(" ");

        System.out.print("LRV->" );
        LRV(binaryTree.root);
        System.out.println(" ");

        System.out.print("RLV->" );
        RLV(binaryTree.root);
        System.out.println(" ");

    }
}
