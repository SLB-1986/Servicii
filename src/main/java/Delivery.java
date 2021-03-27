import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@RequiredArgsConstructor
public class Delivery extends Thread{

    private static final Operations fileOperations = new Operations(new File("src/main/resources/aplicatieTest/delivery.txt"));
    private final String packageLocation;
    private final LocalDate packageDate;

    @Override
    public void run() {
        try {
            if (fileOperations.getDistanceFromTargetLocation(packageLocation) > 20) {
                System.out.println("\n");
                System.out.println("[Delivering for <" + packageLocation + "> and date <" + packageDate + "> in <20> seconds]");
                System.out.println("\n");
                Thread.sleep(20000);
            } else {
                System.out.println("\n");
                System.out.println("[Delivering for <" + packageLocation + "> and date <" + packageDate + "> in <" + fileOperations.getDistanceFromTargetLocation(packageLocation) + "> seconds]");
                System.out.println("\n");
                Thread.sleep(fileOperations.getDistanceFromTargetLocation(packageLocation) * 1000);
            }
            fileOperations.groupPackages(packageLocation, packageDate);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void total() {
        fileOperations.totalValue();
    }

}
