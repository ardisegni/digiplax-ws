package org.worldemunah.digiplax.api.model;

import java.io.Serializable;

/**
 * User: Ariel
 * Date: 9/20/2019
 */
public class ProjectModel implements Serializable {

    private Long id;
    private String name;
    private String timezone;
    private String urlParam;

    protected ProjectModel() {
    }

    public ProjectModel(Long id, String name, String timezone, String urlParam) {
        this.id = id;
        this.name = name;
        this.timezone = timezone;
        this.urlParam = urlParam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", urlParam='" + urlParam + '\'' +
                '}';
    }
}
