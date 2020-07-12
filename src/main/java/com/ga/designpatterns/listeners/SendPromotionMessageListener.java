package com.ga.designpatterns.listeners;

import com.ga.designpatterns.dao.MessageDao;
import com.ga.designpatterns.models.Message;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.states.DecisionState;
import com.ga.designpatterns.states.SalesFunnelState;
import org.springframework.beans.factory.annotation.Autowired;

public class SendPromotionMessageListener  implements StateChangeListener {
    private MessageDao messageDao;

    public SendPromotionMessageListener(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void stateChange(SalesFunnel funnel, SalesFunnelState fromState, SalesFunnelState toState) {
        if (toState.getName() == DecisionState.NAME) {
            messageDao.save(new Message("Promotion", "You are about to decide. Here's a Promotion!"));
        }
    }
}
