public class Converter {
    int lenStep;
    int countCal;

    public Converter(int lenStep, int countCol) {
        this.lenStep = lenStep;
        this.countCal = countCol;

    }

    public double getLenStep(int steps) {
        return this.lenStep * steps / 100000;
    }

    public long getСalories(int steps) {
        return this.countCal * steps / 1000;
    }


}
