package com.ga.designpatterns.states;

import com.ga.designpatterns.models.SalesFunnel;

import java.util.List;

public class InterestState extends SalesFunnelState {
    public final static String NAME = "Interest";
    public static enum InterestedStateType {
        NEW_CUSTOMER,       // New Customer in System
        RENEW_CUSTOMER,     // Customer with Contract
        WIN_CUSTOMER        // Customer in System who did not go with Contract
    }

    private InterestedStateType interestType;

    public InterestState(String metadata) {
        super(NAME, metadata);
    }

    public InterestState(InterestedStateType interestType) {
        super(NAME, interestType.name());
        this.interestType = interestType;
    }

    public void aware(SalesFunnel funnel) {
        // Don't do anything (user should already be aware)
    }

    public void interested(SalesFunnel funnel) {
        // Don't do anything (user should already be in interest)
    }

    public void deciding(SalesFunnel funnel, List<String> competitors) {
        funnel.changeState(new DecisionState(competitors));
    }

    public void acted(SalesFunnel funnel, int numYearsInContract, boolean didChooseUs) {
        funnel.changeState(new ActionState(numYearsInContract, didChooseUs));
    }
}
