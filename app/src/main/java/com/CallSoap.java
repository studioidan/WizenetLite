package com;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CallSoap {
    //#############operation name######################
    public final String USER_Login = "USER_Login";
    public final String USER_Clients = "USER_Clients";
    public final String USER_Status = "USER_Status";
    public final String REMINDERS_retAlert = "REMINDERS_retAlert";
    public final String USER_Details = "USER_Details";
    public final String USER_getClientsContacts = "getClientsContacts";
    public final String ORDER_getMgnetItemsList = "PRODUCTS_ITEMS_LIST";
    public final String CREATE_OFFLINE = "CREATE_OFFLINE";
    public final String ORDER_getMgnetClientItemsList = "PRODUCTS_CLIENTS_ITEMS_LIST";
    public final String CALLS_List = "CALLS_List";
    public final String Wz_Login = "Wz_Login";
    public final String Wz_Calls_List = "Wz_Calls_List";
    public final String Wz_Call_setTime = "Wz_Call_setTime";
    public final String Wz_Call_getTime = "Wz_Call_getTime";
    public final String Wz_Call_Update = "Wz_Call_Update";
    public final String Wz_Call_Statuses = "Wz_Call_Statuses";
    public final String Wz_Forgot = "Wz_Forgot";
    public final String Wz_timeReport = "Wz_timeReport";
    public final String Wz_getState = "Wz_getState";
    public final String Wz_Update_Call_Field = "Wz_Update_Call_Field";
    public final String Wz_Update_Action_Field = "Wz_Update_Action_Field";

    public final String Wz_Get_Client_Item_List = "Wz_Get_Client_Item_List";
    public final String Wz_getUrl = "Wz_getUrl";
    public final String Wz_retClientFavorites = "Wz_retClientFavorites";
    public final String Wz_Send_Call_Offline = "Wz_Send_Call_Offline";
    public final String Wz_calls_Summary = "Wz_calls_Summary";
    public final String Wz_retClientReports = "Wz_retClientReports";
    public final String Wz_Call_setTime_Offline = "Wz_Call_setTime_Offline";
    public final String Wz_ACTIONS_retList = "Wz_ACTIONS_retList";
    public final String Wz_Clients_List = "Wz_Clients_List";
    public final String Wz_ret_ClientsAddressesByActions = "Wz_ret_ClientsAddressesByActions";
    public final String Wz_getCtypeIDandSons = "Wz_getCtypeIDandSons";
    public final String Wz_getProjects = "Wz_getProjects";
    public final String Wz_getTasks = "Wz_getTasks";
    public final String Wz_createISAction = "Wz_createISAction";
    public final String Wz_createISActionTime = "Wz_createISActionTime";
    public final String Wz_getIS_StatusList = "Wz_getIS_StatusList";
    public final String Wz_User_Details = "Wz_User_Details";
    public final String Wz_getOstatusList = "Wz_getOstatusList";
    public final String Wz_getLeadsList = "Wz_getLeadsList";
    public final String Wz_Update_Lead_Field = "Wz_Update_Lead_Field";
    public final String Wz_getUsersOptions = "Wz_getUsersOptions";
    public final String Wz_retProducts = "Wz_retProducts";
    public final String Wz_Json = "Wz_Json";

    //#############name space######################
    public final String NAMESPACE = "http://tempuri.org/";
    //#############SOAP ACTION######################
    public final String USER_Login_SOAP_ACTION = "http://tempuri.org/USER_Login";
    public final String USER_Clients_SOAP_ACTION2 = "http://tempuri.org/USER_Clients";
    public final String USER_Status_SOAP_ACTION3 = "http://tempuri.org/USER_Status";
    public final String REMINDERS_retAlert_SOAP_ACTION4 = "http://tempuri.org/REMINDERS_retAlert";
    public final String USER_Details_SOAP_ACTION5 = "http://tempuri.org/USER_Details";
    public final String USER_getClientsContacts_SOAP_ACTION6 = "http://tempuri.org/getClientsContacts";
    public final String ORDER_getMgnetItemsList_SOAP_ACTION7 = "http://tempuri.org/PRODUCTS_ITEMS_LIST";
    public final String CREATE_OFFLINE_SOAP_ACTION8 = "http://tempuri.org/CREATE_OFFLINE";
    public final String ORDER_getMgnetClientItemsList_SOAP_ACTION9 = "http://tempuri.org/PRODUCTS_CLIENTS_ITEMS_LIST";
    public final String CALLS_List_SOAP_ACTION10 = "http://tempuri.org/CALLS_List";


    //new functions
    public final String Wz_Login_SOAP_ACTION = "http://tempuri.org/Wz_Login";
    public final String Wz_Calls_List_SOAP_ACTION = "http://tempuri.org/Wz_Calls_List";
    public final String Wz_Call_setTime_SOAP_ACTION = "http://tempuri.org/Wz_Call_setTime";
    public final String Wz_Call_getTime_SOAP_ACTION = "http://tempuri.org/Wz_Call_getTime";
    public final String Wz_Call_Update_SOAP_ACTION = "http://tempuri.org/Wz_Call_Update";
    public final String Wz_Call_Statuses_SOAP_ACTION = "http://tempuri.org/Wz_Call_Statuses";
    public final String Wz_Forgot_SOAP_ACTION = "http://tempuri.org/Wz_Forgot";
    public final String Wz_timeReport_SOAP_ACTION = "http://tempuri.org/Wz_timeReport";
    public final String Wz_getState_SOAP_ACTION = "http://tempuri.org/Wz_getState";
    public final String Wz_Update_Call_Field_SOAP_ACTION = "http://tempuri.org/Wz_Update_Call_Field";
    public final String Wz_Get_Client_Item_List_SOAP_ACTION = "http://tempuri.org/Wz_Get_Client_Item_List";
    public final String Wz_getUrl_SOAP_ACTION = "http://tempuri.org/Wz_getUrl";
    public final String Wz_retClientFavorites_SOAP_ACTION = "http://tempuri.org/Wz_retClientFavorites";
    public final String Wz_Send_Call_Offline_SOAP_ACTION = "http://tempuri.org/Wz_Send_Call_Offline";
    public final String Wz_calls_Summary_SOAP_ACTION = "http://tempuri.org/Wz_calls_Summary";
    public final String Wz_retClientReports_SOAP_ACTION = "http://tempuri.org/Wz_retClientReports";
    public final String Wz_Call_setTime_Offline_SOAP_ACTION = "http://tempuri.org/Wz_Call_setTime_Offline";
    public final String Wz_ACTIONS_retList_SOAP_ACTION = "http://tempuri.org/Wz_ACTIONS_retList";
    public final String Wz_Clients_List_SOAP_ACTION = "http://tempuri.org/Wz_Clients_List";
    public final String Wz_ret_ClientsAddressesByActions_SOAP_ACTION = "http://tempuri.org/Wz_ret_ClientsAddressesByActions";
    public final String Wz_getCtypeIDandSons_SOAP_ACTION = "http://tempuri.org/Wz_getCtypeIDandSons";
    public final String Wz_getProjects_SOAP_ACTION = "http://tempuri.org/Wz_getProjects";
    public final String Wz_getTasks_SOAP_ACTION = "http://tempuri.org/Wz_getTasks";
    public final String Wz_createISAction_SOAP_ACTION = "http://tempuri.org/Wz_createISAction";
    public final String Wz_createISActionTime_SOAP_ACTION = "http://tempuri.org/Wz_createISActionTime";
    public final String Wz_getIS_StatusList_SOAP_ACTION = "http://tempuri.org/Wz_getIS_StatusList";
    public final String Wz_Update_Action_Field_SOAP_ACTION = "http://tempuri.org/Wz_Update_Action_Field";
    public final String Wz_User_Details_SOAP_ACTION = "http://tempuri.org/Wz_User_Details";
    public final String Wz_getOstatusList_SOAP_ACTION = "http://tempuri.org/Wz_getOstatusList";
    public final String Wz_getLeadsList_SOAP_ACTION = "http://tempuri.org/Wz_getLeadsList";
    public final String Wz_Update_Lead_Field_SOAP_ACTION = "http://tempuri.org/Wz_Update_Lead_Field";
    public final String Wz_getUsersOptions_SOAP_ACTION = "http://tempuri.org/Wz_getUsersOptions";
    public final String Wz_retProducts_SOAP_ACTION = "http://tempuri.org/Wz_retProducts";
    public final String Wz_Json_SOAP_ACTION = "http://tempuri.org/Wz_Json";


    //public  final String URL = "http://main.wizenet.co.il/webservices/freelance.asmx";
    public String URL;
    Helper h = new Helper();
    //public  final String URL =readTextFromFile();
    String suffix = "/webservices/freelance.asmx";

    //URL is transfer because i cant pass the getInstance to here
    public CallSoap(String url) {
        this.URL = url + suffix;//db.getControlPanel(1).getUrl();
    }

    //###################################
    //LOGIN CALL
    //###################################
    //region login
    public String Call(String mac_address, String email, String pass, String token) {
        SoapObject request = new SoapObject(NAMESPACE, USER_Login);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("Email", email);
        request.addProperty("Pass", pass);
        request.addProperty("GoogleToken", token);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);

        Object response = null;
        try {
            httpTransport.call(USER_Login_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            //response = "error";
            //return response.toString();
            //response=exception.toString();
        }
        try {
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "incorrect";
        }


    }
//endregion

    //////////REMINDER///////////
//region reminder
    public String Call4(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, REMINDERS_retAlert);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(REMINDERS_retAlert_SOAP_ACTION4, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = "error";
            return response.toString();
        }
        try {
            return response.toString();
        } catch (Exception e) {
            h.LogPrintExStackTrace(e);
            return "Error";
        }
    }
    //endregion
    //////////REMINDER///////////

    //region user_details
    public String Call_USER_Details(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, USER_Details);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(USER_Details_SOAP_ACTION5, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
        }
        try {
            return response.toString();
        } catch (Exception e) {
            h.LogPrintExStackTrace(e);
            return "Error";
        }
    }

    //endregion
    //###################################
    //CUSTOMERS CALL
    //###################################
    //region clients
    public String Call2(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, USER_Clients);//namespace , operation
        PropertyInfo pi = new PropertyInfo();
        pi.setName("MACaddress");
        pi.setValue(mac_address);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;

        try {
            httpTransport.call(USER_Clients_SOAP_ACTION2, envelope);
            SoapObject result = (SoapObject) envelope.bodyIn;
            response = result;
        } catch (Exception exception) {
            h.LogPrintExStackTrace(exception);
        }
        return response.toString();
    }

    //endregion
    //###################################
    //STATUS CALL
    //###################################
