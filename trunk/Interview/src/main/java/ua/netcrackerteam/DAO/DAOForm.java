package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.Logable;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 24.01.13
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="Form")
public class DAOForm implements Serializable {
    @Id
    @Column(name= "ID_FORM")
    private Long ID_FORM;

    @Column(name= "FIRST_NAME")
    private String FIRST_NAME;

    @Column(name= "LAST_NAME")
    private String LAST_NAME;

    @Column(name= "MIDDLE_NAME")
    private String MIDDLE_NAME;

    @Column(name= "EXEC_PROJECT")
    private String EXEC_PROJECT;

    @Column(name= "REASON")
    private String REASON;

    @Column(name= "EXTRA_INFO")
    private String EXTRA_INFO;

    @Column(name= "INSTITUTE_YEAR")
    private Integer INSTITUTE_YEAR;

    @Column(name= "INSTITUTE_GRAD_YEAR")
    private Date INSTITUTE_GRAD_YEAR;

    @Column(name= "SCHOOL_GRAD_YEAR")
    private Date SCHOOL_GRAD_YEAR;

    @Column(name= "EXTRA_KNOWLEDGE")
    private String EXTRA_KNOWLEDGE;

    @Column(name= "INTEREST_STUDY")
    private String INTEREST_STUDY;

    @Column(name= "INTEREST_WORK")
    private String INTEREST_WORK;

    @Column(name= "INTEREST_SOFTWARE")
    private String INTEREST_SOFTWARE;

    @Column(name= "INTEREST_TELECOM")
    private String INTEREST_TELECOM;

    @Column(name= "AVG_SCORE")
    private Double AVG_SCORE;

    @Column(name= "AVG_LAST")
    private Double AVG_LAST;

    @Column(name= "PHOTO")
    private File PHOTO;
    @Id
    @Column(name= "ID_STATUS")
    private Long ID_STATUS;
    @Id
    @Column(name= "ID_INSTITUTE")
    private Long ID_INSTITUTE;
    @Id
    @Column(name= "ID_SCHOOL")
    private Long ID_SCHOOL;
    @Id
    @Column(name= "ID_USER")
    private Long ID_USER;
    @Id
    @Column(name= "ID_INTERVIEW")
    private Long ID_INTERVIEW;


    public DAOForm() {
    }

    public DAOForm(Long ID_FORM, String FIRST_NAME, String LAST_NAME, String MIDDLE_NAME, String EXEC_PROJECT, String REASON, String EXTRA_INFO, Integer INSTITUTE_YEAR, Date INSTITUTE_GRAD_YEAR, Date SCHOOL_GRAD_YEAR, String EXTRA_KNOWLEDGE, String INTEREST_STUDY, String INTEREST_WORK, String INTEREST_SOFTWARE, String INTEREST_TELECOM, Double AVG_SCORE, Double AVG_LAST, File PHOTO, Long ID_STATUS, Long ID_INSTITUTE, Long ID_SCHOOL, Long ID_USER, Long ID_INTERVIEW) {
        this.ID_FORM = ID_FORM;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.MIDDLE_NAME = MIDDLE_NAME;
        this.EXEC_PROJECT = EXEC_PROJECT;
        this.REASON = REASON;
        this.EXTRA_INFO = EXTRA_INFO;
        this.INSTITUTE_YEAR = INSTITUTE_YEAR;
        this.INSTITUTE_GRAD_YEAR = INSTITUTE_GRAD_YEAR;
        this.SCHOOL_GRAD_YEAR = SCHOOL_GRAD_YEAR;
        this.EXTRA_KNOWLEDGE = EXTRA_KNOWLEDGE;
        this.INTEREST_STUDY = INTEREST_STUDY;
        this.INTEREST_WORK = INTEREST_WORK;
        this.INTEREST_SOFTWARE = INTEREST_SOFTWARE;
        this.INTEREST_TELECOM = INTEREST_TELECOM;
        this.AVG_SCORE = AVG_SCORE;
        this.AVG_LAST = AVG_LAST;
        this.PHOTO = PHOTO;
        this.ID_STATUS = ID_STATUS;
        this.ID_INSTITUTE = ID_INSTITUTE;
        this.ID_SCHOOL = ID_SCHOOL;
        this.ID_USER = ID_USER;
        this.ID_INTERVIEW = ID_INTERVIEW;
    }

