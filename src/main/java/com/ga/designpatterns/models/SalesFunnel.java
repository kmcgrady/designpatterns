package com.ga.designpatterns.models;

import com.ga.designpatterns.states.AwarenessState;
import com.ga.designpatterns.states.SalesFunnelState;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "salesFunnel")
    private User user;

    public SalesFunnel() {
        this.setState(new AwarenessState());
    }

    public SalesFunnel(String stateName, String stateMetadata) {
        this.setState(SalesFunnelState.stateFromName(stateName, stateMetadata));
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

    public SalesFunnelState getState() { return this.state; }
    public void setState(SalesFunnelState state) {
        this.state = state;
        this.stateName = state.getName();
        this.stateMetadata = state.getMetadata();
    }

    public void changeState(SalesFunnelState state) {
        SalesFunnelState previousState = this.state;
        this.setState(state);
    }

    public void aware() {
        this.state.aware(this);
    }

    public void interested() {
        this.state.interested(this);
    }

    public void deciding(List<String> competitors) {
        this.state.deciding(this, competitors);
    }

    public void acted(int numYearsInContract, boolean didChooseUs) {
        this.state.acted(this, numYearsInContract, didChooseUs);
    }
}
