package com.exemple.GGIT;

import java.util.Objects;

public class Vector2D {
    private int x, y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Vector2D add(Vector2D other){
        return new Vector2D(x + other.x, y + other.y);
    }
    public  Vector2D opposite(){
        return new Vector2D(-x, -y);
    }
    public  Vector2D subtrack(Vector2D other){
        return add(other.opposite());
    }

    @Override

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Vector2D v)) return false;
        return x == v.x && y == v.y;
    }

    @Override

    public String toString(){
        return String.format("(%s, %s)", x, y);
    }

    @Override

    public int hashCode(){
        return Objects.hash(x, y);
    }

}