package ua.netcrackerteam.DAO;

import javax.persistence.*;
import java.io.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author
 */
@Entity
@Table(name="FORM")
public class Form implements Serializable {
    private static final long serialVersionUID = -3254406057751180001L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "form_seq_gen")
    @SequenceGenerator(name = "form_seq_gen", sequenceName = "form_seq")
    @Column(name= "ID_FORM")
    private Long idForm;

    @Column(name= "FIRST_NAME")
    private String firstName;

    @Column(name= "LAST_NAME")
    private String lastName;

    @Column(name= "MIDDLE_NAME")
    private String middleName;

    @Column(name= "EXEC_PROJECT")
    private String execProject;

    @Column(name= "REASON")
    private String reason;

    @Column(name= "EXTRA_INFO")
    private String extraInfo;

    @Column(name= "INSTITUTE_YEAR")
    private Integer instituteYear;

    @Column(name= "INSTITUTE_GRAD_YEAR")
    private Date instituteGradYear;

    @Column(name= "SCHOOL_GRAD_YEAR")
    private Date schoolGradYear;

    @Column(name= "EXTRA_KNOWLEDGE")
    private String extraKnowledge;

    @Column(name= "INTEREST_STUDY")
    private String interestStudy;

    @Column(name= "INTEREST_WORK")
    private String interestWork;

    @Column(name= "INTEREST_SOFTWARE")
    private String interestSoftware;

    @Column(name= "INTEREST_TELECOM")
    private String interestTelecom;

    @Column(name= "AVG_SCORE")
    private Double avgScore;

    @Column(name= "AVG_LAST")
    private Double avgLast;

    @Column(name= "PHOTO")
    private String photo;

    @Column(name= "ID_STATUS")
    private Long idStatus;

    @Column(name= "ID_INSTITUTE")
    private Long idInstitute;

    @Column(name= "ID_SCHOOL")
    private Long idSchool;

    @Column(name= "ID_USER")
    private Long idUser;

    @Column(name= "ID_INTERVIEW")
    private Long idInterview;

    //@Transient
    @OneToMany(mappedBy= "idForm", fetch = FetchType.EAGER )
    private Set<Contact> contacts;

    public Form() {
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Long getIdForm() {
        return idForm;
    }

    public void setIdForm(Long idForm) {
        this.idForm = idForm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getExecProject() {
        return execProject;
    }

    public void setExecProject(String execProject) {
        this.execProject = execProject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Integer getInstituteYear() {
        return instituteYear;
    }

    public void setInstituteYear(Integer instituteYear) {
        this.instituteYear = instituteYear;
    }

    public Date getInstituteGradYear() {
        return instituteGradYear;
    }

    public void setInstituteGradYear(Date instituteGradYear) {
        this.instituteGradYear = instituteGradYear;
    }

    public Date getSchoolGradYear() {
        return schoolGradYear;
    }

    public void setSchoolGradYear(Date schoolGradYear) {
        this.schoolGradYear = schoolGradYear;
    }

    public String getExtraKnowledge() {
        return extraKnowledge;
    }

    public void setExtraKnowledge(String extraKnowledge) {
        this.extraKnowledge = extraKnowledge;
    }

    public String getInterestStudy() {
        return interestStudy;
    }

    public void setInterestStudy(String interestStudy) {
        this.interestStudy = interestStudy;
    }

    public String getInterestWork() {
        return interestWork;
    }

    public void setInterestWork(String interestWork) {
        this.interestWork = interestWork;
    }

    public String getInterestSoftware() {
        return interestSoftware;
    }

    public void setInterestSoftware(String interestSoftware) {
        this.interestSoftware = interestSoftware;
    }

    public String getInterestTelecom() {
        return interestTelecom;
    }

    public void setInterestTelecom(String interestTelecom) {
        this.interestTelecom = interestTelecom;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Double getAvgLast() {
        return avgLast;
    }

    public void setAvgLast(Double avgLast) {
        this.avgLast = avgLast;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdInstitute() {
        return idInstitute;
    }

    public void setIdInstitute(Long idInstitute) {
        this.idInstitute = idInstitute;
    }

    public Long getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(Long idSchool) {
        this.idSchool = idSchool;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(Long idInterview) {
        this.idInterview = idInterview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        if (avgLast != null ? !avgLast.equals(form.avgLast) : form.avgLast != null) return false;
        if (avgScore != null ? !avgScore.equals(form.avgScore) : form.avgScore != null) return false;
        if (execProject != null ? !execProject.equals(form.execProject) : form.execProject != null)
            return false;
        if (extraInfo != null ? !extraInfo.equals(form.extraInfo) : form.extraInfo != null) return false;
        if (extraKnowledge != null ? !extraKnowledge.equals(form.extraKnowledge) : form.extraKnowledge != null)
            return false;
        if (firstName != null ? !firstName.equals(form.firstName) : form.firstName != null) return false;
        if (idForm != null ? !idForm.equals(form.idForm) : form.idForm != null) return false;
        if (idInstitute != null ? !idInstitute.equals(form.idInstitute) : form.idInstitute != null)
            return false;
        if (idInterview != null ? !idInterview.equals(form.idInterview) : form.idInterview != null)
            return false;
        if (idSchool != null ? !idSchool.equals(form.idSchool) : form.idSchool != null) return false;
        if (idStatus != null ? !idStatus.equals(form.idStatus) : form.idStatus != null) return false;
        if (idUser != null ? !idUser.equals(form.idUser) : form.idUser != null) return false;
        if (instituteGradYear != null ? !instituteGradYear.equals(form.instituteGradYear) : form.instituteGradYear != null)
            return false;
        if (instituteYear != null ? !instituteYear.equals(form.instituteYear) : form.instituteYear != null)
            return false;
        if (interestSoftware != null ? !interestSoftware.equals(form.interestSoftware) : form.interestSoftware != null)
            return false;
        if (interestStudy != null ? !interestStudy.equals(form.interestStudy) : form.interestStudy != null)
            return false;
        if (interestTelecom != null ? !interestTelecom.equals(form.interestTelecom) : form.interestTelecom != null)
            return false;
        if (interestWork != null ? !interestWork.equals(form.interestWork) : form.interestWork != null)
            return false;
        if (lastName != null ? !lastName.equals(form.lastName) : form.lastName != null) return false;
        if (middleName != null ? !middleName.equals(form.middleName) : form.middleName != null) return false;
        if (photo != null ? !photo.equals(form.photo) : form.photo != null) return false;
        if (reason != null ? !reason.equals(form.reason) : form.reason != null) return false;
        if (schoolGradYear != null ? !schoolGradYear.equals(form.schoolGradYear) : form.schoolGradYear != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idForm != null ? idForm.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (execProject != null ? execProject.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (extraInfo != null ? extraInfo.hashCode() : 0);
        result = 31 * result + (instituteYear != null ? instituteYear.hashCode() : 0);
        result = 31 * result + (instituteGradYear != null ? instituteGradYear.hashCode() : 0);
        result = 31 * result + (schoolGradYear != null ? schoolGradYear.hashCode() : 0);
        result = 31 * result + (extraKnowledge != null ? extraKnowledge.hashCode() : 0);
        result = 31 * result + (interestStudy != null ? interestStudy.hashCode() : 0);
        result = 31 * result + (interestWork != null ? interestWork.hashCode() : 0);
        result = 31 * result + (interestSoftware != null ? interestSoftware.hashCode() : 0);
        result = 31 * result + (interestTelecom != null ? interestTelecom.hashCode() : 0);
        result = 31 * result + (avgScore != null ? avgScore.hashCode() : 0);
        result = 31 * result + (avgLast != null ? avgLast.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (idStatus != null ? idStatus.hashCode() : 0);
        result = 31 * result + (idInstitute != null ? idInstitute.hashCode() : 0);
        result = 31 * result + (idSchool != null ? idSchool.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (idInterview != null ? idInterview.hashCode() : 0);
        return result;
    }

}
