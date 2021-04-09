package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		Seller seller = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// The program won't know the implementation behind SellerDao, it only knows its interface
		// It's also a way of doing dependency injection without expliciting the implementation
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		try {
			seller = new Seller(1, "Joao das Couves", "couves@gmail.com", sdf.parse("12/07/1994"), 3000.00, obj);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(obj);
		System.out.println(seller);
		
	}
}
