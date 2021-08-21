package com.renanwillian.service;


import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class StartupService {

    @Inject
    MovieListInitializeService importMovieListService;

    void onStart(@Observes StartupEvent event) {
        importMovieListService.importMovieList();
    }
}
