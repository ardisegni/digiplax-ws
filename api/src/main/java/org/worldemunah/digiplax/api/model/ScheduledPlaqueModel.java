package org.worldemunah.digiplax.api.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Ariel
 * Date: 9/13/2019
 */
public class ScheduledPlaqueModel implements Serializable {

    private Long id;

    @NotNull
    private Date dateFrom;

    @NotNull
    private Date dateTo;

    @NotNull
    private PlaqueModel plaque;

    private Integer priority;

    protected ScheduledPlaqueModel() {
    }

    public ScheduledPlaqueModel(Long id, Date dateFrom, Date dateTo, PlaqueModel plaque, int priority) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.plaque = plaque;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public PlaqueModel getPlaque() {
        return plaque;
    }

    public void setPlaque(PlaqueModel plaque) {
        this.plaque = plaque;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ScheduledPlaqueModel{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", plaque=" + plaque +
                ", priority=" + priority +
                '}';
    }
}
