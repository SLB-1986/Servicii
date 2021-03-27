import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Operations {

    private final File file;
    private double totalRevenue;
    private double totalValue;

    public Package mapLineToPackage(String line) {
        String[] fields = line.split(",");
        return Package.builder()
                .targetLocation(fields[0])
                .targetDistance(Integer.parseInt(fields[1]))
                .packageValue(Integer.parseInt(fields[2]))
                .deliveryDate(LocalDate.parse(fields[3]))
                .build();
    }

    public List<Package> readFile() throws IOException {
        return Files.readAllLines(file.toPath())
                .stream()
                .filter(StringUtils::isNotBlank)
                .map(this::mapLineToPackage)
                .collect(Collectors.toList());
    }

    public void groupPackages(String destination, LocalDate localDate) throws IOException {
        List<Package> packages = readFile();
        double groupValue = 0;
        double groupRevenue = 0;

        packages.stream()
                .filter(aPackage -> aPackage.getTargetLocation().equals(destination) && aPackage.getDeliveryDate().equals(localDate))
                .forEach(System.out::println);

        for (Package aPackage: packages) {
            if (aPackage.getTargetLocation().equals(destination) && aPackage.getDeliveryDate().equals(localDate)) {
                groupValue = groupValue + aPackage.getPackageValue();
                groupRevenue = groupRevenue + aPackage.getTargetDistance();
            }
        }
        System.out.println("Group value for " + destination + " packages is: " + groupValue + " LEI");
        System.out.println("Group revenue for " + destination + " packages is: " + groupRevenue + " LEI\n");

        this.totalRevenue += groupRevenue;
        this.totalValue += groupValue;
    }

    public long getDistanceFromTargetLocation(String targetLocation) throws IOException {
        List<Package> packages = readFile();
        for (Package aPackage: packages) {
            if (targetLocation.equals(aPackage.getTargetLocation())) {
                return aPackage.getTargetDistance();
            }
        }
        return -1;
    }

    public void totalValue() {
        System.out.println("\n");
        System.out.println("Total value of delivered packages: " + totalValue);
        System.out.println("Total revenue of delivered packages: " + totalRevenue);
    }
}
