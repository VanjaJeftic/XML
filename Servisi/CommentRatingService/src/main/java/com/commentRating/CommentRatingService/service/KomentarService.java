package com.commentRating.CommentRatingService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commentRating.CommentRatingService.dto.KomentarDTO;
import com.commentRating.CommentRatingService.exception.NotFoundException;
import com.commentRating.CommentRatingService.model.Komentar;
import com.commentRating.CommentRatingService.repository.KomentarRepository;

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
    	KomentarDTO komentar2=new KomentarDTO();
        komentar2.setId(komentarDTO.getId());
        komentar2.setDatum(komentarDTO.getDatum());
        komentar2.setSadrzaj(komentarDTO.getSadrzaj());
        komentar2.setOcena(komentarDTO.getOcena());
        komentar2.setUsernameusera(komentarDTO.getUsernameusera());
        komentar2.setOglas_id(komentarDTO.getOglas_id());
        komentar2.setKorisnik_id(komentarDTO.getKorisnik_id());
        komentar2.setOdgovor_id(komentarDTO.getOdgovor_id());
        komentar2.setOdbijen(false);
        if(komentarDTO.getOcena()==10) {
            komentar2.setOdobren(true);
        } else {
            komentar2.setOdobren(false);
        }


        Komentar komentar = this.komentarRepository.save(new Komentar(komentar2));
        return komentar;
    }

    public Komentar findOne(Long id) {
        return komentarRepository.findById(id).orElseGet(null);
    }
    
    public List<Komentar> getOdobrenByOglasId(Long id){
    	List<Komentar> komentari = komentarRepository.findAll();
    	List<Komentar> odobreniKomentari = new ArrayList<>();
    	for(Komentar k : komentari) {
    		if(k.isOdobren()==true && k.getOglas_id()==id)
    			odobreniKomentari.add(k);
    	}
    	return odobreniKomentari;
    	
    }

    public Komentar izmenaPoljaOdbijen(Komentar kom) {
        Komentar komentar = this.komentarRepository.findById(kom.getId())
                .orElseThrow(() -> new NotFoundException("Komentar sa ovim id-jem ne postoji!"));
        if(kom.isOdobren()==true) {
            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setOdobren(true);
            komentar.setOdbijen(false);
            komentar.setUsernameusera(kom.getUsernameusera());
            komentar.setOglas_id(kom.getOglas_id());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }else{
            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setUsernameusera(kom.getUsernameusera());
            komentar.setOdobren(false);
            komentar.setOdbijen(true);
            komentar.setOglas_id(kom.getOglas_id());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }

        return this.komentarRepository.save(komentar);
    }




    public Komentar izmenaPoljaOdobren(Komentar kom) {
        Komentar komentar = this.komentarRepository.findById(kom.getId())
                .orElseThrow(() -> new NotFoundException("Komentar sa ovim id-jem ne postoji!"));

        if(kom.isOdbijen()==false) {

            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setUsernameusera(kom.getUsernameusera());
            komentar.setOdobren(true);
            komentar.setOdbijen(false);
            komentar.setOglas_id(kom.getOglas_id());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }else{
            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setUsernameusera(kom.getUsernameusera());
            komentar.setOdobren(false);
            komentar.setOdbijen(true);
            komentar.setOglas_id(kom.getOglas_id());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }

        return this.komentarRepository.save(komentar);
    }



    //ovo se za sada ne koristi!!!!
    public Komentar izmenaKomentara(KomentarDTO komentarDTO) {
        Komentar komentar = this.komentarRepository.findById(komentarDTO.getId())
                .orElseThrow(() -> new NotFoundException("Komentar sa ovim id-jem ne postoji!"));

        komentar.setId(komentarDTO.getId());
        komentar.setDatum(komentarDTO.getDatum());
        komentar.setSadrzaj(komentarDTO.getSadrzaj());
        komentar.setOcena(komentarDTO.getOcena());
        komentar.setOdbijen(komentarDTO.isOdbijen());
        komentar.setUsernameusera(komentarDTO.getUsernameusera());
        komentar.setOdobren(false);
        komentar.setOdbijen(false);
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
