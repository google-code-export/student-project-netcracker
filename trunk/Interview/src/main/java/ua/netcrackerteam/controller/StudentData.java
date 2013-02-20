package ua.netcrackerteam.controller;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import ua.netcrackerteam.DAO.Cathedra;
import ua.netcrackerteam.DAO.Faculty;
import ua.netcrackerteam.DAO.Institute;

/**
 * Class-controller for Student Entity with necessary information for use in GUI
 * @author krygin
 */
public class StudentData {
    private int idForm;
    private String studentLastName = "";
    private String studentFirstName = "";
    private String studentMiddleName = "";
    private Institute studentInstitute;
    private String studentInstituteCourse = "";
    private Faculty studentFaculty;
    private Cathedra studentCathedra;
    private String studentInstituteGradYear = "";
    private String studentEmailFirst = "";
    private String studentEmailSecond = "";
    private String studentTelephone = "";
    private String studentOtherContactType = "";
    private String studentOtherContact = "";
    private String studentInterestStudy = "";
    private String studentInterestWork = "";
    private String studentInterestDevelopment = "";
    private String studentInterestOther = "";
    private String studentWorkTypeDeepSpec = "";
    private String studentWorkTypeVarious = "";
    private String studentWorkTypeManagement = "";
    private String studentWorkTypeSale = "";
    private String studentWorkTypeOther = "";
    private double studentCPlusPlusMark = 1;
    private double studentJavaMark = 1;
    private String studentLanguage1 = "";
    private double studentLanguage1Mark = 1;
    private String studentLanguage2 = "";
    private double studentLanguage2Mark = 1;
    private String studentLanguage3 = "";
    private double studentLanguage3Mark = 1;
    private double studentKnowledgeNetwork = 0;
    private double studentKnowledgeEfficientAlgorithms = 0;
    private double studentKnowledgeOOP = 0;
    private double studentKnowledgeDB = 0;
    private double studentKnowledgeWeb = 0;
    private double studentKnowledgeGUI = 0;
    private double studentKnowledgeNetworkProgramming = 0;
    private double studentKnowledgeProgramDesign = 0;
    private String studentKnowledgeOther1 = "";
    private double studentKnowledgeOther1Mark = 0;
    private String studentKnowledgeOther2 = "";
    private double studentKnowledgeOther2Mark = 0;
    private String studentKnowledgeOther3 = "";
    private double studentKnowledgeOther3Mark = 0;
    private String studentExperienceProjects = "";
    private double studentEnglishReadMark  = 1;
    private double studentEnglishWriteMark  = 1;
    private double studentEnglishSpeakMark  = 1;
    private LinkedHashSet studentHowHearAboutCentre;
    private String studentReasonOffer = "";
    private String studentSelfAdditionalInformation = "";

    public String getStudentOtherContactType() {
        return studentOtherContactType;
    }

