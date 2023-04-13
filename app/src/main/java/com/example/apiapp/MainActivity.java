package com.example.apiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

     Button getHttpReq;
     Button postData;
     TextView textResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHttpReq = findViewById(R.id.getHttpReq);
        postData = findViewById(R.id.postData);
        textResponse = findViewById(R.id.textResponse);

        getHttpReq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makeGetRequest();
            }
        });

        postData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makePostRequest();
            }
        });
    }

    private void makeGetRequest()
    {
        String url = "https://reqres.in/api/users?page=1";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        textResponse.setText(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }


    private void makePostRequest()
    {
        String url = "https://reqres.in/api/users";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try
        {
            postData.put("name", "John Doe");
            postData.put("job", "Software Developer");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        textResponse.setText(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }
}
