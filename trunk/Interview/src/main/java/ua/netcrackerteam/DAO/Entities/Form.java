package ua.netcrackerteam.DAO.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
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
    private int idForm;

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
    private Integer instituteGradYear;

    @Column(name= "EXTRA_KNOWLEDGE")
    private String extraKnowledge;

    @Column(name= "INTEREST_STUDY")
    private String interestStudy;

    @Column(name= "INTEREST_WORK")
    private String interestWork;

    @Column(name= "INTEREST_BRANCH_SOFT")
    private String interestBranchSoft;

    @Column(name= "INTEREST_BRANCH_OTHER")
    private String interestBranchOther;
    
    @Column(name="INTEREST_OTHER")
    private String interestOther;
    
    @Column(name="INTEREST_DEEP_SPEC")
    private String interestDeepSpec;

    @Column(name="INTEREST_VARIOUS")
    private String interestVarious;
    
    @Column(name="INTEREST_MANAGMENT")
    private String interestManagment;
    
    @Column(name="INTEREST_SALE")
    private String interestSale;

    @Column(name = "PHOTO")    
    private byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_STATUS")
    private Status status;
    
    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_STATUS_ATTEND")
    private Status statusAttend;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_CATHEDRA")
    private Cathedra cathedra;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_FACULTY")
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_INSTITUTE")
    private Institute institute;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_USER")
    private UserList user;

    @ManyToOne(fetch = FetchType.EAGER,optional=true)
    @JoinColumn(name = "ID_INTERVIEW")
    private Interview interview;    

    @OneToMany(mappedBy= "form", fetch = FetchType.EAGER )
    private Set<Contact> contacts;
    
    @OneToMany(mappedBy= "form", fetch = FetchType.EAGER )
    private Set<Knowledge> knowledges;
    
    @OneToMany(mappedBy= "form", fetch = FetchType.EAGER )
    private Set<Advert> adverts = new LinkedHashSet();

    public Form() {
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    
    public Set<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(Set<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        this.cathedra = cathedra;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
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

    public Integer getInstituteGradYear() {
        return instituteGradYear;
    }

    public void setInstituteGradYear(Integer instituteGradYear) {
        this.instituteGradYear = instituteGradYear;
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

    public String getInterestBranchSoft() {
        return interestBranchSoft;
    }

    public void setInterestBranchSoft(String interestSoftware) {
        this.interestBranchSoft = interestSoftware;
    }

    public String getInterestBranchOther() {
        return interestBranchOther;
    }

    public void setInterestBranchOther(String interestBranchOther) {
        this.interestBranchOther = interestBranchOther;
    }
    
    public String getInterestOther() {
        return interestOther;
    }

    public void setInterestOther(String interestOther) {
        this.interestOther = interestOther;
    }
    
    public String getInterestDeepSpec() {
        return interestDeepSpec;
    }

    public void setInterestDeepSpec(String interestDeepSpec) {
        this.interestDeepSpec = interestDeepSpec;
    }

    public String getInterestVarious() {
        return interestVarious;
    }

    public void setInterestVarious(String interestVarious) {
        this.interestVarious = interestVarious;
    }

    public String getInterestManagment() {
        return interestManagment;
    }

    public void setInterestManagment(String interestManagment) {
        this.interestManagment = interestManagment;
    }

    public String getInterestSale() {
        return interestSale;
    }

    public void setInterestSale(String interestSale) {
        this.interestSale = interestSale;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }    

    public UserList getUser() {
        return user;
    }

    public void setUser(UserList user) {
        this.user = user;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }
    
    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    public Status getStatusAttend() {
        return statusAttend;
    }

    public void setStatusAttend(Status statusAttend) {
        this.statusAttend = statusAttend;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
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
        if (!(o instanceof Form)) return false;

        Form form = (Form) o;

        if (idForm != form.idForm) return false;     
//        if (contacts != null ? !contacts.equals(form.contacts) : form.contacts != null) return false;
        if (execProject != null ? !execProject.equals(form.execProject) : form.execProject != null) return false;
        if (extraInfo != null ? !extraInfo.equals(form.extraInfo) : form.extraInfo != null) return false;
        if (extraKnowledge != null ? !extraKnowledge.equals(form.extraKnowledge) : form.extraKnowledge != null)
            return false;
        if (firstName != null ? !firstName.equals(form.firstName) : form.firstName != null) return false;
//        if (institute != null ? !institute.equals(form.institute) : form.institute != null) return false;
        if (interview != null ? !interview.equals(form.interview) : form.interview != null) return false;
        if (status != null ? !status.equals(form.status) : form.status != null) return false;
        if (user != null ? !user.equals(form.user) : form.user != null) return false;
        if (instituteGradYear != null ? !instituteGradYear.equals(form.instituteGradYear) : form.instituteGradYear != null)
            return false;
        if (instituteYear != null ? !instituteYear.equals(form.instituteYear) : form.instituteYear != null)
            return false;
        if (interestBranchSoft != null ? !interestBranchSoft.equals(form.interestBranchSoft) : form.interestBranchSoft != null)
            return false;
        if (interestStudy != null ? !interestStudy.equals(form.interestStudy) : form.interestStudy != null)
            return false;
        if (interestBranchOther != null ? !interestBranchOther.equals(form.interestBranchOther) : form.interestBranchOther != null)
            return false;
        if (interestWork != null ? !interestWork.equals(form.interestWork) : form.interestWork != null) return false;
        if (lastName != null ? !lastName.equals(form.lastName) : form.lastName != null) return false;
        if (middleName != null ? !middleName.equals(form.middleName) : form.middleName != null) return false;
//        if (photo != null ? !photo.equals(form.photo) : form.photo != null) return false;
        if (reason != null ? !reason.equals(form.reason) : form.reason != null) return false;
        if (photo != null ? !photo.equals(form.photo) : form.photo != null) return false;
        if (reason != null ? !reason.equals(form.reason) : form.reason != null)
            return false;
        if (statusAttend != null ? !statusAttend.equals(form.statusAttend) : form.statusAttend != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idForm;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (execProject != null ? execProject.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (extraInfo != null ? extraInfo.hashCode() : 0);
        result = 31 * result + (instituteYear != null ? instituteYear.hashCode() : 0);
        result = 31 * result + (instituteGradYear != null ? instituteGradYear.hashCode() : 0);
        result = 31 * result + (extraKnowledge != null ? extraKnowledge.hashCode() : 0);
        result = 31 * result + (interestStudy != null ? interestStudy.hashCode() : 0);
        result = 31 * result + (interestWork != null ? interestWork.hashCode() : 0);
        result = 31 * result + (interestBranchSoft != null ? interestBranchSoft.hashCode() : 0);
        result = 31 * result + (interestBranchOther != null ? interestBranchOther.hashCode() : 0);  
//        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (interview != null ? interview.hashCode() : 0);
//        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (statusAttend != null ? statusAttend.hashCode() : 0);
        return result;
    }    

    

    
}
