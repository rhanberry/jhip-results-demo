
CREATE TABLE IF NOT EXISTS results (
    id uuid,
    assertionName text,
    criticality text,
    dateTimeCreated text,
    description text,
    eventDataFileName text,
    eventEventFileName text,
    metadata text,
    passing boolean,
    qfExecutionId text,
    ruleID text,
    ruleName text,
    batchId text,
    runId text,
    PRIMARY KEY(id)
);
