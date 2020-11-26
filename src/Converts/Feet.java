/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converts;

/**
 *
 * @author Guiskater
 */
public class Feet implements InterfaceConverter {
    private final String category;
    
    public Feet() {
        this.category = "lenght";
    }
    
    @Override
    public String toString() {
        return "Feet (ft)";
    }
    
    @Override
    public String getCategory() {
        return this.category;
    }
    
    @Override
    public double toBase(double input) {
        return input * 0.3048;
    }
    
    public double convert(double input) {
        return input * 3.28084;
    }
}