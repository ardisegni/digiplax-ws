package org.worldemunah.digiplax.persistence.main;

import java.io.Serializable;
import java.util.Objects;

/**
 * User: Ariel
 * Date: 5/28/2019
 */
/*
@Entity
@Table(name = "background_type")
@NamedQueries({
        @NamedQuery(name = "BackgroundType.findByCode",
                query = " SELECT  b" +
                        " FROM    BackgroundType b" +
                        " WHERE   b.id = :code"
        )
})
*/
public class BackgroundType implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    */private int id;

    //    @Column(name = "image_file_name", length = 255, nullable = false)
    private String imageFileName;

    protected BackgroundType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BackgroundType)) {
            return false;
        }
        BackgroundType that = (BackgroundType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
