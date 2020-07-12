package com.ga.designpatterns.states;

import com.ga.designpatterns.models.SalesFunnel;

import java.util.List;

public abstract class SalesFunnelState {
    public final static String NAME = "<UNDEFINED>";
    protected String name;
    private String metadata;

    public SalesFunnelState() {
        this.name = NAME;
        this.metadata = "";
    }

    public SalesFunnelState(String name, String metadata) {
        this.name = name;
        this.metadata = metadata;
    }

    public SalesFunnelState(String metadata) {
        this.name = NAME;
        this.metadata = metadata;
    }

    public static SalesFunnelState stateFromName(String name, String metadata) {
        if (name.equals(ActionState.NAME)) {
            return new ActionState(metadata);
        } else if (name.equals(InterestState.NAME)) {
            return new InterestState(metadata);
        } else if (name.equals(DecisionState.NAME)) {
            return new DecisionState(metadata);
        }

        return new AwarenessState(metadata);
    }

    abstract public void aware(SalesFunnel funnel);
    abstract public void interested(SalesFunnel funnel);
    abstract public void deciding(SalesFunnel funnel, List<String> competitors);
    abstract public void acted(SalesFunnel funnel, int numYearsInContract, boolean didChooseUs);

    public String getName() {
        return this.name;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
