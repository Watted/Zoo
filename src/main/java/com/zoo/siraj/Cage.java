package main.java.com.zoo.siraj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cage implements Serializable {
    private static int idOfCage = 0;
    private String id;
    private int size;
    private List<Animal> contentAnimal;

    public Cage(int size) {
        this.id = String.valueOf(++idOfCage);
        this.size = size;
        this.contentAnimal = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Animal> getContentAnimal() {
        return contentAnimal;
    }

    public void setContentAnimal(List<Animal> contentAnimal) {
        this.contentAnimal = contentAnimal;
    }

    public void addAnimal(Animal animal){
        this.contentAnimal.add(animal);
    }

    public boolean removeAnimal(Animal animal){
        return this.contentAnimal.remove(animal);
    }

    @Override
    public String toString() {
        return "\nCage{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", contentAnimal=" + contentAnimal +
                '}';
    }

    public String getId() {
        return this.id;
    }

    public boolean contentThisAnimal(Animal animalById) {
        return this.contentAnimal.contains(animalById);
    }
}
