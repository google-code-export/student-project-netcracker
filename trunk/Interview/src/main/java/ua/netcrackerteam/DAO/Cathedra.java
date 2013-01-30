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
@Table(name="CATHEDRA")
public class Cathedra implements Serializable {
    
    //private static final long serialVersionUID = ????
    
    public Cathedra() {        
    }
    
    @Id
    @Column(name="ID_CATHEDRA")
    private Long idCathedra;
    
    @Column(name="NAME")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="ID_FACULTY")
    private Faculty faculty;

    public Long getIdCathedra() {
        return idCathedra;
    }

    public void setIdCathedra(Long idCathedra) {
        this.idCathedra = idCathedra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }  

}
