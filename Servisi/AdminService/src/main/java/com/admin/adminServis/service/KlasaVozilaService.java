package com.admin.adminServis.service;
import com.admin.adminServis.dto.KlasaVozilaDTO;
import com.admin.adminServis.exceptions.NotFoundException;
import com.admin.adminServis.model.KlasaVozila;
import com.admin.adminServis.repository.KlasaVozilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlasaVozilaService {

    @Autowired
    private KlasaVozilaRepository klasaVozilaRepository;

    @Autowired
    public KlasaVozilaService(
            KlasaVozilaRepository klasaVozilaRepository
    ) {
        this.klasaVozilaRepository = klasaVozilaRepository;
    }

    public KlasaVozila createKlasaVozila(KlasaVozilaDTO klasaVozilaDTO) {
        // this.userConnection.verify(oglasdto.getUser_id());
        KlasaVozila klasaVozila = this.klasaVozilaRepository.save(new KlasaVozila(klasaVozilaDTO));
        return klasaVozila;
    }

    public KlasaVozila updateKlasaVozila(KlasaVozilaDTO klasaVozilaDTO) {
        KlasaVozila klasaVozila = this.klasaVozilaRepository.findById(klasaVozilaDTO.getId())
                .orElseThrow(() -> new NotFoundException("KlasaVozila with that id does not exist!"));

        klasaVozila.setId(klasaVozilaDTO.getId());
        klasaVozila.setNaziv(klasaVozilaDTO.getNaziv());

        return this.klasaVozilaRepository.save(klasaVozila);
    }

    public void delete(Long id) {
        klasaVozilaRepository.deleteById(id);
        return;
    }

    public boolean verify(Long id) {
        try {
            KlasaVozila o = klasaVozilaRepository.findById(id).get();
            if(o != null)
                return true;
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

}
