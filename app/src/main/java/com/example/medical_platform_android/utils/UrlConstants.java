package com.example.medical_platform_android.utils;

public class UrlConstants {
    public static final String base_url = "http://192.168.238.1:8081/users/";
    public static final String login_url = base_url + "users_Login";
    public static final String FINDALL_URL = base_url + "findAll";
    public static final String BASE_URL_DOCTORS = "http://192.168.238.1:8081/doctors/";
    public static final String FINDALL_URL_DOCTORS = BASE_URL_DOCTORS + "findAll";
    public static final String base_url1 = "http://192.168.123.56:8081/chat/";
    public static final String base_url2 = "http://192.168.123.56:8081/medicine-orders/";
    public static final String base_url3 = "http://192.168.123.56:8081/drugs/";
    public static final String FINDALLMESSAGEBYID_URL_CHAT = base_url1 + "findChatMessagesById";
    public static final String SENDMESSAGE_URL_CHAT = base_url1 + "sendMessage";
    public static final String FINDMEDICINEORDERSBYID_URL_Order = base_url2 + "findMedicineOrdersById";
    public static final String GENERATEORDER_URL_ORDER = base_url2 + "generateOrder";

    public static final String FINDALLDRUGS_URL_DRUGS = base_url3 + "findAllDrugs";
    public static final String FINDALLBYMAPPER_URL_DRUGS = base_url3 + "findAllByMapper";

}
