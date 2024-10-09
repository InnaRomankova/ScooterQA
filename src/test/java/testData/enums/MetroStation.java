package testData.enums;

public enum MetroStation {

    ROKOSSOVSKY_BOULEVARD("Бульвар Рокоссовского"),
    CHERKIZOVSKAYA("Черкизовская"),
    PREOBRAZHENSKAYA_SQUARE("Преображенская площадь"),
    SOKOLNIKI("Сокольники"),
    KRASNOSELSKAYA("Красносельская");

    private final String stationName;

    MetroStation(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }
}
