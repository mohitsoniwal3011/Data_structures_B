package com.company.sorting;

import java.util.Scanner;
import java.util.Stack;

class BinaryTreeSort {
    Stack<Integer>  st=new Stack<>();
    static class Node {
        Node left;
        Node right;
        int data;
    }

    public Node makeNode(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.left = newNode.right = null;
        return newNode;
    }

    public Node insertion(Node root, int data) {
        if (root == null) {
            root = makeNode(data);
        } else {
            if (data <= root.data) {
                root.left = insertion(root.left, data);
            } else  {
                root.right = insertion(root.right, data);
            }
        }
        return root;
    }

    public Node Delete(Node root, int data) {
        Node p = root;
        Node parent = null;
        //boolean r_flag=false,l_flag=false ; //will tell us whether p->data is greater then or smaller then right or left of the parent
        Node temp = null;
        int reserved = 0;
        while (p != null) {
            if (p.data == data) {
                break;
            }
            parent = p;
            if (data < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println("Key not found");
        } else if (p.left != null && p.right != null) {
            temp = p.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            System.out.println("replacing " + p.data + " with " + temp.data);
            reserved = temp.data;
            p = Delete(p, temp.data);
            p.data = reserved;
        } else if (p.right != null || p.left != null) {
            if (p.left != null) {
                temp = p.left;
            } else {
                temp = p.right;
            }
            if (p.data > parent.data) {
                parent.right = temp;
            } else {
                parent.left = temp;
            }
            p = null;
        } else {
            //System.out.println("parent.data = "+parent.data +"p.data = "+p.data);
            if (p == root) {
                root = null;
            } else if (parent.data < p.data) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        }
        return root;
    }

    public void inorderTraversal(Node root) {
        //System.out.println("inorder Traversal ");

        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        st.push(root.data);
        inorderTraversal(root.right);
    }
    public void fillUp(int[] arr){
        for(int i= arr.length-1;i>=0;i--){
            arr[i]=st.pop();
        }
    }
    public Node destroy(Node root){
        if(root != null){
            destroy(root.left);
            destroy(root.right);
            root=null;
        }
        return null;
    }
}

public class BinaryTreeSortTest {
    public static void main(String[] args) {
        BinaryTreeSort bst =new BinaryTreeSort();
        BinaryTreeSort.Node root=null;
        Scanner sc =new Scanner(System.in);
        int n;
        System.out.print("Enter number of elements : ");
        n= sc.nextInt();
        int[] arr =new int[n];
        for(int i=0;i< arr.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            arr[i]= sc.nextInt();
            root= bst.insertion(root,arr[i]);
        }
        bst.inorderTraversal(root);
        bst.fillUp(arr);
        root=bst.destroy(root);
        System.out.println("Sorted list is:");
        for(int i=0;i< arr.length;i++){
            System.out.print(arr[i]+"    ");
        }
    }
}

