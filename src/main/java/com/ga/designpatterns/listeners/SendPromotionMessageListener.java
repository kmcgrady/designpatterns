package com.ga.designpatterns.listeners;

import com.ga.designpatterns.dao.MessageDao;
import com.ga.designpatterns.models.Message;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.states.DecisionState;
import com.ga.designpatterns.states.SalesFunnelState;

public class SendPromotionMessageListener  implements StateChangeListener {
    private MessageDao messageDao;

    public SendPromotionMessageListener(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void stateChange(SalesFunnel funnel, SalesFunnelState fromState, SalesFunnelState toState) {
        if (toState.getName() == DecisionState.NAME) {
            String name = funnel.getUser().getName();
            messageDao.save(new Message("Promotion",  name + ", You are about to decide. Here's a Promotion!"));
        }
    }
}
