package org.example.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDTO
{
    @JsonProperty("user_id")
    private String userId;
    private Integer amount;
    private String myHello;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public String getMyHello() { return myHello; }
    public void setMyHello(String myHello){
        this.myHello = myHello;
    }
}
