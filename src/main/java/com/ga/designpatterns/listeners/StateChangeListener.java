package com.ga.designpatterns.listeners;

import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.states.SalesFunnelState;

public interface StateChangeListener {
    public void stateChange(SalesFunnel funnel, SalesFunnelState fromState, SalesFunnelState toState);
}
