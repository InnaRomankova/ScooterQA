package testData.dataProvider;

import testData.enums.MetroStation;

public class FirstScooterOrderFormParameters {

    public static Object[][] provideFirstScooterOrderFormParameters() {
        return new Object[][]{
                {"Иван", "Иванов", "г.Москва, ул.Строителей, д.18, кв.6", MetroStation.KRASNOSELSKAYA, "84999011234"}
        };
    }
}
