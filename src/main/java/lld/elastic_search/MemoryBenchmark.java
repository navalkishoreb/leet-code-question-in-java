package lld.elastic_search;

public class MemoryBenchmark {


    /*
     -Xms32m -Xmx32m
    Indexed: 38000 | Used heap: 24 MB
    Indexed: 39000 | Used heap: 25 MB
    Sentences indexed: 40000
    Memory used: 25 MB

    -Xms500m -Xmx500m
    search
    Indexed: 299000 | Used heap: 336 MB
    search2
    Indexed: 299000 | Used heap: 378 MB

    Search3 with ConcurrentLinkedQueue
    Indexed: 299000 | Used heap: 246 MB
    Search3 with ConcurrentHashSet
    Indexed: 299000 | Used heap: 322 MB
     */
    public static void main(String[] args) throws InterruptedException {
//        run(new Search(), 300_000);
//        run(new Search2(), 300_000);
        run(new Search3(), 300_000);
    }

    private static void run(Cache cache, int count) throws InterruptedException {
        System.gc();
        Thread.sleep(2000);
        long before = usedMemory();

        for (int i = 0; i < count; i++) {
            cache.add( "backend service architecture scalability metadata catalog example sentence number " + i);
            if (i % 1_000 == 0) {
                System.out.println(
                        "Indexed: " + i +
                                " | Used heap: " + usedMemory() + " MB"
                );
            }
        }

        System.gc();
        Thread.sleep(2000);
        long after = usedMemory();

        System.out.println("Sentences indexed: " + count);
        System.out.println("Memory used: " + (after - before) + " MB");
    }

    private static long usedMemory() throws InterruptedException {

        Runtime rt = Runtime.getRuntime();
        return (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
    }
}