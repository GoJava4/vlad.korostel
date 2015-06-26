package com.morkva.model.dao.jdbc.mysql;

import com.morkva.entities.Category;
import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import com.morkva.entities.Quote;
import com.morkva.model.dao.DAO;
import com.morkva.model.dao.PersistException;
import com.morkva.model.dao.DAOFactory;
import com.morkva.model.dao.DaoCreator;
import com.morkva.model.dao.jdbc.DataSource;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by koros on 06.06.2015.
 */
@Component
public class MySQLDaoFactory implements DAOFactory<Connection> {

    private Map<Class, DaoCreator> creators;

    public MySQLDaoFactory() {
        creators = new HashMap<>();
        creators.put(Quote.class, new DaoCreator<Connection>() {
            @Override
            public DAO create(Connection connection) {
                return new MySQLQuoteDAO(MySQLDaoFactory.this);
            }
        });
        creators.put(Category.class, new DaoCreator<Connection>() {
            @Override
            public DAO create(Connection connection) {
                return new MySQLCategoryDao(MySQLDaoFactory.this);
            }
        });
        creators.put(Project.class, new DaoCreator<Connection>() {
            @Override
            public DAO create(Connection connection) {
                return new MySQLProjectDao(MySQLDaoFactory.this);
            }
        });
        creators.put(PaymentOption.class, new DaoCreator<Connection>() {
            @Override
            public DAO create(Connection connection) {
                return new MySQLPaymentOptionDao(MySQLDaoFactory.this);
            }
        });

    }

    @Override
    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DataSource.getInstance().getConnection();
        } catch (SQLException e) {
            throw new PersistException(e);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public DAO getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found");
        }
        return creator.create(connection);
    }
}
