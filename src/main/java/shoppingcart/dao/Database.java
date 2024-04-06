package shoppingcart.dao;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


public class Database {

    private static DataSource dataSource;

    static {
    	BasicDataSource ds = new BasicDataSource();
    	
    	//TODO should put this into property file.
    	ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/useradmin");
		ds.setUsername("chen");
		ds.setPassword("secret");


		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);

		dataSource = ds;
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

}
