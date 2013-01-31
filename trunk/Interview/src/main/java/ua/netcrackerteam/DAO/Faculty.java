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
    private static final long serialVersionUID = -3254400077751181181L;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (idFaculty != null ? !idFaculty.equals(faculty.idFaculty) : faculty.idFaculty != null) return false;
        if (institute != null ? !institute.equals(faculty.institute) : faculty.institute != null) return false;
        if (name != null ? !name.equals(faculty.name) : faculty.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFaculty != null ? idFaculty.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        return result;
    }
}
