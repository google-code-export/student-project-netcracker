package ua.netcrackerteam.DAO;

import ua.netcrackerteam.configuration.Logable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bri
 * Date: 22.01.13
 * Time: 9:10
 * To change this template use File | Settings | File Templates.
 */
public class DAOHR implements Logable {

    public static List searchByFIO(String field, String value) {

        List queryResult = null;

        try {
            queryResult = DBStandartQuery.SelectQuery("form.first_name, form.last_name, form.middle_name, user_category.category",
                    "form left join user_list on form.id_user = user_list.id_user left join USER_LIST on form.id_user = USER_LIST.id_user left join user_category on user_list.id_user_category = user_category.id_user_category",
                    "form." + field + " like '" + value + "'");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return queryResult;
    }

    public static List GetListOfEnrollee() {

        List queryResult = null;

        try {
            queryResult = DBStandartQuery.SelectQuery("form.first_name, form.last_name, form.middle_name, contact.info",
                    "form left join user_list on form.id_user = user_list.id_user left join contact on form.id_form = contact.id_form",
                    "(user_list.id_user_category = '4') and (contact.id_contact_category = 2)");
        }
        catch (NullPointerException ex) {
            logger.error(ex);
        }

        return queryResult;
    }

//    public static List GetListOfUserByInstitute() {
//
//        List queryResult = null;
//
//        try {
//            queryResult = DBStandartQuery.SelectQuery("form.first_name, form.last_name, form.middle_name, contact.info",
//                    "form left join user_list on form.id_user = user_list.id_user left join contact on form.id_form = contact.id_form",
//                    "(user_list.id_user_category = '4') and (contact.id_contact_category = 2)");
//        }
//        catch (NullPointerException ex) {
//            logger.error(ex);
//        }
//
//        return queryResult;
//    }

}
