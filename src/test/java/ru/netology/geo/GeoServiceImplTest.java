package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    private GeoServiceImpl geoService;

    @BeforeEach
    public void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void testByIpLocalhost() {
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertNotNull(location);
        Assertions.assertNull(location.getCity());
        Assertions.assertNull(location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIpMoscow() {
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Lenina", location.getStreet());
        Assertions.assertEquals(15, location.getBuiling());
    }

    @Test
    public void testByIpNewYork() {
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals(" 10th Avenue", location.getStreet());
        Assertions.assertEquals(32, location.getBuiling());
    }

    @Test
    public void testByIpMoscowRange() {
        Location location = geoService.byIp("172.0.0.1");
        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIpNewYorkRange() {
        Location location = geoService.byIp("96.0.0.1");
        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());
    }

    @Test
    public void testByIpUnknown() {
        Location location = geoService.byIp("1.2.3.4");
        Assertions.assertNull(location);
    }
}
