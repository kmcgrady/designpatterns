package com.ga.designpatterns.services;

import com.ga.designpatterns.dao.MessageDao;
import com.ga.designpatterns.dao.SalesFunnelDao;
import com.ga.designpatterns.listeners.SendInterestMessageListener;
import com.ga.designpatterns.listeners.SendPromotionMessageListener;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesFunnelService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private SalesFunnelDao salesFunnelDao;

    public SalesFunnel createSalesFunnel() {
        SalesFunnel salesFunnel = new SalesFunnel();
        salesFunnel.addStateChangeListener(new SendPromotionMessageListener(this.messageDao));
        salesFunnel.addStateChangeListener(new SendInterestMessageListener(this.messageDao));

        return this.saveSalesFunnel(salesFunnel);
    }

    public SalesFunnel getSalesFunnelFromUser(User user) {
        SalesFunnel salesFunnel = user.getSalesFunnel();
        salesFunnel.addStateChangeListener(new SendPromotionMessageListener(this.messageDao));
        salesFunnel.addStateChangeListener(new SendInterestMessageListener(this.messageDao));

        return salesFunnel;
    }

    public SalesFunnel saveSalesFunnel(SalesFunnel salesFunnel) {
        return this.salesFunnelDao.save(salesFunnel);
    }
}
