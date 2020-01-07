package com.results.appp.repository;

import com.results.appp.domain.Results;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data Cassandra repository for the Results entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultsRepository extends CassandraRepository<Results, UUID> {

}
