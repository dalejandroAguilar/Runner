package com.rodion.runner.runnerWorld;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private Frame frame;
    private Runner runner;
    private ArrayList<Barrier> barrierWorld;
    private float vAcceleration;
    private ArrayList<Barrier> barrierBuffer;
    private boolean isOnGround;
    private boolean isAlive;
    private float hDeltaBarrier;
    private float elapsedTime;
    private float elapsedTimeFromJump;
    private float vImpulse;
    private float elapsedDistance;

    public World(Frame frame, Runner runner, ArrayList<Barrier> barrierWorld, float hDeltaBarrier, float vAcceleration) {
        this.frame = frame;
        this.runner = runner;
        this.barrierWorld = barrierWorld;
        this.hDeltaBarrier = hDeltaBarrier;
        this.vAcceleration = vAcceleration;
        barrierBuffer = new ArrayList<Barrier>();
        Barrier barrierSelect = barrierWorld.get(0);
        Frame barrierSelectBody = new Frame(new Node(3*hDeltaBarrier + frame.getPosition().getX(),
                barrierSelect.getBody().getPosition().getY()),
                barrierSelect.getBody().getDimension());
        barrierBuffer.add(new Barrier(barrierSelectBody, barrierSelect.gethVelocity()));
        updateBarrierBuffer();
        elapsedTime = 0;
        vImpulse = runner.getvImpulse();
        elapsedTimeFromJump = 0;
        isOnGround = true;
        elapsedDistance = 0;
    }

    public int randomChoice(int total) {
        Random random = new Random();
        return random.nextInt(total + 1);
    }

    private void updateRemovingBarrierBuffer() {
        for (int i = 0; i < barrierBuffer.size(); i++) {
            if (!frame.isCointaing(barrierBuffer.get(i).getBody()) && !frame.isCrashingWith(barrierBuffer.get(i).getBody())) {
                barrierBuffer.remove(i--);
            }
        }
    }

    private void updateBarrierBuffer() {

        int total = 0;
        int randomChoice = 0;
        float boundPositionFrame = frame.getLastCorner().getX();
        Gdx.app.log("bfs:", "" + (barrierBuffer.size() - 1));
        float newBarrierPosition = barrierBuffer.get(barrierBuffer.size() - 1).getBody().
                getPosition().getX() + hDeltaBarrier;

        updateRemovingBarrierBuffer();

        for (Barrier barrier : barrierWorld) {
            total = total + barrier.getProbability();
        }

        while (newBarrierPosition < boundPositionFrame) {
            randomChoice = randomChoice(total);
            //Gdx.app.log("randomChoice",""+randomChoice);
            total = 0;
            int i;
            for (i = 0; i < barrierWorld.size(); i++) {
                total = total + barrierWorld.get(i).getProbability();
                if (total >= randomChoice)
                    break;
            }

            Gdx.app.log("choice", "" + i);
            Barrier barrierSelect = barrierWorld.get(i);
            Frame barrierSelectBody = new Frame(new Node(newBarrierPosition,
                    barrierSelect.getBody().getPosition().getY()),
                    barrierSelect.getBody().getDimension());
            barrierBuffer.add(new Barrier(barrierSelectBody, barrierSelect.gethVelocity()));

            newBarrierPosition = barrierBuffer.get(barrierBuffer.size() - 1).getBody().
                    getPosition().getX() + hDeltaBarrier;
        }
    }

    public void step(float deltaT) {
        elapsedTime += deltaT;
        elapsedTimeFromJump += deltaT;

        float hAcceleration = -runner.gethAcceleration();
        float hVelocity0 = -runner.gethVelocity0();

        elapsedDistance += - hVelocity0 * deltaT - hAcceleration * deltaT * elapsedTime;

        for (Barrier barrier : barrierBuffer) {
            float x0 = barrier.getBody().getPosition().getX();
            float barrierHVelocity = -barrier.gethVelocity();
            barrier.getBody().getPosition().setX(x0 + (barrierHVelocity + hVelocity0) * deltaT +
                    hAcceleration * deltaT * elapsedTime);
        }
        updateBarrierBuffer();


        if (!isOnGround) {
            vImpulse = runner.getvImpulse();
            float y0 = runner.getBody().getPosition().getY();
            runner.getBody().getPosition().setY(
                    y0 + vImpulse * deltaT - vAcceleration * deltaT * elapsedTimeFromJump
            );
            if (runner.getBody().getPosition().getY() <= frame.getPosition().getY()) {
                isOnGround = true;
            }
        }


        if (isOnGround) {
            runner.getBody().getPosition().setY(frame.getPosition().getY());
            elapsedTimeFromJump = 0;
        }

    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public ArrayList<Barrier> getBarrierWorld() {
        return barrierWorld;
    }

    public void setBarrierWorld(ArrayList<Barrier> barrierWorld) {
        this.barrierWorld = barrierWorld;
    }

    public float getvAcceleration() {
        return vAcceleration;
    }

    public void setvAcceleration(float vAcceleration) {
        this.vAcceleration = vAcceleration;
    }

    public ArrayList<Barrier> getBarrierBuffer() {
        return barrierBuffer;
    }

    public void setBarrierBuffer(ArrayList<Barrier> barrierBuffer) {
        this.barrierBuffer = barrierBuffer;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public float gethDeltaBarrier() {
        return hDeltaBarrier;
    }

    public void sethDeltaBarrier(float hDeltaBarrier) {
        this.hDeltaBarrier = hDeltaBarrier;
    }

    public boolean isCollision() {
        for (Barrier barrier : barrierBuffer) {
            if (runner.getBody().isCrashingWith(barrier.getBody()))
                return true;
        }
        return false;
    }

    public void unjump() {
        vImpulse = - runner.getvImpulse();
        //vImpulse = 0;
        Gdx.app.log("jump", "yes");
    }

    public void jump() {
        isOnGround = false;
        vImpulse = runner.getvImpulse();
        Gdx.app.log("jump", "yes");
    }

    public float getElapsedDistance(){
        return elapsedDistance;
    }
}
