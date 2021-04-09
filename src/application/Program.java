package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		Seller seller = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			seller = new Seller(1, "Joao das Couves", "couves@gmail.com", sdf.parse("12/07/1994"), 3000.00);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(obj);
		System.out.println(seller);
		
	}
}
