package lab3.model;

public class Wood implements  java.io.Serializable
{
    private int id;
    private String name;
    private float density;

    public Wood(int id, String name, float density) {
        this.id = id;
        this.name = name;
        this.density = density;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDensity() {
        return density;
    }


    @Override
    public String toString() {
        return "Дерево{" +
                "id=" + id +
                ", назва='" + name + '\'' +
                ", плотність=" + density +
                '}';
    }

}
