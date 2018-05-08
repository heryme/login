package p.com.gameproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vikas Patel on 7/8/2017.
 */

public class SharePref {

    Context mContext;
    SharedPreferences sharedPref;
    public static final String TICKET_NUMBER = "ticket_number";
    public static final String IS_LOGIN = "is_login";

    public SharePref(Context mContext) {
        this.mContext = mContext;
        sharedPref = mContext.getSharedPreferences("gameProject", Context.MODE_PRIVATE);
    }

    /**
     * Set Ticket Number
     *
     * @param ticketNumber
     */
    public void setTicketNumber(String ticketNumber) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TICKET_NUMBER, ticketNumber);
        editor.commit();
    }

    public String getTicketNumber() {
        return sharedPref.getString(TICKET_NUMBER, "");
    }

    /**
     * User login or not
     * @param isLogin
     */
    public void setUserLogin(Boolean isLogin) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public Boolean isLogin() {
        return sharedPref.getBoolean(IS_LOGIN,false);
    }



}

