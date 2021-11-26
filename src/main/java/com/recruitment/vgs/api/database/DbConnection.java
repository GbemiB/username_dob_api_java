package com.recruitment.vgs.api.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Service
public class DbConnection implements IDbConnection {

	@Override
	public Connection connect() throws Exception {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:32118/XE", "GBEMISOLA", "GBEMISOLA");
		} catch (ClassNotFoundException | SQLException e) {
			log.error("Error connecting to database: {}", e.toString());
			throw e;
		}
		return conn;
	}

}