    public Long getID_FORM() {
        return ID_FORM;
    }

    public void setID_FOR(Long ID_FORM) {
        this.ID_FORM = ID_FORM;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getMIDDLE_NAME() {
        return MIDDLE_NAME;
    }

    public void setMIDDLE_NAME(String MIDDLE_NAME) {
        this.MIDDLE_NAME = MIDDLE_NAME;
    }

    public String getEXEC_PROJECT() {
        return EXEC_PROJECT;
    }

    public void setEXEC_PROJECT(String EXEC_PROJECT) {
        this.EXEC_PROJECT = EXEC_PROJECT;
    }

    public String getREASON() {
        return REASON;
    }

    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    public String getEXTRA_INFO() {
        return EXTRA_INFO;
    }

    public void setEXTRA_INFO(String EXTRA_INFO) {
        this.EXTRA_INFO = EXTRA_INFO;
    }

    public Integer getINSTITUTE_YEAR() {
        return INSTITUTE_YEAR;
    }

    public void setINSTITUTE_YEAR(Integer INSTITUTE_YEAR) {
        this.INSTITUTE_YEAR = INSTITUTE_YEAR;
    }

    public Date getINSTITUTE_GRAD_YEAR() {
        return INSTITUTE_GRAD_YEAR;
    }

    public void setINSTITUTE_GRAD_YEAR(Date INSTITUTE_GRAD_YEAR) {
        this.INSTITUTE_GRAD_YEAR = INSTITUTE_GRAD_YEAR;
    }

    public Date getSCHOOL_GRAD_YEAR() {
        return SCHOOL_GRAD_YEAR;
    }

    public void setSCHOOL_GRAD_YEAR(Date SCHOOL_GRAD_YEAR) {
        this.SCHOOL_GRAD_YEAR = SCHOOL_GRAD_YEAR;
    }

    public String getEXTRA_KNOWLEDGE() {
        return EXTRA_KNOWLEDGE;
    }

    public void setEXTRA_KNOWLEDGE(String EXTRA_KNOWLEDGE) {
        this.EXTRA_KNOWLEDGE = EXTRA_KNOWLEDGE;
    }

    public String getINTEREST_STUDY() {
        return INTEREST_STUDY;
    }

    public void setINTEREST_STUDY(String INTEREST_STUDY) {
        this.INTEREST_STUDY = INTEREST_STUDY;
    }

    public String getINTEREST_WORK() {
        return INTEREST_WORK;
    }

    public void setINTEREST_WORK(String INTEREST_WORK) {
        this.INTEREST_WORK = INTEREST_WORK;
    }

    public String getINTEREST_SOFTWARE() {
        return INTEREST_SOFTWARE;
    }

    public void setINTEREST_SOFTWARE(String INTEREST_SOFTWARE) {
        this.INTEREST_SOFTWARE = INTEREST_SOFTWARE;
    }

    public String getINTEREST_TELECOM() {
        return INTEREST_TELECOM;
    }

    public void setINTEREST_TELECOM(String INTEREST_TELECOM) {
        this.INTEREST_TELECOM = INTEREST_TELECOM;
    }

    public Double getAVG_SCORE() {
        return AVG_SCORE;
    }

    public void setAVG_SCORE(Double AVG_SCORE) {
        this.AVG_SCORE = AVG_SCORE;
    }

    public Double getAVG_LAST() {
        return AVG_LAST;
    }

    public void setAVG_LAST(Double AVG_LAST) {
        this.AVG_LAST = AVG_LAST;
    }

    public File getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(File PHOTO) {
        this.PHOTO = PHOTO;
    }

    public Long getID_STATUS() {
        return ID_STATUS;
    }

    public void setID_STATUS(Long ID_STATUS) {
        this.ID_STATUS = ID_STATUS;
    }

    public Long getID_INSTITUTE() {
        return ID_INSTITUTE;
    }

    public void setID_INSTITUTE(Long ID_INSTITUTE) {
        this.ID_INSTITUTE = ID_INSTITUTE;
    }

    public Long getID_SCHOOL() {
        return ID_SCHOOL;
    }

    public void setID_SCHOOL(Long ID_SCHOOL) {
        this.ID_SCHOOL = ID_SCHOOL;
    }

    public Long getID_USER() {
        return ID_USER;
    }

    public void setID_USER(Long ID_USER) {
        this.ID_USER = ID_USER;
    }

    public Long getID_INTERVIEW() {
        return ID_INTERVIEW;
    }

    public void setID_INTERVIEW(Long ID_INTERVIEW) {
        this.ID_INTERVIEW = ID_INTERVIEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DAOForm)) return false;

