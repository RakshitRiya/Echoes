package com.example.support;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.talkingfingers.R;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;
    //we are storing all the Cards in a list
    private List<Card> CardList;

    //getting the context and Card list with constructor
    public CardAdapter(Context mCtx, List<Card> CardList) {
        this.mCtx = mCtx;
        this.CardList = CardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardview, parent, false);
        return new CardViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        //getting the Card of the specified position
        Card Card = CardList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(Card.getName());
        holder.textViewShortDesc.setText(Card.getAddress());
        holder.textViewRating.setText(String.valueOf(Card.getPhone()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(Card.getId()));
        holder.textViewShortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(((TextView)v).getText().toString()){
                    case "96, 1st cross Rd, 1st Stage, Kadugondanahalli, Bengaluru, Karnataka 560084": {

                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/n57Fo"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "JSS 38th, 1st Main Rd, 8th Block, 7th Block, Jayanagar, Bengaluru, Karnataka 560070": {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/Thh7Y"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "2250, 1st A Cross Road, Nanja Reddy Colony, Jeevan Bima Nagar, Nanja Reddy Colony, Jeevan Bima Nagar, Bengaluru, Karnataka 560017": {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/A31Hf"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "No:28, Rustum Bagh Main Rd, Rustam Bagh Layout, Old Airport Road, Rustam Bagh Layout, Bengaluru, Karnataka 560008":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/tKDaHcFHReo"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "KMC Mercara Trunk Rd, Opposite St.Agnes College, Mallikatte, Kadri, Mangaluru, Karnataka 575002": {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/7cTSUT5fSaz"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Vamanjoor, Mangaluru, Karnataka 575028": {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/s24ux3BWKes"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Gurunath Nagar Old Hb-24, Old Hubli, Old Hubli, Mehboob Nagar Circle, Jannat Nagar, Old Hubli, Hubballi, Karnataka 580024": {
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/TknkB4C4Ug12"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Pocket 40, Kalkaji, New Delhi, Delhi 110019":{
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                            mapIntent.setData(Uri.parse("https://maps.app.goo.gl/g6wrL"));
                            mCtx.startActivity(mapIntent);
                            break;
                    }
                    case "92, Asaf Ali Rd, Kamla Market, Qutab Institutional Area, New Delhi, Delhi 110002":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/RopJZ"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Plot no. 4 & 7, Institutional Area, Bhartendu Harish Chandra Marg, Kakkadi Mode, Preet Vihar, Behind Shanti Mukand Hospital, Dayanand Vihar, Anand Vihar, New Delhi, Delhi 110001":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/5sg6Y"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "383, Sector 2 R K Puram, Sector 2, RK Puram, New Delhi, Delhi 110022":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/mqQ87"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Nehru Vihar, Mukherjee Nagar, Delhi, 110054":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/FwD5q"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "A-221, C Block, Sector 40, Noida, Uttar Pradesh 201303":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://maps.app.goo.gl/XnKKN"));
                        mCtx.startActivity(mapIntent);
                        break;
                }
                case "SB-31, Sector 117, Noida, Uttar Pradesh 201304":{
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("https://maps.app.goo.gl/ShPfX"));
                mCtx.startActivity(mapIntent);
                break;

            }
                    case "First Floor,DDA Community Hall Gali Chandiwali, Paharganj New Delhi-110055":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/ELezFPmVSax"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "215 Brighton Ave, Allston, MA 02134, USA":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/Yw6jmt4HSyL2"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Block F Parkside Office Village Knowledge Gateway Nesfield Road COLCHESTER CO4 3ZL":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://goo.gl/maps/KiqEz5FjgTG2"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }
                    case "Visit site":{
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("https://asl101deafsingers.blogspot.com"));
                        mCtx.startActivity(mapIntent);
                        break;
                    }

        }
        }});
        final CharSequence text = holder.textViewShortDesc.getText();
        final SpannableString spannableString = new SpannableString( text );
        spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.textViewShortDesc.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    @Override
    public int getItemCount() {
        if(CardList==null)return 0;
        return CardList.size();
    }


    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating;
        ImageView imageView;

        public CardViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.locn_name);
            textViewShortDesc = itemView.findViewById(R.id.locn_address);
            textViewRating = itemView.findViewById(R.id.locn_phone);
            imageView = itemView.findViewById(R.id.locn_img);
        }
    }
}