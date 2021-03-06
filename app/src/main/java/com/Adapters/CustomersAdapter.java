package com.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.Activities.MenuActivity;
import com.Activities.R;
import com.Classes.Ccustomer;
import com.Fragments.FragmentCustomer;
import com.Fragments.FragmentCustomerDetails;
import com.Icon_Manager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * the position of adapter is to set the content into listview
 * with the paramters we pass it.
 *  ccustomerArrayList - is the list of customers
 *  ctx - the context we must pass it (relationship between classes and fragments/activities)
 *  Filterable is the additional built in interface that's allow us to implement the filter edit text
 */
public class CustomersAdapter extends BaseAdapter implements Filterable {
    Context c;
    private TextView goToTelephone, goToSms,goToCustomers;
    private TextView goToLandlinePhone;
    private TextView goToGps;
    private TextView profileImage;
    private TextView firstName;
    private TextView lastName;
    private TextView customerDetails;

    ArrayList<Ccustomer> ccustomerArrayList;
    CustomFilter filter;
    ArrayList<Ccustomer> filterList;
    public CustomersAdapter(ArrayList<Ccustomer> ccustomerArrayList,Context ctx) {
        this.c=ctx;
        this.ccustomerArrayList=ccustomerArrayList;
        this.filterList=ccustomerArrayList;
    }
    @Override
    public int getCount() {
        return ccustomerArrayList.size();
    }
    @Override
    public Object getItem(int pos) {
        return ccustomerArrayList.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        return ccustomerArrayList.indexOf(getItem(pos));
    }
    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        Icon_Manager icon_manager;
        icon_manager = new Icon_Manager();
        LayoutInflater inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.customer, null);
            convertView.getTag(pos);
        }

        //###################### TELEPHONE #############################
        goToTelephone = (TextView) convertView.findViewById(R.id.customers_list_item_mobile_call);
        //id1 = (TextView) v.findViewById(R.id.id1);
        goToTelephone.setTypeface(icon_manager.get_Icons("fonts/ionicons.ttf",c));

        goToTelephone.setTextSize(30);
        goToTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                try {
                    callIntent.setData(Uri.parse("tel:" + ccustomerArrayList.get(pos).getCcell()));
                    c.startActivity(callIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(c, "אין אפשרות לבצע שיחה", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Setting the icon of the landline call.
        //TODO all the methods might not work, it probably shouldn't be in the adapter
        goToLandlinePhone = (TextView) convertView.findViewById(R.id.customers_list_item_landline_call);
        goToLandlinePhone.setTypeface(icon_manager.get_Icons("fonts/ionicons.ttf",c));
        goToLandlinePhone.setTextSize(30);
        goToLandlinePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                try {
                    callIntent.setData(Uri.parse("tel:" + ccustomerArrayList.get(pos).getCphone()));
                    c.startActivity(callIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(c, "אין אפשרות לבצע שיחה", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //###################### SMS #############################
        goToSms = (TextView) convertView.findViewById(R.id.customer_list_item_sendsms);
        goToSms.setTypeface(icon_manager.get_Icons("fonts/ionicons.ttf",c));
        goToSms.setTextSize(30);
        goToSms.setTag(pos);
        goToSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:"+ccustomerArrayList.get(pos).getCcell());
                try {
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    c.startActivity(it);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(c, "אין אפשרות לשלוח הודעה", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting the icon of the gps.
        goToGps = (TextView) convertView.findViewById(R.id.customers_list_item_gps);
        goToGps.setTypeface(icon_manager.get_Icons("fonts/ionicons.ttf",c));
        goToGps.setTextSize(30);
        goToGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String url = "waze://?q=" + ccustomerArrayList.get(pos).getCaddress()+" "+ccustomerArrayList.get(pos).getCcity();
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity( intent );
                }
                catch ( ActivityNotFoundException ex  )
                {
                    // If there is no internet.
                    try {
                        // If Waze is not installed, open it in Google Play:
                        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                        c.startActivity(intent);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        //Setting the customer profile image.
        profileImage = (TextView) convertView.findViewById(R.id.customers_list_item_profile_image);
        profileImage.setTypeface(icon_manager.get_Icons("fonts/ionicons.ttf",c));
        profileImage.setTextSize(70);


        //#################    TEXTVIEW    ##########################
        TextView nameTxt=(TextView) convertView.findViewById(R.id.customers_list_item_company);
        nameTxt.setText(ccustomerArrayList.get(pos).getCcompany()); //TODO this part was commented, return if needed: +' '+ccustomerArrayList.get(pos).getCcell());
        convertView.setTag(convertView.getId(),pos);
        convertView.getTag(pos);

        this.firstName = (TextView) convertView.findViewById(R.id.customers_list_item_first_name);
        this.firstName.setText(ccustomerArrayList.get(pos).getCfname());

        this.lastName = (TextView) convertView.findViewById(R.id.customers_list_item_last_name);
        this.lastName.setText(ccustomerArrayList.get(pos).getClname());

        this.customerDetails = (TextView)convertView.findViewById(R.id.customers_list_item_details);
        this.customerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //TODO implement an interface instead

                Ccustomer ccustomer = ccustomerArrayList.get(pos);
                String CID = ccustomer.getCID();
                String firstName = ccustomer.getCfname();
                String lastName = ccustomer.getClname();
                String company = ccustomer.getCcompany();
                String cell = ccustomer.getCcell();
                String phone = ccustomer.getCphone();
                String address = ccustomer.getCaddress();
                String email = ccustomer.getCemail();

                Bundle bundle = new Bundle();
                bundle.putString("CID", CID);
                bundle.putString("FirstName", firstName);
                bundle.putString("LastName", lastName);
                bundle.putString("Company", company);
                bundle.putString("Cell", cell);
                bundle.putString("Phone", phone);
                bundle.putString("Address", address);
                bundle.putString("Email", email);

                android.support.v4.app.FragmentManager fm = ((MenuActivity)c).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                FragmentCustomerDetails fragmentCustomerDetails = new FragmentCustomerDetails();
                fragmentCustomerDetails.setArguments(bundle);
                ft.replace(R.id.container, fragmentCustomerDetails, "FragmentCustomerDetails");
                ft.addToBackStack("FragmentCustomerDetails");
                ft.commit();
            }
        });

        return convertView;
    }
    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            /**
             * call the filter class to return the correct filtered list
             */
            filter=new CustomFilter();
        }
        return filter;
    }
    //INNER CLASS
    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint != null && constraint.length()>0)
            {
                //CONSTARINT TO UPPER
                constraint=constraint.toString().toUpperCase();
                ArrayList<Ccustomer> filters=new ArrayList<Ccustomer>();
                //get specific items

                /**
                 * here i loop the customers list that i've passed before
                 * and filter by chars i type in edittext
                 */
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getCcompany().toUpperCase().contains(constraint))
                    {
                        Ccustomer p=new Ccustomer("","",filterList.get(i).getCemail(),"",filterList.get(i).getCcell(),filterList.get(i).getCcompany(),filterList.get(i).getCID());
                        //p= new Ccustomer()
                        filters.add(p);
                    }
                }
                results.count=filters.size();
                results.values=filters;
            }else
            {
                results.count=filterList.size();
                results.values=filterList;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            /**
             * what are you going to see in the results
             */
            ccustomerArrayList=(ArrayList<Ccustomer>) results.values;
            //update the listview
            notifyDataSetChanged();
        }
    }

}