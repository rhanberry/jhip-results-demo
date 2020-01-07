package com.results.appp.web.rest;

import com.results.appp.domain.Results;
import com.results.appp.repository.ResultsRepository;
import com.results.appp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing {@link com.results.appp.domain.Results}.
 */
@RestController
@RequestMapping("/api")
public class ResultsResource {

    private final Logger log = LoggerFactory.getLogger(ResultsResource.class);

    private static final String ENTITY_NAME = "results";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultsRepository resultsRepository;

    public ResultsResource(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    /**
     * {@code POST  /results} : Create a new results.
     *
     * @param results the results to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new results, or with status {@code 400 (Bad Request)} if the results has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/results")
    public ResponseEntity<Results> createResults(@RequestBody Results results) throws URISyntaxException {
        log.debug("REST request to save Results : {}", results);
        if (results.getId() != null) {
            throw new BadRequestAlertException("A new results cannot already have an ID", ENTITY_NAME, "idexists");
        }
        results.setId(UUID.randomUUID());
        Results result = resultsRepository.save(results);
        return ResponseEntity.created(new URI("/api/results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /results} : Updates an existing results.
     *
     * @param results the results to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated results,
     * or with status {@code 400 (Bad Request)} if the results is not valid,
     * or with status {@code 500 (Internal Server Error)} if the results couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/results")
    public ResponseEntity<Results> updateResults(@RequestBody Results results) throws URISyntaxException {
        log.debug("REST request to update Results : {}", results);
        if (results.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Results result = resultsRepository.save(results);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, results.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /results} : get all the results.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of results in body.
     */
    @GetMapping("/results")
    public List<Results> getAllResults() {
        log.debug("REST request to get all Results");
        return resultsRepository.findAll();
    }

    /**
     * {@code GET  /results/:id} : get the "id" results.
     *
     * @param id the id of the results to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the results, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/results/{id}")
    public ResponseEntity<Results> getResults(@PathVariable UUID id) {
        log.debug("REST request to get Results : {}", id);
        Optional<Results> results = resultsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(results);
    }

    /**
     * {@code DELETE  /results/:id} : delete the "id" results.
     *
     * @param id the id of the results to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/results/{id}")
    public ResponseEntity<Void> deleteResults(@PathVariable UUID id) {
        log.debug("REST request to delete Results : {}", id);
        resultsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
