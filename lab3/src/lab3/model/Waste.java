package lab3.model;

public class  Waste implements IWeight {

    float weight;

    public Waste(float weight) throws Exception {


        if (weight < 0|| weight >1000) {
            throw new Exception(weight + " is not correct! \n"+"must be form 0.20 to 0.100");
        }
        this.weight = weight;
    }

    @Override

    public String toString() {
        return "Waste{" +
                "weight=" + weight +
                '}';
    }
  public float weight()
  {
      return weight;
  }

}
