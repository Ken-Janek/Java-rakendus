// Raamat pärib Teaviku väljad ja meetodid.
public class Raamat extends Teavik {
    public Raamat(int id, String pealkiri) {
        super(id, pealkiri);
    }

    @Override
    public int getLaenutusPaevad() {
        return 21;
    }
}
