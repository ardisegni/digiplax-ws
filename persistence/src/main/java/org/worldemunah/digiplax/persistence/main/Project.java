package org.worldemunah.digiplax.persistence.main;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User: Ariel
 * Date: 4/11/2019
 *
 * CREATE TABLE `digiplax`.`project` (
 * `project_key` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
 * `name` VARCHAR(45) NOT NULL,
 * `timezone` VARCHAR(255) NULL,
 * `url_param` VARCHAR(20) NOT NULL,
 * `date_created` DATETIME NOT NULL,
 * `fk_user` INT(11) UNSIGNED NOT NULL,
 * `active_flag` TINYINT(1) UNSIGNED NOT NULL,
 * PRIMARY KEY (`project_key`),
 * INDEX `project_fk1_idx` (`fk_user` ASC),
 * CONSTRAINT `project_fk1`
 * FOREIGN KEY (`fk_user`)
 * REFERENCES `digiplax`.`bo_user` (`bo_user_key`)
 * ON DELETE NO ACTION
 * ON UPDATE NO ACTION)
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8;
 */
@Entity
@Table(name = "project")
@NamedQueries({
        @NamedQuery(name = "Project.listAll",
                query = " SELECT   p" +
                        " FROM     Project p" +
                        " ORDER BY p.dateCreated DESC"
        ),
        @NamedQuery(name = "Project.findByName",
                query = " SELECT  p" +
                        " FROM    Project p" +
                        " WHERE   p.name = :name"
        )
})
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_key")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "timezone", length = 45, nullable = true)
    private String timezone;

    @Column(name = "url_param", length = 20, nullable = false)
    private String urlParam;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "fk_user", nullable = false)
    private long userKey;

    @Column(name = "active_flag", nullable = false)
    private boolean isActive;

    protected Project() {
    }

    public Project(String name) {
        this.name = name;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getUserKey() {
        return userKey;
    }

    public void setUserKey(long userKey) {
        this.userKey = userKey;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return id.equals(project.id) &&
                name.equals(project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", urlParam='" + urlParam + '\'' +
                ", dateCreated=" + dateCreated +
                ", userKey=" + userKey +
                ", isActive=" + isActive +
                '}';
    }

}
