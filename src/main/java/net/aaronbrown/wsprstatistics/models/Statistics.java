package net.aaronbrown.wsprstatistics.models;

/**
 * Created by aaron on 1/04/17.
 */
public class Statistics {

    private Double average;
    private Double min;
    private Double max;
    private Double count;
    private Double varience;


    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Double getVarience() {
        return varience;
    }

    public void setVarience(Double varience) {
        this.varience = varience;
    }
}
