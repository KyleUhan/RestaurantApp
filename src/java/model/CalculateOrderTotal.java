/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle Uhan
 */
public class CalculateOrderTotal {
    
    private double tax;
    private String adjustedTax;

    public CalculateOrderTotal(double tax) {
        setTax(tax);
    }
    
    
    
    public String calculateTotal(List<String> eachItem){
        Double tax;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        if(eachItem == null){
            eachItem = new ArrayList<>();
        }
        Double total = 0.0;
        for(String item: eachItem){
            total = total + Double.parseDouble(item);
        }
        tax = (total * getTax());
        setAdjustedTax(nf.format(tax));
        total = total + tax;
        return nf.format(total);
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getAdjustedTax() {
        return adjustedTax;
    }

    public void setAdjustedTax(String adjustedTax) {
        this.adjustedTax = adjustedTax;
    }
    
    
    
    
 
}