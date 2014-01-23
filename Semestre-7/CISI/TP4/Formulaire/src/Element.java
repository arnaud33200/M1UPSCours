/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arnaud
 */
public class Element {
    
    private int index;

    public Element(String x, String y, boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public boolean isZ() {
        return z;
    }

    public void setZ(boolean z) {
        this.z = z;
    }
    private String x;
    private String y;
    private boolean z;

    public Element(int index, String x, String y, boolean z) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Element() {
    }
    
 
    
    
}
