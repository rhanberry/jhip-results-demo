import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Results from './results';
import ResultsDetail from './results-detail';
import ResultsUpdate from './results-update';
import ResultsDeleteDialog from './results-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ResultsDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ResultsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ResultsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ResultsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Results} />
    </Switch>
  </>
);

export default Routes;
