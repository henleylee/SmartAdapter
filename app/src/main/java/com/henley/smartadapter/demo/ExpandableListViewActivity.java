package com.henley.smartadapter.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.henley.smartadapter.demo.adapter.ExpandableAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Henley
 * @date 2017/9/22 16:58
 */
public class ExpandableListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelistview);
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandablelistview);
        HashMap<String, List<CardLicense>> datas = getDatas();
        ExpandableAdapter adapter = new ExpandableAdapter(datas);
        expandableListView.setAdapter(adapter);
    }

    public HashMap<String, List<CardLicense>> getDatas() {
        try {
            String json = getFromAssets("cardLicense.json");
            HashMap<String, List<CardLicense>> map = new HashMap<>();
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                if (jsonObject != null) {
                    CardLicense cardLicense = new CardLicense();
                    cardLicense.setProvince(jsonObject.optString("province"));
                    cardLicense.setPcode(jsonObject.optString("Pcode"));
                    cardLicense.setCity(jsonObject.optString("city"));
                    cardLicense.setCode(jsonObject.optString("code"));
                    String key = cardLicense.getProvince() + "(" + cardLicense.getPcode() + ")";
                    if (!map.containsKey(key)) {
                        map.put(key, new ArrayList<CardLicense>());
                    }
                    map.get(key).add(cardLicense);
                }
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFromAssets(String fileName) throws IOException {
        InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName), "UTF-8");
        BufferedReader bufReader = new BufferedReader(inputReader);
        String line = "";
        String Result = "";
        while ((line = bufReader.readLine()) != null)
            Result += line;
        return Result;
    }

}
