import java.util.Random;

public class Printer extends Thread {

    private String currentTask = null;
    private String printerName = null;


    private Random random = new Random();

    Printer(String name) {
        printerName = name;
    }

    boolean isReady() {
        synchronized(Printer.class) {
            return currentTask == null;
        }
    }

    void setTask(String task) {
        if (isReady()) {
            this.currentTask = task;
        }
    }


    @Override
    public void run() {
        while (true) {
            if (currentTask != null) {
                int currentTime = random.nextInt(5000);
                if (currentTime > 5000) {
                    System.out.println("More then 5 seconds!");
                }
                System.out.println("Time for " + printerName + " is " + currentTime);
                try {
                    Thread.sleep(currentTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(printerName + "---------->" + currentTask);
                currentTask = null;
            }
        }
    }
}
