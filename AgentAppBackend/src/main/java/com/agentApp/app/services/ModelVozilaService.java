package com.agentApp.app.services;

import com.agentApp.app.dto.ModelVozilaDTO;
import com.agentApp.app.exception.NotFoundException;
import com.agentApp.app.models.ModelVozila;
import com.agentApp.app.repository.ModelVozilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelVozilaService {

    @Autowired
    private ModelVozilaRepository modelVozilaRepository;

    @Autowired
    public ModelVozilaService(
            ModelVozilaRepository modelVozilaRepository
    ) {
        this.modelVozilaRepository = modelVozilaRepository;
    }

    public ModelVozila createModelVozila(ModelVozilaDTO modelVozilaDTO) {
        // this.userConnection.verify(oglasdto.getUser_id());
        ModelVozila modelVozila = this.modelVozilaRepository.save(new ModelVozila(modelVozilaDTO));
        return modelVozila;
    }

    public ModelVozila updateModelVozila(ModelVozilaDTO modelVozilaDTO) {
        ModelVozila modelVozila = this.modelVozilaRepository.findById(modelVozilaDTO.getId())
                .orElseThrow(() -> new NotFoundException("modelVozila with that id does not exist!"));

        modelVozila.setId(modelVozilaDTO.getId());
        modelVozila.setNaziv(modelVozilaDTO.getNaziv());
        modelVozila.setId_marke(modelVozilaDTO.getId_marke());

        return this.modelVozilaRepository.save(modelVozila);
    }

    public void delete(Long id) {
        modelVozilaRepository.deleteById(id);
        return;
    }

    public boolean verify(Long id) {
        try {
            ModelVozila o = modelVozilaRepository.findById(id).get();
            if(o != null)
                return true;
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}