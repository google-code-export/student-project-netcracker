package ua.netcrackerteam.controller.bean;

import ua.netcrackerteam.DAO.Entities.EnrollmentScores;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 17.03.13
 * Time: 9:41
 * To change this template use File | Settings | File Templates.
 */
public class StudentsMarks {

    private String interviewerName;
    private String comment;
    private String javaKnowledge;
    private String sqlKnowledge;
    private EnrollmentScores enrollment;
    private boolean groupWork;

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJavaKnowledge() {
        return javaKnowledge;
    }

    public void setJavaKnowledge(String javaKnowledge) {
        this.javaKnowledge = javaKnowledge;
    }

    public String getSqlKnowledge() {
        return sqlKnowledge;
    }

    public void setSqlKnowledge(String sqlKnowledge) {
        this.sqlKnowledge = sqlKnowledge;
    }

    public EnrollmentScores getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(EnrollmentScores enrollment) {
        this.enrollment = enrollment;
    }

    public boolean isGroupWork() {
        return groupWork;
    }

    public void setGroupWork(boolean groupWork) {
        this.groupWork = groupWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsMarks that = (StudentsMarks) o;
        if (sqlKnowledge != null ? !sqlKnowledge.equals(that.sqlKnowledge) : that.sqlKnowledge != null) return false;
        if (javaKnowledge != null ? !javaKnowledge.equals(that.javaKnowledge) : that.javaKnowledge != null) return false;
        if (enrollment != null ? !enrollment.equals(that.enrollment) : that.enrollment != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (interviewerName != null ? !interviewerName.equals(that.interviewerName) : that.interviewerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interviewerName != null ? interviewerName.hashCode() : 0;
        result = 31 * result + (sqlKnowledge != null ? sqlKnowledge.hashCode() : 0);
        result = 31 * result + (javaKnowledge != null ? javaKnowledge.hashCode() : 0);
        result = 31 * result + (enrollment != null ? enrollment.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
