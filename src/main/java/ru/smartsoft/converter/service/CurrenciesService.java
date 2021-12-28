package ru.smartsoft.converter.service;

import generated.ValCurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.smartsoft.converter.configuration.ApplicationConfiguration;
import ru.smartsoft.converter.entity.Currency;
import ru.smartsoft.converter.entity.CurrencyValue;
import ru.smartsoft.converter.repository.CurrencyRepository;
import ru.smartsoft.converter.repository.CurrencyValueRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrenciesService {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyValueRepository currencyValueRepository;

    @PostConstruct
    public void init() throws Exception {
        connectToBank();
    }

    /**
     * Scheduling triggers every 3 hours starting at 00am
     */
    @Scheduled(cron = "* * */3 * * ?")
    private void connectToBank() throws Exception {
        String resourceUrl = "http://www.cbr.ru/scripts/XML_daily.asp";
        ResponseEntity<String> response = applicationConfiguration.getRestTemplate().getForEntity(resourceUrl, String.class);

        JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(response.getBody()));
        saveCurrencies(valCurs);
    }

    @Transactional
    private void saveCurrencies(ValCurs valCurs) {
        LocalDate localDate = LocalDate.parse(valCurs.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        if (currencyRepository.countAll() == 0) {
            for (ValCurs.Valute valute : valCurs.getValute()) {
                CurrencyValue currencyValue = buildCurrencyValue(localDate, valute);
                Currency currency = Currency.builder()
                        .charCode(valute.getCharCode())
                        .name(valute.getName())
                        .nominal(valute.getNominal())
                        .currencyValue(currencyValue)
                        .build();
                currencyRepository.save(currency);
            }
        } else if (!currencyValueRepository.existsByDate(localDate)) {
            for (ValCurs.Valute valute : valCurs.getValute()){
                Currency currency = currencyRepository.findByCharCode(valute.getCharCode());
                CurrencyValue currencyValue = buildCurrencyValue(localDate, valute);
                currency.setCurrencyValue(currencyValue);
                currencyRepository.save(currency);
            }
        }
    }

    private CurrencyValue buildCurrencyValue (LocalDate date, ValCurs.Valute valute) {
        return CurrencyValue.builder()
                .date(date)
                .value(new BigDecimal(valute.getValue().replaceAll(",", ".")))
                .build();
    }
}
