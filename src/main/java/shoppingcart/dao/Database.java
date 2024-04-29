package shoppingcart.dao;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import shoppingcart.config.Configuration;


public class Database {

    private static DataSource dataSource;

    static {
    	BasicDataSource ds = new BasicDataSource();
    	
    	ds.setDriverClassName(Configuration.getProperty("ds.driverClassName"));
    	ds.setUrl(Configuration.getProperty("ds.url"));
    	ds.setUsername(Configuration.getProperty("ds.userName"));
    	ds.setPassword(Configuration.getProperty("ds.password"));
    	
    	
    	/*
    	ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/useradmin");
		ds.setUsername("chen");
		ds.setPassword("secret");
       */

		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);

		dataSource = ds;
    }

    public static DataSource getDataSource(){
    	return dataSource;
    }
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

}
