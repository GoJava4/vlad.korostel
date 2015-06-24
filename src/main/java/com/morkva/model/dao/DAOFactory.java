package com.morkva.model.dao;


import org.springframework.stereotype.Component;

/**
 * Created by koros on 06.06.2015.
 */
@Component
public interface DAOFactory<Context> {

    Context getContext() throws PersistException;

    DAO getDao(Context context, Class dtoClass) throws PersistException;


}