//region status
    public String Call3(String mac_address, String longtitude, String latitude) {
        SoapObject request = new SoapObject(NAMESPACE, USER_Status);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("latitude", latitude);
        request.addProperty("longitude", longtitude);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(USER_Status_SOAP_ACTION3, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {

            h.LogPrintExStackTrace(exception);
            return exception.toString();
        }
        return response.toString();

    }
//endregion

    //region clientsContacts
    public String Call_getClientsContacts(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, USER_getClientsContacts);//namespace , operation
        request.addProperty("MACaddress", mac_address);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(USER_getClientsContacts_SOAP_ACTION6, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            h.LogPrintExStackTrace(exception);
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region get item_order products
    public String getOrdersProducts(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, ORDER_getMgnetItemsList);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("PcatID", -1);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(ORDER_getMgnetItemsList_SOAP_ACTION7, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }
//endregion


    //region client items
    public String get_mgnet_client_items_list(String mac_address, String cid) {
        SoapObject request = new SoapObject(NAMESPACE, ORDER_getMgnetClientItemsList);//namespace , operation


        request.addProperty("MACaddress", mac_address);

        request.addProperty("CardCode", cid);

        request.addProperty("PcatID", -1);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(ORDER_getMgnetClientItemsList_SOAP_ACTION9, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion

    //region create_offline
    public String CREATE_OFFLINE(String mac_address, String json) {
        SoapObject request = new SoapObject(NAMESPACE, CREATE_OFFLINE);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("json", json);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(CREATE_OFFLINE_SOAP_ACTION8, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            h.LogPrintExStackTrace(exception);
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region create_offline
    public String GET_CALLS_LIST(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, CALLS_List);//namespace , operation
        request.addProperty("MACaddress", mac_address);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(CALLS_List_SOAP_ACTION10, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            h.LogPrintExStackTrace(exception);
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region Wz_Calls_List
    public String Wz_Calls_List(String mac_address, int CallStatusID) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Calls_List);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("CallStatusID", CallStatusID);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Calls_List_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            h.LogPrintExStackTrace(exception);
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region Wz_Calls_List
    public String Wz_Call_setTime(String mac_address, int CallID, String action, String latitude, String longtitude) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Call_setTime);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("CallID", CallID);
        request.addProperty("action", action);
        request.addProperty("latitude", latitude);
        request.addProperty("longtitude", longtitude);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Call_setTime_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
//region Wz_Calls_List
    public String Wz_Call_getTime(String mac_address, int CallID, String action) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Call_getTime);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("CallID", CallID);
        request.addProperty("action", action);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Call_getTime_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region Wz_Call_Update
    public String Wz_Call_Update(String mac_address, int CallID, int CallStatusID, String CallAnswer) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Call_Update);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("CallID", CallID);
        request.addProperty("CallStatusID", CallStatusID);
        request.addProperty("CallAnswer", CallAnswer);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Call_Update_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }
//endregion

    //region Wz_Call_Statuses
    public String Wz_Call_Statuses(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Call_Statuses);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Call_Statuses_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
//region Wz_Forgot
    public String Wz_Forgot(String mac_address, String Email) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Forgot);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("Email", Email);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Forgot_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }
    //endregion

    //region Wz_timeReport
    public String Wz_timeReport(String mac_address, String action, String latitude, String longtitude) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_timeReport);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("action", action);
        request.addProperty("latitude", latitude);
        request.addProperty("longtitude", longtitude);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_timeReport_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
