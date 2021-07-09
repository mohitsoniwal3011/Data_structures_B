package com.company.sorting;

import java.util.Scanner;

class MergeSort{
    int max =100;
    public void merge_sort(int[] a,int start,int end){
        int [] temp=new int[100];
        if(start < end){
            int middle=(start+end)/2;
            merge_sort(a,start,middle);
            merge_sort(a,middle+1,end);
            merge(temp,a,start,middle,middle+1,end);
            copy(a,temp,start,end);
        }
    }
    public void copy(int[] a,int [] temp,int start,int end){
        for(int i=start;i<=end;i++){
            a[i]=temp[i];
        }

    }
    public void merge(int [] temp,int[] a,int start1 ,int end1 ,int start2, int end2 ){
        int i=start1,j=start2,k=start1;
        while(i<=end1 && j<= end2){
            if(a[i] <= a[j]){ temp[k++]=a[i++]; }
            else { temp[k++]=a[j++]; }
        }
        while(i<=end1){
            temp[k++]=a[i++];
        }
        while( j<= end2){
            temp[k++]=a[j++];
        }
    }
}

public class MergeSortTest {
    public static void main(String[] args) {
        int [] a;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of elements : ");
        int n= sc.nextInt();
        a=new int[n];
        for(int i=0;i<a.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        MergeSort mergeSort=new MergeSort();
        mergeSort.merge_sort(a,0,a.length-1);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+"  ");
        }

    }
}
