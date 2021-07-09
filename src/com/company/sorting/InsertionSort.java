package com.company.sorting;

import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n,i,j,temp;
        System.out.print("Enter number of elements : ");
        n= sc.nextInt();
        int [] arr=new int[n];
        for(i=0;i<n;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            arr[i]=sc.nextInt();
        }
        for(i=1;i<arr.length;i++){
            temp=arr[i];
            for(j=i-1;j>=0 && temp < arr[j];j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=temp;
        }
        System.out.println("Sorted array is : ");
        for(i=0;i<n;i++){
            System.out.print(arr[i]+"      ");
        }

    }
}
