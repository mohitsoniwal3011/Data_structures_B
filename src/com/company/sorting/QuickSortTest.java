package com.company.sorting;

import java.util.Scanner;

class Quicksort{
    public void quickSort(int[] a,int start,int end){
        if(start < end){
            int k=partition(a,start,end);
            quickSort(a,start,k-1);
            quickSort(a,k+1,end);
        }
    }
    public int partition(int [] a,int start, int end){
        //taking first element as pivot
        int pivot=a[start];
        int i=start,j=end;
        int temp=0;
        while(i < j){
            while(i <=end &&  a[i] <= pivot){
                i++;
            }
            while(a[j] > pivot){
                j--;
            }
            if(i < j){
                swap(a,i,j);
            }
        }
        swap(a,start,j);
        return j;
    }
    public void swap(int [] a, int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}

public class QuickSortTest {
    public static void main(String[] args) {
        Quicksort quicksort=new Quicksort();
        int[] a;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of elements : ");
        int n= sc.nextInt();
        a=new int[n];
        for(int i=0;i<a.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        quicksort.quickSort(a,0,a.length-1);
        System.out.println("Sorted list is:");
        for (int j : a) {
            System.out.print(j + "       ");
        }
    }
}
