package com.rodion.runner.runnerWorld;

public class Node {
    private float x;
    private float y;
    private float z;

    public Node(float x, float y) {
        setPosition(x, y);
    }

    public Node(float x, float y,float z) {
        setPosition(x, y,z);
    }

    public Node() {
        this(0, 0);
        z=0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
        setZ(0);
    }

    public void setPosition(float x, float y, float z) {
        setPosition(x,y);
        setZ(z);
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setPosition(Node node) {
        setX(node.getX());
        setY(node.getY());
        setZ(node.getZ());
    }


}
