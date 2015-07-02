package com.morkva.model.dao_v2.templates;

import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import com.morkva.model.dao_v2.PaymentOptionDao;
import com.morkva.model.dao_v2.mappers.PaymentOptionMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by koros on 02.07.2015.
 */
public class PaymentOptionJDBCTemplate implements PaymentOptionDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(PaymentOption entity) {
        String sql = "INSERT INTO payment_options (description, `value`, project_id) VALUE (?,?,?);";

        Object[] args = new Object[] {
                entity.getDescription(),
                entity.getValue(),
                entity.getProjectId()
        };
        jdbcTemplate.update(sql, args);
    }

    @Override
    public PaymentOption getById(Integer id) {
        String sql = "SELECT * FROM payment_options WHERE id = ?;";

        Object[] args = new Object[] {
                id
        };

        PaymentOption paymentOption = jdbcTemplate.queryForObject(sql, args, new PaymentOptionMapper());
        return paymentOption;
    }

    @Override
    public void update(PaymentOption entity) {
        String sql = "UPDATE payment_options SET description=?, `value`=?, project_id=? WHERE id = ?;";

        Object[] args = new Object[] {
                entity.getDescription(),
                entity.getValue(),
                entity.getProjectId(),
                entity.getId()
        };

        jdbcTemplate.update(sql, args);
    }

    @Override
    public void delete(PaymentOption entity) {
        String sql = "DELETE FROM payment_options WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getId());
    }

    @Override
    public List<PaymentOption> getPaymentOptionsOfProject(Project project) {
        String sql = "SELECT * FROM payment_options WHERE project_id = ?;";

        Object[] args = new Object[]{
                project.getId()
        };

        List<PaymentOption> paymentOptions = jdbcTemplate.query(sql, args, new PaymentOptionMapper());
        return paymentOptions;
    }
}
