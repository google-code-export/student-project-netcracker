/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.GUI;

import java.io.Serializable;

/**
 *
 * @author akush_000
 */
public class BlankFormBean implements Serializable{
    //private String = "";
    private String ruFirstName = "";
    private String ruMiddleName= "";
    private String ruLastName= "";
    private String uaFirstName= "";
    private String uaMiddleName= "";
    private String uaLastName= "";
    private String enFirstName= "";
    private String enLastName= "";
    private String university= "";
    private String universityYear= "";
    private String faculty= "";
    private String universityGradYear= "";

    public String getRuFirstName() {
        return ruFirstName;
    }

    public String getRuMiddleName() {
        return ruMiddleName;
    }

    public String getRuLastName() {
        return ruLastName;
    }

    public String getUaFirstName() {
        return uaFirstName;
    }

    public String getUaMiddleName() {
        return uaMiddleName;
    }

    public String getUaLastName() {
        return uaLastName;
    }

    public String getEnFirstName() {
        return enFirstName;
    }

    public String getEnLastName() {
        return enLastName;
    }

    public String getUniversity() {
        return university;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getUniversityGradYear() {
        return universityGradYear;
    }

    public void setRuFirstName(String ruFirstName) {
        this.ruFirstName = ruFirstName;
    }

    public void setRuMiddleName(String ruMiddleName) {
        this.ruMiddleName = ruMiddleName;
    }

    public void setRuLastName(String ruLastName) {
        this.ruLastName = ruLastName;
    }

    public void setUaFirstName(String uaFirstName) {
        this.uaFirstName = uaFirstName;
    }

    public void setUaMiddleName(String uaMiddleName) {
        this.uaMiddleName = uaMiddleName;
    }

    public void setUaLastName(String uaLastName) {
        this.uaLastName = uaLastName;
    }

    public void setEnFirstName(String enFirstName) {
        this.enFirstName = enFirstName;
    }

    public void setEnLastName(String enLastName) {
        this.enLastName = enLastName;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setUniversityGradYear(String universityGradYear) {
        this.universityGradYear = universityGradYear;
    }
    
}
