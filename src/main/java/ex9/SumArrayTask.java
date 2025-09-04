package ex9;

import java.util.concurrent.RecursiveTask;

public class SumArrayTask extends RecursiveTask<Integer> {

    private final int[] array;

    private final int start;

    private final int end;

    private static final int THRESHOLD = 5;

    public SumArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int length = end - start;

        if (length <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {

            int middle = start + length / 2;

            SumArrayTask firstTask = new SumArrayTask(array, start, middle);
            SumArrayTask secondTask = new SumArrayTask(array, middle, end);

            firstTask.fork();
            int secondTaskSum = secondTask.compute();
            return firstTask.join() + secondTaskSum;
        }
    }
}
