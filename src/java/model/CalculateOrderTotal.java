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
 
    public final String calculateTotal(List<String> eachItem){
        Double taxTotal;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        if(eachItem == null){
            eachItem = new ArrayList<>();
        }
        Double total = 0.0;
        for(String item: eachItem){
            total = total + Double.parseDouble(item);
        }
        taxTotal = (total * getTax());
        setAdjustedTax(nf.format(taxTotal));
        total = total + taxTotal;
        return nf.format(total);
    }

    public final double getTax() {
        return tax;
    }

    public final void setTax(final double tax) {
        this.tax = tax;
    }

    public final String getAdjustedTax() {
        return adjustedTax;
    }

    public void setAdjustedTax(String adjustedTax) {
        this.adjustedTax = adjustedTax;
    }
    
    
    
    
 
}