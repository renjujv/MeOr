package database;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abijith
 * 
 */

public class Database {

	/**
	 * The String variables below will produce a legitimate for SqlLite JDBC :
	 * jdbc:sqlite:FileList.db
	 */
	private final String sDriverName = "org.sqlite.JDBC", dbfilename = "FileList.db",
			sJdbc = "jdbc:sqlite", sDbUrl = sJdbc + ":" + dbfilename,tablename="FileList";
	private int iTimeout = 30;
	private Connection conn = null;
	private Statement stmt = null;

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */
	public Database() throws ClassNotFoundException, SQLException {
		// register the driver
		try {
			Class.forName(sDriverName);
		} catch (Exception e) {
			// connection failed.
			System.out.println("DriverName: " + sDriverName
					+ " was not available");
			System.err.println(e);
			throw e;
		}

		// create a database connection
		conn = DriverManager.getConnection(sDbUrl);
		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
			conn = null;
		}

		stmt.setQueryTimeout(iTimeout);
	}

	/**
	 * @throws Exception
	 */
	public void create() throws Exception {

		try {
			stmt.executeUpdate("DROP TABLE IF EXISTS "+tablename);
			stmt.executeUpdate("CREATE TABLE "+tablename+"(filepath TEXT PRIMARY KEY, size INTEGER)");
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

	/**
	 * This method must be called explicitly before disposing each object of
	 * this class in order to close connections externally to the class
	 */
	public void closeConnection() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

	/**
	 * @param filepath
	 *            Absolute pathname of the file to be inserted
	 * @param size
	 *            Size(in bytes) of the file to be inserted
	 * @throws Exception
	 */
	public void insert(Path filepath, long size) throws Exception {
		try {
			stmt.executeUpdate("INSERT INTO "+tablename+" VALUES('" + filepath+ "','" + size + "')");
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

	/**
	 * @param extension
	 * @return
	 * @throws Exception
	 */
	public List<String> retrieve(String extension) throws Exception {

		List<String> filelist = new ArrayList<String>();
		
			try {
				ResultSet rs = stmt.executeQuery("SELECT filepath from "+tablename+" WHERE filepath LIKE '%."
						+ extension + "'");
				try {
					while (rs.next()) {
						String sResult = rs.getString("filepath");
						System.out.println(sResult);
						filelist.add(sResult);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					stmt.close();
				} catch (Exception ignore) {
				}
			}
		return filelist;
	}
	
	protected void finalize(){
		closeConnection();
	}

	public static void main(String[] args) throws Exception {
//
	}
}