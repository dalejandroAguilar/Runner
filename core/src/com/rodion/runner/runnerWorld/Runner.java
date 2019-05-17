package com.rodion.runner.runnerWorld;

public class Runner{
    private Frame body;
    private float hVelocity0;
    private float hAcceleration;
    private float vImpulse;

    public Runner(Frame body, float hVelocity0, float hAcceleration, float vImpulse) {
        this.body = body;
        this.hVelocity0 = hVelocity0;
        this.hAcceleration = hAcceleration;
        this.vImpulse = vImpulse;
    }

    public Runner(Frame body) {
        this(body, 0f, 0f, 0f);
    }

    public Frame getBody() {
        return body;
    }

    public void setBody(Frame body) {
        this.body = body;
    }

    public float gethVelocity0() {
        return hVelocity0;
    }

    public void sethVelocity0(float hVelocity0) {
        this.hVelocity0 = hVelocity0;
    }

    public float gethAcceleration() {
        return hAcceleration;
    }

    public void sethAcceleration(float hAcceleration) {
        this.hAcceleration = hAcceleration;
    }

    public float getvImpulse() {
        return vImpulse;
    }

    public void setvImpulse(float vImpulse) {
        this.vImpulse = vImpulse;
    }


}
