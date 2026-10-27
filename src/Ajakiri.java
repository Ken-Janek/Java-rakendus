public class Ajakiri extends Teavik {
    public Ajakiri(int id, String pealkiri) {
        super(id, pealkiri);
    }

    @Override
    public int getLaenutusPaevad() {
        return 7;
    }
}
