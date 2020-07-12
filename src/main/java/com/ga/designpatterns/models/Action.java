package com.ga.designpatterns.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "actions")
@Entity
public class Action {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name must not be empty")
    private String type;

    @NotNull
    @Size(min=1, message = "Action Information must not be empty")
    private String action;

    public Action() {}
    public Action(String type, String action) {
        this.type = type;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
