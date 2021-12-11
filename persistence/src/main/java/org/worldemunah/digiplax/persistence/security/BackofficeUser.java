package org.worldemunah.digiplax.persistence.security;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * User: Ariel
 * Date: 4/24/2019
 *
 *
 * CREATE TABLE `digiplax`.`bo_user` (
 * `bo_user_key` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
 * `first_name` VARCHAR(45) NOT NULL,
 * `last_name` VARCHAR(45) NOT NULL,
 * `email_address` VARCHAR(50) NOT NULL,
 * `password` VARCHAR(8) NOT NULL,
 * `phone_num` VARCHAR(20) NULL,
 * `last_success_login` DATETIME NULL,
 * `super_user_flag` TINYINT(1) NOT NULL DEFAULT 0,
 * PRIMARY KEY (`bo_user_key`))
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8;
 *
 * ALTER TABLE `digiplax`.`bo_user`
 * ADD UNIQUE INDEX `bo_user_u1` (`email_address` ASC)
 *
 * INSERT INTO `digiplax`.`bo_user` (`bo_user_key`, `first_name`, `last_name`, `email_address`, `password`, `phone_num`, `super_user_flag`)
 * VALUES ('2', 'Ariel', 'Di Segni', 'ardisegni@gmail.com', 'abc123', '0532354447', '1');
 */
@Entity
@Table(name = "bo_user")
@NamedQueries({
        @NamedQuery(name = "BackofficeUser.findByEmailAddress",
                query = " SELECT u" +
                        " FROM   BackofficeUser u" +
                        " WHERE  u.emailAddress = :emailAddress"
        ),
        @NamedQuery(name = "BackofficeUser.listAll",
                query = " SELECT  u" +
                        " FROM    BackofficeUser u"
        )
})
public class BackofficeUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bo_user_key")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "email_address", nullable = false, length = 50)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 8)
    private String password;

    @Column(name = "phone_num", nullable = true, length = 20)
    private String phoneNum;

    @Column(name = "last_success_login", nullable = true)
    private Date lastSuccessLogin;

    @Column(name = "super_user_flag", nullable = false)
    private boolean superUserFlag;

    protected BackofficeUser() {
    }

    public BackofficeUser(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getLastSuccessLogin() {
        return lastSuccessLogin;
    }

    public void setLastSuccessLogin(Date lastSuccessLogin) {
        this.lastSuccessLogin = lastSuccessLogin;
    }

    public boolean isSuperUserFlag() {
        return superUserFlag;
    }

    public void setSuperUserFlag(boolean superUserFlag) {
        this.superUserFlag = superUserFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BackofficeUser)) {
            return false;
        }
        BackofficeUser that = (BackofficeUser) o;
        return id.equals(that.id) &&
                emailAddress.equals(that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailAddress);
    }
}
