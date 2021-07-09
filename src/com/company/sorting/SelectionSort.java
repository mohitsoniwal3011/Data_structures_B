package com.company.sorting;

import java.util.Scanner;


public class SelectionSort {
    public static int max=100;
    public static void main(String[] args) {
        int [] arr;
        int n,min=0,temp=0,i,j;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of elements :");
        n=sc.nextInt();
        if(n>max){
            System.out.println("Elements can not be more then "+max);
            return;
        }
        else {
            arr=new int[n];
        }
        for(i=0;i<arr.length;i++){
            arr[i]=sc.nextInt();
        }
        for(i=0;i<arr.length-1;i++){
            min=i;
            for(j=i+1;j<arr.length;j++){
                if(arr[j] < arr[min]){ min=j; }
            }
            if(min != i){
                temp=arr[i];
                arr[i]=arr[min];
                arr[min]=temp;
            }
        }
        for(i=0;i< arr.length;i++){
            System.out.print(arr[i]+"   ");
        }

    }
}
