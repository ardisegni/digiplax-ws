package org.worldemunah.digiplax.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * User: Ariel
 * Date: 5/10/2019
 */
public class PlaqueModel implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private Long identifier;
    private String donorFirstName;
    private String donorLastName;
    private String honoreeFirstName;
    private String honoreeLastName;
    private String plaqueText;
    private String plaqueHtmlText;
    private Long projectId;
    private String projectName;

    public PlaqueModel() {
    }

    public PlaqueModel(Long id,
                       Long identifier,
                       String donorFirstName,
                       String donorLastName,
                       String honoreeFirstName,
                       String honoreeLastName,
                       String plaqueText,
                       String plaqueHtmlText,
                       Long projectId,
                       String projectName) {
        this.id = id;
        this.identifier = identifier;
        this.donorFirstName = donorFirstName;
        this.donorLastName = donorLastName;
        this.honoreeFirstName = honoreeFirstName;
        this.honoreeLastName = honoreeLastName;
        this.plaqueText = plaqueText;
        this.plaqueHtmlText = plaqueHtmlText;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getDonorFirstName() {
        return donorFirstName;
    }

    public void setDonorFirstName(String donorFirstName) {
        this.donorFirstName = donorFirstName;
    }

    public String getDonorLastName() {
        return donorLastName;
    }

    public void setDonorLastName(String donorLastName) {
        this.donorLastName = donorLastName;
    }

    public String getHonoreeFirstName() {
        return honoreeFirstName;
    }

    public void setHonoreeFirstName(String honoreeFirstName) {
        this.honoreeFirstName = honoreeFirstName;
    }

    public String getHonoreeLastName() {
        return honoreeLastName;
    }

    public void setHonoreeLastName(String honoreeLastName) {
        this.honoreeLastName = honoreeLastName;
    }

    public String getPlaqueText() {
        return plaqueText;
    }

    public void setPlaqueText(String plaqueText) {
        this.plaqueText = plaqueText;
    }

    public String getPlaqueHtmlText() {
        return plaqueHtmlText;
    }

    public void setPlaqueHtmlText(String plaqueHtmlText) {
        this.plaqueHtmlText = plaqueHtmlText;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "PlaqueModel{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", donorFirstName='" + donorFirstName + '\'' +
                ", donorLastName='" + donorLastName + '\'' +
                ", honoreeFirstName='" + honoreeFirstName + '\'' +
                ", honoreeLastName='" + honoreeLastName + '\'' +
                ", plaqueText='" + plaqueText + '\'' +
                ", plaqueHtmlText='" + plaqueHtmlText + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
