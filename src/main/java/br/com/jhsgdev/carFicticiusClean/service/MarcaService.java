package br.com.jhsgdev.carFicticiusClean.service;

import br.com.jhsgdev.carFicticiusClean.data.dto.MarcaDTO;
import br.com.jhsgdev.carFicticiusClean.data.dto.VeiculoDTO;
import br.com.jhsgdev.carFicticiusClean.data.model.Marca;
import br.com.jhsgdev.carFicticiusClean.data.model.Veiculo;
import br.com.jhsgdev.carFicticiusClean.exception.ObjectNotFoundException;
import br.com.jhsgdev.carFicticiusClean.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    MarcaRepository repository;

    public Marca findById(Long id) {
        Optional<Marca> entity = this.repository.findById(id);
        return entity
                .orElseThrow(() -> new ObjectNotFoundException("O marca de id: " + id + ", não foi encontrado!"));
    }

    public Page<Marca> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return this.repository.findAll(pageRequest);
    }

    public MarcaDTO show(Long id) {
        Marca obj = this.findById(id);
        MarcaDTO response = this.toDTO(obj);
        return response;
    }

    public List<MarcaDTO> findAll() {
        List<Marca> result = this.repository.findAll();

        List<MarcaDTO> response = this.toDTO(result);
        return response;
    }

    public MarcaDTO create(MarcaDTO objVo) {
        objVo.setId(null);

        Marca entity = this.toEntity(objVo);
        Marca result = this.save(entity);
        return this.toDTO(result);
    }

    public MarcaDTO update(Long id, MarcaDTO objVo) {
        Marca entity = this.findById(id);
        objVo.setId(entity.getId());

        Marca result = this.save(this.toEntity(objVo));
        return this.toDTO(result);
    }

    private Marca save(Marca entity) {
        return this.repository.saveAndFlush(entity);
    }

    public void destroy(Long id) {
        Marca entity = this.findById(id);
        this.repository.delete(entity);
    }

    public Marca toEntity(MarcaDTO objVO) {
        Marca obj = null;
        if (objVO != null)
            obj = new Marca(objVO.getId(), objVO.getNome());

        return obj;
    }

    public List<Marca> toEntity(List<MarcaDTO> objVO) {
        List<Marca> list = new ArrayList<>();
        objVO.forEach(e -> {
            list.add(this.toEntity(e));
        });

        return list;
    }

    public MarcaDTO toDTO(Marca obj) {
        MarcaDTO objVO = new MarcaDTO();

        if (obj != null)
            objVO = new MarcaDTO(obj.getId(), obj.getNome());
        return objVO;
    }

    public List<MarcaDTO> toDTO(List<Marca> obj) {
        List<MarcaDTO> list = new ArrayList<>();
        obj.forEach(e -> {
            list.add(this.toDTO(e));
        });

        return list;
    }
}