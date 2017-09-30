package sample;


import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class HarmonicSignal implements Signal{
    private double A;
    private double F;
    private double phase;

    public HarmonicSignal(double a, double f, double phase) {
        A = a;
        F = f;
        this.phase = phase;
    }

    @Override
    public double getSignalValue(int n){
        return A * sin((2*PI*F*n)/N + phase);
    }


    public void setA(double a) {
        A = a;
    }

    public void setF(double f) {
        F = f;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    public double getA() {
        return A;
    }

    public double getF() {
        return F;
    }

    public double getPhase() {
        return phase;
    }
}
