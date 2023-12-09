package com.exemple.GGIT;

import java.util.*;

public class WorldMap extends AbstractWorldMap {
    private AnimalsMapping animals;
    private Map<Vector2D, Plant> plants;
    private int dayNumber = 1;
    private static final int noOfAnimals = 12;
    private static final int noOfPlants = 10;
    private static final int animalEnergy = 20;
    private static final int plantEnergy = 5;
    private static final int dayEnergy = 3;
    private static final Random random = new Random();

    public void StartDay(){
        System.out.println("Nowy dzionek. Dzisiaj dzień:" +dayNumber++);
    }

    public WorldMap(int width, int height) {
        super(width, height);
        plants = new HashMap<>();
        for (int i = 0; i < noOfPlants; i++) addPlant();
        animals = new AnimalsMapping();
    }

    private void addPlant() {
        if (width * height == plants.size()) return;

        Vector2D position = getRandomPosition();
        while (isOccupiedByPlant(position)) position = getRandomPosition();
        plants.put(position, new Plant(position));
    }

    private boolean isOccupiedByPlant(Vector2D position) {
        return plants.containsKey(position);
    }

    private Vector2D getRandomPosition() {
        return new Vector2D(random.nextInt(width), random.nextInt(height));
    }

    @Override
    public void run() {
        MapDirection[] directions = MapDirection.values();
        animals.list.forEach(animal -> {
            animal.move(directions[random.nextInt(directions.length)]);
            animals.placeAnimalOnMap(animal);
        });
    }

    @Override
    public void eat() {
        animals.mapping.forEach((pos, animals) -> {
            if (isOccupiedByPlant(pos)) {
                animals.stream().max(Animals::compareTo).ifPresent(animal -> {
                    System.out.println(String.format("Zjadło %s roślinkę", animal.getId()));
                    animal.withChangedEnergy(animal.getEnergy() + plantEnergy);
                    plants.remove(animal.getPosition());
                    addPlant();
                });
            }
        });
    }

    @Override
    public void EndDay() {
        dayNumber++;
        int animalsCount = animals.list.size();
        animals.updateAnimals(
                animals.list.stream()
                        .map(animal -> animal.withChangedEnergy(animal.getEnergy() - dayEnergy))
                        .filter(animal -> animal.getEnergy() >= 0)
                        .map(Animals::dayOlder)
                        .toList()
        );
        System.out.printf("zwierząt było %d, pozostało %d\n", animalsCount, animals.list.size());
    }

    private class AnimalsMapping {
        List<Animals> list;
        Map<Vector2D, List<Animals>> mapping;

        AnimalsMapping(){
            list = new LinkedList<>();
            mapping = new HashMap<>();
            for (int i = 0; i < noOfAnimals; i++) addAnimal();
        }
        void addAnimal() {
            Animals animal = new Animals(getRandomPosition(), animalEnergy);
            placeAnimalOnMap(animal);
            list.add(animal);
        }

        void placeAnimalOnMap(Animals animal){
            mapping.computeIfAbsent(animal.getPosition(), pos -> new LinkedList<>()).add(animal);;
        }

        void updateAnimals(List<Animals> newAnimals){
            list = newAnimals;
            mapping.clear();
            }
        }
    }

