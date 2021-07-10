package com.company.trees;

import java.util.Scanner;

enum NodeColor{
    black,red
}

class RedBlackTree{
    //It is a self balancing tree
    //every node is either black or red
    //root is always black
    //every leaf which is nil/sentinel is black
    //If the node is red then its children are black(No red-red parent child relation)
    //every path from node to any of its descendants nil has the same number of black nodes
    class Node{
        Node left,right,parent;
        NodeColor color ;
        int info;
    }
    Node root ;
    Node location;
    public RedBlackTree(){
        root=sentinel;
    }
    Node sentinel=makeNode(-1,null,NodeColor.black);
    public Node makeNode(int info,Node par,NodeColor color){
        Node newNode =new Node();
        newNode.color=color;
        newNode.info=info;
        newNode.left=newNode.right=sentinel;
        newNode.parent=par;
        return newNode;
    }

    public void rotateLeft(Node ptr){
        Node temp=ptr.right;
        ptr.right=temp.left;
        if(temp.left != sentinel){
            temp.left.parent=ptr;
        }
        temp.parent=ptr.parent;
        if(ptr.parent == sentinel ){
            root =temp;
        }
        else if(ptr == ptr.parent.left){
            ptr.parent.left=temp;
        }
        else {
            ptr.parent.right=temp;
        }
        temp.left=ptr;
        ptr.parent=temp;
    }

    public void rotateRight(Node ptr){
        Node temp=ptr.left;
        ptr.left=temp.right;
        if(temp.right != sentinel){
            temp.right.parent =ptr;
        }
        temp.parent=ptr.parent;
        if(ptr.parent  == sentinel){
            root=temp;
        }
        else if(ptr == ptr.parent.right){
            ptr.parent.right=temp;
        }
        else {
            ptr.parent.left=temp;
        }
        temp.right=ptr;
        ptr.parent=temp;

    }
    public void insertion(int info) {
        if(root == null){
            root=sentinel;
        }
        Node temp=root,parent=sentinel;
        while(temp != sentinel){
            parent=temp;
            if(info <temp.info){
                temp=temp.left;
            }
            else if(info > temp.info){
                temp=temp.right;
            }
            else {
                System.out.println("Duplicate key found!");
                return ;
            }
        }
        Node newNode =makeNode(info,parent,NodeColor.red);
        if(parent == sentinel){
            root=newNode;
        }
        else if(parent.info < info){
            parent.right=newNode;
        }else {
            parent.left=newNode;
        }
        insertBalance(newNode);
    }
    
    public void insertBalance( Node ptr){
        Node parent,uncle,grandParent;
        while(ptr.parent.color == NodeColor.red){
            parent=ptr.parent;
            grandParent=parent.parent;
            if(parent== grandParent.left){
                uncle=grandParent.right;
                if(uncle.color == NodeColor.red ){  /* case L_1 */
                    parent.color =NodeColor.black;
                    uncle.color=NodeColor.black;
                    grandParent.color=NodeColor.red;
                    ptr=grandParent;
                }else {
                    if(ptr == parent.right){        /* case L_2a */
                        rotateLeft(parent);
                        ptr =parent;
                        parent=ptr.parent;
                    }
                    parent.color=NodeColor.black;   /* case L_2b  */
                    grandParent.color=NodeColor.red;
                    rotateRight(grandParent);
                }
            }
            else {
                uncle=grandParent.left;
                if(uncle.color== NodeColor.red){    /* case R_1  */
                    parent.color=NodeColor.black;
                    uncle.color=NodeColor.black;
                    grandParent.color=NodeColor.red;
                    ptr=grandParent;
                }
                else {
                    if(ptr == parent.left){
                        rotateRight(parent);   /* case R_2a   */
                        ptr=parent;
                        parent=ptr.parent;
                    }
                    grandParent.color =NodeColor.red;    /* case R_2b   */
                    parent.color=NodeColor.black;
                    rotateLeft(grandParent);
                }
            }
        }
        root.color =NodeColor.black;
    }

    public boolean find(int key){
        Node temp=root;
        location =sentinel;
        if(root == null ){
            return false;
        }
        if(root.info == key){
            location=root;
            System.out.println(location.info +"     " +location.left.info +"    "+ location.right.info);
            return true;
        }
        if(key < temp.info){
            temp=temp.left;
        }
        else {
            temp=temp.right;
        }
        while(temp != sentinel){
            if(key == temp.info){
                location=temp;
                System.out.println(location.info +"     " +location.left.info +"    "+ location.right.info);
                return true;
            }
            else if(key < temp.info){
                temp=temp.left;
            }else {
                temp=temp.right;
            }
        }
        return false;
    }

    public Node successor(Node ptr){
        Node temp=ptr.right;
        while(temp.left != sentinel){
            temp=temp.left;
        }
        return temp;
    }

    public Node predecessor(Node ptr){
        Node temp=ptr.left;
        while(temp.right != sentinel){
            temp=temp.right;
        }
        return temp;
    }

