package ru.smartsoft.converter.service;

import generated.ValCurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.smartsoft.converter.configuration.ApplicationConfiguration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
public class CurrenciesService {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Scheduled(fixedRate = 1_000_000)
    public void connectToBank () throws Exception{
        String resourceUrl = "http://www.cbr.ru/scripts/XML_daily.asp";
        ResponseEntity<String> response = applicationConfiguration.getRestTemplate().getForEntity(resourceUrl, String.class);

        JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(response.getBody()));
    }
}
