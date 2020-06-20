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
	
	public Komentar kreirajKomentar(KomentarDTO komentarDTO) {
        KomentarDTO komentar2=new KomentarDTO();
        komentar2.setId(komentarDTO.getId());
        komentar2.setDatum(komentarDTO.getDatum());
        komentar2.setSadrzaj(komentarDTO.getSadrzaj());
        komentar2.setOcena(komentarDTO.getOcena());
        komentar2.setOdbijen(false);
        komentar2.setOglas_id(komentarDTO.getOglasid());
        komentar2.setKorisnik_id(komentarDTO.getKorisnik_id());
        komentar2.setOdgovor_id(komentarDTO.getOdgovor_id());
        komentar2.setOdobren(false);


        Komentar komentar = this.komentarRepository.save(new Komentar(komentar2));
        return komentar;
    }

    public Komentar findOne(Long id) {
        return komentarRepository.findById(id).orElseGet(null);
    }

    public List<Komentar> getAll() {
        return komentarRepository.findAll();
    }

    public List<Komentar> findByOglasIdAndUserId(Long id,Long uid) {
    	List<Komentar> komentariOglasa = new ArrayList<>();
    	List<Komentar> komentariOglasaKorisnika = new ArrayList<>();
    	komentariOglasa = komentarRepository.findByOglasid(id);
    	for(Komentar k : komentariOglasa){
    		if(k.getKorisnik_id()==uid) {
    			komentariOglasaKorisnika.add(k);
    		}
    	}
        return komentariOglasaKorisnika;
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
            komentar.setOglasid(kom.getOglasid());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }else{
            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setOdobren(false);
            komentar.setOdbijen(true);
            komentar.setOglasid(kom.getOglasid());
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
            komentar.setOdobren(true);
            komentar.setOdbijen(false);
            komentar.setOglasid(kom.getOglasid());
            komentar.setKorisnik_id(kom.getKorisnik_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
            komentar.setOdgovor_id(kom.getOdgovor_id());
        }else{
            komentar.setId(kom.getId());
            komentar.setDatum(kom.getDatum());
            komentar.setSadrzaj(kom.getSadrzaj());
            komentar.setOcena(kom.getOcena());
            komentar.setOdbijen(kom.isOdbijen());
            komentar.setOdobren(false);
            komentar.setOdbijen(true);
            komentar.setOglasid(kom.getOglasid());
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
        komentar.setOdobren(false);
        komentar.setOdbijen(false);
        komentar.setOglasid(komentarDTO.getOglasid());
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
