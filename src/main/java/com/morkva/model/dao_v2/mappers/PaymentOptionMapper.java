package com.morkva.model.dao_v2.mappers;

import com.morkva.entities.PaymentOption;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koros on 02.07.2015.
 */
public class PaymentOptionMapper implements RowMapper<PaymentOption> {

    private class PersistedPaymentOption extends PaymentOption {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    @Override
    public PaymentOption mapRow(ResultSet resultSet, int i) throws SQLException {
        PersistedPaymentOption paymentOption = new PersistedPaymentOption();
        paymentOption.setId(resultSet.getInt("id"));
        paymentOption.setValue(resultSet.getInt("value"));
        paymentOption.setDescription(resultSet.getString("description"));
        paymentOption.setProjectId(resultSet.getInt("project_id"));
        return paymentOption;
    }
}
