package behaviors;

public class CanBeShipped implements Shippable {
    private final String name;
    private final double weight;

    public CanBeShipped(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getWeight() {
        return weight;
    }
}
