import audio_filters.iir_filter.IIRFilter;
import static java.lang.Math.*;

public class FilterCreator {

    public static IIRFilter makeLowpass(int frequency, int samplerate, double qFactor) {
        double tau = 2 * PI;
        double w0 = tau * frequency / samplerate;
        double _sin = sin(w0);
        double _cos = cos(w0);
        double alpha = _sin / (2 * qFactor);

        double b0 = (1 - _cos) / 2;
        double b1 = 1 - _cos;

        double a0 = 1 + alpha;
        double a1 = -2 * _cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoefficients(new double[]{a0, a1, a2}, new double[]{b0, b1, b0});
        return filt;
    }

    public static IIRFilter makeHighpass(int frequency, int samplerate, double qFactor) {
        double tau = 2 * PI;
        double w0 = tau * frequency / samplerate;
        double _sin = sin(w0);
        double _cos = cos(w0);
        double alpha = _sin / (2 * qFactor);

        double b0 = (1 + _cos) / 2;
        double b1 = -1 - _cos;

        double a0 = 1 + alpha;
        double a1 = -2 * _cos;
        double a2 = 1 - alpha;

        IIRFilter filt = new IIRFilter(2);
        filt.setCoefficients(new double[]{a0, a1, a2}, new double[]{b0, b1, b0});
        return filt;
    }

    // Similarly, implement other methods using the same structure.

    public static void main(String[] args) {
        // Example usage:
        int frequency = 1000;
        int samplerate = 48000;
        double qFactor = 1 / sqrt(2);

        IIRFilter lowpassFilter = makeLowpass(frequency, samplerate, qFactor);
        System.out.println(lowpassFilter.toString());
    }
}
