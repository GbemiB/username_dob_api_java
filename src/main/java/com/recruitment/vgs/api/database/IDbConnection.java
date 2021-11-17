package com.recruitment.vgs.api.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public interface IDbConnection {

	Connection connect() throws Exception;

	default void closeConnection(Connection connection, CallableStatement callableStatement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	default void closeConnection(Connection connection, CallableStatement callableStatement) {
		if (callableStatement != null) {
			try {
				callableStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
