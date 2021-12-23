package UI.data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public static String generateMonth() {
        String[] monthes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Random index = new Random();
        int indexInt = index.nextInt(monthes.length);
        String randomMonth = monthes[indexInt];
        return randomMonth;
    }

    public static String generateYear() {
        String[] years = {"21", "22", "23", "24", "25", "26"};
        Random index = new Random();
        int indexInt = index.nextInt(years.length);
        String randomYear = years[indexInt];
        return randomYear;
    }

    public static String generateCVC() {
        String[] cvcCodes = {"151", "262", "233", "274", "215", "296", "589", "999", "242", "147", "856", "854"};
        Random index = new Random();
        int indexInt = index.nextInt(cvcCodes.length);
        String randomCVC = cvcCodes[indexInt];
        return randomCVC;
    }

    public static String validCardNumber() {
        String validCardNumber = "1111222233334444";
        return validCardNumber;
    }

    public static String declinedCardNumber() {
        String invalidCardNumber = "5555666677778888";
        return invalidCardNumber;
    }

    public static String generateFullNameInEng() {
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        return fullName;
    }

    public static String generateFullNameInRus() {
        Faker faker = new Faker(new Locale("ru"));
        String fullName = faker.name().fullName();
        return fullName;
    }


}