package com.morkva.services;

import com.morkva.entities.*;
import com.morkva.model.dao.PaymentBonusDao;
import com.morkva.model.dao.PaymentDao;
import com.morkva.model.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
@Service("projectService")
@Transactional
public class ProjectService {

    @Autowired
    ProjectDao projectDAO;

    @Qualifier("paymentBonusDao")
    @Autowired
    private PaymentBonusDao paymentBonusDao;

    @Qualifier("paymentDao")
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private PaymentStatusService paymentStatusService;


    public List<Project> getProjectsOfCategory(Category category) {
        return projectDAO.getProjectsOfCategory(category);
    }

    public Project getById(Integer projectId) {
        return projectDAO.getById(projectId);
    }

    public void update(Project project) {
        projectDAO.update(project);
    }

    @Transactional
    public void donate(Project project, Integer amount, User user) {

        PaymentStatus okStatus = paymentStatusService.getById(1);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(new Date());
        payment.setUser(user);
        payment.setProject(project);
        payment.setStatus(okStatus);
        paymentDao.create(payment);

        List<PaymentBonus> paymentBonusesOfProject = paymentBonusDao.getPaymentBonusesOfProject(project);
        int listSize = paymentBonusesOfProject.size();
        for (int i = 0; i < listSize-1; i++) {
            PaymentBonus paymentBonus = paymentBonusesOfProject.get(i);
            if (amount >= paymentBonus.getMinMoney() && amount > paymentBonusesOfProject.get(i+1).getMinMoney()) {
                paymentBonus.setBonusesLeft(paymentBonus.getBonusesLeft() - 1);
                paymentBonusDao.update(paymentBonus);
            }
        }
        if (amount >= paymentBonusesOfProject.get(listSize-1).getMinMoney()) {
            PaymentBonus paymentBonus = paymentBonusesOfProject.get(listSize-1);
            paymentBonus.setBonusesLeft(paymentBonus.getBonusesLeft() - 1);
            paymentBonusDao.update(paymentBonus);
        }

        project.setCurrentMoney(project.getCurrentMoney() + amount);
        projectDAO.update(project);
    }
}
