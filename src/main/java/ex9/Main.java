package ex9;

public class Main {

    private static final int ARRAY_SIZE = 1_000_000_00;

    public static void main(String[] args) {
        int[] array = new int[ARRAY_SIZE];
        FillInArrayTask fillInArrayTask = new FillInArrayTask(array, 0 , ARRAY_SIZE);
        fillInArrayTask.compute();
        SumArrayTask sumArrayTask = new SumArrayTask(array, 0, ARRAY_SIZE);
        System.out.println("Array sum: " + sumArrayTask.compute());
    }

}