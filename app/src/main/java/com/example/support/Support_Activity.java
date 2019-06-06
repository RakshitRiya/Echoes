package com.example.support;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.talkingfingers.R;

import java.util.ArrayList;

public class Support_Activity extends AppCompatActivity {

    private RadioButton faciltiesRadioButton,educationRadio;
    RecyclerView recyclerView;
    Spinner stateSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        stateSpinner = findViewById(R.id.state_spinner);
        faciltiesRadioButton=findViewById(R.id.failities_radio);
        educationRadio=findViewById(R.id.education_radio);
        //getting the recyclerview from xml
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setNestedScrollingEnabled(false);
        RadioGroup radioGroup =findViewById(R.id.support_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId)
                {
                    case R.id.failities_radio:{
                        stateSpinner.setVisibility(View.INVISIBLE);
                        RecyclerView recyclerView = findViewById(R.id.recyclerview);
                        ArrayList CardList = new ArrayList<>();
                        //adding some items to our list
                        recyclerView.setVisibility(View.VISIBLE);
                        CardList.add(new Card("Delhi foundation of Deaf Women","First Floor,DDA Community Hall Gali Chandiwali, Paharganj New Delhi-110055","+919250109188",R.drawable.dfodw));
                        CardList.add(new Card("Deaf inc","215 Brighton Ave, Allston, MA 02134, USA","+16175054823",R.drawable.deafinc));
                        CardList.add(new Card("Royal Deaf","Block F Parkside Office Village Knowledge Gateway Nesfield Road COLCHESTER CO4 3ZL","3006882525",R.drawable.royaldeaf));
                        CardList.add(new Card("Deaf Singers","Visit site","",R.drawable.deafsingers));

                        //creating recyclerview adapter
                        CardAdapter adapter = new CardAdapter(Support_Activity.this, CardList);
                        //setting adapter to recyclerview
                        recyclerView.setAdapter(adapter);

                        break;
                    }
                    case R.id.education_radio:{
                        stateSpinner.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                        recyclerView.setAdapter(null);
                        break;
                    }
                }
            }
        });
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                if (position == 0) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(new CardAdapter(Support_Activity.this, null));
                    return;
                }
                recyclerView.setVisibility(View.VISIBLE);
                switch (parent.getSelectedItem().toString()) {
                    case "Karnataka": { //initializing the Cardlist
                        ArrayList CardList = new ArrayList<>();
                        //adding some items to our list
                        CardList.add(
                                new Card(
                                        "Sunaad Kannada School For Hearing Impared", "96, 1st cross Rd, 1st Stage, Kadugondanahalli, Bengaluru, Karnataka 560084", "+917022460158", R.drawable.sunaad));
                        CardList.add(
                                new Card(
                                        "J S S Sahana Integrated & Special School", "JSS 38th, 1st Main Rd, 8th Block, 7th Block, Jayanagar, Bengaluru, Karnataka 560070", "+918022970127", R.drawable.sahana));
                        CardList.add(
                                new Card(
                                        "National Residential School For The Deaf", "2250, 1st A Cross Road, Nanja Reddy Colony, Jeevan Bima Nagar, Nanja Reddy Colony, Jeevan Bima Nagar, Bengaluru, Karnataka 560017", "+919986630182", R.drawable.nrsftd));
                        CardList.add(
                                new Card(
                                        "The Sheila Kothavala Institute For The Deaf", "No:28, Rustum Bagh Main Rd, Rustam Bagh Layout, Old Airport Road, Rustam Bagh Layout, Bengaluru, Karnataka 560008", "+9108025262274", R.drawable.shiela));
                        CardList.add(
                                new Card(
                                        "St. Agnes Special School", "KMC Mercara Trunk Rd, Opposite St.Agnes College, Mallikatte, Kadri, Mangaluru, Karnataka 575002", "+9108242443376", R.drawable.agnes));
                        CardList.add(
                                new Card(
                                        "SDM Mangala Jyothi Integrated School", "Vamanjoor, Mangaluru, Karnataka 575028", "+918242262030", R.drawable.mangala));
                        CardList.add(
                                new Card(
                                        "Deaf & Dumb School", "Gurunath Nagar Old Hb-24, Old Hubli, Old Hubli, Mehboob Nagar Circle, Jannat Nagar, Old Hubli, Hubballi, Karnataka 580024", "+918362305244", R.drawable.dnds));
                        //creating recyclerview adapter
                        CardAdapter adapter = new CardAdapter(Support_Activity.this, CardList);
                        //setting adapter to recyclerview
                        recyclerView.setAdapter(adapter);
                    }
                    break;
                    case "Delhi":{
                        ArrayList CardList=new ArrayList();
                        CardList.add(new Card("Govt Secondary School For The Deaf","Pocket 40, Kalkaji, New Delhi, Delhi 110019","+919871562466",R.drawable.gssftdk));
                        CardList.add(new Card("Delhi Association Of the Deaf","92, Asaf Ali Rd, Kamla Market, Qutab Institutional Area, New Delhi, Delhi 110002","+919810483308",R.drawable.daftdkm));
                        CardList.add(new Card("Premala Bai Chavan School For The Deaf","Plot no. 4 & 7, Institutional Area, Bhartendu Harish Chandra Marg, Kakkadi Mode, Preet Vihar, Behind Shanti Mukand Hospital, Dayanand Vihar, Anand Vihar, New Delhi, Delhi 110001","+919250109188",R.drawable.pbcsftd));
                        CardList.add(new Card("Suniye school for speech and hearing impaired ","383, Sector 2 R K Puram, Sector 2, RK Puram, New Delhi, Delhi 110022","+919873031973 ",R.drawable.ssfsahi));
                        CardList.add(new Card("Government Lady Noyce School Of Deaf","Nehru Vihar, Mukherjee Nagar, Delhi, 110054","+918368922769",R.drawable.glnsod));
                        CardList.add(new Card("Ali Yavar Jung National Institute Of Speech And Hearing Disabilities (Divyangjan)","A-221, C Block, Sector 40, Noida, Uttar Pradesh 201303","+918527696052",R.drawable.ayjniosahd));
                        CardList.add(new Card("Noida Deaf Society","SB-31, Sector 117, Noida, Uttar Pradesh 201304","+917838517770",R.drawable.nds));

                        CardAdapter adapter = new CardAdapter(Support_Activity.this, CardList);
                        //setting adapter to recyclerview
                        recyclerView.setAdapter(adapter);
                    }
                    break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
