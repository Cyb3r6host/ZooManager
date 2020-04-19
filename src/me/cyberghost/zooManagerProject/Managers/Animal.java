package me.cyberghost.zooManagerProject.Managers;

import java.io.Serializable;

public class Animal implements Serializable {
    private Integer password;
    private String name;
    private String animalClass;
    private Double weight;
    private Double MaxAge;

    public Animal(Integer password, String name, String animalClass, Double weight, Double maxAge) {
        this.password = password;
        this.name = name;
        this.animalClass = animalClass;
        this.weight = weight;
        MaxAge = maxAge;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(String animalClass) {
        this.animalClass = animalClass;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getMaxAge() {
        return MaxAge;
    }

    public void setMaxAge(Double maxAge) {
        MaxAge = maxAge;
    }
}
