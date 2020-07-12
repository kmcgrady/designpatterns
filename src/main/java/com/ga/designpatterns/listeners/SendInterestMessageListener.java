package com.ga.designpatterns.listeners;

import com.ga.designpatterns.dao.MessageDao;
import com.ga.designpatterns.models.Message;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.states.InterestState;
import com.ga.designpatterns.states.SalesFunnelState;

public class SendInterestMessageListener implements StateChangeListener {
    private MessageDao messageDao;

    public SendInterestMessageListener(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void stateChange(SalesFunnel funnel, SalesFunnelState fromState, SalesFunnelState toState) {
        if (toState.getName() == InterestState.NAME) {
            String name = funnel.getUser().getName();
            InterestState interestState = (InterestState) toState;
            if (interestState.getInterestType() == InterestState.InterestedStateType.NEW_CUSTOMER) {
                messageDao.save(new Message("Interest", "Thank you for your interest " + name + "! Here's some more information"));
            } else if (interestState.getInterestType() == InterestState.InterestedStateType.RENEW_CUSTOMER) {
                messageDao.save(new Message("Interest", "Thank you for using us"  + name + "! Would you like to renew?"));
            } else if (interestState.getInterestType() == InterestState.InterestedStateType.WIN_CUSTOMER) {
                messageDao.save(new Message("Interest", "We didn't get the sale "  + name +  " :(. Can we win you over?"));
            }
        }
    }
}
