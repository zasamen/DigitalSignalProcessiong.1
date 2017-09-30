package sample;

public class SpecialPolyharmonicSignal extends HarmonicSignal {

    private double aFactor;
    private double fFactor;
    private double phaseFactor;
    private double aBase;
    private double fBase;
    private double phaseBase;

    public SpecialPolyharmonicSignal(double aFactor, double fFactor, double phaseFactor, double aBase, double fBase, double phaseBase) {
        super(aBase, fBase, phaseBase);
        this.aFactor = aFactor;
        this.fFactor = fFactor;
        this.phaseFactor = phaseFactor;
        this.aBase = aBase;
        this.fBase = fBase;
        this.phaseBase = phaseBase;
    }

    @Override
    public double getSignalValue(int n) {
        setA(aBase + n * aFactor);
        setF(fBase + n * fFactor);
        setPhase(phaseBase + n * phaseFactor);
        return super.getSignalValue(n);
    }

}
