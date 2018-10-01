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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

import inclass2.group1.beaconproject.dataTable.DatabaseDataManager;

public class MainActivity extends AppCompatActivity {

    TextView section;

    private BeaconManager beaconManager;
    private BeaconRegion region1;//produce
    private BeaconRegion region2;//grocery
    private BeaconRegion region3;//lifestyle

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase;

    DatabaseDataManager databaseDataManager;

    public static String LIFESTYLE = "lifestyle";
    public static String GROCERY = "grocery";
    public static String PRODUCE = "produce";

    ProgressDialog progressDialog;
    DiscountInfo discountInfo;
    ArrayList<Discounts> lifestyle = new ArrayList<Discounts>();
    ArrayList<Discounts> grocery = new ArrayList<Discounts>();
    ArrayList<Discounts> produce = new ArrayList<Discounts>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        section = findViewById(R.id.textViewSection);

        databaseDataManager = new DatabaseDataManager(this);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Welcome to Smart Discounts!");
        loadDiscounts();

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
                        section.setText("Discounts from Produce");
                        mAdapter = new DiscountsAdapter(getApplicationContext(),produce);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if(nearestBeacon.getMajor()==55125 && nearestBeacon.getMinor()==738){
                        section.setText("Discounts from Lifestyle");
                        mAdapter = new DiscountsAdapter(getApplicationContext(),lifestyle);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if(nearestBeacon.getMajor()==59599 && nearestBeacon.getMinor()==33091){
                        section.setText("Discounts from Grocery");
                        mAdapter = new DiscountsAdapter(getApplicationContext(),grocery);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
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

    private void loadDiscounts() {
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
    }

    private void groupDiscounts(DiscountInfo discountInfo) {
        for(Discounts info:discountInfo.getDiscountDetails()){
//            databaseDataManager.save(info);
            if(info.getRegion().equals(LIFESTYLE)){
                lifestyle.add(info);
                if(mDatabase.child("discounts")==null){
                    mDatabase.child("discounts").child("lifestyle").push().setValue(info);
                }
            }else if(info.getRegion().equals(GROCERY)){
                grocery.add(info);
                if(mDatabase.child("discounts")==null){
                    mDatabase.child("discounts").child("grocery").push().setValue(info);
                }
            }else{
                produce.add(info);
                if(mDatabase.child("discounts")==null) {
                    mDatabase.child("discounts").child("produce").push().setValue(info);
                }
            }
        }
        Log.d("test", "After grouping LifeStyle List is : " + lifestyle.size());
        Log.d("test", "After grouping LifeStyle List is : " + grocery.size());
        Log.d("test", "After grouping LifeStyle List is : " + produce.size());

        /*List<Discounts> notes = databaseDataManager.getAll();//reads all the data
        Log.d("test", "Entered all the data into the DB: " + notes.toString());*/
        if(mDatabase.getDatabase()!=null){
            mDatabase.child("discounts").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("test", "onDataChange: " +dataSnapshot.getChildren());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        databaseDataManager.close();
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
