/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converts;

/**
 *
 * @author enzoj
 */
public class CubicKilometer implements InterfaceConverter {
    public final String category;

    public CubicKilometer() {
        this.category = "volume";
    }
    
    @Override
    public String toString() {
        return "Cubic Hectometer (hm3)";
    }
    
    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public double toBase(double input) {
        return input *1E+9; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double convert(double input) {
       return input * 1E-9; //To change body of generated methods, choose Tools | Templates.
    }
    
}