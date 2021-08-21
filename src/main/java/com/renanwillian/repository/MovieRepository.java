package com.renanwillian.repository;

import com.renanwillian.entity.Movie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {

    public boolean existsMovieByProducer(Long producerId) {
        return (Boolean) getEntityManager().createNativeQuery(sqlExistsMovieByProducer())
                                           .setParameter("producerId", producerId)
                                           .getSingleResult();
    }

    private String sqlExistsMovieByProducer() {
        return "SELECT EXISTS (" +
                "  SELECT 1 FROM movie " +
                "  JOIN movie_producer ON movie_producer.movie_id = movie.movie_id " +
                "  WHERE producer_id = :producerId " +
                ") ";
    }
}
