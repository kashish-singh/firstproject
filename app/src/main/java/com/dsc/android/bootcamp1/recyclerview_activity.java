package com.dsc.android.bootcamp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recyclerview_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private List<RecyclerViewData> UserList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_activity);
        recyclerView = findViewById(R.id.recycler_view);
      //  createMockList();
        apicall();
        setUpRecyclerView();

    }
    private void apicall()
    {
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
                Call<UserWrapper> call= apiServices.getUserList();
              call.enqueue(new Callback<UserWrapper>() {
                  @Override
                  public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                      if(response.body()!= null)
                      {
                          UserList.addAll(response.body().getRecyclerViewData());
                      }
                  }

                  @Override
                  public void onFailure(Call<UserWrapper> call, Throwable t) {

                  }
              });

    }
   /* private void createMockList(){
        RecyclerViewData data;
        data = new RecyclerViewData("https://bit.ly/2NT7svr","annie","11111111");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr","stacey","2222222222");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr","tom","33333333333");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr","dick","44444444444");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr","harry","55555555555");
        mockDataList.add(data);
        data = new RecyclerViewData("https://bit.ly/2NT7svr","ginnie","66666666666");
        mockDataList.add(data);

    }*/
    private void setUpRecyclerView()
    {
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecyclerViewDataList(UserList);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
