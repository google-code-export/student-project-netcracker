package ua.netcrackerteam.controller;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 17.03.13
 * Time: 9:41
 * To change this template use File | Settings | File Templates.
 */
public class StudentsMarks {

    private int idUser;
    private String userName;
    private String studentMark;

    public String getUserName() {
        return userName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(String studentMark) {
        this.studentMark = studentMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsMarks that = (StudentsMarks) o;

        if (studentMark != null ? !studentMark.equals(that.studentMark) : that.studentMark != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (studentMark != null ? studentMark.hashCode() : 0);
        return result;
    }
}
