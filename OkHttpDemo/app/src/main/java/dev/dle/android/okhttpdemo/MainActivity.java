package dev.dle.android.okhttpdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
  
  private static final String TAG = "Response";
  Button loadApi;
  Button postReq;
  private String result = "";
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });
    
    
    loadApi = (Button) findViewById(R.id.loadApi);
    postReq = (Button) findViewById(R.id.postReq);
    
    loadApi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        
        try {
          getHttpResponse();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    
    postReq.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        
        try {
          postRequest();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    
    try {
      getHttpResponse();
      Log.w(TAG, result);
    } catch (IOException e) {
      e.printStackTrace();
      Log.w(TAG, result);
    }
    
  }
  
  public void getHttpResponse() throws IOException {
  

    
    
    
    
    String url = "https://reqres.in/api/users";
    OkHttpClient client = new OkHttpClient();
     String[] localResult = {""};
    Request request = new Request.Builder()
      .url(url)
      .header("Accept", "application/json")
      .header("Content-Type", "application/json")
      .build();
    
    //        Response response = client.newCall(request).execute();
    //        Log.e(TAG, response.body().string());
    
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        
        String mMessage = e.getMessage().toString();
        Log.w("failure Response", mMessage);
        //call.cancel();
      }
      
      @Override
      public void onResponse(Call call, Response response) throws IOException {
        
        String mMessage = response.body().string();
        //        Log.e(TAG, mMessage);
        result = mMessage;
        
      }
    });
    

  }
  
  public void postRequest() throws IOException {
    
    MediaType MEDIA_TYPE = MediaType.parse("application/json");
    String url = "https://reqres.in/api/users";
    
    OkHttpClient client = new OkHttpClient();
    
    JSONObject postdata = new JSONObject();
    try {
      postdata.put("name", "paul rudd");
      postdata.put("movie", "I Love You, Man.");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
    
    Request request = new Request.Builder()
      .url(url)
      .post(body)
      .header("Accept", "application/json")
      .header("Content-Type", "application/json")
      .build();
    
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        
        String mMessage = e.getMessage().toString();
        Log.e("failure Response", mMessage);
        //call.cancel();
      }
      
      @Override
      public void onResponse(Call call, Response response) throws IOException {
        
        String mMessage = response.body().string();
        Log.e(TAG, mMessage);
        
      }
    });
  }
  
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    
    return super.onOptionsItemSelected(item);
  }
}
