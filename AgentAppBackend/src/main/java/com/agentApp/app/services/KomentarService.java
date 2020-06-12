package com.agentApp.app.services;

import com.agentApp.app.dto.KlasaVozilaDTO;
import com.agentApp.app.dto.KomentarDTO;
import com.agentApp.app.exception.NotFoundException;
import com.agentApp.app.models.KlasaVozila;
import com.agentApp.app.models.Komentar;
import com.agentApp.app.repository.KlasaVozilaRepository;
import com.agentApp.app.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KomentarService {

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    public KomentarService(
            KomentarRepository komentarRepository
    ) {
        this.komentarRepository = komentarRepository;
    }

    public Komentar kreirajKomentar(KomentarDTO komentarDTO) {
/*Komentar komentar=new Komentar();
        komentar.setId(komentarDTO.getId());
        komentar.setDatum(komentarDTO.getDatum());
        komentar.setSadrzaj(komentarDTO.getSadrzaj());
        komentar.setOcena(komentarDTO.getOcena());
        komentar.setObjavljen(komentarDTO.isObjavljen());
        komentar.setOglas_id(komentarDTO.getOglas_id());
        komentar.setKorisnik_id(komentarDTO.getKorisnik_id());
        komentar.setOdgovor_id(komentarDTO.getOdgovor_id());
        komentar.setOdgovor_id(komentarDTO.getOdgovor_id());


        */
        Komentar komentar = this.komentarRepository.save(new Komentar(komentarDTO));
        return komentar;
    }

    public Komentar izmenaKomentara(KomentarDTO komentarDTO) {
        Komentar komentar = this.komentarRepository.findById(komentarDTO.getId())
                .orElseThrow(() -> new NotFoundException("Komentar sa ovim id-jem ne postoji!"));

        komentar.setId(komentarDTO.getId());
        komentar.setDatum(komentarDTO.getDatum());
        komentar.setSadrzaj(komentarDTO.getSadrzaj());
        komentar.setOcena(komentarDTO.getOcena());
        komentar.setObjavljen(komentarDTO.isObjavljen());
        komentar.setOglas_id(komentarDTO.getOglas_id());
        komentar.setKorisnik_id(komentarDTO.getKorisnik_id());
        komentar.setOdgovor_id(komentarDTO.getOdgovor_id());
        komentar.setOdgovor_id(komentarDTO.getOdgovor_id());


        return this.komentarRepository.save(komentar);
    }

    public void delete(Long id) {
        komentarRepository.deleteById(id);
        return;
    }




}
