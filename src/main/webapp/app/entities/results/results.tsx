import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './results.reducer';
import { IResults } from 'app/shared/model/results.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Results = (props: IResultsProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { resultsList, match } = props;
  return (
    <div>
      <h2 id="results-heading">
        <Translate contentKey="resultsMigrationTestApp.results.home.title">Results</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="resultsMigrationTestApp.results.home.createLabel">Create new Results</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {resultsList && resultsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.assertionName">Assertion Name</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.criticality">Criticality</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.dateTimeCreated">Date Time Created</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.eventDataFileName">Event Data File Name</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.eventEventFileName">Event Event File Name</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.metadata">Metadata</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.passing">Passing</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.qfExecutionId">Qf Execution Id</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.ruleID">Rule ID</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.ruleName">Rule Name</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.batchId">Batch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="resultsMigrationTestApp.results.runId">Run Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {resultsList.map((results, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${results.id}`} color="link" size="sm">
                      {results.id}
                    </Button>
                  </td>
                  <td>{results.assertionName}</td>
                  <td>{results.criticality}</td>
                  <td>{results.dateTimeCreated}</td>
                  <td>{results.description}</td>
                  <td>{results.eventDataFileName}</td>
                  <td>{results.eventEventFileName}</td>
                  <td>{results.metadata}</td>
                  <td>{results.passing ? 'true' : 'false'}</td>
                  <td>{results.qfExecutionId}</td>
                  <td>{results.ruleID}</td>
                  <td>{results.ruleName}</td>
                  <td>{results.batchId}</td>
                  <td>{results.runId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${results.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${results.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${results.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">
            <Translate contentKey="resultsMigrationTestApp.results.home.notFound">No Results found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ results }: IRootState) => ({
  resultsList: results.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Results);
