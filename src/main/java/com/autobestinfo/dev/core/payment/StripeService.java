package com.autobestinfo.dev.core.payment;
import com.autobestinfo.dev.user.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


//https://github.com/stripe/stripe-java
//https://stripe.com/docs/api/java#update_customer
@Service
public class StripeService {

    @Value("${STRIPE_SECRET_KEY}") //system properties can also be @Value("XXXXX-xxxx")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    public Customer createCustomer(Token token, User user) throws StripeException{
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("description", "Customer for " + user.getUsername());
        customerParams.put("email", token.getEmail());
        customerParams.put("source", token.getId());
        // ^ obtained with Stripe.js
        return Customer.create(customerParams);
    }


    public Customer retrieveCustomer(String customerId) throws StripeException{
        return Customer.retrieve(customerId);
    }


    public Customer udpateCustomer(String customerId, Token token) throws StripeException{
        Customer cu = Customer.retrieve(customerId);
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("email", token.getEmail());
        updateParams.put("source", token.getId());
        return cu.update(updateParams);
    }


    public boolean deleteCustomer(String customerId) throws StripeException{
        Customer cu = Customer.retrieve(customerId);
        cu.delete();
        return true;
    }


    public CustomerCollection listCustomers(String limitNum) throws StripeException{
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("limit", limitNum);
        return Customer.list(customerParams);
    }


    public Subscription createSubscription(Customer customer, String planId, String discount)throws StripeException{
        Map<String, Object> item = new HashMap<>();
        item.put("plan", planId);
        Map<String, Object> items = new HashMap<>();
        items.put("0", item);
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customer.getId());
        params.put("items", items);
        params.put("tax_percent", 13);//HST 13%

        switch (discount) {
            case "0.1":
                params.put("trial_period_days", 180);
                break;
            case "0.9":
                params.put("trial_period_days", 30);
                params.put("coupon", "10%OFF");
                break;
            case "0.2":
                // test without trail
                break;
            default:
                params.put("trial_period_days", 30);
                break;
        }
        return Subscription.create(params);
    }


    public Subscription retrieveSubscription(String subscriptionId) throws StripeException{
        return Subscription.retrieve(subscriptionId);
    }

    public Subscription updateSubscriptionPlan(String customerId, String subscriptionId, String planId, String discount)throws StripeException{
        Subscription sub = Subscription.retrieve(subscriptionId);
        Map<String, Object> item = new HashMap<>();
        item.put("plan", planId);
        Map<String, Object> items = new HashMap<>();
        items.put("0", item);
        Map<String, Object> params = new HashMap<>();
        params.put("items", items);
        params.put("cancel_at_period_end", false);
        switch (discount) {
            case "0.1":
                break;
            case "0.9":
                params.put("coupon", "10%OFF");
                break;
            case "0.2":
                // test without trail
                break;
            default:
                break;
        }
        sub.update(params);

        //charge the padding invoice: pro
        Map<String, Object> invoiceParams = new HashMap<>();
        invoiceParams.put("customer", customerId);
        Invoice.create(invoiceParams);

        return Subscription.retrieve(subscriptionId);
    }


    public Subscription updateSubscriptionTrial(String subscriptionId, int trialEnd)throws StripeException{
        Subscription sub = Subscription.retrieve(subscriptionId);
        Map<String, Object> params = new HashMap<>();
        if (trialEnd == 0) {
            params.put("trial_end", "now");
        }
        else {
            params.put("trial_end", trialEnd);
        }
        return sub.update(params);
    }


    public boolean CancelSubscription(String subscriptionId) throws StripeException {
        Subscription sub = Subscription.retrieve(subscriptionId);
        Map<String, Object> params = new HashMap<>();
        params.put("at_period_end", true);
        sub.cancel(params);
        return true;
    }

}
