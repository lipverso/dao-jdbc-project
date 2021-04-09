package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	//3. Declarar objeto do tipo Connection, objeto de conexao com banco de dados do JDBC
	private static Connection conn = null;
	
	//Conectar com banco de dados no JDBC, significa instanciar um objeto do tipo Connection
	
	//4 . Metodo para conectar ao banco de dados
	public static Connection getConnection() {
		// 5. Verificar se esta nulo, se sim, conectar com banco
		if (conn == null) {
			Properties props = loadProperties();
			
			//Db url e o que esta definido no db.properties
			String url = props.getProperty("dburl");
			
			// Para que possa obter a conexao, chamar DriverManager
			try {
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	// 1 . Primeiro criar metodos estaticos auxiliares para carregar o que esta contido dentro de db.properties
	
	private static Properties loadProperties() {
		
		//2 . Abrir db.properties, ler os dados e guardar em um objeto Properties props
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			
			props.load(fs);
			
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		if (conn != null) {
			//Se tiver estanciada, fechar conexao
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closePreparedSt(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