    public void delete(int key ) {
        Node child, succ, pred;
        if(root == sentinel ){
            System.out.println("Tree is empty ");
            return;
        }
        if (!find(key)) {
            System.out.println("Item is not present");
            return;
        }
        Node ptr = location;
        while (ptr.left != sentinel || ptr.right != sentinel) {
            if (ptr.left != sentinel && ptr.right == sentinel) {
                pred = predecessor(ptr);
                ptr.info = pred.info;
                ptr = pred;
            } else {
                succ = successor(ptr);
                ptr.info = succ.info;
                ptr = succ;
            }
        }
        if(ptr == root){
            root=sentinel;
            return;
        }
        NodeColor c = ptr.color;
        Node sibling;
        boolean isLeft = false;
        Node par = ptr.parent;
        if (ptr == ptr.parent.left) {
            isLeft = true;
            sibling = ptr.parent.right;
            ptr.parent.left = sentinel;
            ptr = ptr.parent.left;
        } else {
            sibling = ptr.parent.left;
            ptr.parent.right = sentinel;
            ptr = ptr.parent.right;
        }
        ptr.parent = par;
        if (c == NodeColor.black) {
            deleteBalance(ptr, sibling, isLeft);
        }
    }

    public void deleteBalance(Node ptr,Node sibling,boolean isLeft){
        while(ptr != root){
            if(ptr == ptr.parent.left || isLeft){
                isLeft =false;
                if(sibling.color ==NodeColor.red){
                    //case L_1
                    sibling.color =NodeColor.black;
                    ptr.parent.color=NodeColor.red;
                    rotateLeft(ptr.parent);
                    sibling=ptr.parent.right;
                }
                if(sibling.left.color == NodeColor.black && sibling.right.color == NodeColor.black){
                    //case L_2a and case L_2b
                    sibling.color=NodeColor.red;
                    if(ptr.parent.color == NodeColor.red){
                        //case L_2a
                        ptr.parent.color=NodeColor.black;
                        return;
                    }
                    else {
                        //case L_2b
                        ptr=ptr.parent;
                    }
                }
                else {
                    //case L_3a , L_3b
                    if(sibling.right.color == NodeColor.black){
                        //case L_3a
                        sibling.left.color=NodeColor.black;
                        sibling.color=NodeColor.red;
                        rotateRight(sibling);
                        sibling=ptr.parent.right;
                    }
                    //case L_3b
                    sibling.color=ptr.parent.color;
                    ptr.parent.color=NodeColor.black;
                    sibling.right.color=NodeColor.black;
                    rotateLeft(ptr.parent);
                    return;
                }
            }
            else {
                //sibling = ptr.parent.left;
                if (sibling.color == NodeColor.red) {
                    //case R_1
                    ptr.parent.color = NodeColor.red;
                    sibling.color = NodeColor.black;
                    rotateRight(ptr.parent);
                    sibling = ptr.parent.left;
                }
                if (sibling.left.color == NodeColor.black && sibling.right.color == NodeColor.black) {
                    sibling.color = NodeColor.red;
                    if (ptr.parent.color == NodeColor.red) {
                        ptr.parent.color = NodeColor.black;
                        return;
                    } else {
                        ptr = ptr.parent;
                    }
                }
                else {
                    if(sibling.left.color ==NodeColor.black){
                        //case R_3a
                        sibling.color=NodeColor.red;
                        sibling.right.color=NodeColor.black;
                        rotateLeft(sibling);
                        System.out.println("sibling before = "+sibling.info+"   sibling after = "+ptr.parent.left.info);
                        sibling=ptr.parent.left;

                    }
                    sibling.color=ptr.parent.color;   //case R_3b
                    ptr.parent.color=NodeColor.black;
                    sibling.left.color=NodeColor.black;
                    rotateRight(ptr.parent);
                    return;
                }
            }
        }
    }

    public void inorderTraversal(Node root){
        if(root != sentinel){
            inorderTraversal(root.left);
            System.out.println(root.info+"    ");
            inorderTraversal(root.right);
        }
    }

    public void preorderTraversal(Node root){
        if(root != sentinel){
            System.out.println(root.info+"    ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    public void postorderTraversal(Node root){
        if(root != sentinel){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.println(root.info+"    ");
        }
    }
}

public class RedBlackTreeTest {
    public static void main(String[] args) {
        RedBlackTree redBlackTree=new RedBlackTree();
        int choice;
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1.Insert a value");
            System.out.println("2.Delete a value");
            System.out.println("3.Search a value");
            System.out.println("4.Inorder traversal");
            System.out.println("5.Preorder traversal");
            System.out.println("6.Postorder traversal");
            System.out.println("7.Display");
            System.out.println("8.Quit");
            System.out.println("Enter you choice");
            switch(sc.nextInt()){
                case 1 -> {
                    System.out.println("Enter the value to be inserted");
                    choice= sc.nextInt();
                    redBlackTree.insertion(choice);
                }
                case 2 ->{
                    System.out.println("Enter the value to e deleted");
                    choice= sc.nextInt();
                    redBlackTree.delete(choice);
                }
                case 3 ->{
                    System.out.println("Enter the value to be searched");
                    choice=sc.nextInt();
                    if(redBlackTree.find( choice)){
                        System.out.println("key is present");
                    }
                    else {
                        System.out.println("Key is not present in the tree ");
                    }

                }
                case 4 -> redBlackTree.inorderTraversal(redBlackTree.root);
                case 5 -> redBlackTree.preorderTraversal(redBlackTree.root);
                case 6 -> redBlackTree.postorderTraversal(redBlackTree.root);
            }
        }
    }
}
