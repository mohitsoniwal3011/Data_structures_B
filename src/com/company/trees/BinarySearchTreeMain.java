package com.company.trees;

class BinarySearchTree{
    static class Node{
        Node left ;
        Node right;
        int data;
    }
    public Node makeNode(int data){
        Node newNode =new Node();
        newNode.data =data;
        newNode.left=newNode.right=null;
        return newNode;
    }
    public Node insertion(Node root, int data) {
        if (root == null) {
            root = makeNode(data);
        }
        else {
            if(data < root.data){
                root.left=insertion(root.left,data);
            }
            else if(data > root.data){
                root.right=insertion(root.right,data);
            }
            else {
                System.out.println("Duplicate key found");
            }
        }
        return root;
    }
    public Node Delete(Node root,int data){
        Node p=root;
        Node parent=null;
        //boolean r_flag=false,l_flag=false ; //will tell us whether p->data is greater then or smaller then right or left of the parent
        Node temp=null;
        int reserved=0;
        while(p!= null){
            if(p.data == data){
                break;
            }
            parent=p;
            if(data <p.data){
                p=p.left;
            }else {
                p=p.right;
            }
        }
        if(p == null){
            System.out.println("Key not found");
        }
        else if(p.left != null && p.right != null){
            temp=p.right;
            while(temp.left != null){
                temp=temp.left;
            }
            System.out.println("replacing "+p.data +" with "+temp.data);
            reserved=temp.data;
            p=Delete(p,temp.data);
            p.data=reserved;
        }
        else if(p.right != null  || p.left != null ){
            if(p.left != null){
                temp=p.left;
            }
            else {
                temp=p.right;
            }
            if(p.data > parent.data ){
                parent.right=temp;
            }
            else{
                parent.left=temp;
            }
            p=null;
        }
        else {
            //System.out.println("parent.data = "+parent.data +"p.data = "+p.data);
            if(p == root){
                root=null;
            }
            else if( parent.data <p.data){
                parent.right=null;
            }
            else {
                parent.left=null;
            }
        }
        return root;
    }
    public void inorderTraversal(Node root){
        //System.out.println("inorder Traversal ");
        if(root == null){
            return ;
        }
        inorderTraversal(root.left);
        System.out.print(root.data+"    ");
        inorderTraversal(root.right);
        //System.out.println();
    }
    public void preorderTraversal(Node root){
        //System.out.println("Preorder Traversal ");
        if(root == null){
            return ;
        }
        System.out.print(root.data+"    ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }
}
public class BinarySearchTreeMain {
    public static void main(String[] args) {
        BinarySearchTree bst =new BinarySearchTree();
        BinarySearchTree.Node  root=null;
        root=bst.insertion(root,100);
        root=bst.insertion(root,50);
        root=bst.insertion(root,150);
        root=bst.insertion(root,20);
        root=bst.insertion(root,200);
        root=bst.insertion(root,10);
        root=bst.insertion(root,40);
        root=bst.insertion(root,180);
        root=bst.insertion(root,220);
        root=bst.insertion(root,210);
        root=bst.insertion(root,230);
        root=bst.insertion(root,160);
        root=bst.insertion(root,190);
        System.out.println("before deletion");
        bst.inorderTraversal(root);
        //root= bst.Delete(root,200);
        //root= bst.Delete(root,150);
        //root= bst.Delete(root,210);
        System.out.println('\n'+"after deletion");
        bst.inorderTraversal(root);
    }
}
