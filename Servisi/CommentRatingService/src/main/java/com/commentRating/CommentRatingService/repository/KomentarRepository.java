package com.commentRating.CommentRatingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commentRating.CommentRatingService.model.Komentar;

public interface KomentarRepository extends JpaRepository <Komentar, Long> {
    List<Komentar> findByOdobren(boolean b);
    List<Komentar> findByOdbijen(boolean b);
    //List<Komentar> findByOglas_id(long id);

}
