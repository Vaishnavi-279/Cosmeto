package Products;

import Cosmeto.BillHandler;
import Cosmeto.Cosmeto;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

interface Bill {
	public void printBill(int totalBill) ;
}

public class ProductsList{
	private int choice;
	private int amount = 0;
	private StringBuilder userChoices = new StringBuilder();
	private List<String> chosenProducts = new ArrayList<>();
	public List<String> userChosenProducts = new ArrayList<>();
	public static int cash = 0;
	//BillHandler billHandler = new BillHandler();
	//BillHandler bh;
	int result;
	File file;
	FileReader fr;
	BufferedReader br;
	Scanner sc = new Scanner(System.in);

	public ProductsList(int choice) {
		this.choice = choice;
		displayProducts();
	}

	public void displayProducts() {
		try{
			if (choice == 1) {
				file = new File("L'Oreal Paris.txt");
			}
			else if(choice == 2) {
				file = new File("MaybellineProducts.txt");
			}
			else if(choice == 3) {
				file = new File("Lakme Cosmetics.txt");
			}
			readFile(file);
		}
		finally{
			selection(file);
		}
	}
	
	public void readFile(File file) {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			System.out.println("\nProducts available: \n");
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void selection(File current_file){
		String x;
		String proDetails;
		System.out.println("\nSelect the items you want to buy: ");
		System.out.println("Enter '1' for first item\nEnter '2' for second item\nEnter '3' for third item\nEnter '4' for changing the brand.\nType 'exit' for stopping the order.\n");
		try{
			while (!(x = sc.next()).equals("exit")) {
				if(x.equals("1") || x.equals("2") || x.equals("3")){
					int limit = Integer.parseInt(x);
					System.out.println("Enter the number of products: ");
					int quantity = sc.nextInt();			
					amount = price(current_file, limit, quantity);
					String productName = getProductName(current_file, limit);
					String number = Integer.toString(quantity);
					proDetails = productName + " ("+number + ")" + "item/s";
					userChosenProducts.add(proDetails);
					result = bill(amount);
				}
				else if(x.equals("4")){
					Cosmeto brand_change = new Cosmeto();
					brand_change.brandSelection(3);
					System.exit(0);
				}
				else {
                				System.out.println("Invalid option. Please enter a valid choice...");
					System.exit(0);
           	 		}
				System.out.println("\nWant to buy more??? :-) \nEnter your brand choice\nElse type exit :(");
			}
		}
		finally{
			//billHandler.printBill("Bill.txt ", result, userChosenProducts);
			ShoppingCart shoppingCart = new ShoppingCart(userChosenProducts);
    			shoppingCart.printBill(result);
			shoppingCart.fileBill(result);
			System.out.println("\t\t| Total bill is " +result);
			System.out.println("\t\t+-----------------------------------------------------------------+");
		}
	}

	public String getProductName(File current_file, int limit) {
    		String productName = "";
    		try {
        			fr = new FileReader(file);
        			br = new BufferedReader(fr);
       			String line;
        			int i = 1;
        			while ((line = br.readLine()) != null) {
            			if (i == limit) {
                				productName = line;
            			}
            			i++;
        			}
    		} 
		catch (IOException e) {
        			e.printStackTrace();
    		}
		finally {
        			try {
            			if (br != null) {
                				br.close();
            			}
            			if (fr != null) {
                				fr.close();
            			}
        			}
			catch (IOException e) {
            			e.printStackTrace();
        			}
    		}
    		return productName;
	}


	public int price(File current_file, int limit, int quantity){
		int cost = 0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line, numericpart;
			int i = 1;
			while ((line = br.readLine()) != null && i <= limit) {
				numericpart = line.replaceAll("[^0-9]", "");
				cost = Integer.parseInt(numericpart);
				i++;
			}	
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return cost * quantity;
		}
	}
	
	public static int bill(int amount){
		cash = cash + amount;
		return cash;
	}

	public class ShoppingCart implements Bill{

		private List<String> chosenProducts;

		public ShoppingCart(List<String> chosenProducts) {
        			this.chosenProducts = chosenProducts;
    		}

    		@Override
    		public void printBill(int totalBill) {
			System.out.println("\n");
			printBorder();
			System.out.println("\t\t|\t\t\t COSMETO");
			printBorder();
        			System.out.println("\t\t| Ordered Items :");
			System.out.println("\t\t|");
        			for (String product : chosenProducts) {
				printLine(product);
        			}
			printBorder();
    		}

		public static void printBorder() {
        			System.out.println("\t\t+-----------------------------------------------------------------+");
    		}

		public static void printLine(String content) {
        			System.out.println("\t\t| " + content);
    		}
	
	public void fileBill(int totalBill) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Bill.txt", true))) {
            writer.println("\n");
            printBorder(writer);
            writer.println("\t\t|\t\t\t COSMETO");
            printBorder(writer);
            writer.println("\t\t| Ordered Items :");
            writer.println("\t\t|");
            for (String product : chosenProducts) {
                printLine(writer, product);
            }
            printBorder(writer);

            // You can add the totalBill information or any other details you need

            writer.println("\t\t| Total Bill: " + totalBill);
		printBorder(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void printBorder(PrintWriter writer) {
        writer.println("\t\t+-----------------------------------------------------------------+");
    }

    public static void printLine(PrintWriter writer, String content) {
        writer.println("\t\t| " + content);
    }
}
}