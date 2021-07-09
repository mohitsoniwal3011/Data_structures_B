package com.company.Hashing;

import java.util.Scanner;

class Employee{
    String name;
    int id;
    int age;
}
class EmpRec{
    Employee info;
    EmpRec next;
    EmpRec(){
        info=new Employee();
        next=null;
    }
}
class SeparateChaining{
    int maxSize;
    EmpRec [] table;
    SeparateChaining(int capacity){
        maxSize =capacity;
        table=new EmpRec[capacity];
    }

    public void insert(EmpRec rec){
        int key =rec.info.id;
        int location=hash(key);
        if(search(key) != -1){
            System.out.println("Duplicate key found");
            return;
        }
        EmpRec temp;
        if(table[location] == null){
            table[location]=rec;
        }else {
            //inserting at the beginning of the list
            temp=table[location].next;
            table[location].next=rec;
            rec.next=temp;
        }
    }

    public void delete(int key){
        int location =hash(key);
        if(table[location] == null){
            System.out.println("Key "+key+" not found");
            return;
        }
        if(table[location].info.id == key){
            table[location]=table[location].next;
            return;
        }
        EmpRec ptr=table[location],temp;
        while(ptr.next !=null ){
            if(ptr.next.info.id == key){
                temp=ptr.next;
                ptr.next=temp.next;
                return;
            }
            ptr=ptr.next;
        }
        System.out.println("Key"+key+"not found");
    }

    public int search(int key ){
        EmpRec ptr;
        int location =hash(key);
        ptr=table[location];
        while(ptr != null){
            if(ptr.info.id==key){
                return location;
            }
            ptr=ptr.next;
        }
        return -1;
    }
    
    public void display(){
        EmpRec ptr;
        for(int i=0;i<table.length;i++){
            System.out.printf("\n[%d]:",i);
            ptr=table[i];
            while(ptr != null){
                System.out.print(ptr.info.name+"  "+ptr.info.age+"  "+ptr.info.id+"      ");
                ptr=ptr.next;
            }
        }
        System.out.println("");
    }
    public int hash(int key){
        return key%maxSize;
    }

}

public class SeparateChainingTest {
    public static void main(String[] args) {
        int choice,capacity;
        Scanner sc=new Scanner(System.in);
        EmpRec rec;
        System.out.print("Enter size of table:");
        capacity= sc.nextInt();
        SeparateChaining separateChaining=new SeparateChaining(capacity);
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
                case 1 ->{
                    rec=new EmpRec();
                    System.out.println("Enter details of employee");
                    System.out.print("name :");
                    rec.info.name=sc.nextLine();
                    System.out.print("age :");
                    rec.info.age=sc.nextInt();
                    System.out.print("id :");
                    rec.info.id= sc.nextInt();
                    separateChaining.insert(rec);
                }
                case 2 ->{
                    System.out.println("Enter key to be deleted");
                    key= sc.nextInt();
                    separateChaining.delete(key);
                }
                case 3 ->{
                    System.out.println("Enter value key to be searched:");
                    key= sc.nextInt();
                    result=separateChaining.search(key);
                    if(result == -1 ){
                        System.out.println("Key not found");
                    }
                    else {
                        System.out.println("Key found at index "+result);
                    }
                }
                case 4 -> separateChaining.display();
                case 5 ->{
                    System.out.println("Thank you, have a nice day");
                    return;
                }
                default -> System.out.println("Wrong choice entered, enter again");
            }
        }

    }
}
