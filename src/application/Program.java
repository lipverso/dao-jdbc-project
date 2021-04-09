package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
			//Programa para deletar dados por JDBC
		/* Neste caso, teremos que criar uma classe chamada DbIntegrityException, pois e muito comum, ao deletar dados de um banco
		 * ocorrer um problema de Integridade Referencial. Se eu tenho uma tabela ligada a outra por uma chave estrangeira, e a segunda tabela
		 * deixar de existir, a primeira tabela, que estava referenciando a outra, vai estar errada, pois a outra nao existira mais, ocorrendo
		 * uma falha de integridade referencial. Por isso, por padrao, voce nao pode apagar uma tabela que esteja sendo referenciada por outra.
		 * Para tratar este tipo de erro, criamos o DbIntegrityException
		 * PS: Se deletarmos algo que nao esta referenciado, nao sera lançada exceçao nenhuma.
		 */
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = DB.getConnection();
			ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			/*
			* ps.setInt(1, 5);
			*/
			
			//Agora vamos tentar apagar o Departamento de codigo 2, que esta referenciado por outra tabela
			ps.setInt(1, 2); //Ao executar, podemos ver que ele lança um SqlIntegrityConstraintViolationException. 
			//Por isso criamos a classe DbIntegrityException
			
			int rowsAffected = ps.executeUpdate(); //Para executar o comando SQL, retornando quantas linhas foram afetadas
			
			System.out.println("Done! Rows affected: " + rowsAffected);
			
		} catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeConnection();
			DB.closePreparedSt(ps);
		}
		
		}
}
