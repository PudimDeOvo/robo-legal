public class SleepUtil {
    public static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e);
        }
    }
}