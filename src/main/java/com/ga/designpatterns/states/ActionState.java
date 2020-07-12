package com.ga.designpatterns.states;

import com.ga.designpatterns.models.SalesFunnel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class ActionState extends SalesFunnelState {
    public final static String NAME = "Action";
    private Date dateActed;
    private int yearsInContract;
    private boolean didChooseUs;

    public ActionState(String metadata) {
        super(NAME, metadata);

        String[] dataArr = metadata.split(";");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM HH:mm:ss z yyyy");
        try {
            this.yearsInContract = Integer.parseInt(dataArr[1]);
            this.didChooseUs = Boolean.parseBoolean(dataArr[2]);
            this.dateActed = sdf.parse(dataArr[0]);
        } catch (ParseException e) {
            System.err.println(e);
            this.dateActed = new Date();
        }
    }

    public ActionState(int yearsInContract, boolean didChooseUs) {
        super(NAME, "");

        this.dateActed = new Date(); // Use Today's Date
        this.yearsInContract = yearsInContract;
        this.didChooseUs = didChooseUs;

        String[] dataItems = {
                this.dateActed.toString(),
                Integer.toString(this.yearsInContract),
                Boolean.toString(this.didChooseUs)
        };

        this.setMetadata(String.join(";", dataItems));
    }

    public void aware(SalesFunnel funnel) {
        // Don't do anything (user should already be aware)
    }

    public void interested(SalesFunnel funnel) {
        InterestState.InterestedStateType interestType;

        if (this.didChooseUs) {
            interestType = InterestState.InterestedStateType.RENEW_CUSTOMER;
        } else {
            interestType = InterestState.InterestedStateType.WIN_CUSTOMER;
        }

        funnel.changeState(new InterestState(interestType));
    }

    public void deciding(SalesFunnel funnel, List<String> competitors) {
        funnel.changeState(new DecisionState(competitors));
    }

    public void acted(SalesFunnel funnel, int numYearsInContract, boolean didChooseUs) {
        funnel.changeState(new ActionState(numYearsInContract, didChooseUs));
    }
}
