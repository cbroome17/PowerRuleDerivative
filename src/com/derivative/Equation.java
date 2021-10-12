/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.derivative;

/**
 *
 * @author Tripl
 */
public class Equation {
    private int coefficient;
    private int varExponent;
    
    public Equation() {
        
    }
    
    public Equation(String input) {
        findCoefficient(input);
        findExponent(input);
    }
    
    public int findCoefficient(String x) {
        //varpos is to know how to index the for loop, in this case it is needed in order to tell the loop when to stop
        int varpos = x.indexOf("x"); //loop will stop once it has reached the index of x
        varpos = ((varpos == -1)? x.length() : x.indexOf("x")); //if x is not in the input then there is not variable with an exponent therefoe, it can tell the loop to stop at x.length
        String tempVar = ""; //in order to concatenate the coefficient in case the coefficient is a multi-digit number
        
        if (varpos == 0) { //if 0 then it means there is no coefficient entered which means the coefficient is 1
            coefficient = 1;
            return coefficient; 
        }
        
        
        for (int i = 0; i < varpos; i++) {
            tempVar += x.charAt(i);
        }
        //wrap in try/catch in order to prevent program crash in the event a user enters a non integer.
        coefficient = Integer.parseInt(tempVar); //parse the string up until the value of varpos is reached
        return coefficient;
    }  //end findCoefficient
    
    
    public int findExponent(String x) {
        int varpos = x.indexOf("^"); //Will use this "^" to mark an exponent, is not needed for exponents though
        
        if (x.indexOf("x") == -1) { //if there is no x then there is no exponent
            varExponent = 0;
            return 0;
        }
        
        varpos = ((varpos < 1) ?  x.indexOf("x") : x.indexOf("^")); //if ^ is absent then it will use the integer after the x as the exponent by default
        
        
        if (varpos + 1 == x.length()) { //if there is a variable and nothing entered after the variable then exponent will == 1
            varExponent = 1;
            return varExponent;
        }
        
        String tempVar = "";
        for (int i = varpos + 1; i < x.length(); i++) {
            tempVar += x.charAt(i);
        }
        
        varExponent = Integer.parseInt(tempVar);
        return varExponent;
    } // end findExponent
    
    @Override
    public String toString() { 
        
        String calculatedDerivative = null;
        if ((varExponent - 1) == 0) { //if exponent is equal to 1 
            calculatedDerivative = (coefficient + "");
        }  
        else if (varExponent == 0) { //if exponent is equal to 0, there is no x, if no x then it is a constant therefore the derivative of a constant is 0
            calculatedDerivative = "0";
        }
        else if ((varExponent - 1) == 1) { //in order to prevent an output of a power of 1; example: 9x^1
            calculatedDerivative = (coefficient * varExponent) + "x"; 
        }
        else { //Normal derivative expression; example: 3x^4 --> 12x^3
            calculatedDerivative = (coefficient * varExponent) + "x^" + (varExponent - 1);
        }
        return calculatedDerivative;
       
    }
    
    
} //end equation class