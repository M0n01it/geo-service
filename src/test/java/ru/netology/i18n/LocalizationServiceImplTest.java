package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    @Test
    public void testLocaleRussian() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testLocaleAmerican() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String message = localizationService.locale(Country.USA);
        assertEquals("Welcome", message);
    }
}