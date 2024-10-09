package com.jdbcpxx;

import java.util.Scanner;

public class MainStatement {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int option = 1;
            do {
                System.out.println("\n1. Insert Data");
                System.out.println("2. Update Data");
                System.out.println("3. Delete Data");
                System.out.println("4. Fetch Data");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        StatementCrud.insertData();
                        break;

                    case 2:
                        StatementCrud.updateData();
                        break;

                    case 3:
                        StatementCrud.deleteData();
                        break;

                    case 4:
                        StatementCrud.fetchData();
                        break;

                    case 0:
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (option != 0);

            sc.close();
        } catch (Exception e) {
            System.out.println("An exception occurred in main():");
            e.printStackTrace();
        }
    }
}

	

