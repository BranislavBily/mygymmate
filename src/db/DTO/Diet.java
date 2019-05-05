package db.DTO;

public class Diet {
    private int actualCalories;
    private int actualProtein;
    private int actualWater;
    private String date;

    public int getActualCalories() {
        return actualCalories;
    }

    public void setActualCalories(int actualCalories) {
        this.actualCalories = actualCalories;
    }

    public int getActualProtein() {
        return actualProtein;
    }

    public void setActualProtein(int actualProtein) {
        this.actualProtein = actualProtein;
    }

    public int getActualWater() {
        return actualWater;
    }

    public void setActualWater(int actualWater) {
        this.actualWater = actualWater;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "actualCalories=" + actualCalories +
                ", actualProtein=" + actualProtein +
                ", actualWater=" + actualWater +
                ", date='" + date + '\'' +
                '}';
    }
}
