import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IResults, defaultValue } from 'app/shared/model/results.model';

export const ACTION_TYPES = {
  FETCH_RESULTS_LIST: 'results/FETCH_RESULTS_LIST',
  FETCH_RESULTS: 'results/FETCH_RESULTS',
  CREATE_RESULTS: 'results/CREATE_RESULTS',
  UPDATE_RESULTS: 'results/UPDATE_RESULTS',
  DELETE_RESULTS: 'results/DELETE_RESULTS',
  RESET: 'results/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IResults>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ResultsState = Readonly<typeof initialState>;

// Reducer

export default (state: ResultsState = initialState, action): ResultsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RESULTS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RESULTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RESULTS):
    case REQUEST(ACTION_TYPES.UPDATE_RESULTS):
    case REQUEST(ACTION_TYPES.DELETE_RESULTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RESULTS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RESULTS):
    case FAILURE(ACTION_TYPES.CREATE_RESULTS):
    case FAILURE(ACTION_TYPES.UPDATE_RESULTS):
    case FAILURE(ACTION_TYPES.DELETE_RESULTS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESULTS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RESULTS):
    case SUCCESS(ACTION_TYPES.UPDATE_RESULTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RESULTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/results';

// Actions

export const getEntities: ICrudGetAllAction<IResults> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RESULTS_LIST,
  payload: axios.get<IResults>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IResults> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RESULTS,
    payload: axios.get<IResults>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IResults> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RESULTS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IResults> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RESULTS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IResults> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RESULTS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
