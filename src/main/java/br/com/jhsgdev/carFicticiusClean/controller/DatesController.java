package br.com.jhsgdev.carFicticiusClean.controller;

import br.com.jhsgdev.carFicticiusClean.data.dto.VeiculoDTO;
import br.com.jhsgdev.carFicticiusClean.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/dates")
public class DatesController {

    @Autowired
    private VeiculoService service;

    @GetMapping("/{id}")
    public VeiculoDTO show(@PathVariable Long id) {
        return this.service.show(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        this.service.destroy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> dates(@RequestBody LinkedHashMap dates) {

        String stringDataA = dates.get("dataA").toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = stringDataA;

        LocalDate dataA = LocalDate.parse(dateString, dateTimeFormatter);

        String stringDataB = dates.get("dataB").toString();
        LocalDate dataB = LocalDate.parse(stringDataB, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dataB);

        LinkedHashMap<String, String> objectDates = new LinkedHashMap<String, String>();
        objectDates.put("dataA", dataA.toString());
        objectDates.put("dataB", dataB.toString());

        String mensagem = null;

        if(!dataA.isAfter(dataB)){
            mensagem = "A data 'B' é Maior que a data 'A'.";
        }else{
            mensagem = "A data 'A' é Maior que a data 'B'.";
        }

        objectDates.put("mensagem", mensagem);

        return ResponseEntity.ok(objectDates);
    }

    @GetMapping("/array")
    public ResponseEntity<LinkedHashMap> array(@RequestBody List<Integer> array) {

        System.out.println(array);

        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

        return ResponseEntity.ok(result);
    }
}
