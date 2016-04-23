package com.example.cameron.bsttest;

import java.util.Arrays;

/**
 * Created by Cameron on 3/19/2016.
 */
public class BinaryTree {

    Node root;
    static int[] nodeLocation = new int[14]; //holds the 15 possible node positions and keys for drawing binary search tree
    //int currentNode;
    int height;
    Boolean[] position = {null,null,null};

    public void addNode(int key) {
        //currentNode = 0;
        position[0]=null;
        position[1]=null;
        position[2]=null;

        // Create a new Node and initialize it

        Node newNode = new Node(key);

        // If there is no root this becomes root

        if (root == null) {




            root = newNode;
            nodeLocation[getNodeLocation(position)]=root.key; //setting position of first node




            //System.out.println("Height: " +height);

        } else {

            // Set root as the Node we will start
            // with as we traverse the tree

            Node focusNode = root;

            // Future parent for our new Node

            Node parent;

            while (true) {
                height++;
                //System.out.println("Height: "+height);
                // root is the top parent so we start
                // there

                parent = focusNode;

                // Check if the new node should go on
                // the left side of the parent node



                if (key < focusNode.key) {

                    // Switch focus to the left child

                    focusNode = focusNode.leftChild;
                    position[height-1]=true;


                    // If the left child has no children
                    if (focusNode != null){

                    }
                    if (focusNode == null) {

                        // then place the new node on the left of it

                        newNode.setPosition(position);
                        nodeLocation[getNodeLocation(position)]=newNode.key;
                        parent.leftChild = newNode;


                        //nodeLocation[currentNode]=root.key;

                        height=0;
                        return; // All Done

                    }

                } else { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;
                    position[height-1]=false;

                    // If the right child has no children

                    if (focusNode == null) {

                        // then place the new node on the right of it
                        newNode.setPosition(position);
                        nodeLocation[getNodeLocation(position)]=newNode.key;
                        parent.rightChild = newNode;


                        //nodeLocation[currentNode]=root.key;



                        height=0;
                        return; // All Done

                    }

                }

            }

        }

    }

    public void printHeight(int height){
        System.out.println(height);
    }
    // All nodes are visited in ascending order (in-order traversal)
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth

    public void inOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            // Traverse the left node

            inOrderTraverseTree(focusNode.leftChild);

            // Visit the currently focused on node

            System.out.println("checking node: " + focusNode);

            // Traverse the right node

