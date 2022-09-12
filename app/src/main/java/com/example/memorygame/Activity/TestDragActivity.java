package com.example.memorygame.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.memorygame.Adapter.CustomerListAdapter;
import com.example.memorygame.Object.Customer;
import com.example.memorygame.R;
import com.example.memorygame.listener.OnCustomerListChangedListener;
import com.example.memorygame.listener.OnStartDragListener;
import com.example.memorygame.utilities.ItemTouchHelperCallback;
import com.example.memorygame.utilities.SampleData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
public class TestDragActivity extends AppCompatActivity implements OnCustomerListChangedListener, OnStartDragListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String LIST_OF_SORTED_DATA_ID = "json_list_sorted_data_id";
    public final static String PREFERENCE_FILE = "preference_file";
    private RecyclerView mRecyclerView;
    private CustomerListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private List<Customer> mCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_customer_list);
        mSharedPreferences = this.getApplicationContext().getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        setupRecyclerView();
    }

    @Override
    public void onNoteListChanged(List<Customer> customers) {
        //after drag and drop operation, the new list of Customers is passed in here

        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<Long> listOfSortedCustomerId = new ArrayList<>();

        for (Customer customer: customers){
            listOfSortedCustomerId.add(customer.getId());
        }
        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedCustomerIds = gson.toJson(listOfSortedCustomerId);
        //save to SharedPreference
        mEditor.putString(LIST_OF_SORTED_DATA_ID, jsonListOfSortedCustomerIds).commit();
        mEditor.commit();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    private void setupRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mCustomers = getSampleData();

        //setup the adapter with empty list
        mAdapter = new CustomerListAdapter(mCustomers, this, this, this);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//        mRecyclerView.addItemDecoration(new
//                HorizontalDividerItemDecoration.Builder(this)
//                .colorResId(R.color.black)
//                .size(2)
//                .build());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Customer> getSampleData() {
        //Get the sample data
        List<Customer> customerList = SampleData.addSampleCustomers();

        //create an empty array to hold the list of sorted Customers
        List<Customer> sortedCustomers = new ArrayList<Customer>();

        //get the JSON array of the ordered of sorted customers
        String jsonListOfSortedCustomerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");

        //check for null
        if (!jsonListOfSortedCustomerId.isEmpty()) {

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            List<Long> listOfSortedCustomersId = gson.fromJson
                    (jsonListOfSortedCustomerId, new TypeToken<List<Long>>() {
                    }.getType());
            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0) {
                for (Long id : listOfSortedCustomersId) {
                    for (Customer customer : customerList) {
                        if (customer.getId().equals(id)) {
                            sortedCustomers.add(customer);
                            customerList.remove(customer);
                            break;
                        }
                    }
                }
            }
            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (customerList.size() > 0) {
                sortedCustomers.addAll(customerList);
            }
            return sortedCustomers;
        } else {
            return customerList;
        }
    }
}