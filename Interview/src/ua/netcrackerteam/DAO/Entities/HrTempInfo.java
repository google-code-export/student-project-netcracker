package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author krygin
 */
@Entity
@Table(name="HR_TEMP_INFO")
public class HrTempInfo implements Serializable {
    private static final long serialVersionUID = -3231116055681222451L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "hr_temp_info_seq_gen")
    @SequenceGenerator(name = "hr_temp_info_seq_gen", sequenceName = "hr_temp_info_seq")
    @Column(name= "id_hr_temp_info")
    private int idHrTempInfo;

    @ManyToOne
    @JoinColumn(name = "ID_FORM")
    private Form form;

    @Column(name= "INSTITUTE_NAME")
    private String instituteName;

    @Column(name= "FACULTY_NAME")
    private String facultyName;

    @Column(name= "CATHEDRA_NAME")
    private String cathedraName;

    public int getIdHrTempInfo() {
        return idHrTempInfo;
    }

    public void setIdHrTempInfo(int idHrTempInfo) {
        this.idHrTempInfo = idHrTempInfo;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getCathedraName() {
        return cathedraName;
    }

    public void setCathedraName(String cathedraName) {
        this.cathedraName = cathedraName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HrTempInfo that = (HrTempInfo) o;

        if (idHrTempInfo != that.idHrTempInfo) return false;
        if (cathedraName != null ? !cathedraName.equals(that.cathedraName) : that.cathedraName != null) return false;
        if (facultyName != null ? !facultyName.equals(that.facultyName) : that.facultyName != null) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (instituteName != null ? !instituteName.equals(that.instituteName) : that.instituteName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHrTempInfo;
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (instituteName != null ? instituteName.hashCode() : 0);
        result = 31 * result + (facultyName != null ? facultyName.hashCode() : 0);
        result = 31 * result + (cathedraName != null ? cathedraName.hashCode() : 0);
        return result;
    }
}
