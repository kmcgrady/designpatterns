package com.ga.designpatterns.models;

import com.ga.designpatterns.listeners.SendInterestMessageListener;
import com.ga.designpatterns.listeners.SendPromotionMessageListener;
import com.ga.designpatterns.listeners.StateChangeListener;
import com.ga.designpatterns.states.AwarenessState;
import com.ga.designpatterns.states.SalesFunnelState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sales_funnels")
@Entity
public class SalesFunnel {
    @Id
    @GeneratedValue
    private int id;

    private String stateName;

    private String stateMetadata;

    @Transient
    private SalesFunnelState state;

    @Transient
    private List<StateChangeListener> listeners;

    @OneToOne(mappedBy = "salesFunnel")
    private User user;

    public SalesFunnel() {
        this.setState(new AwarenessState());
        this.initListeners();
    }

    public SalesFunnel(String stateName, String stateMetadata) {
        this.setState(SalesFunnelState.stateFromName(stateName, stateMetadata));
        this.initListeners();
    }

    private void initListeners() {
        this.listeners = new ArrayList<StateChangeListener>();
    }

    public int getId() {
        return id;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateMetadata() {
        return stateMetadata;
    }

    public User getUser() {
        return user;
    }

    public SalesFunnelState getState() {
        if (this.state.getName() != this.stateName) {
            this.state = SalesFunnelState.stateFromName(this.stateName, this.stateMetadata);
        }

        return this.state;
    }
    public void setState(SalesFunnelState state) {
        this.state = state;
        this.stateName = state.getName();
        this.stateMetadata = state.getMetadata();
    }

    public void changeState(SalesFunnelState state) {
        SalesFunnelState previousState = this.state;
        this.setState(state);

        this.notifyListeners(previousState, this.state);
    }

    public void aware() {
        this.getState().aware(this);
    }

    // TODO State Pattern
    public void interested() {
        this.getState().interested(this);
    }

    public void deciding(List<String> competitors) {
        this.getState().deciding(this, competitors);
    }

    public void acted(int numYearsInContract, boolean didChooseUs) {
        this.getState().acted(this, numYearsInContract, didChooseUs);
    }

    // TODO Observer Pattern implement the following methods
    private void notifyListeners(SalesFunnelState previousState, SalesFunnelState nextState) {
        for (StateChangeListener listener : this.listeners) {
            listener.stateChange(this, previousState, nextState);
        }
    }

    public void addStateChangeListener(StateChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeStateChangeListener(StateChangeListener listener) {
        this.listeners.remove(listener);
    }
}
