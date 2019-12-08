/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author HP
 */
public class Point implements Comparable<Point> {
    private double x;
    private double y;
    private static final double TOL = Math.pow(10, -14); // seuil de tolerance

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point that) {
        if (negligeable(this.x, that.x)) {
            return 0;
        }
        double tmp = this.x - that.x;
            return tmp < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return String.valueOf(this.x + " " + this.y);
    }
    
    public static boolean negligeable(double x1, double x2) {
        if (Math.abs(x1) > TOL && Math.abs(x2) > TOL) {
            return Math.abs((x1 - x2) / x2) < TOL;
        }
        return Math.abs(x1) < TOL && Math.abs(x2) < TOL;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double getTOL() {
        return TOL;
    } 
}
