package testData.dataProvider;

public class InvalidLastNameParameters {

    public static Object[][] provideInvalidLastNameParameters() {
        return new Object[][]{

/**
 * Перечень невалидных данных для поля "Фамилия":
 * 1. Пустое поле.
 * 2. Фамилия на киррилице менее 2-х символов.
 * 3. Пробел.
 * 4. Латиница.
 * 5. Цифры.
 * 6. Любые символы: . , _ " : ? * \ | < / > ( ) № & + - = ! ; % ~ ` @ # $ ^ { } [ ] ‘
 * 7. Максимально допустимое количество символов неизвестно.
*/

                {""}
//                {"И"}, {" "}, {"ИвановL"}, {"Иванов8"}, {"Иванов."}, {"Иванов,"}, {"Иванов_"}, {"Иванов\""},
//                {"Иванов:"}, {"Иванов?"}, {"Иванов*"}, {"Иванов\\"}, {"Иванов|"}, {"Иванов<"}, {"Иванов/"}, {"Иванов>"}, {"Иванов("}, {"Иванов)"},
//                {"Иванов№"}, {"Иванов&"}, {"Иванов+"}, {"Иванов-"}, {"Иванов="}, {"Иванов!"}, {"Иванов;"}, {"Иванов%"}, {"Иванов~"}, {"Иванов`"},
//                {"Иванов@"}, {"Иванов#"}, {"Иванов$"}, {"Иванов^"}, {"Иванов{"}, {"Иванов}"}, {"Иванов["}, {"Иванов]"}, {"Иванов‘"}
        };
    }
}
