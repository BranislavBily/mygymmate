package db.DTO;

public class Weight {

    private double weight;
    private String date;


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return
                "Weight= " + weight +"kg"+ "                Last Updated :" + date;
    }
}
