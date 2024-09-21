
package com.mycompany.poly;

import java.util.ArrayList;


/**
 * 
 * @author Aaliyah M
 * Poly is a polynomial class. Given a list of coefficients, Poly will rewrite
 * the expression in the form of a polynomial, add polynomials together, and
 * evaluate the solution with a given x
 * 
 */
public class Poly {

    private int[] coeffs = new int[6];
    
    //constructor
    public Poly(int[] coefficients) {         
        coeffs = coefficients;   
    }
    
    //returns the degree of a polynomial
    public int degree(){
        int max = coeffs[0];
        int power = 0;
        
        for (int i=0; i<coeffs.length; i++) {
            if (coeffs[i] > max) {
                max = coeffs[i];
                power = i;
            }          
        }       
        return power;
    }
   
    //rewrites the polynomial into a string
    @Override
    public String toString() {
        ArrayList<String> strArray = new ArrayList<>();
        
        for (int i = coeffs.length-1; i>=0; i--) { 
            if (coeffs[i] != 0) {

                if (i==0) {
                    strArray.add(Integer.toString(coeffs[i]));                
                }           
                else {                
                    strArray.add(Integer.toString(coeffs[i]) + "x^" + Integer.toString(i));
                } 
            }
        }       
        return String.join("+", strArray);
    }
    
    //adds two polynomials together
    public Poly add(Poly a) {   
        int length = Math.max(a.coeffs.length, coeffs.length);
        int[] newCo = new int[length];
        
        for (int i=length-1;i>=0;i--) {
            newCo[i] = coeffs[i] + a.coeffs[i];
        }        
        
        Poly polynom = new Poly(newCo);
        return polynom;
    }
    
    //evaluate the solution to the polynomial given a value of x
    public double evaluate(double x) {
        int result = 0;
        
        for (int i=5;i>0;i--) {
            result = result + (int) (coeffs[i] * Math.pow(x,i));
        }
        result += coeffs[0];
        
        return result;
    }
    
    public static void main(String[] args) {
       
        int[] coefficients = {4,0,-8,0,3,2};
        Poly poly1 = new Poly(coefficients); 
        
        Poly deg = new Poly(coefficients);
        System.out.println("Power of the highest coefficient: " + deg.degree());
        
        System.out.println("Polynomial: " + poly1.toString());
              
        int[] coefficients2 = {0,-2,4,1,0,0};
        Poly poly2 = new Poly(coefficients2);
        Poly sum = poly1.add(poly2);
        System.out.println("Sum of polynomials: " + sum);
        
        double eval = poly1.evaluate(2.0);
        System.out.println(poly1 + " evaluates to: " + (int)eval);
        System.out.println();
        
        //Test cases
        int[] testCos = {5,2,4,8,1,1};
        int[] testCos2 = {7,1,5,2,1,1};
        Poly testPoly = new Poly(testCos);
        Poly testPoly2 = new Poly(testCos2);
        Poly degreeTest = new Poly(testCos);
        Poly testAddition = testPoly.add(testPoly2);
        
        System.out.println("Tests");
        System.out.println("Test 1 -- Printing polynomial: " + testPoly);
        System.out.println("Test 2 -- Degree with the largest coefficient: x^" + degreeTest.degree());
        System.out.println("Test 3 -- Adding polynomials:\n\t  " + testPoly + "\n\t+ " + testPoly2 + "\n\t ---------------------------\n\t " + testAddition);
        System.out.println("Test 4 -- Solving for Polynomial when x = 3.0: " + (int)testPoly.evaluate(3.0));
        
             
    }
}
