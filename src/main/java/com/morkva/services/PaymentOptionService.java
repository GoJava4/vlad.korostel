package com.morkva.services;

import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import com.morkva.model.dao_v3.PaymentOptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by koros on 02.07.2015.
 */
@Service("paymentOptionService")
public class PaymentOptionService {

    @Autowired
    PaymentOptionDao paymentOptionDao;

    public List<PaymentOption> getPaymentOptionsOfProject(Project project) {
        return paymentOptionDao.getPaymentOptionsOfProject(project);
    }
}
