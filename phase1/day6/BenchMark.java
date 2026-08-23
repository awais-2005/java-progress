package phase1.day6;

@SuppressWarnings({ "unused" })
public class BenchMark {
    public static void main(String[] args) {
        int interations = 200000;
        int warmup = 20000;

        stringTest(warmup);
        stringBuilderTest(warmup);
        stringBufferTest(warmup);

        double t1 = stringTest(interations);
        double t2 = stringBuilderTest(interations);
        double t3 = stringBufferTest(interations);

        System.out.println("Time taken by String: %.3fms".formatted(t1));
        System.out.println("Time taken by StringBuilder: %.3fms".formatted(t2));
        System.out.println("Time taken by StringBuffer: %.3fms".formatted(t3));

    }

    private static double stringTest(int iterations) {
        String str = "";
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str += "-";
        }
        return (System.nanoTime() - startTime) / 1_000_000.0;
    }

    private static double stringBuilderTest(int iterations) {
        StringBuilder str = new StringBuilder();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str.append("-");
        }
        return (System.nanoTime() - startTime) / 1_000_000.0;
    }

    private static double stringBufferTest(int iterations) {
        StringBuffer str = new StringBuffer();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str.append("-");
        }
        return (System.nanoTime() - startTime) / 1_000_000.0;
    }

}
