package com.company.sorting;

import java.util.Scanner;

class CountingSort{
    public int findKey(int [] arr){
        int key =-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i] > key){
                key=arr[i];
            }
        }
        return key;
    }
    public int findMin(int [] arr){
        int min=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] < min){
                min=arr[i];
            }
        }
        return min;
    }
    public void count_sort(int [] arr){
        boolean changed =false;
        int min =findMin(arr);
        if(min != 0){

            for(int i=0;i<arr.length;i++){
                arr[i]=arr[i]+Math.abs(min);
            }
        }
        int key =findKey(arr);
        System.out.println("min = "+min+" key = "+key);
        int [] count=new int[key+1+Math.abs(min)]; //to store the frequency of each element
        int [] temp =new int[arr.length];
        for(int i=0;i<arr.length;i++){
            count[arr[i]]++;
        }
        for(int i=1;i<count.length;i++){
            count[i]=count[i]+count[i-1];
        }
        for(int i=arr.length-1;i>=0;i--){
           temp[--count[arr[i]]]=arr[i];
        }
        for(int i =0;i<temp.length;i++){
            arr[i]=temp[i]+min;
        }
    }

}

public class CountingSortTest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.print("Enter number of elements : ");
        n= sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            arr[i]=sc.nextInt();
        }
        CountingSort countingSort=new CountingSort();
        countingSort.count_sort(arr);
        System.out.println("Sorted array is : ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"      ");
        }
    }

}
