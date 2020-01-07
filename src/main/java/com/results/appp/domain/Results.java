package com.results.appp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

/**
 * A Results.
 */
@Table("results")
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String assertionName;

    private String criticality;

    private String dateTimeCreated;

    private String description;

    private String eventDataFileName;

    private String eventEventFileName;

    private String metadata;

    private Boolean passing;

    private String qfExecutionId;

    private String ruleID;

    private String ruleName;

    private String batchId;

    private String runId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAssertionName() {
        return assertionName;
    }

    public Results assertionName(String assertionName) {
        this.assertionName = assertionName;
        return this;
    }

    public void setAssertionName(String assertionName) {
        this.assertionName = assertionName;
    }

    public String getCriticality() {
        return criticality;
    }

    public Results criticality(String criticality) {
        this.criticality = criticality;
        return this;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public Results dateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
        return this;
    }

    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getDescription() {
        return description;
    }

    public Results description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDataFileName() {
        return eventDataFileName;
    }

    public Results eventDataFileName(String eventDataFileName) {
        this.eventDataFileName = eventDataFileName;
        return this;
    }

    public void setEventDataFileName(String eventDataFileName) {
        this.eventDataFileName = eventDataFileName;
    }

    public String getEventEventFileName() {
        return eventEventFileName;
    }

    public Results eventEventFileName(String eventEventFileName) {
        this.eventEventFileName = eventEventFileName;
        return this;
    }

    public void setEventEventFileName(String eventEventFileName) {
        this.eventEventFileName = eventEventFileName;
    }

    public String getMetadata() {
        return metadata;
    }

    public Results metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Boolean isPassing() {
        return passing;
    }

    public Results passing(Boolean passing) {
        this.passing = passing;
        return this;
    }

    public void setPassing(Boolean passing) {
        this.passing = passing;
    }

    public String getQfExecutionId() {
        return qfExecutionId;
    }

    public Results qfExecutionId(String qfExecutionId) {
        this.qfExecutionId = qfExecutionId;
        return this;
    }

    public void setQfExecutionId(String qfExecutionId) {
        this.qfExecutionId = qfExecutionId;
    }

    public String getRuleID() {
        return ruleID;
    }

    public Results ruleID(String ruleID) {
        this.ruleID = ruleID;
        return this;
    }

    public void setRuleID(String ruleID) {
        this.ruleID = ruleID;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Results ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getBatchId() {
        return batchId;
    }

    public Results batchId(String batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getRunId() {
        return runId;
    }

    public Results runId(String runId) {
        this.runId = runId;
        return this;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Results)) {
            return false;
        }
        return id != null && id.equals(((Results) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Results{" +
            "id=" + getId() +
            ", assertionName='" + getAssertionName() + "'" +
            ", criticality='" + getCriticality() + "'" +
            ", dateTimeCreated='" + getDateTimeCreated() + "'" +
            ", description='" + getDescription() + "'" +
            ", eventDataFileName='" + getEventDataFileName() + "'" +
            ", eventEventFileName='" + getEventEventFileName() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", passing='" + isPassing() + "'" +
            ", qfExecutionId='" + getQfExecutionId() + "'" +
            ", ruleID='" + getRuleID() + "'" +
            ", ruleName='" + getRuleName() + "'" +
            ", batchId='" + getBatchId() + "'" +
            ", runId='" + getRunId() + "'" +
            "}";
    }
}
