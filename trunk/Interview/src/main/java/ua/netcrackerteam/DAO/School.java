/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.netcrackerteam.DAO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="SCHOOL")
public class School implements Serializable {
    //private static final long serialVersionUID = ???
    
    @Id
    @Column(name="ID_SHOOL")
    private Long idSchool;
    
    @Column(name="NAME")
    private String name;

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
