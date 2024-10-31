/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nlong
 */
public class PlanCampain {
    private Integer id;
    private Plan plan;
    private Product product;
    private Integer quantity;
    private Float estimatedeffort;

    public PlanCampain() {
    }

    public PlanCampain(Integer id, Plan plan, Product product, Integer quantity, float estimatedeffort) {
        this.id = id;
        this.plan = plan;
        this.product = product;
        this.quantity = quantity;
        this.estimatedeffort = estimatedeffort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public float getEstimatedeffort() {
        return estimatedeffort;
    }

    public void setEstimatedeffort(float estimatedeffort) {
        this.estimatedeffort = estimatedeffort;
    }
    
    
}
