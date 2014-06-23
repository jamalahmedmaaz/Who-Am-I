package com.app.orm;

import java.io.Serializable;

/**
 * User: Jamal
 * Date: 6/23/14
 * Time: 1:10 PM
 * <p/>
 * Pair of objects.
 *
 * @param <T1>
 * @param <T2>
 * @author Jamal
 */
public class Pair<T1, T2> implements Serializable {

    /**
     * Serialization ID.
     */
    public static final long serialVersionUID = 2201926023084766419L;
    private T1 first;
    private T2 second;

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

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    public static <T1, T2> Pair<T1, T2> newPair(T1 first, T2 second) {
        return new Pair<T1, T2>(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null) {
                return false;
            }
        } else if (!first.equals(other.first)) {
            return false;
        }
        if (second == null) {
            if (other.second != null) {
                return false;
            }
        } else if (!second.equals(other.second)) {
            return false;
        }
        return true;
    }

}