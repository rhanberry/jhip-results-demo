import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './results.reducer';
import { IResults } from 'app/shared/model/results.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IResultsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ResultsUpdate = (props: IResultsUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { resultsEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/results');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...resultsEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="resultsMigrationTestApp.results.home.createOrEditLabel">
            <Translate contentKey="resultsMigrationTestApp.results.home.createOrEditLabel">Create or edit a Results</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : resultsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="results-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="results-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="assertionNameLabel" for="results-assertionName">
                  <Translate contentKey="resultsMigrationTestApp.results.assertionName">Assertion Name</Translate>
                </Label>
                <AvField id="results-assertionName" type="text" name="assertionName" />
              </AvGroup>
              <AvGroup>
                <Label id="criticalityLabel" for="results-criticality">
                  <Translate contentKey="resultsMigrationTestApp.results.criticality">Criticality</Translate>
                </Label>
                <AvField id="results-criticality" type="text" name="criticality" />
              </AvGroup>
              <AvGroup>
                <Label id="dateTimeCreatedLabel" for="results-dateTimeCreated">
                  <Translate contentKey="resultsMigrationTestApp.results.dateTimeCreated">Date Time Created</Translate>
                </Label>
                <AvField id="results-dateTimeCreated" type="text" name="dateTimeCreated" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="results-description">
                  <Translate contentKey="resultsMigrationTestApp.results.description">Description</Translate>
                </Label>
                <AvField id="results-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="eventDataFileNameLabel" for="results-eventDataFileName">
                  <Translate contentKey="resultsMigrationTestApp.results.eventDataFileName">Event Data File Name</Translate>
                </Label>
                <AvField id="results-eventDataFileName" type="text" name="eventDataFileName" />
              </AvGroup>
              <AvGroup>
                <Label id="eventEventFileNameLabel" for="results-eventEventFileName">
                  <Translate contentKey="resultsMigrationTestApp.results.eventEventFileName">Event Event File Name</Translate>
                </Label>
                <AvField id="results-eventEventFileName" type="text" name="eventEventFileName" />
              </AvGroup>
              <AvGroup>
                <Label id="metadataLabel" for="results-metadata">
                  <Translate contentKey="resultsMigrationTestApp.results.metadata">Metadata</Translate>
                </Label>
                <AvField id="results-metadata" type="text" name="metadata" />
              </AvGroup>
              <AvGroup check>
                <Label id="passingLabel">
                  <AvInput id="results-passing" type="checkbox" className="form-check-input" name="passing" />
                  <Translate contentKey="resultsMigrationTestApp.results.passing">Passing</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="qfExecutionIdLabel" for="results-qfExecutionId">
                  <Translate contentKey="resultsMigrationTestApp.results.qfExecutionId">Qf Execution Id</Translate>
                </Label>
                <AvField id="results-qfExecutionId" type="text" name="qfExecutionId" />
              </AvGroup>
              <AvGroup>
                <Label id="ruleIDLabel" for="results-ruleID">
                  <Translate contentKey="resultsMigrationTestApp.results.ruleID">Rule ID</Translate>
                </Label>
                <AvField id="results-ruleID" type="text" name="ruleID" />
              </AvGroup>
              <AvGroup>
                <Label id="ruleNameLabel" for="results-ruleName">
                  <Translate contentKey="resultsMigrationTestApp.results.ruleName">Rule Name</Translate>
                </Label>
                <AvField id="results-ruleName" type="text" name="ruleName" />
              </AvGroup>
              <AvGroup>
                <Label id="batchIdLabel" for="results-batchId">
                  <Translate contentKey="resultsMigrationTestApp.results.batchId">Batch Id</Translate>
                </Label>
                <AvField id="results-batchId" type="text" name="batchId" />
              </AvGroup>
              <AvGroup>
                <Label id="runIdLabel" for="results-runId">
                  <Translate contentKey="resultsMigrationTestApp.results.runId">Run Id</Translate>
                </Label>
                <AvField id="results-runId" type="text" name="runId" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/results" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  resultsEntity: storeState.results.entity,
  loading: storeState.results.loading,
  updating: storeState.results.updating,
  updateSuccess: storeState.results.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ResultsUpdate);
