import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './results.reducer';
import { IResults } from 'app/shared/model/results.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ResultsDetail = (props: IResultsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { resultsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="resultsMigrationTestApp.results.detail.title">Results</Translate> [<b>{resultsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="assertionName">
              <Translate contentKey="resultsMigrationTestApp.results.assertionName">Assertion Name</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.assertionName}</dd>
          <dt>
            <span id="criticality">
              <Translate contentKey="resultsMigrationTestApp.results.criticality">Criticality</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.criticality}</dd>
          <dt>
            <span id="dateTimeCreated">
              <Translate contentKey="resultsMigrationTestApp.results.dateTimeCreated">Date Time Created</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.dateTimeCreated}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="resultsMigrationTestApp.results.description">Description</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.description}</dd>
          <dt>
            <span id="eventDataFileName">
              <Translate contentKey="resultsMigrationTestApp.results.eventDataFileName">Event Data File Name</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.eventDataFileName}</dd>
          <dt>
            <span id="eventEventFileName">
              <Translate contentKey="resultsMigrationTestApp.results.eventEventFileName">Event Event File Name</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.eventEventFileName}</dd>
          <dt>
            <span id="metadata">
              <Translate contentKey="resultsMigrationTestApp.results.metadata">Metadata</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.metadata}</dd>
          <dt>
            <span id="passing">
              <Translate contentKey="resultsMigrationTestApp.results.passing">Passing</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.passing ? 'true' : 'false'}</dd>
          <dt>
            <span id="qfExecutionId">
              <Translate contentKey="resultsMigrationTestApp.results.qfExecutionId">Qf Execution Id</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.qfExecutionId}</dd>
          <dt>
            <span id="ruleID">
              <Translate contentKey="resultsMigrationTestApp.results.ruleID">Rule ID</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.ruleID}</dd>
          <dt>
            <span id="ruleName">
              <Translate contentKey="resultsMigrationTestApp.results.ruleName">Rule Name</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.ruleName}</dd>
          <dt>
            <span id="batchId">
              <Translate contentKey="resultsMigrationTestApp.results.batchId">Batch Id</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.batchId}</dd>
          <dt>
            <span id="runId">
              <Translate contentKey="resultsMigrationTestApp.results.runId">Run Id</Translate>
            </span>
          </dt>
          <dd>{resultsEntity.runId}</dd>
        </dl>
        <Button tag={Link} to="/results" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/results/${resultsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ results }: IRootState) => ({
  resultsEntity: results.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ResultsDetail);
