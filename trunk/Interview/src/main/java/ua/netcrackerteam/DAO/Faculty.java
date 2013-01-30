/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.netcrackerteam.DAO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="FACULTY")
public class Faculty implements Serializable {
    //private static final long serialVersionUID = ????
    
    public Faculty() {        
    }
    
    @Id
    @Column(name = "ID_FACULTY")
    private Long idFaculty;
    
    @Column(name = "NAME")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="ID_INSTITUTE")
    private Institute institute;

    public Long getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Long idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
    
}
