/*
康瀚中
105403018
資管2A
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoiceManagemantSystem 
{
	public static void main(String[] args)
	{
		Invoice[] invoice = {
								new Invoice( 83, "Electric sander", 7, 57.98),
								new Invoice( 24, "Power saw", 18, 99.99),
								new Invoice(  7, "Sledge hammer",11,21.50),
								new Invoice( 77, "Hammer", 76, 11.99),
								new Invoice( 39, "Lawn mower", 3, 79.50),
								new Invoice( 68, "Screwdriver", 106,6.99),
								new Invoice( 56, "Jig saw", 21, 11.00),
								new Invoice(  3, "Wrench", 34, 7.50),
								new Invoice( 45, "Wrench", 13, 7.50),
								new Invoice( 22, "Hammer", 47, 11.99)
							}; 

		Scanner input = new Scanner(System.in);
		
		List<Invoice> list = Arrays.asList(invoice);
		System.out.println("Welcome to invoices mangesment system.");
		System.out.println("Functions: Sort/Report/Select");
		System.out.print("Choice(-1 to exit): ");
		while(true)
		{
			String choice = input.next();
			if(choice.equals("-1"))
			{
				break;
			}
			else if(choice.equals("sort"))
			{
				System.out.println("Order by ID/Quantity/Price? ");
				choice = input.next();
				if(choice.equalsIgnoreCase("ID"))
				{
					list.stream()
						.sorted(Comparator.comparing(Invoice::getPartNumber))
						.forEach(System.out ::println);
					System.out.println();
					System.out.println("Welcome to invoices mangesment system.");
					System.out.println("Functions: Sort/Report/Select");
					System.out.println("Choice(-1 to exit): ");
				}
				else if(choice.equalsIgnoreCase("quantity"))
				{
					list.stream()
					.sorted(Comparator.comparing(Invoice::getQuantity))
					.forEach(System.out ::println);
					System.out.println();
					System.out.println("Welcome to invoices mangesment system.");
					System.out.println("Functions: Sort/Report/Select");
					System.out.println("Choice(-1 to exit): ");
				}
				else if(choice.equalsIgnoreCase("price"))
				{
					list.stream()
						.sorted(Comparator.comparing(Invoice::getPrice))
						.forEach(System.out ::println);
					System.out.println();
					System.out.println("Welcome to invoices mangesment system.");
					System.out.println("Functions: Sort/Report/Select");
					System.out.println("Choice(-1 to exit): ");
				}
				else
				{
					list.stream()
						.sorted(Comparator.comparing(Invoice::getPartNumber))
						.forEach(System.out ::println);
					System.out.println();
					System.out.println("Welcome to invoices mangesment system.");
					System.out.println("Functions: Sort/Report/Select");
					System.out.println("Choice(-1 to exit): ");
				}
			}
			else if(choice.equalsIgnoreCase("report"))
			{
				HashMap<String,Integer> invoiceQuantityMap = new HashMap<String,Integer>();
				HashMap<String,Double> invoiceTotalMap = new HashMap<String,Double>();
				TreeSet<String> partDescriptionSet = new TreeSet<String>();
				System.out.printf("%nInvoice grouped by description:%n");
				list.stream()
					.forEach(value -> partDescriptionSet.add(value.getPartDescription()));
				partDescriptionSet.stream()
								  .forEach(
										  	key -> 
								  				 {
								  					invoiceQuantityMap.put(key,list.stream()								  							 						
								  										           .filter(value -> key == value.getPartDescription())								  													  
								  										           .mapToInt(Invoice::getQuantity)
								  										           .sum()
								  										  );
								  				 }
								  		  );
				
				partDescriptionSet.stream()
				  				  .forEach(
				  						  	key -> 
				  						  		 {
				  						  			 invoiceTotalMap.put(key,invoiceQuantityMap.get(key)*list.stream()
				  						  					 												 .filter(value -> key == value.getPartDescription())
				  						  					 												 .mapToDouble(Invoice::getPrice)
				  						  					 												 .average()
				  						  					 												 .getAsDouble()
				  						  					 			);
				  						  		 }
				  						  );
				partDescriptionSet.stream()
								  .sorted(Comparator.comparing(key -> invoiceTotalMap.get(key)))
								  .forEach(key ->  System.out.printf("Description: %-20sInvoice amount:%8.2f%n",key,invoiceTotalMap.get(key)));
				
				
				
				System.out.println();
				System.out.println("Welcome to invoices mangesment system.");
				System.out.println("Functions: Sort/Report/Select");
				System.out.println("Choice(-1 to exit): ");
			}
			else if(choice.equalsIgnoreCase("select"))
			{
				System.out.print("Input the range to show (min,max): ");
				try
				{
					String closedRange = input.next();
					String closedRangeArray[] = closedRange.split(",");
					int min = Integer.parseInt(closedRangeArray[0]);
					int max = Integer.parseInt(closedRangeArray[1]);
				
					Predicate<Invoice> range = i -> (i.getPrice()*i.getQuantity() >= min && i.getPrice()*i.getQuantity() <= max);
				
					System.out.printf("Invoices mapped description and invoice amount for invoices in the range %d-%d%n",min,max);
					list.stream()
						.filter(range)
						.sorted(Comparator.comparing(value -> value.getQuantity()*value.getPrice()))
						.forEach(value -> System.out.printf("Description: %-20sInvoice amount:%8.2f%n",value.getPartDescription(),value.getQuantity()*value.getPrice()));
				}
				catch(InputMismatchException e)
				{
					System.err.println("Please enter range as format min,max");
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.err.println("Please enter range as format min,max");
				}
				/*catch(Exception e)
				{
					System.err.println("Please enter range as format min,max");
				}*/
				
				System.out.println();
				System.out.println("Welcome to invoices mangesment system.");
				System.out.println("Functions: Sort/Report/Select");
				System.out.println("Choice(-1 to exit): ");
			}
			else
			{
				System.out.print("Wrong input. Enter again: ");
			}
			
		}
	}
}