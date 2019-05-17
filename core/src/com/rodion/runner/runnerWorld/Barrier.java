package com.rodion.runner.runnerWorld;

public class Barrier {
    private Frame body;
    private float hVelocity;
    private int probability;

    public Barrier(Frame body) {
        this.body = body;
        hVelocity = 0;
        probability = 1;
    }

    public Barrier(Frame body, float hVelocity) {
        this.body = body;
        this.hVelocity = hVelocity;
        probability = 1;
    }

    public Barrier(Frame body, float hVelocity, int probability) {
        this.body = body;
        this.hVelocity = hVelocity;
        this.probability = probability;
    }

    public Frame getBody() {
        return body;
    }

    public void setBody(Frame body) {
        this.body = body;
    }

    public float gethVelocity() {
        return hVelocity;
    }

    public void sethVelocity(float hVelocity) {
        this.hVelocity = hVelocity;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}