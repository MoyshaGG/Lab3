package lab3.model;

public class Timber extends AbstractForm {
    private float length;
    private float height;
    private float width;

    public Timber(Wood wood, float length, float height, float width) throws Exception {
        super(wood);

        if (length <0|| length > 100) {
            throw new Exception(length + " is not correct! \n"+"must be form 0 to 100");
        }
        this.length = length;

        if (height <0|| height > 100) {
            throw new Exception(length + " is not correct! \n"+"must be form 0 to 100");
        }
        this.height = height;

        if (width <0|| width > 100) {
            throw new Exception(length + " is not correct! \n"+"must be form 0 to 100");
        }

        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
    public float volume()
    {
        return length*height*width;
    }

    @Override
    public String toString() {
        return "Timber{" +"Дерево=" + wood.getName() + ", Вага=" + weight() + '}';
    }

}
