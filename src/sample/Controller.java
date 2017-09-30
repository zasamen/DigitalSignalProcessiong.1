package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.PI;
import static java.util.stream.Collectors.toList;

public class Controller {

    @FXML
    private LineChart<Integer, Double> lineChart;
    @FXML
    private RadioButton radioButtonA;
    @FXML
    private RadioButton radioButtonB;
    @FXML
    private RadioButton radioButtonC;
    @FXML
    private ToggleButton secondTaskToggleButton;
    @FXML
    private Button startButton;
    @FXML
    private ToggleButton thirdTaskToggleButton;

    @FXML
    private void start() {
        lineChart.setCreateSymbols(false);
        startButton.setDisable(true);
        if (radioButtonA.isSelected()) {
            putPhasedHarmonicSeries();
        } else if (radioButtonB.isSelected()) {
            putFHarmonicSeries();
        } else if (radioButtonC.isSelected()) {
            putAHarmonicSeries();
        } else if (secondTaskToggleButton.isSelected()){
            putPolyharmonicSeries();
        } else if (thirdTaskToggleButton.isSelected()){
            putSpecialPolyharmonicSeries();
        }
        startButton.setDisable(false);
    }

    @FXML
    private void showSecond() {
        boolean isSelectedAndVisible = !secondTaskToggleButton.isSelected();
        toggleFirst(isSelectedAndVisible);
        thirdTaskToggleButton.setDisable(!isSelectedAndVisible);
    }

    @FXML
    private void showThird() {
        boolean isSelectedAndVisible = !thirdTaskToggleButton.isSelected();
        toggleFirst(isSelectedAndVisible);
        secondTaskToggleButton.setDisable(!isSelectedAndVisible);
    }

    private void toggleFirst(boolean isSelectedAndVisible) {
        radioButtonA.setSelected(isSelectedAndVisible);
        radioButtonB.setSelected(isSelectedAndVisible);
        radioButtonC.setSelected(isSelectedAndVisible);
        radioButtonA.setDisable(!isSelectedAndVisible);
        radioButtonB.setDisable(!isSelectedAndVisible);
        radioButtonC.setDisable(!isSelectedAndVisible);
    }


    private boolean putPhasedHarmonicSeries() {
        double a = 10;
        double f = 2;
        lineChart.getData().clear();
        @SuppressWarnings("unchecked")
        boolean b = lineChart.getData().addAll(
                getHarmonicSeries(a, f, 0),
                getHarmonicSeries(a, f, PI / 6),
                getHarmonicSeries(a, f, PI / 4),
                getHarmonicSeries(a, f, PI / 2),
                getHarmonicSeries(a, f, PI));
        return b;
    }

    private boolean putFHarmonicSeries() {
        double a = 3;
        double phase = PI / 2;
        lineChart.getData().clear();
        @SuppressWarnings("unchecked")
        boolean b = lineChart.getData().addAll(
                getHarmonicSeries(a, 5, phase),
                getHarmonicSeries(a, 4, phase),
                getHarmonicSeries(a, 2, phase),
                getHarmonicSeries(a, 6, phase),
                getHarmonicSeries(a, 3, phase));
        return b;
    }

    private boolean putAHarmonicSeries() {
        double f = 1;
        double phase = PI / 2;
        lineChart.getData().clear();
        @SuppressWarnings("unchecked")
        boolean b = lineChart.getData().addAll(
                getHarmonicSeries(2, f, phase),
                getHarmonicSeries(3, f, phase),
                getHarmonicSeries(6, f, phase),
                getHarmonicSeries(5, f, phase),
                getHarmonicSeries(1, f, phase));
        return b;
    }

    private boolean putPolyharmonicSeries() {
        lineChart.getData().clear();
        @SuppressWarnings("unchecked")
        boolean b = lineChart.getData().add(getPolyHarmonicSignalsSeries(new HarmonicSignal(1, 1, 0),
                new HarmonicSignal(1, 2, PI / 4),
                new HarmonicSignal(1, 3, PI / 6),
                new HarmonicSignal(1, 4, 2 * PI),
                new HarmonicSignal(1, 5, PI)));
        return b;
    }

    private boolean putSpecialPolyharmonicSeries() {
        lineChart.getData().clear();
        return lineChart.getData().add(getSpecialHarmonicSeries());
    }

    private Series<Integer, Double> getHarmonicSeries(double a, double f, double phase) {
        Series<Integer, Double> series = new Series<>();
        series.getData().addAll(new HarmonicSignal(a, f, phase).getSignal()
                .entrySet().stream().map(entry ->
                        new Data<>(entry.getKey(), entry.getValue()))
                .collect(toList()));
        series.setName(String.format("a=%s,f=%s,phase=%s", a, f, phase));
        return series;
    }

    private Series<Integer, Double> getPolyHarmonicSignalsSeries(Signal... signals) {
        Series<Integer, Double> series = new Series<>();
        series.getData().addAll(new PolyharmonicSignal(signals).getSignal()
                .entrySet().stream().map(entry ->
                        new Data<>(entry.getKey(), entry.getValue()))
                .collect(toList()));
//        series.setName(String.format("a=%s,f=%s,phase=%s", a, f, phase));
        return series;
    }

    private Series<Integer, Double> getSpecialHarmonicSeries() {
        Series<Integer, Double> series = new Series<>();
        series.getData().addAll(mapToDataList(
                new SpecialPolyharmonicSignal(13, 15, PI / 4, -0.000005, 0.000005, 0.000005* PI)
                        .getSignal()));
        return series;
    }

    private List<Data<Integer, Double>> mapToDataList(Map<Integer, Double> map) {
        return map.entrySet().stream().map(
                e -> new Data<>(e.getKey(), e.getValue())
        ).collect(Collectors.toList());
    }

}
