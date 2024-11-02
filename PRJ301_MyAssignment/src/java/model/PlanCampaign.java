/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nlong
 */
public class PlanCampaign {
    private Integer camid;
    private Plan plan;
    private Product product;
    private Integer quantity;
    private Float estimatedeffort;

    public PlanCampaign() {
    }

    public PlanCampaign(Integer camid, Plan plan, Product product, Integer quantity, Float estimatedeffort) {
        this.camid = camid;
        this.plan = plan;
        this.product = product;
        this.quantity = quantity;
        this.estimatedeffort = estimatedeffort;
    }

    public Integer getCamid() {
        return camid;
    }

    public void setCamid(Integer camid) {
        this.camid = camid;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getEstimatedeffort() {
        return estimatedeffort;
    }

    public void setEstimatedeffort(Float estimatedeffort) {
        this.estimatedeffort = estimatedeffort;
    }

    
    
}