        DAOForm daoForm = (DAOForm) o;

        if (AVG_LAST != null ? !AVG_LAST.equals(daoForm.AVG_LAST) : daoForm.AVG_LAST != null) return false;
        if (AVG_SCORE != null ? !AVG_SCORE.equals(daoForm.AVG_SCORE) : daoForm.AVG_SCORE != null) return false;
        if (EXEC_PROJECT != null ? !EXEC_PROJECT.equals(daoForm.EXEC_PROJECT) : daoForm.EXEC_PROJECT != null)
            return false;
        if (EXTRA_INFO != null ? !EXTRA_INFO.equals(daoForm.EXTRA_INFO) : daoForm.EXTRA_INFO != null) return false;
        if (EXTRA_KNOWLEDGE != null ? !EXTRA_KNOWLEDGE.equals(daoForm.EXTRA_KNOWLEDGE) : daoForm.EXTRA_KNOWLEDGE != null)
            return false;
        if (FIRST_NAME != null ? !FIRST_NAME.equals(daoForm.FIRST_NAME) : daoForm.FIRST_NAME != null) return false;
        if (ID_FORM != null ? !ID_FORM.equals(daoForm.ID_FORM) : daoForm.ID_FORM != null) return false;
        if (ID_INSTITUTE != null ? !ID_INSTITUTE.equals(daoForm.ID_INSTITUTE) : daoForm.ID_INSTITUTE != null)
            return false;
        if (ID_INTERVIEW != null ? !ID_INTERVIEW.equals(daoForm.ID_INTERVIEW) : daoForm.ID_INTERVIEW != null)
            return false;
        if (ID_SCHOOL != null ? !ID_SCHOOL.equals(daoForm.ID_SCHOOL) : daoForm.ID_SCHOOL != null) return false;
        if (ID_STATUS != null ? !ID_STATUS.equals(daoForm.ID_STATUS) : daoForm.ID_STATUS != null) return false;
        if (ID_USER != null ? !ID_USER.equals(daoForm.ID_USER) : daoForm.ID_USER != null) return false;
        if (INSTITUTE_GRAD_YEAR != null ? !INSTITUTE_GRAD_YEAR.equals(daoForm.INSTITUTE_GRAD_YEAR) : daoForm.INSTITUTE_GRAD_YEAR != null)
            return false;
        if (INSTITUTE_YEAR != null ? !INSTITUTE_YEAR.equals(daoForm.INSTITUTE_YEAR) : daoForm.INSTITUTE_YEAR != null)
            return false;
        if (INTEREST_SOFTWARE != null ? !INTEREST_SOFTWARE.equals(daoForm.INTEREST_SOFTWARE) : daoForm.INTEREST_SOFTWARE != null)
            return false;
        if (INTEREST_STUDY != null ? !INTEREST_STUDY.equals(daoForm.INTEREST_STUDY) : daoForm.INTEREST_STUDY != null)
            return false;
        if (INTEREST_TELECOM != null ? !INTEREST_TELECOM.equals(daoForm.INTEREST_TELECOM) : daoForm.INTEREST_TELECOM != null)
            return false;
        if (INTEREST_WORK != null ? !INTEREST_WORK.equals(daoForm.INTEREST_WORK) : daoForm.INTEREST_WORK != null)
            return false;
        if (LAST_NAME != null ? !LAST_NAME.equals(daoForm.LAST_NAME) : daoForm.LAST_NAME != null) return false;
        if (MIDDLE_NAME != null ? !MIDDLE_NAME.equals(daoForm.MIDDLE_NAME) : daoForm.MIDDLE_NAME != null) return false;
        if (PHOTO != null ? !PHOTO.equals(daoForm.PHOTO) : daoForm.PHOTO != null) return false;
        if (REASON != null ? !REASON.equals(daoForm.REASON) : daoForm.REASON != null) return false;
        if (SCHOOL_GRAD_YEAR != null ? !SCHOOL_GRAD_YEAR.equals(daoForm.SCHOOL_GRAD_YEAR) : daoForm.SCHOOL_GRAD_YEAR != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ID_FORM != null ? ID_FORM.hashCode() : 0;
        result = 31 * result + (FIRST_NAME != null ? FIRST_NAME.hashCode() : 0);
        result = 31 * result + (LAST_NAME != null ? LAST_NAME.hashCode() : 0);
        result = 31 * result + (MIDDLE_NAME != null ? MIDDLE_NAME.hashCode() : 0);
        result = 31 * result + (EXEC_PROJECT != null ? EXEC_PROJECT.hashCode() : 0);
        result = 31 * result + (REASON != null ? REASON.hashCode() : 0);
        result = 31 * result + (EXTRA_INFO != null ? EXTRA_INFO.hashCode() : 0);
        result = 31 * result + (INSTITUTE_YEAR != null ? INSTITUTE_YEAR.hashCode() : 0);
        result = 31 * result + (INSTITUTE_GRAD_YEAR != null ? INSTITUTE_GRAD_YEAR.hashCode() : 0);
        result = 31 * result + (SCHOOL_GRAD_YEAR != null ? SCHOOL_GRAD_YEAR.hashCode() : 0);
        result = 31 * result + (EXTRA_KNOWLEDGE != null ? EXTRA_KNOWLEDGE.hashCode() : 0);
        result = 31 * result + (INTEREST_STUDY != null ? INTEREST_STUDY.hashCode() : 0);
        result = 31 * result + (INTEREST_WORK != null ? INTEREST_WORK.hashCode() : 0);
        result = 31 * result + (INTEREST_SOFTWARE != null ? INTEREST_SOFTWARE.hashCode() : 0);
        result = 31 * result + (INTEREST_TELECOM != null ? INTEREST_TELECOM.hashCode() : 0);
        result = 31 * result + (AVG_SCORE != null ? AVG_SCORE.hashCode() : 0);
        result = 31 * result + (AVG_LAST != null ? AVG_LAST.hashCode() : 0);
        result = 31 * result + (PHOTO != null ? PHOTO.hashCode() : 0);
        result = 31 * result + (ID_STATUS != null ? ID_STATUS.hashCode() : 0);
        result = 31 * result + (ID_INSTITUTE != null ? ID_INSTITUTE.hashCode() : 0);
        result = 31 * result + (ID_SCHOOL != null ? ID_SCHOOL.hashCode() : 0);
        result = 31 * result + (ID_USER != null ? ID_USER.hashCode() : 0);
        result = 31 * result + (ID_INTERVIEW != null ? ID_INTERVIEW.hashCode() : 0);
        return result;
    }
}
