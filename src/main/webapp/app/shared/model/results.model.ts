export interface IResults {
  id?: string;
  assertionName?: string;
  criticality?: string;
  dateTimeCreated?: string;
  description?: string;
  eventDataFileName?: string;
  eventEventFileName?: string;
  metadata?: string;
  passing?: boolean;
  qfExecutionId?: string;
  ruleID?: string;
  ruleName?: string;
  batchId?: string;
  runId?: string;
}

export const defaultValue: Readonly<IResults> = {
  passing: false
};
