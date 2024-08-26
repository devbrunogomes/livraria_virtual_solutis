package solutis.livrariavirtual_solutis.model;

import java.sql.Connection;
import java.sql.DriverManager;


public class DAO {

	private Connection con;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/livraria_virtual";
	private String user = "root";
	private String password = "nina";

	public Connection conectar()  {
        try {
             Class.forName(driver);
             con = DriverManager.getConnection(url,user,password);
             return con;
        
        	} catch(Exception e) {
        		System.out.println(e);
        		return null;
        	}
    }
}
