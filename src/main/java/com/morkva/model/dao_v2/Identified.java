package com.morkva.model.dao_v2;

import java.io.Serializable;

/**
 * Created by koros on 15.06.2015.
 */
public interface Identified<PK extends Serializable> {
    PK getId();
}
