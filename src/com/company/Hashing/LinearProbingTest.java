package com.company.Hashing;

import java.util.Scanner;

enum type_of_record{
    Empty,Occupied,Deleted
}

class EmpNode{
    String name;
    int age;
    int id;
}

class Record{
    EmpNode info;
    type_of_record status;
    Record (){
        status =type_of_record.Empty;
    }
}

class LinearProbing {
    int maxSize;
    Record [] table;
    LinearProbing(int capacity){
        table =new Record[capacity];
        for(int i=0;i<table.length;i++){
            table[i]=new Record();
        }
        maxSize=capacity;
    }

    public void insert(EmpNode empNode){
        int key =empNode.id;
        int h =hash(key);
        int location=h;
        for(int i=1;i<=table.length;i++ ){
            if(table[location].status == type_of_record.Empty || table[location].status == type_of_record.Deleted){
                table[location].info=empNode;
                table[location].status=type_of_record.Occupied;
                return;
            }
            else if(table[location].info.id == key ){
                System.out.println("Duplicate key found ");
                return;
            }
            location=(h+i)%maxSize;
        }
        System.out.println("Record can't be inserted table overflow");
    }

    public int search(int key){
        int h =hash(key);
        int location =h;
        for(int i=1;i<= table.length;i++){
            if(table[location].status ==type_of_record.Empty){
                return -1;
            }
            if(table[location].info.id == key ){
                return location;
            }
            location=(h+i)%maxSize;
        }
        return -1;
    }

    public void delete(int key){
        int index=search(key);
        if(index == -1){
            System.out.println("Key not found");
        }
        else {
            table[index].status=type_of_record.Deleted;
        }
    }


    public void display(){
        for(int i=0;i<table.length;i++){
            if(table[i].status==type_of_record.Occupied){
                System.out.println("occupiued : "+table[i].info.name+"  "+table[i].info.age+"   "+table[i].info.id);
            }
            else if(table[i].status == type_of_record.Deleted){
                System.out.println("Deleted");
            }
            else {
                System.out.println("Empty");
            }
        }
    }
    public int hash(int key){
        return key%maxSize;
    }
}


public class LinearProbingTest {
    public static  int max =50;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter size of table : ");
        int capacity= sc.nextInt();
        LinearProbing linearProbing=new LinearProbing(capacity);
        EmpNode empNode;
        int choice;
        int key;
        while(true){
            System.out.println("1. Insert a record");
            System.out.println("2. Deleted a record");
            System.out.println("3. Search  a record");
            System.out.println("4. Display table ");
            System.out.println("5. Exit ");
            System.out.println("Enter choice:");
            choice=sc.nextInt();
            sc.nextLine();  // to capture the extra '\n' entered after scanning 'choice'
            int result;
            switch(choice){
                case 1 -> {
                    empNode =new EmpNode();
                    System.out.println("Enter name , age and id of the employee");
                    empNode.name=sc.nextLine();
                    empNode.age=sc.nextInt();
                    empNode.id= sc.nextInt();
                    linearProbing.insert(empNode);
                }
                case 2 ->{
                    System.out.println("Enter key to be deleted:");
                    linearProbing.delete(sc.nextInt());

                }
                case 3 ->{
                    System.out.println("Enter key to be searched");
                    result=linearProbing.search(sc.nextInt());
                    if(result == -1 ){
                        System.out.println("Key not found!");
                    }else {
                        System.out.println("Key found at index "+result);
                    }
                }
                case 4 ->{
                    linearProbing.display();
                }
                case 5 ->{
                    System.out.println("Thank you, have a nice day");
                    return;
                }
                default -> {
                    System.out.println("Wrong choice entered, Enter again");
                }
            }
        }
    }
}
