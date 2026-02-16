package kakeibo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseConnection {

	static final String url = "jdbc:postgresql://localhost:5432/postgres";
	static final String user = "postgres";
	static final String password = "postgrestest";
	
	static Connection connect() throws IllegalStateException {
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
}
