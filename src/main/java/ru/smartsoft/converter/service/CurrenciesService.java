package ru.smartsoft.converter.service;

import generated.ValCurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.smartsoft.converter.configuration.ApplicationConfiguration;
import ru.smartsoft.converter.entity.Currency;
import ru.smartsoft.converter.entity.CurrencyValue;
import ru.smartsoft.converter.repository.CurrenciesRepository;
import ru.smartsoft.converter.repository.CurrenciesValueRepository;

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
    private CurrenciesRepository currenciesRepository;

    @Autowired
    private CurrenciesValueRepository currenciesValueRepository;

    /**
     * Scheduling triggers every 6 hours starting at 00am
     */
    @Scheduled(cron = "* * */6 * * ?")
    private void connectToBank() throws Exception{
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

        if (currenciesRepository.countAll() == 0) {
            for (ValCurs.Valute valute : valCurs.getValute()) {
                CurrencyValue currencyValue = buildCurrencyValue(localDate, valute);
                Currency currency = Currency.builder()
                        .charCode(valute.getCharCode())
                        .name(valute.getName())
                        .nominal(valute.getNominal())
                        .value(currencyValue)
                        .build();
                currenciesRepository.save(currency);
            }
        } else if (!currenciesValueRepository.existsByDate(localDate)) {
            for (ValCurs.Valute valute : valCurs.getValute()){
                Currency currency = currenciesRepository.findByCharCode(valute.getCharCode());
                CurrencyValue currencyValue = buildCurrencyValue(localDate, valute);
                currency.setValue(currencyValue);
                currenciesRepository.save(currency);
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
