package org.worldemunah.digiplax.persistence.main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User: Ariel
 * Date: 6/4/2019
 *
 *
 * CREATE TABLE `digiplax`.`scheduled_plaque` (
 * `scheduled_plaque_key` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
 * `plaque_key` INT(11) UNSIGNED NOT NULL,
 * `date_from` DATETIME NOT NULL,
 * `date_to` DATETIME NOT NULL,
 * `priority` SMALLINT(6) UNSIGNED NULL,
 * PRIMARY KEY (`scheduled_plaque_key`),
 * INDEX `scheduled_plaque_fk1_idx` (`plaque_key` ASC),
 * CONSTRAINT `scheduled_plaque_fk1`
 * FOREIGN KEY (`plaque_key`)
 * REFERENCES `digiplax`.`plaque` (`plaque_key`)
 * ON DELETE NO ACTION
 * ON UPDATE NO ACTION)
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8;
 */
@Entity
@Table(name = "scheduled_plaque")
@NamedQueries({
        @NamedQuery(name = "ScheduledPlaque.listActive",
                query = " SELECT   sp" +
                        " FROM     ScheduledPlaque sp" +
                        " WHERE    :currentDateTime BETWEEN sp.dateFrom AND sp.dateTo" +
                        " AND      sp.plaque.project.id = :projectId" +
                        " ORDER BY sp.dateFrom DESC"
        ),
        @NamedQuery(name = "ScheduledPlaque.listScheduled",
                query = " SELECT   sp" +
                        " FROM     ScheduledPlaque sp" +
                        " WHERE    :currentDateTime < sp.dateTo" +
                        " ORDER BY sp.dateFrom DESC"
        ),
        @NamedQuery(name = "ScheduledPlaque.listPast",
                query = " SELECT   sp" +
                        " FROM     ScheduledPlaque sp" +
                        " WHERE    :currentDateTime > sp.dateTo" +
                        " ORDER BY sp.dateFrom DESC"
        ),
        @NamedQuery(name = "ScheduledPlaque.listByPlaque",
                query = " SELECT  sp" +
                        " FROM    ScheduledPlaque sp" +
                        " WHERE   sp.plaque.id = :plaqueId"
        )
})
public class ScheduledPlaque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduled_plaque_key", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plaque_key", nullable = false)
    private Plaque plaque;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "priority", nullable = false)
    private int priority;

    public ScheduledPlaque() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plaque getPlaque() {
        return plaque;
    }

    public void setPlaque(Plaque plaque) {
        this.plaque = plaque;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScheduledPlaque)) {
            return false;
        }
        ScheduledPlaque that = (ScheduledPlaque) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ScheduledPlaque{" +
                "id=" + id +
                ", plaque=" + plaque +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", priority=" + priority +
                '}';
    }

}
