package com.ga.designpatterns.states;

import com.ga.designpatterns.models.SalesFunnel;

import java.util.List;

public class AwarenessState extends SalesFunnelState {
    public final static String NAME = "Awareness";
    public AwarenessState() {
        super(NAME, "");
    }

    public AwarenessState(String metadata) {
        super(NAME, metadata);
    }

    public void aware(SalesFunnel funnel) {
        // Don't do anything (user should already be aware)
    }

    public void interested(SalesFunnel funnel) {
        funnel.changeState(new InterestState(InterestState.InterestedStateType.NEW_CUSTOMER));
    }

    public void deciding(SalesFunnel funnel, List<String> competitors) {
        funnel.changeState(new DecisionState(competitors));
    }

    public void acted(SalesFunnel funnel, int numYearsInContract, boolean didChooseUs) {
        funnel.changeState(new ActionState(numYearsInContract, didChooseUs));
    }
}
