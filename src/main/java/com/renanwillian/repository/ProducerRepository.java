package com.renanwillian.repository;

import com.renanwillian.entity.Producer;
import com.renanwillian.service.dto.ProducerAwardDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@SuppressWarnings("unchecked")
public class ProducerRepository implements PanacheRepository<Producer> {

    public Optional<Producer> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public List<ProducerAwardDTO> listProducerWithMaxIntervalAward() {
        return mapToProducerAwardDTO(getEntityManager().createNativeQuery(sqlProducerWithMaxIntervalAward())
                                                       .getResultList());
    }

    public List<ProducerAwardDTO> listProducerWithMinIntervalAward() {
        return mapToProducerAwardDTO(getEntityManager().createNativeQuery(sqlProducerWithMinIntervalAward())
                                                       .getResultList());
    }

    private List<ProducerAwardDTO> mapToProducerAwardDTO(List<Object[]> resultList) {
        return resultList.stream()
                         .map(objects -> new ProducerAwardDTO.Builder()
                                 .withProducer((String) objects[0])
                                 .withPreviousWin((Integer) objects[1])
                                 .withFollowingWin((Integer) objects[2])
                                 .withInterval((Integer) objects[3]).build())
                         .collect(Collectors.toList());
    }

    private String sqlProducerIntervalAward() {
        return " WITH producer_row_number AS ( " +
                "    SELECT ROW_NUMBER() OVER () AS row_number, producer_id, year " +
                "    FROM (" +
                "      SELECT producer_id, year FROM movie_producer " +
                "      JOIN movie ON movie_producer.movie_id = movie.movie_id " +
                "      WHERE winner IS TRUE " +
                "      ORDER BY producer_id, year " +
                "    ) AS p " +
                "), " +
                "award_interval AS ( " +
                "    SELECT p1.producer_id, p1.year AS previous, p2.year AS following, p2.year - p1.year AS years_between " +
                "    FROM producer_row_number p1 " +
                "    JOIN producer_row_number p2 ON p1.producer_id = p2.producer_id AND p1.row_number + 1 = p2.row_number " +
                ") " +
                "SELECT name, previous, following, years_between " +
                "FROM award_interval " +
                "JOIN producer ON award_interval.producer_id = producer.producer_id ";
    }

    private String sqlProducerWithMaxIntervalAward() {
        return sqlProducerIntervalAward() + "WHERE years_between = (SELECT MAX(years_between) FROM award_interval) ";
    }

    private String sqlProducerWithMinIntervalAward() {
        return sqlProducerIntervalAward() + "WHERE years_between = (SELECT MIN(years_between) FROM award_interval) ";
    }
}