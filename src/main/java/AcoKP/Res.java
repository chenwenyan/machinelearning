package AcoKP;

/**
 * Res
 * Author： wychen
 * Date: 2017/12/13
 * Time: 10:32
 */
public class Res {

    private Integer id;
    private float weight;
    private float value;
    private float pheromone;
    private boolean selected;
    private float probability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPheromone() {
        return pheromone;
    }

    public void setPheromone(float pheromone) {
        this.pheromone = pheromone;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probobility) {
        this.probability = probobility;
    }

    public Res(float weight, float value, float pheromone ){
        this.weight = weight;
        this.value = value;
        this.pheromone = pheromone;
        this.selected = false;
        this.probability = 0;
    }
}
