package ex10.reader_writer_problem;

public class Main {
    public static void main(String[] args) {
        ReaderWriterProblem readerWriterProblem = new ReaderWriterProblem();

        Runnable reader = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    readerWriterProblem.getElement(i);
                    Thread.sleep(100);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable writer = () -> {
            for (int i = 0; i < 10; i++) {
                readerWriterProblem.setElement(String.valueOf(i));
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread w1 = new Thread(writer, "Writer-1");
        Thread r1 = new Thread(reader, "Reader-1");
        Thread r2 = new Thread(reader, "Reader-2");

        w1.start();
        r1.start();
        r2.start();
    }
}
