/**
 * Copyright (C) MeOr Project
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.renjujv.meor.core;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abijith
 * 
 */

public class Database {

	private final String tablename = "filelist";
	private Connection connection;
	private Statement statement;

	public Database() {
		int queryTimeout = 30;
		String driverName = "org.sqlite.JDBC";
		String dbfilename = "filelist.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + dbfilename;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(sDbUrl);
			statement = connection.createStatement();
			statement.setQueryTimeout(queryTimeout);
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println("DriverName: " + driverName + " was not available. More details: "
					+classNotFoundException.getMessage());
		} catch (Exception exception) {
			statement = null;
			connection = null;
			exception.printStackTrace();
		}
	}

	public void create() {
		try{
//			statement.executeUpdate("DROP TABLE IF EXISTS " + tablename);
			statement.executeUpdate("CREATE TABLE " + tablename + "(filepath TEXT PRIMARY KEY, size INTEGER)");
			statement.close();
		} catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
	}

	public boolean filelistTableExists(){
		int result = 0;
		try{
			result = statement.executeUpdate("select count() from " + tablename);
			statement.close();

		} catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		return (result == 0 || result > 0 );
	}

	/**
	 * This method must be called explicitly before disposing each object of
	 * this class in order to close connections externally to the class
	 */
	public void closeConnection() {
		try {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		} catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
	}

	/**
	 * @param filepath Absolute pathname of the file to be inserted
	 * @param size Size(in bytes) of the file to be inserted
	 */
	public void insert(Path filepath, long size) {
		try {
			statement.executeUpdate("INSERT INTO " + tablename + " VALUES('" + filepath + "','" + size + "')");
			statement.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public List<String> retrieve(String extension, String query) {
		String filteredfileNameQuery;
		if(query != null) filteredfileNameQuery = " WHERE filepath LIKE '%" + query + "%' AND filepath LIKE '%."+extension+"'";
		else filteredfileNameQuery = " WHERE filepath LIKE '%." + extension + "'";
		String retrieveFilesQuery = "SELECT filepath from " + tablename + filteredfileNameQuery;

		List<String> fileList = new ArrayList<>();
		try {
			ResultSet rs = statement.executeQuery(retrieveFilesQuery);
			while (rs.next()) {
				String queryResultfilePath = rs.getString("filepath");
				fileList.add(queryResultfilePath);
			}
			statement.close();
		} catch (SQLException sqlException){
			sqlException.printStackTrace();
		}
		return fileList;
	}
}
