package com.renanwillian.repository;

import com.renanwillian.entity.Studio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class StudioRepository implements PanacheRepository<Studio> {

    public Optional<Studio> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}