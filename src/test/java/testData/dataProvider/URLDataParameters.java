package testData.dataProvider;

import testData.PageName;

public class URLDataParameters {

    public static Object[][] provideURLData() {
        return new Object[][]{
                {"https://qa-scooter.praktikum-services.ru/", PageName.HOME_PAGE},
                {"https://qa-scooter.praktikum-services.ru/order", PageName.FIRST_ORDER_FORM_PAGE},
                {"https://qa-scooter.praktikum-services.ru/track?t=", PageName.ORDER_STATUS_PAGE}
        };
    }
}
