package com.example.medical_platform_android.utils;

public class UrlConstants {

    //ip该这个就行了
    public static final String url="http://192.168.123.56:8081/";
    public static final String base_url1 = "http://192.168.123.56:8081/chat/";
    public static final String base_url2 = "http://192.168.123.56:8081/medicine-orders/";
    public static final String base_url3 = "http://192.168.123.56:8081/drugs/";
    //public static final String BASE_URL_DOCTORS = "http://192.168.123.56:8081/doctors/";
    public static final String base_url = url+"users/";
    public static final String login_url = base_url + "users_Login";
    public static final String register_url = url+"user_AddUsers";
    public static final String users_IfExit = url+"user_IfExit";

    public static final String FINDALL_URL_DOCTORS = base_url + "findAll";

    public static final String xzw_url=url;
    public static final String xzw_old_path_image="http://localhost:8090/userImage/userImage";
    public static final String FINDALL_URL = base_url + "findAll";

    //public static final String FINDALL_URL_DOCTORS = BASE_URL_DOCTORS + "findAll";
    public static final String FINDALLMESSAGEBYID_URL_CHAT = base_url1 + "findChatMessagesById";
    public static final String SENDMESSAGE_URL_CHAT = base_url1 + "sendMessage";
    public static final String FINDMEDICINEORDERSBYID_URL_Order = base_url2 + "findMedicineOrdersById";
    public static final String GENERATEORDER_URL_ORDER = base_url2 + "generateOrder";
    public static final String BASE_URL_DOCTORS = url+"/doctors/";

    //ip该这个就行了
    public static final String url="http://192.168.43.171:8081/";
    public static final String base_url = url+"users/";
    public static final String login_url = base_url + "users_Login";
    public static final String register_url = url+"user_AddUsers";

    public static final String users_IfExit = url+"user_IfExit";

    public static final String FINDALL_URL_DOCTORS = base_url + "findAll";



    public static final String xzw_url=url;
    public static final String xzw_old_path_image="http://localhost:8090/userImage/userImage";

    public static final String FINDALL_URL = base_url + "findAll";
    public static final String BASE_URL_DOCTORS = url+"/doctors/";

    public static final String FINDALLDRUGS_URL_DRUGS = base_url3 + "findAllDrugs";
    public static final String FINDALLBYMAPPER_URL_DRUGS = base_url3 + "findAllByMapper";

    public static final String findAllUser_url = base_url + "FindAllUsers";
    public static final String Change_Password = base_url + "UpdateUsers";
    public static final String delete_UserList = base_url + "users_DeleteUsers";
    public static final String findRoleName = base_url + "findRoleName";
    public static final String AddUserRole = base_url + "AddUserRole";
    public static final String DeleteUserRole = base_url + "DeleteUserRole";
    public static final String Get_User_HealthStatus = base_url + "Get_User_HealthStatus";
    public static final String Add_User_HealthStatus = base_url + "Add_User_HealthStatus";
    public static final String Delete_User_HealthStatus = base_url + "Delete_User_HealthStatus";
    public static final String FindAllDrugs = base_url + "FindAllDrugs";
    public static final String UpdateDrugs = base_url + "UpdateDrugs";
    public static final String AddDrugs = base_url + "AddDrugs";
    public static final String DeleteDrugs = base_url + "DeleteDrugs";

}
