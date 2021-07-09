package com.company.trees;

import java.util.Scanner;

class Heap{
    int [] arr;  //array to represent heap
    int hSize; //number of nodes in the heap
    int maxValue;  //all the values in the heap must be less then this value
    Scanner sc;
    public Heap() {
        sc=new Scanner(System.in);
        hSize=0;
        maxValue=10000;
        arr=new int[100];
        arr[0]=maxValue;
    }
    public void display(){
        if(hSize == 0){
            System.out.println("Tree is empty");
            return;
        }
        System.out.println("Number of elements = "+hSize);
        for(int i=1;i<=hSize;i++){
            System.out.print(arr[i]+"   ");
        }
        System.out.println("");
    }
    public  void insert(){
        System.out.println("Enter the element to be inserted: ");
        int num =sc.nextInt();
        hSize++;
        arr[hSize]=num;
        this.restoreUp(hSize);
    }
    public void restoreUp(int i){
        int parent=i/2;
        int k=arr[i];
        while(arr[parent] < k){
            arr[i]=arr[parent];
            i=parent;
            parent=i/2;
        }
        arr[i]=k;
    }
    public void buildHeap(){
        System.out.print("Enter number of elements: ");
        hSize=sc.nextInt();
        int i=1;
        System.out.print("Enter element 1: ");
        arr[1]=sc.nextInt();
        for(i=2;i<=hSize;i++){
            System.out.print("Enter element "+i+" = ");
            arr[i]= sc.nextInt();
        }
        for(i=2;i<=hSize;i++){
            this.restoreUp(i);
        }

    }
    public int delete(){
        int result=arr[1];
        arr[1]=arr[hSize];
        hSize--;
        restoreDown(1);
        return result;
    }
    public void restoreDown(int i){
        int left=2*i;
        int right=2*i+1;
        int num =arr[i];
       while(right <= hSize){
           if(num >= arr[left] && num >= arr[right]){
               arr[i]=num;
               return;
           }
           else if(arr[left] >arr[right]){
               arr[i]=arr[left];
               i=left;
           }
           else {
               arr[i]=arr[right];
               i=right;
           }
           left=2*i;
           right=2*i+1;
       }
       if(left == hSize && num <=arr[left]  ) {
           arr[i]=arr[left];
           i=left;
       }
       arr[i]=num;
    }
}


public class HeapTest {
    public static void main(String[] args) {
        //number the nodes level by level from 1 to 'n' and store it in the array of size (n+1)
        //if the node has number k then it will be stored in arr[k].
        //Its parent will be present on index floor(k/2)
        //Its left child will be at index '2k'
        //Its right child will be at index '2k+1'
        Heap heap=new Heap();
        Scanner sc=new Scanner(System.in);
        int choice;
        int result;
        while(true) {
            System.out.println("1.Insert");
            System.out.println("2.Delete root");
            System.out.println("3.Display");
            System.out.println("4.Build heap");
            System.out.println("5.Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1->heap.insert();
                case 2-> {
                    result = heap.delete();
                    System.out.println("Deleted item = "+result);
                }
                case 3->heap.display();
                case 4->heap.buildHeap();
                case 5 -> { return ; }
                default-> System.out.println("Wrong choice,Enter again");
            }
        }
    }
}
