package ua.netcrackerteam.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 01.02.13
 * Time: 1:23
 * To change this template use File | Settings | File Templates.
 */

/**
 * @author Filipenko
 */
@Entity
@Table(name = "USER_CATEGORY")
public class UserCategory implements Serializable {

    @Id
    @Column(name= "ID_USER_CATEGORY")
    private Long idUSerCategory;

    @Column(name= "CATEGORY")
    private String name;

    public Long getIdUSerCategory() {
        return idUSerCategory;
    }

    public void setIdUSerCategory(Long idUSerCategory) {
        this.idUSerCategory = idUSerCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
