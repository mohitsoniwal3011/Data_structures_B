package com.company.sorting;

import java.util.Scanner;

class HeapSort{
    int hSize=0;
    public void heap_sort(int [] arr,int Size){
        buildHeap(arr,Size);
        int length=hSize;
        int[] sortedArr=new int[length];
        for(int i=0;i<length;i++){
            deleteRoot(arr);
        }
    }
    public void restoreUp(int [] arr,int i){
        int parent=i/2;
        int k=arr[i];
        while(arr[parent] <= k){
            arr[i]=arr[parent];
            i=parent;
            parent=i/2;
        }
        arr[i]=k;
    }
    public void buildHeap(int [] arr,int size ){
        hSize=size;
        for(int i=1;i<=hSize;i++){
            restoreUp(arr,i);
        }
    }
    public void display(int[] arr,int size){
        for(int i=1;i<=size;i++){
            System.out.print(arr[i]+"   ");
        }
        System.out.println("");
    }
    public void restoreDown(int [] arr,int i){
        int num =arr[i];
        int left =2*i;
        int right=2*i+1;
        while(right <= hSize){
            if(num >= arr[left] && num>= arr[right]){
                arr[i]=num;
                return;
            }
            else if(arr[left] >= arr[right]){
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
        if(left == hSize && num<= arr[left]){
            arr[i]=arr[left];
            i=left;
        }
        arr[i]=num;
    }
    public void  deleteRoot(int [] arr){
        if(hSize == 0){
            System.out.println("Tree is empty");
            return;
        }
        int result=arr[1];
        arr[1]=arr[hSize];
        hSize--;
        arr[hSize+1]=result;
        restoreDown(arr,1);
    }
}

public class HeapSortTest {
    public static int max=100;
    public static int maxValue=10000;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int size;
        int [] arr=new int[max];
        arr[0]=maxValue;
        System.out.print("Enter number of element : ");
        size=sc.nextInt();
        for(int i=1;i<=size;i++){
            System.out.print("Enter element "+i+" = " );
            arr[i]=sc.nextInt();
        }
        HeapSort heapSort =new HeapSort();
        System.out.println("Array before sort is : ");
        heapSort.display(arr,size);
        heapSort.heap_sort(arr,size);
        System.out.println("Sorted array is:");
        heapSort.display(arr,size);
    }
}
