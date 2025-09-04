package ex9;

import java.util.concurrent.RecursiveAction;

public class FillInArrayTask extends RecursiveAction {

    private final int[] array;

    private final int start;

    private final int end;

    private static final int THRESHOLD = 5;

    public FillInArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        int length = end - start;

        if (length <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                array[i] = i;
            }
        } else {

            int middle = start + length / 2;

            FillInArrayTask firstTask = new FillInArrayTask(array, start, middle);
            FillInArrayTask secondTask = new FillInArrayTask(array, middle, end);

            invokeAll(firstTask, secondTask);
        }
    }

}
