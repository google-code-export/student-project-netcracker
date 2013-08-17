/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.Entities.Form;
import ua.netcrackerteam.DAO.Entities.Interview;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.configuration.Logable;
import ua.netcrackerteam.controller.bean.StudentInterview;
import ua.netcrackerteam.controller.exceptions.StudentInterviewException;

import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Klitna Tetiana
 */

public class RegistrationToInterview implements Logable {

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

        @Override
        public String[] getWeekdays() {
            return new String[]{"суббота", "воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница"};
        }
    };

    /**
     * Registration form's student to the interview
     *
     * @param userName    - login student
     * @param interviewId - selected interview by student
     */
    public void updateRegistrationToInterview(String userName, int interviewId) throws StudentInterviewException {
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
        if (form == null) {
            throw new StudentInterviewException(StudentInterviewException.NO_FORM_EXCEPTION);
        }
        Interview interview = HibernateFactory.getInstance().getDAOInterview().getInterview(interviewId);
        if (interview == null) {
            throw new StudentInterviewException(StudentInterviewException.NO_INTERVIEW_EXCEPTION);
        }
        if (getRestOfPositionsOnInterview(interview) == 0) {
            throw new StudentInterviewException(StudentInterviewException.FULL_INTERVIEW_EXCEPTION);
        }
        if (validTime(interview)) {
            form.setInterview(interview);
            HibernateFactory.getInstance().getStudentDAO().updateForm(form);
        } else {
            throw new StudentInterviewException(StudentInterviewException.WRONG_TIME_EXCEPTION);
        }
    }

    private boolean validTime(Interview interview) {
        Date currentDate = new Date();
        Date selectedInterviewDate = interview.getStartDate();
        long differenceInMinutes = ((selectedInterviewDate.getTime() / 60000) - (currentDate.getTime() / 60000));
        if (differenceInMinutes < 30) {
            return false;
        }
        return true;
    }

    /**
     * Get all interviews for view to the GUI
     *
     * @return List<StudentInterview>
     */
    public List<StudentInterview> getInterviews() {

        List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
        ListIterator<Interview> iterator = interviews.listIterator();

        List<StudentInterview> listInterviews = new ArrayList<StudentInterview>();
        Interview interview = null;

        Interview nullInterview = HibernateFactory.getInstance().getDAOInterview().getReserveInterview();

        while (iterator.hasNext()) {
            interview = iterator.next();
            if (!interview.equals(nullInterview) && validTime(interview)) {
                StudentInterview stInterview = getStudentInterviewFromInterview(interview);
                listInterviews.add(stInterview);
            }
        }
        Collections.sort(listInterviews, new CustomComparator());
        return listInterviews;
    }

    private StudentInterview getStudentInterviewFromInterview(Interview interview) {
        StudentInterview studentInterview = new StudentInterview(interview.getIdInterview(),
                getRestOfPositionsOnInterview(interview));
        Date startDate = interview.getStartDate();
        Format formatter = new SimpleDateFormat("dd MMMM (EEEE)", myDateFormatSymbols);
        String strDate = formatter.format(startDate);
        studentInterview.setInterviewStartDay(strDate);

        studentInterview.setInterviewStartDate(startDate);

        formatter = new SimpleDateFormat("HH:mm");
        String strStartTime = formatter.format(startDate);
        studentInterview.setStartTime(strStartTime);

        String strEndTime = formatter.format(interview.getEndDate());
        studentInterview.setEndTime(strEndTime);
        return studentInterview;
    }

    private int getRestOfPositionsOnInterview(Interview interview) {
        List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
        int amountStudentsToInterview = (forms == null ? 0 : forms.size());
        return interview.getMaxNumber() - amountStudentsToInterview;
    }

    /**
     * Get interview student by the student login
     *
     * @param userName
     * @return
     */
    public int getInterviewID(String userName) {
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
        Interview interview = null;
        if (form != null) {
            interview = form.getInterview();
        }
        int currentInterviewID = -1;
        if (interview != null) {
            currentInterviewID = interview.getIdInterview();
        }
        return currentInterviewID;
    }

    public StudentInterview getSelectedInterview(String userName) {
        Form form = HibernateFactory.getInstance().getStudentDAO().getFormByUserName(userName);
        Interview interview = null;
        if (form != null) {
            interview = form.getInterview();
            if (interview != null) {
                return getStudentInterviewFromInterview(interview);
            }
        }
        return null;
    }

    public StudentInterview getNullInterview() {
        Interview nullInterview = HibernateFactory.getInstance().getDAOInterview().getReserveInterview();
        StudentInterview stInterview = new StudentInterview(nullInterview.getIdInterview(), getRestOfPositionsOnInterview(nullInterview));
        return stInterview;
    }

    public class CustomComparator implements Comparator<StudentInterview> {
        @Override
        public int compare(StudentInterview o1, StudentInterview o2) {
            return o1.getInterviewStartDate().compareTo(o2.getInterviewStartDate());
        }
    }


}
