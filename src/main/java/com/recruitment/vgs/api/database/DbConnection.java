package com.recruitment.vgs.api.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DbConnection implements IDbConnection {

	private static final Logger logger = LoggerFactory.getLogger(DbConnection.class);

	@Override
	public Connection connect() throws Exception {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:32118/XE", "GBEMISOLA", "GBEMISOLA");
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("Error connecting to database: {}", e.toString());
			throw e;
		}
		return conn;
	}

}
