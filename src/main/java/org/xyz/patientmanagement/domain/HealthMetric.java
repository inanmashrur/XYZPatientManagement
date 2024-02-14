package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @Positive
    @DecimalMax("250.0")
    private float height;

    @Positive
    @DecimalMax("500.0")
    private float weight;

    @Positive
    @DecimalMin("35.0")
    @DecimalMax("45.0")
    private float temperature;

    @Positive
    @Max(value = 200)
    private int bpHigh;

    @Positive
    @Max(value = 120)
    private int bpLow;

    @Positive
    @Max(value = 220)
    private int heartRate;

    @Positive
    @Max(value = 300)
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

    public int getBpHigh() {
        return bpHigh;
    }

    public void setBpHigh(int bpHIgh) {
        this.bpHigh = bpHIgh;
    }

    public int getBpLow() {
        return bpLow;
    }

    public void setBpLow(int bpLow) {
        this.bpLow = bpLow;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int hearthRate) {
        this.heartRate = hearthRate;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }
}
