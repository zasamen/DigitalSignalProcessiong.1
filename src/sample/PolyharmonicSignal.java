package sample;

import java.util.Arrays;
import java.util.List;

public class PolyharmonicSignal implements Signal {

    private int N = 2048;

    private final List<Signal> signals;

    public PolyharmonicSignal(Signal... signals) {
        this.signals = Arrays.asList(signals);

    }

    @Override
    public double getSignalValue(int n) {
        return signals.stream().mapToDouble(signal -> signal.getSignalValue(n)).sum();
    }
}
