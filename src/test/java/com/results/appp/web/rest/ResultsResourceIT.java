package com.results.appp.web.rest;

import com.results.appp.AbstractCassandraTest;
import com.results.appp.ResultsMigrationTestApp;
import com.results.appp.domain.Results;
import com.results.appp.repository.ResultsRepository;
import com.results.appp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.List;
import java.util.UUID;

import static com.results.appp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ResultsResource} REST controller.
 */
@SpringBootTest(classes = ResultsMigrationTestApp.class)
public class ResultsResourceIT extends AbstractCassandraTest {

    private static final String DEFAULT_ASSERTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASSERTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CRITICALITY = "AAAAAAAAAA";
    private static final String UPDATED_CRITICALITY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_TIME_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_TIME_CREATED = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_DATA_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_DATA_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_EVENT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_EVENT_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PASSING = false;
    private static final Boolean UPDATED_PASSING = true;

    private static final String DEFAULT_QF_EXECUTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_QF_EXECUTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RULE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RUN_ID = "AAAAAAAAAA";
    private static final String UPDATED_RUN_ID = "BBBBBBBBBB";

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restResultsMockMvc;

    private Results results;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResultsResource resultsResource = new ResultsResource(resultsRepository);
        this.restResultsMockMvc = MockMvcBuilders.standaloneSetup(resultsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Results createEntity() {
        Results results = new Results()
            .assertionName(DEFAULT_ASSERTION_NAME)
            .criticality(DEFAULT_CRITICALITY)
            .dateTimeCreated(DEFAULT_DATE_TIME_CREATED)
            .description(DEFAULT_DESCRIPTION)
            .eventDataFileName(DEFAULT_EVENT_DATA_FILE_NAME)
            .eventEventFileName(DEFAULT_EVENT_EVENT_FILE_NAME)
            .metadata(DEFAULT_METADATA)
            .passing(DEFAULT_PASSING)
            .qfExecutionId(DEFAULT_QF_EXECUTION_ID)
            .ruleID(DEFAULT_RULE_ID)
            .ruleName(DEFAULT_RULE_NAME)
            .batchId(DEFAULT_BATCH_ID)
            .runId(DEFAULT_RUN_ID);
        return results;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Results createUpdatedEntity() {
        Results results = new Results()
            .assertionName(UPDATED_ASSERTION_NAME)
            .criticality(UPDATED_CRITICALITY)
            .dateTimeCreated(UPDATED_DATE_TIME_CREATED)
            .description(UPDATED_DESCRIPTION)
            .eventDataFileName(UPDATED_EVENT_DATA_FILE_NAME)
            .eventEventFileName(UPDATED_EVENT_EVENT_FILE_NAME)
            .metadata(UPDATED_METADATA)
            .passing(UPDATED_PASSING)
            .qfExecutionId(UPDATED_QF_EXECUTION_ID)
            .ruleID(UPDATED_RULE_ID)
            .ruleName(UPDATED_RULE_NAME)
            .batchId(UPDATED_BATCH_ID)
            .runId(UPDATED_RUN_ID);
        return results;
    }

    @BeforeEach
    public void initTest() {
        resultsRepository.deleteAll();
        results = createEntity();
    }

    @Test
    public void createResults() throws Exception {
        int databaseSizeBeforeCreate = resultsRepository.findAll().size();

        // Create the Results
        restResultsMockMvc.perform(post("/api/results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(results)))
            .andExpect(status().isCreated());

        // Validate the Results in the database
        List<Results> resultsList = resultsRepository.findAll();
        assertThat(resultsList).hasSize(databaseSizeBeforeCreate + 1);
        Results testResults = resultsList.get(resultsList.size() - 1);
        assertThat(testResults.getAssertionName()).isEqualTo(DEFAULT_ASSERTION_NAME);
        assertThat(testResults.getCriticality()).isEqualTo(DEFAULT_CRITICALITY);
        assertThat(testResults.getDateTimeCreated()).isEqualTo(DEFAULT_DATE_TIME_CREATED);
        assertThat(testResults.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testResults.getEventDataFileName()).isEqualTo(DEFAULT_EVENT_DATA_FILE_NAME);
        assertThat(testResults.getEventEventFileName()).isEqualTo(DEFAULT_EVENT_EVENT_FILE_NAME);
        assertThat(testResults.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testResults.isPassing()).isEqualTo(DEFAULT_PASSING);
        assertThat(testResults.getQfExecutionId()).isEqualTo(DEFAULT_QF_EXECUTION_ID);
        assertThat(testResults.getRuleID()).isEqualTo(DEFAULT_RULE_ID);
        assertThat(testResults.getRuleName()).isEqualTo(DEFAULT_RULE_NAME);
        assertThat(testResults.getBatchId()).isEqualTo(DEFAULT_BATCH_ID);
        assertThat(testResults.getRunId()).isEqualTo(DEFAULT_RUN_ID);
    }

    @Test
    public void createResultsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultsRepository.findAll().size();

        // Create the Results with an existing ID
        results.setId(UUID.randomUUID());

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultsMockMvc.perform(post("/api/results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(results)))
            .andExpect(status().isBadRequest());

        // Validate the Results in the database
        List<Results> resultsList = resultsRepository.findAll();
        assertThat(resultsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllResults() throws Exception {
        // Initialize the database
        results.setId(UUID.randomUUID());
        resultsRepository.save(results);

        // Get all the resultsList
        restResultsMockMvc.perform(get("/api/results"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(results.getId().toString())))
            .andExpect(jsonPath("$.[*].assertionName").value(hasItem(DEFAULT_ASSERTION_NAME)))
            .andExpect(jsonPath("$.[*].criticality").value(hasItem(DEFAULT_CRITICALITY)))
            .andExpect(jsonPath("$.[*].dateTimeCreated").value(hasItem(DEFAULT_DATE_TIME_CREATED)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].eventDataFileName").value(hasItem(DEFAULT_EVENT_DATA_FILE_NAME)))
            .andExpect(jsonPath("$.[*].eventEventFileName").value(hasItem(DEFAULT_EVENT_EVENT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].passing").value(hasItem(DEFAULT_PASSING.booleanValue())))
            .andExpect(jsonPath("$.[*].qfExecutionId").value(hasItem(DEFAULT_QF_EXECUTION_ID)))
            .andExpect(jsonPath("$.[*].ruleID").value(hasItem(DEFAULT_RULE_ID)))
            .andExpect(jsonPath("$.[*].ruleName").value(hasItem(DEFAULT_RULE_NAME)))
            .andExpect(jsonPath("$.[*].batchId").value(hasItem(DEFAULT_BATCH_ID)))
            .andExpect(jsonPath("$.[*].runId").value(hasItem(DEFAULT_RUN_ID)));
    }
    
    @Test
    public void getResults() throws Exception {
        // Initialize the database
        results.setId(UUID.randomUUID());
        resultsRepository.save(results);

        // Get the results
        restResultsMockMvc.perform(get("/api/results/{id}", results.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(results.getId().toString()))
            .andExpect(jsonPath("$.assertionName").value(DEFAULT_ASSERTION_NAME))
            .andExpect(jsonPath("$.criticality").value(DEFAULT_CRITICALITY))
            .andExpect(jsonPath("$.dateTimeCreated").value(DEFAULT_DATE_TIME_CREATED))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.eventDataFileName").value(DEFAULT_EVENT_DATA_FILE_NAME))
            .andExpect(jsonPath("$.eventEventFileName").value(DEFAULT_EVENT_EVENT_FILE_NAME))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.passing").value(DEFAULT_PASSING.booleanValue()))
            .andExpect(jsonPath("$.qfExecutionId").value(DEFAULT_QF_EXECUTION_ID))
            .andExpect(jsonPath("$.ruleID").value(DEFAULT_RULE_ID))
            .andExpect(jsonPath("$.ruleName").value(DEFAULT_RULE_NAME))
            .andExpect(jsonPath("$.batchId").value(DEFAULT_BATCH_ID))
            .andExpect(jsonPath("$.runId").value(DEFAULT_RUN_ID));
    }

    @Test
    public void getNonExistingResults() throws Exception {
        // Get the results
        restResultsMockMvc.perform(get("/api/results/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateResults() throws Exception {
        // Initialize the database
        results.setId(UUID.randomUUID());
        resultsRepository.save(results);

        int databaseSizeBeforeUpdate = resultsRepository.findAll().size();

        // Update the results
        Results updatedResults = resultsRepository.findById(results.getId()).get();
        updatedResults
            .assertionName(UPDATED_ASSERTION_NAME)
            .criticality(UPDATED_CRITICALITY)
            .dateTimeCreated(UPDATED_DATE_TIME_CREATED)
            .description(UPDATED_DESCRIPTION)
            .eventDataFileName(UPDATED_EVENT_DATA_FILE_NAME)
            .eventEventFileName(UPDATED_EVENT_EVENT_FILE_NAME)
            .metadata(UPDATED_METADATA)
            .passing(UPDATED_PASSING)
            .qfExecutionId(UPDATED_QF_EXECUTION_ID)
            .ruleID(UPDATED_RULE_ID)
            .ruleName(UPDATED_RULE_NAME)
            .batchId(UPDATED_BATCH_ID)
            .runId(UPDATED_RUN_ID);

        restResultsMockMvc.perform(put("/api/results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResults)))
            .andExpect(status().isOk());

        // Validate the Results in the database
        List<Results> resultsList = resultsRepository.findAll();
        assertThat(resultsList).hasSize(databaseSizeBeforeUpdate);
        Results testResults = resultsList.get(resultsList.size() - 1);
        assertThat(testResults.getAssertionName()).isEqualTo(UPDATED_ASSERTION_NAME);
        assertThat(testResults.getCriticality()).isEqualTo(UPDATED_CRITICALITY);
        assertThat(testResults.getDateTimeCreated()).isEqualTo(UPDATED_DATE_TIME_CREATED);
        assertThat(testResults.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testResults.getEventDataFileName()).isEqualTo(UPDATED_EVENT_DATA_FILE_NAME);
        assertThat(testResults.getEventEventFileName()).isEqualTo(UPDATED_EVENT_EVENT_FILE_NAME);
        assertThat(testResults.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testResults.isPassing()).isEqualTo(UPDATED_PASSING);
        assertThat(testResults.getQfExecutionId()).isEqualTo(UPDATED_QF_EXECUTION_ID);
        assertThat(testResults.getRuleID()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testResults.getRuleName()).isEqualTo(UPDATED_RULE_NAME);
        assertThat(testResults.getBatchId()).isEqualTo(UPDATED_BATCH_ID);
        assertThat(testResults.getRunId()).isEqualTo(UPDATED_RUN_ID);
    }

    @Test
    public void updateNonExistingResults() throws Exception {
        int databaseSizeBeforeUpdate = resultsRepository.findAll().size();

        // Create the Results

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResultsMockMvc.perform(put("/api/results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(results)))
            .andExpect(status().isBadRequest());

        // Validate the Results in the database
        List<Results> resultsList = resultsRepository.findAll();
        assertThat(resultsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteResults() throws Exception {
        // Initialize the database
        results.setId(UUID.randomUUID());
        resultsRepository.save(results);

        int databaseSizeBeforeDelete = resultsRepository.findAll().size();

        // Delete the results
        restResultsMockMvc.perform(delete("/api/results/{id}", results.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Results> resultsList = resultsRepository.findAll();
        assertThat(resultsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
