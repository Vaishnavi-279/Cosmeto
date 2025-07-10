package Cosmeto;

import Products.ProductsList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class OutOfRange extends Exception {
}

public class Cosmeto {
	Scanner sc;

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Cosmetic Store!");
        		try {
            		FileReader fr = new FileReader("Brands.txt");
            		BufferedReader br = new BufferedReader(fr);
            		System.out.println("Select one brand from the following...");
            		String line;
            		int cnt = 0;
            		while ((line = br.readLine()) != null) {
                			System.out.println(line);
                			cnt++;
            		}
            		br.close();
            		fr.close();
            		brandSelection(cnt);
        		}
		catch (IOException e) {
            		System.out.println("Please enter a valid choice...");
			System.exit(0);
        		}
		finally {
            		System.out.println("\t\t| Thank you for visiting :)");
			System.out.println("\t\t+-----------------------------------------------------------------+");
        		}
    	}

    	public static void brandSelection(int cnt) {
        		System.out.println("Enter the number corresponding to your choice:");
        		Scanner sc = new Scanner(System.in);
        		int choice = sc.nextInt();
        		try {
            		if (choice < 1 || choice > cnt) {
                			throw new OutOfRange();
            		}
			else {
                			ProductsList productList = new ProductsList(choice);
            		}
        		}
		catch (OutOfRange e) {
            		System.out.println("Please enter a valid choice...");
        		}
    	}
}
