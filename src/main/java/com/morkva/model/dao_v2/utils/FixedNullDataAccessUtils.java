package com.morkva.model.dao_v2.utils;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

import java.util.Collection;

/**
 * Created by koros on 01.07.2015.
 */
public class FixedNullDataAccessUtils extends DataAccessUtils {

    public static <T> T requiredSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        int size = results != null?results.size():0;
        if(size == 0) {
            return null;
        } else if(results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, size);
        } else {
            return results.iterator().next();
        }
    }
}
