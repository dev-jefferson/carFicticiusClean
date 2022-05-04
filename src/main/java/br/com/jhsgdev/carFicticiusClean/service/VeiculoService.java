package br.com.jhsgdev.carFicticiusClean.service;

import br.com.jhsgdev.carFicticiusClean.data.dto.RetornoGastoCombustivelDTO;
import br.com.jhsgdev.carFicticiusClean.data.dto.VeiculoDTO;
import br.com.jhsgdev.carFicticiusClean.data.model.Veiculo;
import br.com.jhsgdev.carFicticiusClean.exception.ObjectNotFoundException;
import br.com.jhsgdev.carFicticiusClean.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * VeiculoService
 *
 * @author Jefferson Henrique Sousa Galvão
 *
 */
@Service
public class VeiculoService {

    @Autowired
    MarcaService marcaService;
    @Autowired
    VeiculoRepository repository;

    public Veiculo findById(Long id) {
        Optional<Veiculo> entity = this.repository.findById(id);
        return entity
                .orElseThrow(() -> new ObjectNotFoundException("O veículo de id: " + id + ", não foi encontrado!"));
    }

    public Page<Veiculo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return this.repository.findAll(pageRequest);
    }

    public VeiculoDTO show(Long id) {
        Veiculo obj = this.findById(id);
        VeiculoDTO response = this.toDTO(obj);
        return response;
    }

    public List<VeiculoDTO> findAll() {
        List<Veiculo> result = this.repository.findAll();
        List<VeiculoDTO> response = this.toDTO(result);
        return response;
    }

    @Transactional
    public VeiculoDTO create(VeiculoDTO objVo) {
        objVo.setId(null);

        Veiculo entity = this.toEntity(objVo);
        entity.setMarca((this.marcaService.findById(objVo.getMarca().getId())));

        Veiculo result = this.save(entity);

        return this.toDTO(result);
    }

    @Transactional
    public VeiculoDTO update(Long id, VeiculoDTO objVo) {
        Veiculo entity = this.findById(id);
        objVo.setId(entity.getId());
        Veiculo result = this.save(this.merge(entity, objVo));
        return this.toDTO(result);
    }

    private Veiculo merge(Veiculo entity, VeiculoDTO objVo) {
        if (objVo.getNome() != null) entity.setNome(objVo.getNome());
        if (objVo.getMarca()!= null) entity.setMarca(this.marcaService.findById(objVo.getMarca().getId()));
        if (objVo.getModelo() != null) entity.setModelo(objVo.getModelo());
        if (objVo.getDataFabricacao() != null) entity.setDataFabricacao(objVo.getDataFabricacao());
        if (objVo.getConsumoMedioCidade() != null) entity.setConsumoMedioCidade(objVo.getConsumoMedioCidade());
        if (objVo.getConsumoMedioRodovia() != null) entity.setConsumoMedioRodovia(objVo.getConsumoMedioRodovia());

        return entity;
    }

    private Veiculo save(Veiculo entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Transactional
    public void destroy(Long id) {
        Veiculo entity = this.findById(id);
        this.repository.delete(entity);
    }

    public Veiculo toEntity(VeiculoDTO objVO) {
        Veiculo obj = null;

        if (objVO != null) {

            obj = new Veiculo();
            obj.setNome(objVO.getNome());
            obj.setMarca(this.marcaService.findById(objVO.getMarca().getId()));
            obj.setModelo(objVO.getModelo());
            obj.setDataFabricacao(objVO.getDataFabricacao());
            obj.setConsumoMedioCidade(objVO.getConsumoMedioCidade());
            obj.setConsumoMedioRodovia(objVO.getConsumoMedioRodovia());
        }
        return obj;
    }

    public List<Veiculo> toEntity(List<VeiculoDTO> objVO) {
        List<Veiculo> list = new ArrayList<>();
        objVO.forEach(e -> {
            list.add(this.toEntity(e));
        });

        return list;
    }

    public VeiculoDTO toDTO(Veiculo obj) {
        VeiculoDTO objVO = new VeiculoDTO();

        if (obj != null)
            objVO = new VeiculoDTO(obj.getId(), obj.getNome(), (this.marcaService.show(obj.getMarca().getId())),
                        obj.getModelo(), obj.getDataFabricacao(), obj.getConsumoMedioCidade(), obj.getConsumoMedioRodovia());

        return objVO;
    }

    public List<VeiculoDTO> toDTO(List<Veiculo> obj) {
        List<VeiculoDTO> list = new ArrayList<>();
        obj.forEach(e -> {
            list.add(this.toDTO(e));
        });

        return list;
    }

    public List<RetornoGastoCombustivelDTO> calculaPrevisaoGastos(Double precoGasolina, Integer kmPercorridoCidade, Integer kmPercorridoRodovia) {
        List<Veiculo> result = this.repository.findAll();
        List<RetornoGastoCombustivelDTO> response = this.toListRetornoGastoCombustivelDTO(result, precoGasolina, kmPercorridoCidade, kmPercorridoRodovia);

        return response;
    }

    public List<RetornoGastoCombustivelDTO> toListRetornoGastoCombustivelDTO(List<Veiculo> obj, Double precoGasolina, Integer kmPercorridoCidade, Integer kmPercorridoRodovia) {
        List<RetornoGastoCombustivelDTO> list = new ArrayList<>();
        obj.forEach(e -> {
            RetornoGastoCombustivelDTO t = this.toRetornoGastoCombustivelDTO(e);
            t.calculaGastoCombustivel(precoGasolina, kmPercorridoCidade, kmPercorridoRodovia);
            list.add(t);
        });
        return list;
    }

    public RetornoGastoCombustivelDTO toRetornoGastoCombustivelDTO(Veiculo obj) {
        RetornoGastoCombustivelDTO objVO = new RetornoGastoCombustivelDTO();
        if (obj != null)
            objVO = new RetornoGastoCombustivelDTO(obj.getId(), obj.getNome(), (this.marcaService.show(obj.getMarca().getId())),
                    obj.getModelo(), obj.getDataFabricacao(), obj.getConsumoMedioCidade(), obj.getConsumoMedioRodovia());
        return objVO;
    }
}
