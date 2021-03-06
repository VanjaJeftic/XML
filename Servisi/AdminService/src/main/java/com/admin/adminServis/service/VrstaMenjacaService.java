package com.admin.adminServis.service;

import com.admin.adminServis.dto.VrstaMenjacaDTO;
import com.admin.adminServis.exceptions.NotFoundException;
import com.admin.adminServis.model.VrstaMenjaca;
import com.admin.adminServis.repository.VrstaMenjacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VrstaMenjacaService {

    @Autowired
    private VrstaMenjacaRepository vrstaMenjacaRepository;

    @Autowired
    public VrstaMenjacaService(
            VrstaMenjacaRepository vrstaMenjacaRepository
    ) {
        this.vrstaMenjacaRepository = vrstaMenjacaRepository;
    }

    public VrstaMenjaca createVrstaMenjaca(VrstaMenjacaDTO vrstaMenjacaDTO) {
        // this.userConnection.verify(oglasdto.getUser_id());
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaRepository.save(new VrstaMenjaca(vrstaMenjacaDTO));
        return vrstaMenjaca;
    }

    public VrstaMenjaca updateVrstaMenjaca(VrstaMenjacaDTO vrstaMenjacaDTO) {
        VrstaMenjaca vrstaMenjaca = this.vrstaMenjacaRepository.findById(vrstaMenjacaDTO.getId())
                .orElseThrow(() -> new NotFoundException("Vrsta menjaca with that id does not exist!"));

        vrstaMenjaca.setId(vrstaMenjacaDTO.getId());
        vrstaMenjaca.setNaziv(vrstaMenjacaDTO.getNaziv());

        return this.vrstaMenjacaRepository.save(vrstaMenjaca);
    }

    public void delete(Long id) {
        vrstaMenjacaRepository.deleteById(id);
        return;
    }

    public boolean verify(Long id) {
        try {
            VrstaMenjaca o = vrstaMenjacaRepository.findById(id).get();
            if(o != null)
                return true;
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}
