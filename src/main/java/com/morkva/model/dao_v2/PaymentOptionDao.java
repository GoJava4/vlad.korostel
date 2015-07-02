package com.morkva.model.dao_v2;

import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;

import java.util.List;

/**
 * Created by koros on 02.07.2015.
 */
public interface PaymentOptionDao extends Dao<PaymentOption> {

    List<PaymentOption> getPaymentOptionsOfProject(Project project);
}
