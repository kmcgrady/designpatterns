package com.ga.designpatterns.states;

import com.ga.designpatterns.models.SalesFunnel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DecisionState extends SalesFunnelState {
    public final static String NAME = "Decision";
    private List<String> competitors;
    private String name = "Decision";

    public DecisionState() {
        super(NAME, "");
        // Assume empty list of competitors
        this.competitors = new ArrayList<String>();
    }

    public DecisionState(String metadata) {
        super(NAME, metadata);

        String[] compArr = metadata.split(";");
        this.competitors = new ArrayList<String>(Arrays.asList(compArr));
    }

    public DecisionState(List<String> competitors) {
        this.competitors = competitors;
    }

    public void aware(SalesFunnel funnel) {
        // Don't do anything (user should already be aware)
    }

    public void interested(SalesFunnel funnel) {
        // Don't do anything (user should have acted or be deciding now)
    }

    public void deciding(SalesFunnel funnel, List<String> competitors) {
        funnel.changeState(new DecisionState(competitors));
    }

    public void acted(SalesFunnel funnel, int numYearsInContract, boolean didChooseUs) {
        funnel.changeState(new ActionState(numYearsInContract, didChooseUs));
    }
}