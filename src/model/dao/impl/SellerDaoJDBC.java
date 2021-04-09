package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	//Declare Connection variable
	private Connection conn;
	
	//Inject conn in class constructor
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller Seller) {
		
	}

	@Override
	public void update(Seller Seller) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?");	
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			// Important: ResultSet returns a table. We need to instantiate the objects that compose the table in memory.
			// In this case, we want to instantiate Department and Seller objects
			
			// When we execute the query and return the result to the ResultSet, it will be pointing to position 0, which doesn`t contain any object
			// The object is on position 1
			// We have to check if there's any result being returned from the query, by using the next() function:
			
			if (rs.next()) { // If the ResultSet contains elements, it will fall into the if statement, meaning the Seller with the given Id was found
				
				// We then to go through the table content which is being returned to the ResultSet in order to instantitate the desired objects (Seller and its Department)
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				
				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(dep);
				
				return seller;
				
			}
			// If not, return null. In other words, there aren't any Sellers with the indicated Id
			return null;
			
		} catch (SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			
			// We won't close the conn variable, because the same DaoObject might do other operations
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}
	
	

}
