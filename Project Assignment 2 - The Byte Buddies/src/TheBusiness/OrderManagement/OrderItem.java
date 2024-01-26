/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.OrderManagement;

import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.ProductManagement.Product;

/**
 *
 * @author kal bugrara
 */
public class OrderItem {
    SolutionOffer solutionoffer;   
    double actualPrice;
    int quantity;

    public OrderItem(SolutionOffer so, double paidprice, int q) {
        solutionoffer = so;
        so.addOrderItem(this); //make sure product links back to the item
        quantity = q;
        this.actualPrice = paidprice;
    }

    public double getOrderItemTotal() {
        return actualPrice * quantity;
    }

//The following calculates what the price gain would have been if products were sold at target price
    public double getOrderItemTargetTotal() {
        return solutionoffer.getTargetPrice() * quantity;
    }

    //returns positive if seller is making higher margin than target
    //returns negative if seller is making lower margin than target
    //otherwise zero meaning neutral
    public double calculatePricePerformance() {
        return (actualPrice - solutionoffer.getTargetPrice()) * quantity;
    }

    public boolean isActualAboveTarget() {
        if (actualPrice > solutionoffer.getTargetPrice()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActualBelowTarget() {
        if (actualPrice < solutionoffer.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isActualATTarget() {
        if (actualPrice == solutionoffer.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public SolutionOffer getSolutionOffer() {
        return solutionoffer;
    }

    public double getActualPrice() {
        return actualPrice;

    }

    public void setSolutionoffer(SolutionOffer solutionoffer) {
        this.solutionoffer = solutionoffer;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
