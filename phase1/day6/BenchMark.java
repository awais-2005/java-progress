package phase1.day6;

@SuppressWarnings({ "unused" })
public class BenchMark {
    public static void main(String[] args) {
        int interations = 200000;
        int warmup = 20000;

        stringTest(warmup);
        stringBuilderTest(warmup);
        stringBufferTest(warmup);

        float t1 = stringTest(interations);
        float t2 = stringBuilderTest(interations);
        float t3 = stringBufferTest(interations);

        System.out.println("Time taken by String: %.3fms".formatted(t1));
        System.out.println("Time taken by StringBuilder: %.3fms".formatted(t2));
        System.out.println("Time taken by StringBuffer: %.3fms".formatted(t3));

    }

    private static float stringTest(int iterations) {
        String str = "";
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str += "-";
        }
        return (float) (System.nanoTime() - startTime) / 1_000_000;
    }

    private static float stringBuilderTest(int iterations) {
        StringBuilder str = new StringBuilder();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str.append("-");
        }
        return (float) (System.nanoTime() - startTime) / 1_000_000;
    }

    private static float stringBufferTest(int iterations) {
        StringBuffer str = new StringBuffer();
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            str.append("-");
        }
        return (float) (System.nanoTime() - startTime) / 1_000_000;
    }

}
