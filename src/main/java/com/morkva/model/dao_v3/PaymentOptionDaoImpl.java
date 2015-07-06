package com.morkva.model.dao_v3;

import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
@Repository("paymentOptionDao")
@Transactional
public class PaymentOptionDaoImpl extends AbstractDao<PaymentOption> implements PaymentOptionDao {

    public PaymentOptionDaoImpl() {
        super(PaymentOption.class);
    }

    @Override
    public List<PaymentOption> getPaymentOptionsOfProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(PaymentOption.class)
                .add(Restrictions.eq("project.id", project.getId()))
                .addOrder(Order.asc("value"))
                .list();
    }
}