    public void setStudentOtherContactType(String studentOtherContactType) {
        this.studentOtherContactType = studentOtherContactType;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public Institute getStudentInstitute() {
        return studentInstitute;
    }

    public void setStudentInstitute(Institute studentInstitute) {
        this.studentInstitute = studentInstitute;
    }

    public String getStudentInstituteCourse() {
        return studentInstituteCourse;
    }

    public void setStudentInstituteCourse(String studentInstituteCourse) {
        this.studentInstituteCourse = studentInstituteCourse;
    }

    public Faculty getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(Faculty studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public Cathedra getStudentCathedra() {
        return studentCathedra;
    }

    public void setStudentCathedra(Cathedra studentCathedra) {
        this.studentCathedra = studentCathedra;
    }

    public String getStudentInstituteGradYear() {
        return studentInstituteGradYear;
    }

    public void setStudentInstituteGradYear(String studentInstituteGradYear) {
        this.studentInstituteGradYear = studentInstituteGradYear;
    }

    public String getStudentEmailFirst() {
        return studentEmailFirst;
    }

    public void setStudentEmailFirst(String studentEmailFirst) {
        this.studentEmailFirst = studentEmailFirst;
    }

    public String getStudentEmailSecond() {
        return studentEmailSecond;
    }

    public void setStudentEmailSecond(String studentEmailSecond) {
        this.studentEmailSecond = studentEmailSecond;
    }

    public String getStudentTelephone() {
        return studentTelephone;
    }

    public void setStudentTelephone(String studentTelephone) {
        this.studentTelephone = studentTelephone;
    }

    public String getStudentOtherContact() {
        return studentOtherContact;
    }

    public void setStudentOtherContact(String studentOtherContact) {
        this.studentOtherContact = studentOtherContact;
    }

    public String getStudentInterestStudy() {
        return studentInterestStudy;
    }

    public void setStudentInterestStudy(String studentInterestStudy) {
        this.studentInterestStudy = studentInterestStudy;
    }

    public String getStudentInterestWork() {
        return studentInterestWork;
    }

    public void setStudentInterestWork(String studentInterestWork) {
        this.studentInterestWork = studentInterestWork;
    }

    public String getStudentInterestDevelopment() {
        return studentInterestDevelopment;
    }

    public void setStudentInterestDevelopment(String studentInterestDevelopment) {
        this.studentInterestDevelopment = studentInterestDevelopment;
    }

    public String getStudentInterestOther() {
        return studentInterestOther;
    }

    public void setStudentInterestOther(String studentInterestOther) {
        this.studentInterestOther = studentInterestOther;
    }

    public String getStudentWorkTypeDeepSpec() {
        return studentWorkTypeDeepSpec;
    }

    public void setStudentWorkTypeDeepSpec(String studentWorkTypeDeepSpec) {
        this.studentWorkTypeDeepSpec = studentWorkTypeDeepSpec;
    }

    public String getStudentWorkTypeVarious() {
        return studentWorkTypeVarious;
    }

    public void setStudentWorkTypeVarious(String studentWorkTypeVarious) {
        this.studentWorkTypeVarious = studentWorkTypeVarious;
    }

    public String getStudentWorkTypeManagement() {
        return studentWorkTypeManagement;
    }

    public void setStudentWorkTypeManagement(String studentWorkTypeManagement) {
        this.studentWorkTypeManagement = studentWorkTypeManagement;
    }

    public String getStudentWorkTypeSale() {
        return studentWorkTypeSale;
    }

    public void setStudentWorkTypeSale(String studentWorkTypeSale) {
        this.studentWorkTypeSale = studentWorkTypeSale;
    }

    public String getStudentWorkTypeOther() {
        return studentWorkTypeOther;
    }

    public void setStudentWorkTypeOther(String studentWorkTypeOther) {
        this.studentWorkTypeOther = studentWorkTypeOther;
    }

    public double getStudentCPlusPlusMark() {
        return studentCPlusPlusMark;
    }

    public void setStudentCPlusPlusMark(double studentCPlusPlusMark) {
        this.studentCPlusPlusMark = studentCPlusPlusMark;
    }

    public double getStudentJavaMark() {
        return studentJavaMark;
    }

    public void setStudentJavaMark(double studentJavaMark) {
        this.studentJavaMark = studentJavaMark;
    }

    public String getStudentLanguage1() {
        return studentLanguage1;
    }

    public void setStudentLanguage1(String studentLanguage1) {
        this.studentLanguage1 = studentLanguage1;
    }

    public double getStudentLanguage1Mark() {
        return studentLanguage1Mark;
    }

    public void setStudentLanguage1Mark(double studentLanguage1Mark) {
        this.studentLanguage1Mark = studentLanguage1Mark;
    }

    public String getStudentLanguage2() {
        return studentLanguage2;
    }

    public void setStudentLanguage2(String studentLanguage2) {
        this.studentLanguage2 = studentLanguage2;
    }

    public double getStudentLanguage2Mark() {
        return studentLanguage2Mark;
    }

    public void setStudentLanguage2Mark(double studentLanguage2Mark) {
        this.studentLanguage2Mark = studentLanguage2Mark;
    }

    public String getStudentLanguage3() {
        return studentLanguage3;
    }

    public void setStudentLanguage3(String studentLanguage3) {
        this.studentLanguage3 = studentLanguage3;
    }

    public double getStudentLanguage3Mark() {
        return studentLanguage3Mark;
    }

    public void setStudentLanguage3Mark(double studentLanguage3Mark) {
        this.studentLanguage3Mark = studentLanguage3Mark;
    }

    public double getStudentKnowledgeNetwork() {
        return studentKnowledgeNetwork;
    }

    public void setStudentKnowledgeNetwork(double studentKnowledgeNetwork) {
        this.studentKnowledgeNetwork = studentKnowledgeNetwork;
    }

    public double getStudentKnowledgeEfficientAlgorithms() {
        return studentKnowledgeEfficientAlgorithms;
    }

    public void setStudentKnowledgeEfficientAlgorithms(double studentKnowledgeEfficientAlgorithms) {
        this.studentKnowledgeEfficientAlgorithms = studentKnowledgeEfficientAlgorithms;
    }

    public double getStudentKnowledgeOOP() {
        return studentKnowledgeOOP;
    }

    public void setStudentKnowledgeOOP(double studentKnowledgeOOP) {
        this.studentKnowledgeOOP = studentKnowledgeOOP;
    }

    public double getStudentKnowledgeDB() {
        return studentKnowledgeDB;
    }

    public void setStudentKnowledgeDB(double studentKnowledgeDB) {
        this.studentKnowledgeDB = studentKnowledgeDB;
    }

    public double getStudentKnowledgeWeb() {
        return studentKnowledgeWeb;
    }

    public void setStudentKnowledgeWeb(double studentKnowledgeWeb) {
        this.studentKnowledgeWeb = studentKnowledgeWeb;
    }

    public double getStudentKnowledgeGUI() {
        return studentKnowledgeGUI;
    }

    public void setStudentKnowledgeGUI(double studentKnowledgeGUI) {
        this.studentKnowledgeGUI = studentKnowledgeGUI;
    }

    public double getStudentKnowledgeNetworkProgramming() {
        return studentKnowledgeNetworkProgramming;
    }

    public void setStudentKnowledgeNetworkProgramming(double studentKnowledgeNetworkProgramming) {
        this.studentKnowledgeNetworkProgramming = studentKnowledgeNetworkProgramming;
    }

    public double getStudentKnowledgeProgramDesign() {
        return studentKnowledgeProgramDesign;
    }

    public void setStudentKnowledgeProgramDesign(double studentKnowledgeProgramDesign) {
        this.studentKnowledgeProgramDesign = studentKnowledgeProgramDesign;
    }

    public String getStudentKnowledgeOther1() {
        return studentKnowledgeOther1;
    }

    public void setStudentKnowledgeOther1(String studentKnowledgeOther1) {
        this.studentKnowledgeOther1 = studentKnowledgeOther1;
    }

    public double getStudentKnowledgeOther1Mark() {
        return studentKnowledgeOther1Mark;
    }

    public void setStudentKnowledgeOther1Mark(double studentKnowledgeOther1Mark) {
        this.studentKnowledgeOther1Mark = studentKnowledgeOther1Mark;
    }

    public String getStudentKnowledgeOther2() {
        return studentKnowledgeOther2;
    }

    public void setStudentKnowledgeOther2(String studentKnowledgeOther2) {
        this.studentKnowledgeOther2 = studentKnowledgeOther2;
    }

    public double getStudentKnowledgeOther2Mark() {
        return studentKnowledgeOther2Mark;
    }

    public void setStudentKnowledgeOther2Mark(double studentKnowledgeOther2Mark) {
        this.studentKnowledgeOther2Mark = studentKnowledgeOther2Mark;
    }

    public String getStudentKnowledgeOther3() {
        return studentKnowledgeOther3;
    }

    public void setStudentKnowledgeOther3(String studentKnowledgeOther3) {
        this.studentKnowledgeOther3 = studentKnowledgeOther3;
    }

    public double getStudentKnowledgeOther3Mark() {
        return studentKnowledgeOther3Mark;
    }

    public void setStudentKnowledgeOther3Mark(double studentKnowledgeOther3Mark) {
        this.studentKnowledgeOther3Mark = studentKnowledgeOther3Mark;
    }

    public String getStudentExperienceProjects() {
        return studentExperienceProjects;
    }

    public void setStudentExperienceProjects(String studentExperienceProjects) {
        this.studentExperienceProjects = studentExperienceProjects;
    }

    public double getStudentEnglishReadMark() {
        return studentEnglishReadMark;
    }

    public void setStudentEnglishReadMark(double studentEnglishReadMark) {
        this.studentEnglishReadMark = studentEnglishReadMark;
    }

    public double getStudentEnglishWriteMark() {
        return studentEnglishWriteMark;
    }

    public void setStudentEnglishWriteMark(double studentEnglishWriteMark) {
        this.studentEnglishWriteMark = studentEnglishWriteMark;
    }

    public double getStudentEnglishSpeakMark() {
        return studentEnglishSpeakMark;
    }

    public void setStudentEnglishSpeakMark(double studentEnglishSpeakMark) {
        this.studentEnglishSpeakMark = studentEnglishSpeakMark;
    }

    public LinkedHashSet getStudentHowHearAboutCentre() {
        return studentHowHearAboutCentre;
    }

    public void setStudentHowHearAboutCentre(LinkedHashSet studentHowHearAboutCentre) {
        this.studentHowHearAboutCentre = studentHowHearAboutCentre;
    }

    public String getStudentReasonOffer() {
        return studentReasonOffer;
    }

    public void setStudentReasonOffer(String studentReasonOffer) {
        this.studentReasonOffer = studentReasonOffer;
    }

    public String getStudentSelfAdditionalInformation() {
        return studentSelfAdditionalInformation;
    }

    public void setStudentSelfAdditionalInformation(String studentSelfAdditionalInformation) {
        this.studentSelfAdditionalInformation = studentSelfAdditionalInformation;
    }
}
