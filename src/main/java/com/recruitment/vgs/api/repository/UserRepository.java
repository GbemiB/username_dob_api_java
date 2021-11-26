package com.recruitment.vgs.api.repository;

import com.recruitment.vgs.api.database.DbConnection;
import com.recruitment.vgs.api.domain.Request;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepository implements IUserRepository {

    @Value("${package.db}")
    private String dataBasePackage;

    @Autowired
    DbConnection dbConnection;

    @Override
    public String save(Request request) throws Exception {

        Connection connection = null;
        OracleCallableStatement callableStatement = null;
        String responseCode, responseMessage;

        try {
            connection = dbConnection.connect();
            callableStatement = (OracleCallableStatement) connection
                    .prepareCall("{ call " + dataBasePackage + ".save_user(?,?,?,?) }");

            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.setString(3, request.getUsername());
            callableStatement.setDate(4, (Date) request.getDateOfBirth());
            callableStatement.execute();

            responseCode = callableStatement.getString(1);
            responseMessage = callableStatement.getString(2);

            log.info("Persist user in data base: {}", responseMessage);

        } catch (Exception ex) {
            log.error("Error occurred while trying to persist user detail {}", ex.getMessage());
            throw ex;
        } finally {
            dbConnection.closeConnection(connection, callableStatement);
        }

        return responseMessage;
    }

    @Override
    public String update(Request request) throws Exception {
        Connection connection = null;
        OracleCallableStatement callableStatement = null;
        String responseCode, responseMessage;

        try {
            connection = dbConnection.connect();
            callableStatement = (OracleCallableStatement) connection
                    .prepareCall("{ call " + dataBasePackage + ".update_user(?,?,?,?) }");

            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.setString(3, request.getUsername());
            callableStatement.setDate(4, (Date) request.getDateOfBirth());
            callableStatement.execute();

            responseCode = callableStatement.getString(1);
            responseMessage = callableStatement.getString(2);

            log.info("Persist updated user details in data base: {}", responseCode);

        } catch (Exception ex) {
            log.error("Error occurred while trying to update user detail {}", ex.getMessage());
            throw ex;
        } finally {
            dbConnection.closeConnection(connection, callableStatement);
        }

        return responseCode;
    }


    @Override
    public Optional<Request> getByUsername(String username) throws Exception {
        Connection connection = null;
        oracle.jdbc.OracleCallableStatement callableStatement = null;
        ResultSet resultSet = null;

        String responseCode;
        String responseMessage;

        try {
            connection = dbConnection.connect();

            callableStatement = (oracle.jdbc.OracleCallableStatement) connection
                    .prepareCall("{ call " + dataBasePackage + ".get_by_user_id(?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.setString(4, username);

            callableStatement.execute();

            responseCode = callableStatement.getString(1);
            responseMessage = callableStatement.getString(2);

            resultSet = (ResultSet) callableStatement.getObject(3);

            log.info("Fetch by user id response message: {}", responseCode);

            if (responseCode.equals("000") && resultSet != null) {
                Request request = new Request();
                while (resultSet.next()) {
                    request.setUsername(resultSet.getString("username"));
                    request.setDateOfBirth(resultSet.getDate("date_of_birth"));
                }
                return Optional.of(request);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            dbConnection.closeConnection(connection, callableStatement, resultSet);
        }

        return Optional.empty();
    }
}
