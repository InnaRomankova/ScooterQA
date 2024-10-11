package testData;

import static runner.ProjectProperties.getPropertyValue;

public class UrlData {

    public static final String SCOOTER_BASE_URL = getPropertyValue("scooter.base.url");
    public static final String SCOOTER_FIRST_ORDER_FORM_URL = SCOOTER_BASE_URL + getPropertyValue("scooter.orderFormPage.endpoint");
    public static final String SCOOTER_ORDER_STATUS_PAGE_URL = SCOOTER_BASE_URL + getPropertyValue("scooter.orderStatusPage.endpoint");
    public static final String DZEN_BASE_URL = getPropertyValue("dzen.base.url");
    public static final String YANDEX_BASE_URL = getPropertyValue("yandex.base.url");
}
