package com.app.orm;

import java.io.Serializable;


/**
 * User: Jamal
 * Date: 6/23/14
 * Time: 6:41 PM
 */


public class Triplet<T1, T2, T3> implements Serializable {
    private T1 first;
    private T2 second;
    private T3 third;

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public T3 getThird() {
        return third;
    }

    public void setThird(T3 third) {
        this.third = third;
    }

    public Triplet(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Triplet() {
    }

    public static <T1, T2, T3> Triplet<T1, T2, T3> newTriplet(T1 first, T2 second, T3 third) {
        return new Triplet<T1, T2, T3>(first, second, third);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + "," + third + ")";
    }
}
