package org.example;
import java.util.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;

import org.bson.Document;
public class Main {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection ;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>();
        //int count =0;
        createConnection();



        char choose='A';//Variable to choose type of action to be performed
        while(choose !='Q')
        {
            System.out.println("Choose an option :(A)Add Account\t(D)Display Accounts\t(S)Save to database\t(I)Deposit funds\t(W)Withdraw funds\t(Q)Quit");
            choose = sc.next().charAt(0);
            //System.out.println(choose);
            if (choose =='A')//To Add account
            {
                System.out.println("enter type of  account \n B for bank account \t S for savings account ");
                char type = sc.next().charAt(0);
                if (type=='B')//for Bank account
                {
                    System.out.println("Please enter your name");
                    String name=sc.next();
                    System.out.println("Please enter initial balance");
                    Double bala=sc.nextDouble();
                    BankAccount bankAccount = new BankAccount(name,bala );
                    System.out.println("Bank Account Creation Sucessfull");
                    System.out.println("Name" + bankAccount.getName());
                    System.out.println("Initial Balance: $" + bankAccount.getBalance());
                    accounts.add(bankAccount);
                   // count++;

                }

                else if(type=='S')// for saving account
                {
                    System.out.println("Please enter your name");
                    String name=sc.next();
                    System.out.println("Please enter initial balance");
                    Double bala=sc.nextDouble();
                    SavingsAccount savingsAccount = new SavingsAccount(name,bala);
                    System.out.println("Bank Account Creation Sucessfull");
                    System.out.println("Name" + savingsAccount.getName());
                    System.out.println("Initial Balance: $" + savingsAccount.getBalance());
                    accounts.add(savingsAccount);
                }
                else
                {
                    System.out.println("Invalid option , Start from begining");
                }

            }
            else if(choose=='D')// to display data
            {
                for (BankAccount acc : accounts) {
                    System.out.println("Name: " + acc.getName() + ", Balance: $" + acc.getBalance());
                }
            }
            else if(choose =='I')//to deposit
            {
                System.out.println("enter your name");
                String Tempname=sc.next();
                for (BankAccount acc : accounts) {
                    if (acc.getName().equals(Tempname))
                    {
                        System.out.println("enter amount to deposit");
                        Double money=sc.nextDouble();
                        acc.deposit(money);
                        System.out.println("After deposit: $" + acc.getBalance());


                    }
                    else {
                        //System.out.println("Name: " + acc.getName()+Tempname);
                        System.out.println("No account found");
                    }

                }

            }
            else if(choose =='W')//for withdraw
            {
                System.out.println("enter your name");
                String Tempname=sc.next();

                for (BankAccount acc : accounts) {
                    if (acc.getName().equals(Tempname))
                    {
                        System.out.println("enter amount to withdraw");
                        Double money=sc.nextDouble();
                        acc.withdraw(money);
                        System.out.println("After Withdraw $" + acc.getBalance());


                    }
                    else {
                        System.out.println("No account found");
                       // System.out.println("Name: " + acc.getName()+Tempname);

                    }

                }


            }
            else if (choose=='S')
            {
                for (BankAccount acc : accounts)
                {
                insertDoc(acc.getName(), acc.getBalance());

                }
            }
            else {
                if(choose != 'Q')
                {
                //System.out.println("\nInvalid option \n");
            }}

        }

    //insertDoc();
       // retDoc();
        //retDoc();
    }

    private static void createConnection() {

        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("BANK");
    }
    private static void insertDoc(String S,Double bal) {
        collection = database.getCollection("BANKACC");
        Document doc = new Document();
        doc.put("Name",S);
        doc.put("Balance",bal);
        collection.insertOne(doc);
    }
    private static void retDoc() {
        //collection = database.getCollection("BANKACC");
        FindIterable<Document> iterable = collection.find();

        for(Document doc :iterable) {
            String nam = doc.getString("Name");
            System.out.println(nam);
            Double bal = doc.getDouble("Balance");
        }
    }
}