//region Wz_getState
    public String Wz_getState(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getState);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getState_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
//region Wz_Update_Call_Field
    public String Wz_Update_Call_Field(String mac_address, String callid, String field, String value) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Update_Call_Field);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("callid", callid);
        request.addProperty("field", field);
        request.addProperty("value", value);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Update_Call_Field_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
//region Wz_Update_Call_Field
    public String Wz_Update_Action_Field(String mac_address, String actionid, String field, String value) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Update_Action_Field);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("actionid", actionid);
        request.addProperty("field", field);
        request.addProperty("value", value);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Update_Action_Field_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

    //endregion
    //region Wz_Get_Client_Item_List
    public String Wz_Get_Client_Item_List(String mac_address, String cid) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Get_Client_Item_List);//namespace , operation


        request.addProperty("MACaddress", mac_address);

        request.addProperty("CardCodes", cid);

        request.addProperty("PcatID", -1);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Get_Client_Item_List_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_Get_Client_Item_List
    public String Wz_getUrl(String mac_address, String msid) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getUrl);//namespace , operation


        request.addProperty("MACaddress", mac_address);

        request.addProperty("msid", msid);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE("http://main.wizenet.co.il/webservices/freelance.asmx");
        Object response = null;
        try {
            httpTransport.call(Wz_getUrl_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_retClientFavorites
    public String Wz_retClientFavorites(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_retClientFavorites);//namespace , operation
        request.addProperty("MACaddress", mac_address);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_retClientFavorites_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_Send_Call_Offline
    public String Wz_Send_Call_Offline(String mac_address, String jsonString) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Send_Call_Offline);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("jsonString", jsonString);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Send_Call_Offline_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_calls_Summary
    public String Wz_calls_Summary(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_calls_Summary);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_calls_Summary_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_retClientReports
    public String Wz_retClientReports(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_retClientReports);//namespace , operation
        request.addProperty("MACaddress", mac_address);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_retClientReports_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion

    //region Wz_Call_setTime_Offline
    public String Wz_Call_setTime_Offline(String mac_address, String jsonString) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Call_setTime_Offline);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("jsonString", jsonString);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Call_setTime_Offline_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_ACTIONS_retList
    public String Wz_ACTIONS_retList(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_ACTIONS_retList);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_ACTIONS_retList_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    //region Wz_Clients_List
    public String Wz_Clients_List(String mac_address, int CtypeID, int CparentID) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Clients_List);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("CtypeID", CtypeID);
        request.addProperty("CparentID", CparentID);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Clients_List_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    // region Wz_Clients_List
    public String Wz_ret_ClientsAddressesByActions(String mac_address, String action) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_ret_ClientsAddressesByActions);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("action", action);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_ret_ClientsAddressesByActions_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    // region Wz_getCtypeIDandSons
    public String Wz_getCtypeIDandSons(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getCtypeIDandSons);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getCtypeIDandSons_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_getProjects
    public String Wz_getProjects(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getProjects);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getProjects_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    // region Wz_getProjects
    public String Wz_getTasks(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getTasks);//namespace , operation
        request.addProperty("MACaddress", mac_address);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getTasks_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    // region Wz_getProjects
    public String Wz_createISAction(String mac_address, String jsonString) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_createISAction);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("jsonString", jsonString);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_createISAction_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
    // region Wz_createISActionTime
    public String Wz_createISActionTime(String mac_address, String jsonString) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_createISActionTime);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("jsonString", jsonString);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_createISActionTime_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_createISActionTime
    public String Wz_getIS_StatusList(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getIS_StatusList);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getIS_StatusList_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_createISActionTime
    public String Wz_User_Details(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_User_Details);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_User_Details_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_getOstatusList
    public String Wz_getOstatusList(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getOstatusList);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getOstatusList_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_getLeadsList
    public String Wz_getLeadsList(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getLeadsList);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getLeadsList_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_Update_Lead_Field
    public String Wz_Update_Lead_Field(String mac_address, String oid, String field, String value) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Update_Lead_Field);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("oid", oid);
        request.addProperty("field", field);
        request.addProperty("value", value);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Update_Lead_Field_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_Update_Lead_Field
    public String Wz_getUsersOptions(String mac_address, String typing) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_getUsersOptions);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        request.addProperty("typing", typing);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_getUsersOptions_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_retProducts
    public String Wz_retProducts(String mac_address) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_retProducts);//namespace , operation
        request.addProperty("MACaddress", mac_address);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_retProducts_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    //endregion
// region Wz_Json
    public String Wz_Json(String mac_address, String jsonString, String action) {
        SoapObject request = new SoapObject(NAMESPACE, Wz_Json);
        request.addProperty("MACaddress", mac_address);
        request.addProperty("jsonString", jsonString);
        request.addProperty("action", action);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);
        Object response = null;
        try {
            httpTransport.call(Wz_Json_SOAP_ACTION, envelope);
            response = envelope.bodyIn;
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
//endregion
}
