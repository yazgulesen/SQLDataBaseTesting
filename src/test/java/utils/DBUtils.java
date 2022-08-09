package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    public static List<Map<String,String>> getTableData (String query){
        Connection connection = null; //variable to hold connection
        Statement statement = null; //result us execute the queries on database
        ResultSet result = null; //helps us store the results
        ResultSetMetaData resultSetMetaData = null;//object to give information about it table and data
        List<Map<String,String>> tableData=null;
        try{
            connection = DriverManager.getConnection(ConfigReader.getPropertyValue("dbURL")
             ,ConfigReader.getPropertyValue("dBUserName"), ConfigReader.getPropertyValue("dBPassword"));

            statement =  connection.createStatement();
            result = statement.executeQuery("select * from person");
            resultSetMetaData = result.getMetaData();

           tableData = new ArrayList<>();

            while(result.next()) {
                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    row.put(resultSetMetaData.getColumnName(i), result.getString(resultSetMetaData.getColumnName(i)));//not sure
                }
                tableData.add(row);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBUtils.closeResultSet(result);
            DBUtils.closeConnection(connection);
            DBUtils.closeStatement(statement);
        }

        return tableData;
    }
    public static void closeResultSet(ResultSet result) {
        if(result !=null){
            try{
                result.close();
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }

        }
    }

    public static void closeStatement(Statement statement) {
        if(statement !=null){
            try{
                statement.close();
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }

        }
    }


    public static void closeConnection(Connection connection) {
        if(connection !=null){
            try{
                connection.close();
            }catch(SQLException sqe){
                sqe.printStackTrace();
            }

        }
    }
}
