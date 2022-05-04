package br.com.jhsgdev.carFicticiusClean.controller;

import br.com.jhsgdev.carFicticiusClean.data.dto.RetornoGastoCombustivelDTO;
import br.com.jhsgdev.carFicticiusClean.data.dto.VeiculoDTO;
import br.com.jhsgdev.carFicticiusClean.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @GetMapping("/{id}")
    public VeiculoDTO show(@PathVariable Long id) {
        return this.service.show(id);
    }

    @GetMapping
    public List<VeiculoDTO> findAll() {
        List<VeiculoDTO> list = this.service.findAll();
        return list;
    }

    @PostMapping
    public VeiculoDTO create(@RequestBody VeiculoDTO objVo) {
        return this.service.create(objVo);
    }

    @PutMapping("/{id}")
    public VeiculoDTO update(@PathVariable Long id, @RequestBody VeiculoDTO objVo) {
        return this.service.update(id, objVo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        this.service.destroy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getPrevisaoGastos")
    public List<RetornoGastoCombustivelDTO> getPrevisaoGastos(@RequestBody LinkedHashMap body) {
        Double precoGasolina = (Double) body.get("precoGasolina");
        Integer kmTotalPercorridoCidade = (Integer) body.get("kmTotalPercorridoCidade");
        Integer kmTotalPercorridoRodovia = (Integer)  body.get("kmTotalPercorridoRodovia");
        return this.service.calculaPrevisaoGastos(precoGasolina, kmTotalPercorridoCidade, kmTotalPercorridoRodovia);
    }

}
