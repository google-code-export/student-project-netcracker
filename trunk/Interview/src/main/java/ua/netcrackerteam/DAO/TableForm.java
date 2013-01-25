package ua.netcrackerteam.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;
import java.sql.Date;

/**
 * @author
 */
@Entity
@Table(name="FORM")
public class TableForm {
    @Id
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
    private File photo;

    @Id
    @Column(name= "ID_STATUS")
    private Long idStatus;

    @Id
    @Column(name= "ID_INSTITUTE")
    private Long idInstitute;

    @Id
    @Column(name= "ID_SCHOOL")
    private Long idSchool;

    @Id
    @Column(name= "ID_USER")
    private Long idUser;

    @Id
    @Column(name= "ID_INTERVIEW")
    private Long idInterview;

    public TableForm() {
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

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
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
}
