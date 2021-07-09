package com.company.sorting;

import java.util.Scanner;

class MergeSortLinkedList{
    static class Node {
        int value;
        Node next;
        Node (int value){
            this.value=value;
            this.next=null;
        }
    }
    public Node mergeSort(Node head){
        Node firstHead,secondHead,mergedHead;
        if(head!= null && head.next != null){
            firstHead=head;
            secondHead=divide(head);
            firstHead=mergeSort(firstHead);
            secondHead=mergeSort(secondHead);
            mergedHead=merge(firstHead,secondHead);
            return mergedHead;
        }else {
            return head;
        }
    }
    public Node merge(Node p1,Node p2){
        Node mergedHead;
        Node temp;
        if(p1.value <= p2.value){
            mergedHead=p1;
            p1=p1.next;
        }else {
            mergedHead=p2;
            p2=p2.next;
        }
        temp=mergedHead;
        while(p1 != null && p2!= null){
            if(p1.value <= p2.value){
                temp.next=p1;
                p1= p1.next;
            }else {
                temp.next=p2;
                p2=p2.next;
            }
            temp=temp.next;
        }
        while(p1 != null){
            temp.next=p1;
            p1=p1.next;
            temp=temp.next;
        }
        while(p2 != null){
            temp.next=p2;
            p2=p2.next;
            temp=temp.next;
        }
        temp.next=null;
        return mergedHead;
    }
    public Node createList(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int i=1;
        int n=sc.nextInt();
        if(n== 0){
            return null;
        }
        System.out.print("Enter value of Node "+(i)+" = ");
        int value=sc.nextInt();
        Node head =new Node(value);
        Node p=head;
        for(i=2;i<= n;i++){
            System.out.print("Enter value of Node "+(i)+" = ");
            value=sc.nextInt();
            p.next=new Node(value);
            p=p.next;
        }
        return head;
    }
    public Node divide(Node head){
        Node p=head,q=head,second_head,temp=null;
        while(q!= null){
            temp=p;
            p=p.next;
            q=q.next;
            if(q != null){
                q=q.next;
            }
        }
        second_head =p;
        temp.next=null;
        return second_head;

    }
    public void display(Node head){
        Node p=head;
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        while(p!= null){
            System.out.print(p.value+"      ");
            p=p.next;
        }
        System.out.println("");
    }


}

public class MergeSortLinkedListTest {
    public static void main(String[] args) {
        MergeSortLinkedList mergeSortLinkedList=new MergeSortLinkedList();
        MergeSortLinkedList.Node head=mergeSortLinkedList.createList();
        System.out.println("Unsorted list is : ");
        mergeSortLinkedList.display(head);
        head= mergeSortLinkedList.mergeSort(head);
        System.out.println("Sorted list is : ");
        mergeSortLinkedList.display(head);

    }
}
