package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// This class will have static operations to instantiate the Dao class
	public static SellerDao createSellerDao(){
		return new SellerDaoJDBC();
		// This way, DaoFactory will have a method which returns the interface type, 
		// yet internally it's going to instantiate an implementation (jdbc in this case)
	}

}
