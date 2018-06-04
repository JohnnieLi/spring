package com.autobestinfo.dev.membership;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "memberships")
public class Membership {
    @Id
    private String _id;

    private Number type;  //0: self pay  1: license
    // license: {type: Schema.Types.ObjectId, ref:'License'},
    private String customerId;
    private String subscriptionId;
    private String billingEmail;
    private String period; //Monthly, Yearly
    private Date period_start;
    private Date trial_end;
    private Date nextBillingDate;
    private double creditAmount;
    private String creditCardLast4;
    private String creditType;
    private String planId;
    private String planTitle;
    private String username;
    private String status; //active, unpaid, cancelled, post-due (stripe subscription status)
    private Boolean specialPromo;  // default = false, if is true, then belong to Free account without payment information

    public Number getType() {
        return type;
    }

    public void setType(Number type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getPeriod_start() {
        return period_start;
    }

    public void setPeriod_start(Date period_start) {
        this.period_start = period_start;
    }

    public Date getTrial_end() {
        return trial_end;
    }

    public void setTrial_end(Date trial_end) {
        this.trial_end = trial_end;
    }

    public Date getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(Date nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getCreditCardLast4() {
        return creditCardLast4;
    }

    public void setCreditCardLast4(String creditCardLast4) {
        this.creditCardLast4 = creditCardLast4;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSpecialPromo() {
        return specialPromo;
    }

    public void setSpecialPromo(Boolean specialPromo) {
        this.specialPromo = specialPromo;
    }
}
