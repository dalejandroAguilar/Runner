package com.rodion.runner.runnerWorld;

import com.badlogic.gdx.Gdx;

public class Frame {
    private Node position;
    private Node dimension;

    public Frame(Node position, Node dimension) {
        this.position = position;
        this.dimension = dimension;
    }

    public Frame() {
        this.position = new Node();
        this.dimension = new Node();
    }

    public Node getPosition() {
        return position;
    }

    public void setPosition(Node position) {
        this.position = position;
    }

    public Node getDimension() {
        return dimension;
    }

    public void setDimension(Node dimension) {
        this.dimension = dimension;
    }

    public Node getFirstCorner() {
        return position;
    }

    public Node getLastCorner() {
        Node node = new Node(position.getX() + dimension.getX() - 1, position.getY() + dimension.getY() - 1);
        return node;
    }

    public float getArea() {
        return dimension.getX() * dimension.getY();
    }

    public boolean isCrashingWith(Frame frame) {
        float w1 = dimension.getX();
        float h1 = dimension.getY();
        float w2 = frame.dimension.getX();
        float h2 = frame.dimension.getY();
        float x2 = frame.position.getX() - position.getX();
        float y2 = frame.position.getY() - position.getY();
        float xIntersection;
        float yIntersection;
        //to origen

        if (x2 < 0)
            x2 = -x2 + w1 - w2;
        if (y2 < 0)
            y2 = -y2 + h1 - h2;

        xIntersection = x2 - w1;
        yIntersection = y2 - h1;

        if (xIntersection <= 0.f && yIntersection <= 0.f)
            return true;
        return false;
    }

    public boolean isCointaing(Frame frame) {
        float w1 = dimension.getX();
        float h1 = dimension.getY();
        float w2 = frame.dimension.getX();
        float h2 = frame.dimension.getY();
        float x2 = frame.position.getX() - position.getX();
        float y2 = frame.position.getY() - position.getY();

        if (x2 < 0 || x2 > w1) {
            return false;
        }
        if (y2 < 0 || y2 > h1) {
            return false;
        }
        if (x2 + w2 < 0 || x2 + w2 > w1) {
            return false;
        }
        if (y2 + h2 < 0 || y2 + h2 > h1) {
            return false;
        }
        return true;
    }


}
