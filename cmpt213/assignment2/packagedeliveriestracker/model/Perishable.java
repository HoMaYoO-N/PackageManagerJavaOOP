package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Perishable extends Package {
    private LocalDateTime expiryDate;

    @Override
    public String toString() {
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toString() + "ExpiryDate: " + expiryDate.format(formatObj) + '\n';
    }

    public Perishable(String name, String notes, double priceInDollar, double weight, LocalDateTime expectedDeliveryDate,String type, LocalDateTime expiryDate) {
        super(name, notes, priceInDollar, weight, expectedDeliveryDate,type);
        this.expiryDate = expiryDate;
    }
}
