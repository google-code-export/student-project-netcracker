/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller.bean;

/**
 *
 * @author akush_000
 */
public class StudentDataShort {
    private int idForm;
    private String studentLastName = "";
    private String studentFirstName = "";
    private String studentMiddleName = "";
    private String studentInstitute;
    private String studentInstituteCourse = "";
    private String studentFaculty;
    private String studentCathedra;

    public int getIdForm() {
        return idForm;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentInstitute(String studentInstitute) {
        this.studentInstitute = studentInstitute;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public void setStudentCathedra(String studentCathedra) {
        this.studentCathedra = studentCathedra;
    }

    public String getStudentInstitute() {
        return studentInstitute;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public String getStudentCathedra() {
        return studentCathedra;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public String getStudentInstituteCourse() {
        return studentInstituteCourse;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public void setStudentInstituteCourse(String studentInstituteCourse) {
        this.studentInstituteCourse = studentInstituteCourse;
    }
    
}
