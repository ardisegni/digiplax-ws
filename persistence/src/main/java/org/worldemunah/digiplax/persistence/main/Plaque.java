package org.worldemunah.digiplax.persistence.main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User: Ariel
 * Date: 4/11/2019
 *
 * CREATE TABLE `plaque` (
 * `plaque_key` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * `identifier` int(11) unsigned NOT NULL,
 * `donor_first_name` varchar(255) DEFAULT NULL,
 * `donor_last_name` varchar(255) DEFAULT NULL,
 * `honoree_first_name` varchar(255) DEFAULT NULL,
 * `honoree_last_name` varchar(255) DEFAULT NULL,
 * `plaque_type` smallint(2) unsigned NOT NULL,
 * `plaque_text` varchar(4000) NOT NULL,
 * `details` varchar(255) DEFAULT NULL,
 * `location` varchar(45) DEFAULT NULL,
 * `time_on_screen` int(11) unsigned DEFAULT NULL,
 * `background_type` smallint(2) unsigned NOT NULL,
 * `display_status` smallint(2) unsigned NOT NULL,
 * `date_created` datetime NOT NULL,
 * `date_updated` datetime DEFAULT NULL,
 * `fk_user` int(11) unsigned DEFAULT NULL,
 * PRIMARY KEY (`plaque_key`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=693 DEFAULT CHARSET=utf8;
 *
 * ALTER TABLE `digiplax`.`plaque`
 * ADD COLUMN `plaque_html_text` VARCHAR(4000) NULL DEFAULT NULL AFTER `plaque_text`;
 *
 * ALTER TABLE `digiplax`.`plaque`
 * ADD COLUMN `fk_project` INT(11) UNSIGNED NULL AFTER `fk_user`,
 * ADD INDEX `plaque_fk1_idx` (`fk_user` ASC),
 * ADD INDEX `plaque_fk2_idx` (`fk_project` ASC);
 * ALTER TABLE `digiplax`.`plaque`
 * ADD CONSTRAINT `plaque_fk1`
 * FOREIGN KEY (`fk_user`)
 * REFERENCES `digiplax`.`bo_user` (`bo_user_key`)
 * ON DELETE NO ACTION
 * ON UPDATE NO ACTION,
 * ADD CONSTRAINT `plaque_fk2`
 * FOREIGN KEY (`fk_project`)
 * REFERENCES `digiplax`.`project` (`project_key`)
 * ON DELETE NO ACTION
 * ON UPDATE NO ACTION;
 */
@Entity
@Table(name = "plaque")
@NamedQueries({
        @NamedQuery(name = "Plaque.listByProject",
                query = " SELECT   p" +
                        " FROM     Plaque p" +
                        " WHERE    p.project.id = :projectId" +
                        " ORDER BY p.identifier"
        ),
        @NamedQuery(name = "Plaque.findByIdentifier",
                query = " SELECT  p" +
                        " FROM    Plaque p" +
                        " WHERE   p.identifier = :identifier"
        )
})
public class Plaque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plaque_key")
    private Long id;

    @Column(name = "identifier", nullable = false)
    private Long identifier;

    @Column(name = "donor_first_name", length = 255, nullable = true)
    private String donorFirstName;

    @Column(name = "donor_last_name", length = 255, nullable = true)
    private String donorLastName;

    @Column(name = "honoree_first_name", length = 255, nullable = true)
    private String honoreeFirstName;

    @Column(name = "honoree_last_name", length = 255, nullable = true)
    private String honoreeLastName;

    @Column(name = "plaque_type", nullable = false)
    private Integer plaqueType;

    @Column(name = "background_type", nullable = false)
    private Integer backgroundType;

    @Column(name = "display_status", nullable = false)
    private Integer displayStatus;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_updated", nullable = true)
    private Date dateUpdated;

    @Column(name = "plaque_text", length = 4000, nullable = true)
    private String plaqueText;

    @Column(name = "plaque_html_text", length = 4000, nullable = true)
    private String plaqueHtmlText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_project", referencedColumnName = "project_key", nullable = false)
    private Project project;

    public Plaque() {
        this.plaqueType = 1;
        this.backgroundType = 1;
        this.displayStatus = 1;
        this.dateCreated = new Date(System.currentTimeMillis());
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

    public Integer getPlaqueType() {
        return plaqueType;
    }

    public void setPlaqueType(Integer plaqueType) {
        this.plaqueType = plaqueType;
    }

    public Integer getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(Integer backgroundType) {
        this.backgroundType = backgroundType;
    }

    public Integer getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(Integer displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plaque)) {
            return false;
        }
        Plaque plaque = (Plaque) o;
        return id.equals(plaque.id) &&
                identifier.equals(plaque.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier);
    }
}
