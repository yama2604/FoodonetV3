package com.roa.foodonetv3.commonMethods;

import android.content.Context;
import com.roa.foodonetv3.R;
import java.util.Locale;

public class StartServiceMethods {
    private static final String TAG = "StartServiceMethods";

    /** HTTP_TYPES */
    public static final int HTTP_GET = 1;
    public static final int HTTP_POST = 2;
    public static final int HTTP_PUT = 3;
    public static final int HTTP_DELETE = 4;


    /** builds and handles the building of the required URL.
     * actions in post - add JSON_TO_SEND = (String) json data to intent.
     * actions in put - add JSON_TO_SEND = (String) json data to intent.
     * @param actionType all actions - add String ACTION_TYPE to intent
     * @param args
     * edit publication - add args[0] = (String) publication id
     * delete publication - add args[0] = (String) publication id
     * get reports - add args[0] = (String) publication id, args[1] = (String) publication version
     * add report - add args[0] = (String) publication id
     * register for publication - add args[0] = (String) publication id
     * get publication registered users - add args[0] = (String) publication id
     * add group - add args[0] = String group name
     * get groups - add args[0] = (String) user id
     * add group member - add args[0] = (String) group id
     */

    public static String getUrlAddress(Context context, int actionType, String[] args) {
        /** prepares the url address according to the action intended */
        StringBuilder builder = new StringBuilder();

        /** add the server to foodonet */
        builder.append(context.getResources().getString(R.string.foodonet_server));
        switch (actionType){
            case ReceiverConstants.ACTION_GET_PUBLICATIONS:
                builder.append(context.getString(R.string.foodonet_publications));
                break;
            case ReceiverConstants.ACTION_ADD_PUBLICATION:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(context.getResources().getString(R.string._json));
                break;
            case ReceiverConstants.ACTION_EDIT_PUBLICATION: // not tested
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string._json));
                break;
            case ReceiverConstants.ACTION_DELETE_PUBLICATION:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string._json));
                break;
            case ReceiverConstants.ACTION_GET_REPORTS:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_reports_version));
                builder.append(args[1]);
                break;
            case ReceiverConstants.ACTION_ADD_REPORT: // not tested
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_publication_reports));
                break;
            case ReceiverConstants.ACTION_ADD_USER:
                builder.append(context.getResources().getString(R.string.foodonet_users));
                break;
            case ReceiverConstants.ACTION_REGISTER_TO_PUBLICATION:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_registered_user_for_publications));
                break;
            case ReceiverConstants.ACTION_GET_PUBLICATION_REGISTERED_USERS:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_registered_user_for_publications));
                break;
            case ReceiverConstants.ACTION_GET_ALL_PUBLICATIONS_REGISTERED_USERS:
                builder.append(context.getResources().getString(R.string.foodonet_publications));
                builder.append("/1");
                builder.append(context.getResources().getString(R.string.foodonet_registered_user_for_publications));
                break;
            case ReceiverConstants.ACTION_ADD_GROUP:
                builder.append(context.getResources().getString(R.string.foodonet_groups));
                break;
            case ReceiverConstants.ACTION_GET_GROUPS:
                builder.append(context.getResources().getString(R.string.foodonet_users));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_groups));
                break;
            case ReceiverConstants.ACTION_POST_FEEDBACK:
                builder.append(context.getResources().getString(R.string.foodonet_feedback));
                break;
            case ReceiverConstants.ACTION_ADD_GROUP_MEMBER:
                builder.append(context.getResources().getString(R.string.foodonet_groups));
                builder.append(String.format(Locale.US,"/%1$s",args[0]));
                builder.append(context.getResources().getString(R.string.foodonet_group_members));
                break;
            case ReceiverConstants.ACTION_ACTIVE_DEVICE_NEW_USER:
                builder.append(context.getResources().getString(R.string.foodonet_active_devices));
                break;
        }
        return builder.toString();
    }

    /** gets the appropriate https type
     * @value actionType - from ReceiverConstants
     * @return int (GET,POST,DELETE,PUT)*/
    public static int getHTTPType(int actionType){
        switch (actionType){
            case ReceiverConstants.ACTION_GET_PUBLICATIONS:
                return HTTP_GET;
            case ReceiverConstants.ACTION_ADD_PUBLICATION:
                return HTTP_POST;
            case ReceiverConstants.ACTION_EDIT_PUBLICATION: // not tested
                return HTTP_POST;
            case ReceiverConstants.ACTION_DELETE_PUBLICATION:
                return HTTP_DELETE;
            case ReceiverConstants.ACTION_GET_REPORTS:
                return HTTP_GET;
            case ReceiverConstants.ACTION_ADD_REPORT: // not tested
                return HTTP_POST;
            case ReceiverConstants.ACTION_ADD_USER:
                return HTTP_POST;
            case ReceiverConstants.ACTION_REGISTER_TO_PUBLICATION:
                return HTTP_POST;
            case ReceiverConstants.ACTION_GET_PUBLICATION_REGISTERED_USERS:
                return HTTP_GET;
            case ReceiverConstants.ACTION_GET_ALL_PUBLICATIONS_REGISTERED_USERS:
                return HTTP_GET;
            case ReceiverConstants.ACTION_ADD_GROUP:
                return HTTP_POST;
            case ReceiverConstants.ACTION_GET_GROUPS:
                return HTTP_GET;
            case ReceiverConstants.ACTION_POST_FEEDBACK:
                return HTTP_POST;
            case ReceiverConstants.ACTION_ADD_GROUP_MEMBER:
                return HTTP_POST;
            case ReceiverConstants.ACTION_ACTIVE_DEVICE_NEW_USER:
                return HTTP_POST;
        }
        return -1;
    }
}
