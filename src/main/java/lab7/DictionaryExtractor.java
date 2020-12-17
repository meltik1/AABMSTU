package lab7;

import oracle.jdbc.datasource.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DictionaryExtractor {
    private OracleDataSource ods;
    private Connection connection;

    DictionaryExtractor() throws SQLException {
        ods = new oracle.jdbc.pool.OracleDataSource();
        String url = "jdbc:oracle:thin:@//localhost:1521/dblabs";
        ods.setURL(url);
        ods.setUser("meltik");
        ods.setPassword("300800");
        try {
            connection = ods.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Map<String, Double> getMapOfCliets() throws SQLException {

        Map<String, Double> mapOfClients = new HashMap<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EMAIL, CREDIT_RATING FROM CUSTOMERS");
        while (resultSet.next()) {
            mapOfClients.put(resultSet.getString(1), resultSet.getDouble(2));
        }

        return mapOfClients;
    }


}
