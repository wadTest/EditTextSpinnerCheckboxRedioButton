package com.prospec.edittextspinnercheckboxrediobutton;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

public class MySQLClient {

    //    บันทึก/รับ URL ใหม่
    private static final String DATA_INSERT_URL = "http://119.59.103.121/app_mobile/test%20checkbox%20spinner/CRUD.php";

    //INSTANCE FIELDS
    private final Context c;

    public MySQLClient(Context c) {
        this.c = c;
    }

    //    SAVE/INSERT URL
    public void add(Spacecraft s, final View... inputViews) {
        if (s == null) {
            Toast.makeText(c, "ไม่มีข้อมูลที่จะบันทึก", Toast.LENGTH_SHORT).show();
        } else {
            AndroidNetworking.post(DATA_INSERT_URL)
                    .addBodyParameter("action", "save")
                    .addBodyParameter("edittext", s.getName())// รับ EditText
                    .addBodyParameter("spinner", s.getPropellant())// รับ Spinner
                    .addBodyParameter("checkbox", String.valueOf(s.getTechnologyExists()))// รับ Checkbox
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if (response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(c, "การตอบสนองของเซิร์ฟเวอร์ PHP : " + responseString, Toast.LENGTH_SHORT).show();

                                    if (responseString.equalsIgnoreCase("Success")) {
                                        //RESET VIEWS
                                        EditText nameTxt = (EditText) inputViews[0];
                                        Spinner spPropellant = (Spinner) inputViews[1];

                                        nameTxt.setText("");
                                        spPropellant.setSelection(0);
                                    } else {
                                        Toast.makeText(c, "PHP ไม่ประสบความสำเร็จ : ", Toast.LENGTH_SHORT).show();

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "การตอบสนองที่ดี แต่ JAVA ไม่สามารถแยก JSON มันได้รับ : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(c, "ไม่สำเร็จ: ข้อผิดพลาดคือ : " + anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
        }
    }

}//Main Class