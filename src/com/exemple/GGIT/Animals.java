package com.exemple.GGIT;

public class Animals implements Comparable<Animals>{
    private Vector2D position;

    private int id;
    private static int counter = 0;
    private int energy;
    private int age;

    public Animals(Vector2D position, int energy) {
        this.position = position;
        this.energy = energy;
        age = 1;
        id = counter++;
    }

    public int getId(){
        return id;
    }

    public Vector2D getPosition(){
        return position;
    }

    public void move (MapDirection direction){
        position = pbc(position.add(direction.getUnitVector()));
        System.out.println("Nowa pozycja: " +position);
    }

    private Vector2D pbc(Vector2D position){
        int width = Simulation.getWidth();
        int height = Simulation.getHeight();
        if (position.getX() < 0) return position.add(new Vector2D(width, 0));
        if (position.getX() >= width) return position.subtrack(new Vector2D(width, 0));
        if (position.getY() < 0) return position.add(new Vector2D(0, height));
        if (position.getY() >= height) return position.subtrack(new Vector2D(0, height));
        return position;
    }

    public int getEnergy(){
        return energy;
    }

    public int getAge(){
        return age;
    }

    public Animals withChangedEnergy(int newEnergy){
        energy = newEnergy;
        return this;
    }

    public Animals dayOlder(){
        age++;
        return this;
    }

    public int compareTo(Animals o){
        return energy == o.getEnergy() ? id - o.id : energy - o.energy;
    }
}
