package ua.netcrackerteam.DAO;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Maksym Zhokha
 */
@Entity
@Table(name="INSTITUTE")
public class Institute implements Serializable {
    //private static final serialVerstionUID = ???
    
    public Institute() {        
    }
    
    @Id
    @Column(name="ID_INSTITUTE")
    private Long instituteId;
    
    @Column(name="NAME")
    private String name;
    
    @OneToMany(mappedBy="idInstitute", fetch = FetchType.EAGER)
    private Set<Form> forms;

    public Long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Form> getForms() {
        return forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }
}
