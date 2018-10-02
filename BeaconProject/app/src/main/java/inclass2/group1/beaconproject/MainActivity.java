package inclass2.group1.beaconproject;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView section;

    private BeaconManager beaconManager;
    private BeaconRegion region1;//produce
    private BeaconRegion region2;//grocery
    private BeaconRegion region3;//lifestyle

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    public static String loadLifestyle = "http://ec2-18-222-225-70.us-east-2.compute.amazonaws.com:3000/lifestyleDiscounts";
    public static String loadProduce = "http://ec2-18-222-225-70.us-east-2.compute.amazonaws.com:3000/produceDiscounts";
    public static String loadGrocery = "http://ec2-18-222-225-70.us-east-2.compute.amazonaws.com:3000/groceryDiscounts";

    ProgressDialog progressDialog;
    DiscountInfo discountInfo;
    ArrayList<Discounts> lifestyle = new ArrayList<Discounts>();
    ArrayList<Discounts> grocery = new ArrayList<Discounts>();
    ArrayList<Discounts> produce = new ArrayList<Discounts>();

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        section = findViewById(R.id.textViewSection);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading discounts!");

        beaconManager = new BeaconManager(this);
        region1 = new BeaconRegion("Produce",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 1564, 34409);
        region2 = new BeaconRegion("Grocery",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 55125, 738);
        region3 = new BeaconRegion("Lifestyle",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 59599, 33091);

        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> beacons) {
                Log.d("test", "Total beacons found are: " + beacons.size());
                Toast.makeText(getApplicationContext(),"Beacon found",Toast.LENGTH_SHORT).show();
                if(!beacons.isEmpty()){
                    Beacon nearestBeacon = beacons.get(0);
                    Log.d("test", "nearest beacon is : " + nearestBeacon.getMajor() + nearestBeacon.getMinor());
                    if(nearestBeacon.getMajor()==1564 && nearestBeacon.getMinor()==34409){
                        progressDialog.show();
                        loadDiscounts(loadProduce);
                        section.setText("Discounts from Produce");
                    }else if(nearestBeacon.getMajor()==55125 && nearestBeacon.getMinor()==738){
                        progressDialog.show();
                        loadDiscounts(loadGrocery);
                        section.setText("Discounts from Lifestyle");
                    }else if(nearestBeacon.getMajor()==59599 && nearestBeacon.getMinor()==33091){
                        progressDialog.show();
                        loadDiscounts(loadLifestyle);
                        section.setText("Discounts from Grocery");
                    }
                }
            }
        });
    }

   @Override
    protected void onResume() {
        super.onResume();
//        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region1);
                beaconManager.startRanging(region2);
                beaconManager.startRanging(region3);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region1);
        beaconManager.stopRanging(region2);
        beaconManager.stopRanging(region3);
        super.onPause();
    }

    private void loadDiscounts(String loadURL){
        final Request request = new Request.Builder().url(loadURL).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("test", "onFailure:"  + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String result = response.body().toString();
                    Log.d("test", "onResponse: " + result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            discountInfo = gson.fromJson(result,DiscountInfo.class);
                            mAdapter = new DiscountsAdapter(getApplicationContext(),produce);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }
                    });

                }
            }
        });
    }

    /*private void loadDiscounts() {
        try {
            String readFile = readJsonFile();
            Log.d("test", "reading json string file : " + readFile);
            Gson gson = new Gson();
            discountInfo = gson.fromJson(readFile,DiscountInfo.class);
            Log.d("test", "Loading discount list : " + discountInfo.toString());
            groupDiscounts(discountInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String readJsonFile() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.discount);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        String jsonString = writer.toString();
        return jsonString;
    }
}
