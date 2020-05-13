package com.example.demo.controller;

import com.example.demo.service.IpService;
import com.example.demo.util.IPLocate;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @Author WangHaiQiang
 * @Date Created in 15:25 2020/5/13
 **/
@Controller
public class IpController {


    @Autowired
    private IpService ipService;


    @RequestMapping(value = "/geoLite2Test")
    @ResponseBody
    public List<String> geoLite2Test() throws Exception {

        List<String> ipList = ipService.getIpList();

        File database = new File("C:\\Users\\Wahaha\\Downloads\\GeoLite2-City.mmdb");

        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        List<String> ipTestResult = new LinkedList<>();

        for (int i = 0; i < 99997; i++) {
            InetAddress ipAddress = InetAddress.getByName(ipList.get(0));
            String ipResult = geoLite2Test(reader, ipAddress);
            ipTestResult.add(ipResult);
        }

        return ipTestResult;
    }

    private String geoLite2Test(DatabaseReader reader, InetAddress ipAddress) throws IOException, GeoIp2Exception {
        CityResponse response = reader.city(ipAddress);

        Country country = response.getCountry();
        Subdivision subdivision = response.getMostSpecificSubdivision();
        City city = response.getCity();
        Postal postal = response.getPostal();
        Location location = response.getLocation();

        String ipResult = country.getIsoCode() + "||" + country.getName() + "||" + country.getNames().get("zh-CN")
                + "||" + subdivision.getName() + "||" + subdivision.getIsoCode()
                +"||" + city.getName() + "||" + postal.getCode()
                + "||" + location.getLatitude() + "||" + location.getLongitude();

        return ipResult;

    }


    @RequestMapping(value = "/aiwenTest")
    @ResponseBody
    public List<String> aiwenTest() {

        List<String> ipList = ipService.getIpList();

        String fileName = "C:\\Users\\Wahaha\\Downloads\\IP_trial_2020M05_single_WGS84_dat\\IP_trial_2020M05_single_WGS84.dat";
        IPLocate iplocate = IPLocate.loadDat(fileName);

        List<String> aiwenTestResult = new LinkedList<>();
        String ip;
        for (int i = 0; i < 99997; i++) {
            ip = ipList.get(i);
            String result = iplocate.locate_ip(ip);
            aiwenTestResult.add(result);
        }
        return aiwenTestResult;
    }


}
