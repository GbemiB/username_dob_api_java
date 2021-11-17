package com.recruitment.vgs.api.repository;

import com.recruitment.vgs.api.database.DbConnection;
import com.recruitment.vgs.api.domain.UserRequestDto;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Value("${package.db}")
    private String dataBasePackage;

    private final DbConnection dbConnection;

    public UserRepository(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public String save(UserRequestDto requestDto) throws Exception {

        Connection connection = null;
        OracleCallableStatement callableStatement = null;
        String responseCode, responseMessage;

        try {
            connection = dbConnection.connect();
            callableStatement = (OracleCallableStatement) connection
                    .prepareCall("{ call " + dataBasePackage + ".save_user(?,?,?,?) }");

            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.setString(3, requestDto.getUsername());
            callableStatement.setString(4, requestDto.getDateOfBirth());
            callableStatement.execute();

            responseCode = callableStatement.getString(1);
            responseMessage = callableStatement.getString(2);

            logger.debug("Persist user in data base: {}", responseMessage);

        } catch (Exception ex) {
            logger.error("Error occurred while trying to persist user detail {}", ex.getMessage());
            throw ex;
        } finally {
            dbConnection.closeConnection(connection, callableStatement);
        }

        return responseCode;
    }

    @Override
    public String update(UserRequestDto requestDto) throws Exception {
        Connection connection = null;
        OracleCallableStatement callableStatement = null;
        String responseCode, responseMessage;

        try {
            connection = dbConnection.connect();
            callableStatement = (OracleCallableStatement) connection
                    .prepareCall("{ call " + dataBasePackage + ".save_user(?,?,?,?) }");

            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.setString(3, requestDto.getUsername());
            callableStatement.setString(4, requestDto.getDateOfBirth());
            callableStatement.execute();

            responseCode = callableStatement.getString(1);
            responseMessage = callableStatement.getString(2);

            logger.debug("Persist updated user details in data base: {}", responseMessage);

        } catch (Exception ex) {
            logger.error("Error occurred while trying to update user detail {}", ex.getMessage());
            throw ex;
        } finally {
            dbConnection.closeConnection(connection, callableStatement);
        }

        return responseCode;
    }


    @Override
    public Optional<UserRequestDto> getByUsername(String username) throws Exception {
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

            logger.info("Fetch by user id response message: {}", responseMessage);

            if (responseCode.equals("000") && resultSet != null) {
                UserRequestDto userRequestDto = new UserRequestDto();
                while (resultSet.next()) {
                    userRequestDto.setUsername(resultSet.getString("username"));
                    userRequestDto.setDateOfBirth(resultSet.getString("date_of_birth"));
                }
                return Optional.of(userRequestDto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            dbConnection.closeConnection(connection, callableStatement, resultSet);
        }

        return Optional.empty();
    }
}
