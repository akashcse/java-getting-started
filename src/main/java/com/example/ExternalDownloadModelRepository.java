package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExternalDownloadModelRepository {
	private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
	
	public static void save(ExternalDownloadModel externalDownloadModel) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://216.172.183.197:3306/getdirec_verapaxforms?"
                            + "user=getdirec_verapax&password=verapax");

            statement = connect.createStatement();

            preparedStatement = connect
                    .prepareStatement("insert into  getdirec_verapaxforms.external_download_model values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, externalDownloadModel.getExternalId());
            preparedStatement.setBoolean(2, externalDownloadModel.isAgree());
            preparedStatement.setString(3, externalDownloadModel.getEmail());
            preparedStatement.setString(4, externalDownloadModel.getEmailFrom());
            preparedStatement.setString(5, externalDownloadModel.getFilePath());
            preparedStatement.setString(6, externalDownloadModel.getFirstName());
            preparedStatement.setString(7, externalDownloadModel.getFormCreateTime());
            preparedStatement.setString(8, externalDownloadModel.getLastName());
            preparedStatement.setString(9, externalDownloadModel.getLastUpdateTime());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
        	if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        }

    }

	

    public static ExternalDownloadModel findOne(String instance) throws Exception {
    	ExternalDownloadModel externalDownloadModel = null;
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://216.172.183.197:3306/getdirec_verapaxforms?"
                            + "user=getdirec_verapax&password=verapax");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from getdirec_verapaxforms.external_download_model where external_id = '"+instance+"'");

            while (resultSet.next()) {
            	externalDownloadModel = new ExternalDownloadModel();
            	
            	externalDownloadModel.setExternalId(resultSet.getString("external_id"));
            	externalDownloadModel.setAgree(resultSet.getBoolean("agree"));
            	externalDownloadModel.setEmail(resultSet.getString("email"));
            	externalDownloadModel.setEmailFrom(resultSet.getString("email_from"));
            	externalDownloadModel.setFilePath(resultSet.getString("file_path"));
            	externalDownloadModel.setFirstName(resultSet.getString("first_name"));
            	externalDownloadModel.setFormCreateTime(resultSet.getString("form_create_time"));
            	externalDownloadModel.setLastName(resultSet.getString("last_name"));
            	externalDownloadModel.setLastUpdateTime(resultSet.getString("last_update_time"));
            }
            

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

        return externalDownloadModel;
    }

    // You need to close the resultSet
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
