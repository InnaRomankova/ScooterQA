package testData;

public enum RentalPeriod {

    ONE_DAY("сутки"),
    TWO_DAYS("двое суток"),
    THREE_DAYS("трое суток"),
    FOUR_DAYS("четверо суток"),
    FIVE_DAYS("пятеро суток"),
    SIX_DAYS("шестеро суток"),
    SEVEN_DAYS("семеро суток");

    private final String rentalPeriod;

    RentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }
}