            inOrderTraverseTree(focusNode.rightChild);

        }

    }

    public void preorderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            System.out.println(focusNode);

            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);

        }

    }

    public void postOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);

            System.out.println(focusNode);

        }

    }

    public Node findNode(int key) {

        // Start at the top of the tree

        Node focusNode = root;

        // While we haven't found the Node
        // keep looking

        while (focusNode.key != key) {

            // If we should search to the left

            if (key < focusNode.key) {

                // Shift the focus Node to the left child

                focusNode = focusNode.leftChild;

            } else {

                // Shift the focus Node to the right child

                focusNode = focusNode.rightChild;

            }

            // The node wasn't found

            if (focusNode == null)
                return null;

        }

        return focusNode;

    }

    public boolean remove(int key) {

        // Start at the top of the tree

        Node focusNode = root;
        Node parent = root;

        // When searching for a Node this will
        // tell us whether to search to the
        // right or left

        boolean isItALeftChild = true;

        // While we haven't found the Node
        // keep looking

        while (focusNode.key != key) {

            parent = focusNode;

            // If we should search to the left

            if (key < focusNode.key) {

                isItALeftChild = true;

                // Shift the focus Node to the left child

                focusNode = focusNode.leftChild;

            } else {

                // Greater than focus node so go to the right

                isItALeftChild = false;

                // Shift the focus Node to the right child

                focusNode = focusNode.rightChild;

            }

            // The node wasn't found

            if (focusNode == null)
                return false;

        }

        // If Node doesn't have children delete it

        if (focusNode.leftChild == null && focusNode.rightChild == null) {

            // If root delete it

            if (focusNode == root)
                root = null;

                // If it was marked as a left child
                // of the parent delete it in its parent

            else if (isItALeftChild)
                parent.leftChild = null;

                // Vice versa for the right child

            else
                parent.rightChild = null;

        }

        // If no right child

        else if (focusNode.rightChild == null) {

            if (focusNode == root)
                root = focusNode.leftChild;

                // If focus Node was on the left of parent
                // move the focus Nodes left child up to the
                // parent node

            else if (isItALeftChild)
                parent.leftChild = focusNode.leftChild;

                // Vice versa for the right child

            else
                parent.rightChild = focusNode.leftChild;

        }

        // If no left child

        else if (focusNode.leftChild == null) {

            if (focusNode == root)
                root = focusNode.rightChild;

                // If focus Node was on the left of parent
                // move the focus Nodes right child up to the
                // parent node

            else if (isItALeftChild)
                parent.leftChild = focusNode.rightChild;

                // Vice versa for the left child

            else
                parent.rightChild = focusNode.rightChild;

        }

        // Two children so I need to find the deleted nodes
        // replacement

        else {

            Node replacement = getReplacementNode(focusNode);

            // If the focusNode is root replace root
            // with the replacement

            if (focusNode == root)
                root = replacement;

                // If the deleted node was a left child
                // make the replacement the left child

            else if (isItALeftChild)
                parent.leftChild = replacement;

                // Vice versa if it was a right child

            else
                parent.rightChild = replacement;

            replacement.leftChild = focusNode.leftChild;

        }

        return true;

    }

    public int getNodeLocation(Boolean position[]){
        int intPosition = -1;
        switch (Arrays.toString(position)) {
            case "[null, null, null]" :  intPosition = 0;
                break;
            case "[true, null, null]" :  intPosition = 1;
                break;
            case "[false, null, null]" :  intPosition = 2;
                break;
            case "[true, true, null]" :  intPosition = 3;
                break;
            case "[true, false, null]" :  intPosition = 4;
                break;
            case "[false, true, null]" :  intPosition = 5;
                break;
            case "[false, false, null]" :  intPosition = 6;
                break;
            case "[true, true, true]" :  intPosition = 7;
                break;
            case "[true, true, false]" :  intPosition = 8;
                break;
            case "[true, false, true]" :  intPosition = 9;
                break;
            case "[true, false, false]" :  intPosition = 10;
                break;
            case "[false, true, true]" :  intPosition = 11;
                break;
            case "[false, true, false]" :  intPosition = 12;
                break;
            case "[false, false, true]" :  intPosition = 13;
                break;
            case "[false, false, false]" :  intPosition = 14;;
                break;
            default:
                System.out.println("Error identifying position matrix");
        }
        System.out.println("Node added to location: "+intPosition);
        return intPosition;
    }

    public Node getReplacementNode(Node replacedNode) {

        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.rightChild;

        // While there are no more left children

        while (focusNode != null) {

            replacementParent = replacement;

            replacement = focusNode;

            focusNode = focusNode.leftChild;

        }

        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild

        if (replacement != replacedNode.rightChild) {

            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;

        }

        return replacement;

    }

    public static void main(String[] args) {

        BinaryTree theTree = new BinaryTree();

        theTree.addNode(50);

        theTree.addNode(25);

        theTree.addNode(15);

        theTree.addNode(30);

        theTree.addNode(75);

        theTree.addNode(85);

        System.out.println("Contents of binary tree array: "+Arrays.toString(nodeLocation));

        // Different ways to traverse binary trees

        // theTree.inOrderTraverseTree(theTree.root);

        // theTree.preorderTraverseTree(theTree.root);

        // theTree.postOrderTraverseTree(theTree.root);

        // Find the node with key 75

        //System.out.println("\nNode with the key 75");

        //System.out.println(theTree.findNode(75));

        //System.out.println("Remove Key 25");
        //maybe remove is wrong?
        //theTree.remove(25);

        //System.out.println(theTree.findNode(25));

        System.out.println("Starting in-order traversal");
        theTree.inOrderTraverseTree(theTree.root);

    }
}

class Node {

    int key;
    Boolean[] position= {null,null,null}; //this should be updated to an integer at some point, or just removed

    Node leftChild;
    Node rightChild;

    Node(int key) {

        this.key = key;

    }

    public String toString() {

        return key + " and has boolean " +position[0]+" "+position[1]+" "+position[2];

	        /*
	         * return name + " has the key " + key + "\nLeft Child: " + leftChild +
	         * "\nRight Child: " + rightChild + "\n";
	         */

    }

    public void setPosition(Boolean addPosition[]){
        System.arraycopy(addPosition, 0, position, 0, addPosition.length );

    }

}