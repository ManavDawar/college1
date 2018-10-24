package com.example.manav.log.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manav.log.R;
import com.example.manav.log.noticeModal;
import com.example.manav.log.noticeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class notification extends Fragment {




    RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    SwipeRefreshLayout pullToRefresh;


    public notification() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("1");
        databaseReference.keepSynced(true);

        pullToRefresh =  view.findViewById(R.id.pullToRefresh);

        recyclerView = view.findViewById(R.id.notice_board);
        populateRecyclerView();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                populateRecyclerView();
                pullToRefresh.setRefreshing(false);
            }
        });

        return view;

    }

    private void populateRecyclerView() {
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<noticeModal,noticeViewholder>
                (noticeModal.class,R.layout.notice_row,noticeViewholder.class,databaseReference){

            @Override
            protected void populateViewHolder(noticeViewholder viewHolder, noticeModal model, int position) {
                viewHolder.setDate(model.getDate());
                viewHolder.setContent(model.getContent());
            }

            @Override
            public void onBindViewHolder(noticeViewholder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

