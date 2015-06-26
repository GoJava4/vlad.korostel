package com.morkva.model.dao.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by koros on 25.06.2015.
 */
public class DataSource {
    private static DataSource dataSource;
    private BasicDataSource ds;

    private String user = "vlad_korostel";
    private String password = "vlad_korostel";
    private String url = "jdbc:mysql://tortik.asuscomm.com:3306/vlad_korostel";
    private String driver = "com.mysql.jdbc.Driver";

    private DataSource() throws IOException, SQLException, PropertyVetoException{
        ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);

        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}
