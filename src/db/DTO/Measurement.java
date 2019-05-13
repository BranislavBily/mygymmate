package db.DTO;

public class Measurement {

    private double leftArm;
    private double rightArm;
    private double leftForeArm;
    private double rightForeArm;
    private double shoulders;
    private double waist;
    private double chest;
    private double leftThigh;
    private double rightThigh;
    private double leftCalf;
    private double rightCalf;

    public double getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(double leftArm) {
        this.leftArm = leftArm;
    }

    public double getRightArm() {
        return rightArm;
    }

    public void setRightArm(double rightArm) {
        this.rightArm = rightArm;
    }

    public double getLeftForeArm() {
        return leftForeArm;
    }

    public void setLeftForeArm(double leftForeArm) {
        this.leftForeArm = leftForeArm;
    }

    public double getRightForeArm() {
        return rightForeArm;
    }

    public void setRightForeArm(double rightForeArm) {
        this.rightForeArm = rightForeArm;
    }

    public double getShoulders() {
        return shoulders;
    }

    public void setShoulders(double shoulders) {
        this.shoulders = shoulders;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getLeftThigh() {
        return leftThigh;
    }

    public void setLeftThigh(double leftThigh) {
        this.leftThigh = leftThigh;
    }

    public double getRightThigh() {
        return rightThigh;
    }

    public void setRightThigh(double rightThigh) {
        this.rightThigh = rightThigh;
    }

    public double getLeftCalf() {
        return leftCalf;
    }

    public void setLeftCalf(double leftCalf) {
        this.leftCalf = leftCalf;
    }

    public double getRightCalf() {
        return rightCalf;
    }

    public void setRightCalf(double rightCalf) {
        this.rightCalf = rightCalf;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "leftArm=" + leftArm +
                ", rightArm=" + rightArm +
                ", leftForeArm=" + leftForeArm +
                ", rightForeArm=" + rightForeArm +
                ", shoulders=" + shoulders +
                ", waist=" + waist +
                ", chest=" + chest +
                ", leftThigh=" + leftThigh +
                ", rightThigh=" + rightThigh +
                ", leftCalf=" + leftCalf +
                ", rightCalf=" + rightCalf +
                '}';
    }
}