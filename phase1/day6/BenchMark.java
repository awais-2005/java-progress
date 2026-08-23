package phase1.day6;

@SuppressWarnings({ "unused" })
public class BenchMark {
    public static void main(String[] args) {
        String str = "";
        StringBuilder strbldr = new StringBuilder();
        StringBuffer strbfr = new StringBuffer();
        long start = System.currentTimeMillis();
        int iterations = 100000;
        for (int i = 0; i < iterations; i++) {
            str += "-";
        }
        System.out.println("Time taken by String: %dms".formatted(System.currentTimeMillis() - start));

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            strbldr.append("-");
        }
        System.out.println(
                "Time taken by StringBuilder: %.3fms".formatted((double) (System.nanoTime() - start) / 1000_000));

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            strbfr.append("-");
        }
        System.out.println("Time taken by StringBuffer: %dms".formatted(System.currentTimeMillis() - start));
    }
}
