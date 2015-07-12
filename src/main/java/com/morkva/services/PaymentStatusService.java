package com.morkva.services;

import com.morkva.model.dao.PaymentStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("paymentStatusService")
public class PaymentStatusService {


    @Qualifier("paymentStatusDao")
    @Autowired
    private PaymentStatusDao paymentStatusDao;
}
