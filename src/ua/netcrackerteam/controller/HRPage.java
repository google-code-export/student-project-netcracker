package ua.netcrackerteam.controller;

import ua.netcrackerteam.DAO.DAOHRImpl;
import ua.netcrackerteam.DAO.DAOInterviewerImpl;
import ua.netcrackerteam.DAO.Entities.*;
import ua.netcrackerteam.applicationForm.ApplicationForm;
import ua.netcrackerteam.configuration.HibernateFactory;
import ua.netcrackerteam.controller.bean.*;
import ua.netcrackerteam.controller.exceptions.HRException;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 12.03.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class HRPage {

    private static int ID_NULL_INTERVIEW = 1;
    private DAOHRImpl dao = new DAOHRImpl();

    public static byte[] getPdfForView(int formID) {
        return new ApplicationForm(formID).generateFormPDF();
    }

    private static List<StudentDataShort> getStudentDataList(List<Form> forms) {
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        StudentDataShort stDataShort = null;
        for(Form form : forms) {
            stDataShort = new StudentDataShort();
            stDataShort.setIdForm(form.getIdForm());
            stDataShort.setStudentLastName(form.getLastName());
            stDataShort.setStudentCathedra(form.getCathedra().toString());
            stDataShort.setStudentFaculty(form.getCathedra().getFaculty().toString());
            stDataShort.setStudentInstitute(form.getCathedra().getFaculty().getInstitute().toString());
            stDataShort.setStudentFirstName(form.getFirstName());
            stDataShort.setStudentMiddleName(form.getMiddleName());
            stDataShort.setStudentInstituteCourse(form.getInstituteYear().toString());
            studentList.add(stDataShort);
        }
        return studentList;
    }

    public static List<HrTempInfo> getHRTempInfo(){
        List<HrTempInfo> hrTempInfos = new DAOHRImpl().getHrTempInfo();
        return hrTempInfos;
    }

    public static HrTempInfo getHRTempInfoByFormID(int formID){
        HrTempInfo hrTempInfos = new DAOHRImpl().getHrTempInfoByFormID(formID);
        return hrTempInfos;
    }

    public static int getCountOfHRTempInfo() {
        int count = 0;
        count = new DAOHRImpl().getHrTempInfo().size();
        return count;
    }

    public static List<StudentDataShort> getAllForms() {
        List<Form> allForms = new DAOHRImpl().getAllRegisteredForms();
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static Long getCountOfAllForms() {
        return new DAOHRImpl().getAllRegisteredFormsCount();
    }

    public static Long getCountOfNonVerificatedForms() {
        return new DAOHRImpl().getNonVerificatedFormsCount();
    }

    public static Long getCountOfBlankWithoutInterview() {
        return new DAOHRImpl().getBlanksWithoutInterviewCount();
    }

    public static List<StudentDataShort> getBlankWithoutInterview() {
        List<Form> blanksWithoutInterview = new DAOHRImpl().getBlanksWithoutInterview();
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(blanksWithoutInterview != null) {
            studentList = getStudentDataList(blanksWithoutInterview);
        }
        return studentList;
    }

    public static String getUserNameByFormId(int formId) {
        UserList user = new DAOHRImpl().getUserDataByFormId(formId);
        return user.getUserName();
    }

    public static int getUserIDByFormId(int formId) {
        UserList user = new DAOHRImpl().getUserDataByFormId(formId);
        return user.getIdUser();
    }

    public static List<StudentDataShort> getNonVerificatedForms() {
        List<Form> allForms = new DAOHRImpl().getNonVerificatedForms();
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static List<StudentDataShort> getStudentsByInterviewID (int interviewID) {
        List<Form> allForms = new DAOInterviewerImpl().getAllFormsByInterview(interviewID);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }

    public static void setStudentMark(int formID, String hrName, String Mark) {
        new DAOHRImpl().setHRMark(formID, Mark, hrName);
    }

    public static void deleteStudentBlank(int formID) {
        new DAOHRImpl().deleteForm(formID);
    }

    public static void verificateForm(Integer formID) {
        new DAOHRImpl().verificateForm(formID);
    }

    public static List<StudentDataShort> searchStudents(String searchFilter, String value) {
        if ("Фамилия".equals(searchFilter)) {
            searchFilter = "lastName";
        }
        else if ("Имя".equals(searchFilter)) {
            searchFilter = "firstName";
        }
        else if ("Отчество".equals(searchFilter)) {
            searchFilter = "middleName";
        }
        else if ("Номер анкеты".equals(searchFilter)) {
            searchFilter = "idForm";
        }

        List<Form> allForms = new DAOHRImpl().search(searchFilter, value);
        List<StudentDataShort> studentList = new ArrayList<StudentDataShort>();
        if(allForms != null) {
            studentList = getStudentDataList(allForms);
        }
        return studentList;
    }
    
    /*
     * Anna
     */
    public List<HRInterview> getInterviewsList() {
        List<HRInterview> intervList = new ArrayList<HRInterview>();
        List<Interview> interviews = HibernateFactory.getInstance().getDAOInterview().getInterview();
        Interview nullInterview = HibernateFactory.getInstance().getDAOInterview().getReserveInterview();
        for(Interview interview : interviews) {
            if(!interview.equals(nullInterview)) {
                HRInterview hrInterview = new HRInterview();

                hrInterview.setId(interview.getIdInterview());

                Date startDate = interview.getStartDate();
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");      
                String strDate = formatter.format(startDate);
                hrInterview.setDate(strDate);

                formatter = new SimpleDateFormat("HH:mm");     
                String strStartTime = formatter.format(startDate);
                hrInterview.setStartTime(strStartTime);

                String strEndTime = formatter.format(interview.getEndDate());
                hrInterview.setEndTime(strEndTime);

                hrInterview.setInterviewersNum(interview.getInterviwerNumber());
                hrInterview.setPositionNum(interview.getMaxNumber());

                List<Form> forms = HibernateFactory.getInstance().getStudentDAO().getFormsByInterviewId(interview.getIdInterview());
                int  amountStudentsToInterview = (forms == null? 0: forms.size()); 
                hrInterview.setRestOfPositions(hrInterview.getPositionNum() - amountStudentsToInterview);
                intervList.add(hrInterview);
            }
        }
        Collections.sort(intervList, new CustomComparator());
        return intervList;
    }

    public class CustomComparator implements Comparator<HRInterview> {
        @Override
        public int compare(HRInterview o1, HRInterview o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

    private Date addMinutesToDate(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public void saveNewInterviews(Date startDate, Date endDate, int intervNum, int maxStudents, int oneDuration) {
        Date currentEndDate = addMinutesToDate(startDate,oneDuration);
        Date currentStartDate = startDate;
        while(currentEndDate.before(endDate)) {
            Interview interview = new Interview();
            interview.setEndDate(currentEndDate);
            interview.setStartDate(currentStartDate);
            interview.setInterviwerNumber(intervNum);
            interview.setMaxNumber(maxStudents);
            interview.setReserve(0);
            dao.addNewInterview(interview);
            currentStartDate = currentEndDate;
            currentEndDate = addMinutesToDate(currentStartDate,oneDuration);
        }
    }


    
    public void deleteInterview(int idInterview, int idReserveInterview) throws HRException{
        if (ID_NULL_INTERVIEW!= idReserveInterview) {
            new DAOHRImpl().deleteInterview(idInterview);
        }else {
            throw new HRException(HRException.TRY_DELETE_NULL_INTERVIEW);
        }
    }
    
    public static void editInterview(int id, Date start, Date end, int intervNum, int maxStudents) {
        Interview interview = new Interview();
        interview.setIdInterview(id);
        interview.setEndDate(end);
        interview.setStartDate(start);
        interview.setInterviwerNumber(intervNum);
        interview.setMaxNumber(maxStudents);
        interview.setReserve(0);
        new DAOHRImpl().editInterview(interview);
    }

    public static void addNewInstFaculCath(String instituteName, String facultyName, String cathedraName){
        DAOHRImpl daohr = new DAOHRImpl();
        Institute institute = daohr.addInstitute(instituteName);
        Faculty faculty = daohr.addFaculty(institute, facultyName);
        daohr.addCathedra(faculty, cathedraName);
    }

    public static StudentData getStDataByFormID(int formID) {

        StudentData std = new StudentData();
        DAOHRImpl daohr = new DAOHRImpl();
        Form form = (Form)StudentPage.searchSomething("Form", "idForm", String.valueOf(formID)).get(0);
        //int idForm = form.getIdForm();
        if (form != null)
        {
            std.setIdForm(form.getIdForm());
            std.setStudentLastName(form.getLastName());
            std.setStudentFirstName(form.getFirstName());
            std.setStudentMiddleName(form.getMiddleName());
            std.setStudentInstitute(form.getCathedra().getFaculty().getInstitute());
            std.setStudentInstituteCourse(form.getInstituteYear().toString());
            std.setStudentFaculty(form.getCathedra().getFaculty());
            std.setStudentCathedra(form.getCathedra());
            std.setStudentInstituteGradYear(form.getInstituteGradYear().toString());

            //krygin added code to set new institute info
            /*if(!daohr.getHrTempInfoByFormID(idForm).getInstituteName().equals("")){
                if(!daohr.getHrTempInfoByFormID(idForm).getFacultyName().equals("")){
                    if (!daohr.getHrTempInfoByFormID(idForm).getCathedraName().equals("")){
                        std.setStudentOtherInstitute(daohr.getHrTempInfoByFormID(idForm).getInstituteName());
                        std.setStudentOtherFaculty(daohr.getHrTempInfoByFormID(idForm).getFacultyName());
                        std.setStudentOtherCathedra(daohr.getHrTempInfoByFormID(idForm).getCathedraName());
                    } else {
                        std.setStudentOtherInstitute(daohr.getHrTempInfoByFormID(idForm).getInstituteName());
                        std.setStudentOtherFaculty(daohr.getHrTempInfoByFormID(idForm).getFacultyName());
                    }
                } else {
                    std.setStudentOtherInstitute(daohr.getHrTempInfoByFormID(idForm).getInstituteName());
                    std.setStudentOtherCathedra(daohr.getHrTempInfoByFormID(idForm).getCathedraName());
                }
            }*/

            std.setPhoto(form.getPhoto());

            Set contacts = form.getContacts();
            Iterator iterCont = contacts.iterator();
            while(iterCont.hasNext()) {
                Contact contact = (Contact) iterCont.next();
                String contactCategory = contact.getContactCategory().getCategory();
                if (contactCategory.equals("email1")) {
                    std.setStudentEmailFirst(contact.getInfo());
                }
                else if (contactCategory.equals("email2")) {
                    std.setStudentEmailSecond(contact.getInfo());
                }
                else if (contactCategory.equals("cellphone1")) {
                    std.setStudentTelephone(contact.getInfo());
                }
                else {
                    std.setStudentOtherContactType(contactCategory);
                    std.setStudentOtherContact(contact.getInfo());
                }
            }

            std.setStudentInterestStudy(form.getInterestStudy());
            std.setStudentInterestWork(form.getInterestWork());
            std.setStudentInterestDevelopment(form.getInterestBranchSoft());
            std.setStudentInterestOther(form.getInterestBranchOther());
            std.setStudentWorkTypeDeepSpec(form.getInterestDeepSpec());
            std.setStudentWorkTypeVarious(form.getInterestVarious());
            std.setStudentWorkTypeManagement(form.getInterestManagment());
            std.setStudentWorkTypeSale(form.getInterestSale());
            std.setStudentWorkTypeOther(form.getInterestOther());

            Set knowledges = form.getKnowledges();
            Iterator iterKnow = knowledges.iterator();
            Set<Knowledge> otherKnowledges = new HashSet();
            while(iterKnow.hasNext()) {
                Knowledge knowledge = (Knowledge) iterKnow.next();
                String branch = knowledge.getBranch().getName();
                if (branch.equals("C++")) {
                    std.setStudentCPlusPlusMark(knowledge.getScore());
                }
                else if (branch.equals("Java")) {
                    std.setStudentJavaMark(knowledge.getScore());
                }
                else if (branch.equals("Сетевые технологии")) {
                    std.setStudentKnowledgeNetwork(knowledge.getScore());
                }
                else if (branch.equals("Эффективные алгоритмы")) {
                    std.setStudentKnowledgeEfficientAlgorithms(knowledge.getScore());
                }
                else if (branch.equals("ООП")) {
                    std.setStudentKnowledgeOOP(knowledge.getScore());
                }
                else if (branch.equals("БД")) {
                    std.setStudentKnowledgeDB(knowledge.getScore());
                }
                else if (branch.equals("Web")) {
                    std.setStudentKnowledgeWeb(knowledge.getScore());
                }
                else if (branch.equals("GUI")) {
                    std.setStudentKnowledgeGUI(knowledge.getScore());
                }
                else if (branch.equals("Сетевое программирование")) {
                    std.setStudentKnowledgeNetworkProgramming(knowledge.getScore());
                }
                else if (branch.equals("Проектирование программ")) {
                    std.setStudentKnowledgeProgramDesign(knowledge.getScore());
                }
                else if (branch.trim().equalsIgnoreCase("Английский(чтение)")) {
                    std.setStudentEnglishReadMark(knowledge.getScore());
                }
                else if (branch.trim().equalsIgnoreCase("Английский(письмо)")) {
                    std.setStudentEnglishWriteMark(knowledge.getScore());
                }
                else if (branch.trim().equalsIgnoreCase("Английский(речь)")) {
                    std.setStudentEnglishSpeakMark(knowledge.getScore());
                }
                else {
                    otherKnowledges.add(knowledge);
                }
            }
            Iterator iterOtherKnow = otherKnowledges.iterator();
            Knowledge know;
            ArrayList<Knowledge> progLangs = new ArrayList<Knowledge>();
            //Filipenko//+
            ArrayList<Knowledge> itKnow = new ArrayList<Knowledge>();
            //Filipenko//=
            while(iterOtherKnow.hasNext()) {
                know = (Knowledge) iterOtherKnow.next();
                if(know.getBranch().getBranchCategory().getName()
                        .trim().equalsIgnoreCase("Языки программирования"))
                {
                    progLangs.add(know);
                    iterOtherKnow.remove();
                }
                //Filipenko//+
                else if (know.getBranch().getBranchCategory().getName().trim().equalsIgnoreCase("Знания в области IT технологий")) {
                    itKnow.add(know);
                }
                //Filipenko//=
            }

            Iterator iterProgLangs = progLangs.iterator();
            if(iterProgLangs.hasNext()) {
                know = (Knowledge) iterProgLangs.next();
                std.setStudentLanguage1(know.getBranch().getName());
                std.setStudentLanguage1Mark(know.getScore());
            }
            if(iterProgLangs.hasNext()) {
                know = (Knowledge) iterProgLangs.next();
                std.setStudentLanguage2(know.getBranch().getName());
                std.setStudentLanguage2Mark(know.getScore());
            }
            if(iterProgLangs.hasNext()) {
                know = (Knowledge) iterProgLangs.next();
                std.setStudentLanguage3(know.getBranch().getName());
                std.setStudentLanguage3Mark(know.getScore());
            }

            //Filipenko//+
            /*if(iterOtherKnow.hasNext()) {
                know = (Knowledge) iterOtherKnow.next();
                std.setStudentKnowledgeOther1(know.getBranch().getName());
                std.setStudentKnowledgeOther1Mark(know.getScore());
            }
            if(iterOtherKnow.hasNext()) {
                know = (Knowledge) iterOtherKnow.next();
                std.setStudentKnowledgeOther2(know.getBranch().getName());
                std.setStudentKnowledgeOther2Mark(know.getScore());
            }
            if(iterOtherKnow.hasNext()) {
                know = (Knowledge) iterOtherKnow.next();
                std.setStudentKnowledgeOther3(know.getBranch().getName());
                std.setStudentKnowledgeOther3Mark(know.getScore());
            }*/
            Iterator iterITLangs = itKnow.iterator();
            if(iterITLangs.hasNext()) {
                know = (Knowledge) iterITLangs.next();
                std.setStudentKnowledgeOther1(know.getBranch().getName());
                std.setStudentKnowledgeOther1Mark(know.getScore());
            }
            if(iterITLangs.hasNext()) {
                know = (Knowledge) iterITLangs.next();
                std.setStudentKnowledgeOther2(know.getBranch().getName());
                std.setStudentKnowledgeOther2Mark(know.getScore());
            }
            if(iterITLangs.hasNext()) {
                know = (Knowledge) iterITLangs.next();
                std.setStudentKnowledgeOther3(know.getBranch().getName());
                std.setStudentKnowledgeOther3Mark(know.getScore());
            }
            //Filipenko//=

            std.setStudentExperienceProjects(form.getExecProject());


            Set adverts = form.getAdverts();
            LinkedHashSet linkedAdverts = new LinkedHashSet();
            Iterator iterAdv = adverts.iterator();
            while(iterAdv.hasNext()) {
                Advert advert = (Advert) iterAdv.next();
                String advertDecription = advert.getAdvertCategory().getDescription();
                if(advertDecription.trim().equalsIgnoreCase("Другое (уточните)")) {
                    linkedAdverts.add(advertDecription);
                    std.setStudentHowHearAboutCentreOther(advert.getOther());
                    iterAdv.remove();
                } else {
                    linkedAdverts.add(advertDecription);
                }
            }
            if (std.getStudentHowHearAboutCentreOther() == null) { std.setStudentHowHearAboutCentreOther("");}
            std.setStudentHowHearAboutCentre(linkedAdverts);


            std.setStudentReasonOffer(form.getReason());
            std.setStudentSelfAdditionalInformation(form.getExtraInfo());
        }

        return std;

    }

    public static List<DifferenceData> getDifferencesOfBlanks(int currFormId) {
        DAOHRImpl currDAO = new DAOHRImpl();
        Form currForm = currDAO.getForm(currFormId);
        Integer currUserId = 0;
        if (!(currForm == null)) {
            currUserId = currForm.getUser().getIdUser();
        }
        return currDAO.getDiff(currUserId);
    }

    public StudentsMarks getHRStudentMarksByFormID(int idForm) {
        StudentsMarks hrStudentMark = new StudentsMarks();
//        //hrStudentMark.setGroupWork(true);
//        hrStudentMark.setInterviewerName("qwe");
//        hrStudentMark.setComment("qweqwe");
        return hrStudentMark;
    }

    public StudentsMarks getInterviewerMarksByFormID(int idForm) {
        StudentsMarks interviewerStudentMark = new StudentsMarks();
        interviewerStudentMark.setGroupWork(true);
        interviewerStudentMark.setInterviewerName("qweq");
        interviewerStudentMark.setJavaKnowledge("-_-");
        interviewerStudentMark.setSqlKnowledge("o_O");
        interviewerStudentMark.setComment("qweqwe");
        return interviewerStudentMark;
    }

    public List<EnrollmentScores> getEnrollmentScores() {
        List<EnrollmentScores> enrollmentScores = new ArrayList<EnrollmentScores>();
        return enrollmentScores;
    }
}
