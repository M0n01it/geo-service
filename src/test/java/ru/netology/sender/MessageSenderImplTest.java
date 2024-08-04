package ru.netology.sender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendWithRussianIp() {
        // Подготовка
        String ip = "172.0.32.11";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        // Действие
        String result = messageSender.send(headers);

        // Проверка
        assertEquals("Добро пожаловать", result);
    }
}