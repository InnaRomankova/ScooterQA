package testData.dataProvider;

import testData.enums.MetroStation;
import testData.enums.RentalPeriod;
import testData.enums.ScooterColor;

public class ScooterOrderFormParameters {

    public static Object[][] provideScooterOrderFormParameters() {
        return new Object[][]{
                {"Иван", "Иванов", "г.Москва, ул.Строителей, д.18, кв.6", MetroStation.KRASNOSELSKAYA, "84999011234",
                        "5", RentalPeriod.ONE_DAY, ScooterColor.BLACK_PEARL, "3-й этаж"},
                {"Петр", "Петров", "г.Москва, ул.Новокузнецкая, 4-15", MetroStation.PREOBRAZHENSKAYA_SQUARE,
                        "84999015555", "14", RentalPeriod.FIVE_DAYS, ScooterColor.GREY_HOPELESSNESS, "домофон не работает"}
        };
    }
}
