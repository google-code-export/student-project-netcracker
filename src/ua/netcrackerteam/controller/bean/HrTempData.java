package ua.netcrackerteam.controller.bean;

/**
 * @author krygin
 */
public class HrTempData {
    private int idHrTempInfo;
    private String studentInstitute;
    private String studentFaculty;
    private String studentCathedra;

    public int getIdHrTempInfo() {
        return idHrTempInfo;
    }

    public void setIdHrTempInfo(int idHrTempInfo) {
        this.idHrTempInfo = idHrTempInfo;
    }

    public String getStudentInstitute() {
        return studentInstitute;
    }

    public void setStudentInstitute(String studentInstitute) {
        this.studentInstitute = studentInstitute;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public String getStudentCathedra() {
        return studentCathedra;
    }

    public void setStudentCathedra(String studentCathedra) {
        this.studentCathedra = studentCathedra;
    }
}
