package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "health_metric")
public class HealthMetric extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "healthMetricSeq")
    @SequenceGenerator(name = "healthMetricSeq", sequenceName = "health_metric_seq")
    private long id;

    private float height;

    private float weight;

    private float temperature;

    private int bpHIgh;

    private int bpLow;

    private int hearthRate;

    private int sugarLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getBpHIgh() {
        return bpHIgh;
    }

    public void setBpHIgh(int bpHIgh) {
        this.bpHIgh = bpHIgh;
    }

    public int getBpLow() {
        return bpLow;
    }

    public void setBpLow(int bpLow) {
        this.bpLow = bpLow;
    }

    public int getHearthRate() {
        return hearthRate;
    }

    public void setHearthRate(int hearthRate) {
        this.hearthRate = hearthRate;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }
}
