package ua.netcrackerteam.DAO;

/**
 * Created with IntelliJ IDEA.
 * User: Filipenko
 * Date: 01.02.13
 * Time: 0:35
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Filipenko
 */
@Entity
@Table(name="BRANCH")
public class Branch implements Serializable {

    @Id
    @Column(name= "ID_BRANCH")
    private Long idBranch;

    @Column(name= "NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    public Long getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Long idBranch) {
        this.idBranch = idBranch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}


