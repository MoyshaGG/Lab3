package lab3.model;

public abstract class AbstractForm implements IWeight , java.io.Serializable {
    protected Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public Wood getWood() {
        return wood;
    }

    public abstract float volume();

    @Override
    public float weight() {
        return wood.getDensity() * volume();
    }
}
