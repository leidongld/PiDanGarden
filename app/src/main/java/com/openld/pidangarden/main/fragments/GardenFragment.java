package com.openld.pidangarden.main.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openld.pidangarden.R;
import com.openld.pidangarden.garden.addplantingarden.v.AddPlantInGardenActivity;
import com.openld.pidangarden.garden.modifyplantingarden.v.ModifyPlantInGardenActivity;
import com.openld.pidangarden.main.adapters.GardenAdapter;
import com.openld.pidangarden.main.beans.PlantInGardenBean;
import com.openld.toolkit.PreventContinueClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GardenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GardenFragment extends Fragment {
    private static final int REQUEST_CODE_FOR_ADD_GARDEDN_PLANT = 1 << 2;

    private FloatingActionButton mBtnAdd;

    private RecyclerView mRcvPlants;

    private GardenAdapter mGardenAdapter;

    private List<PlantInGardenBean> mPlantInGardenBeanList;

    public GardenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GardenFragment.
     */
    public static GardenFragment newInstance() {
        GardenFragment fragment = new GardenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: 2021/8/17 获取初始传参
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garden, container, false);

        mBtnAdd = view.findViewById(R.id.btn_add);
        mRcvPlants = view.findViewById(R.id.rcv_plants);

        // 按钮点击处理
        mBtnAdd.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), ModifyPlantInGardenActivity.class);
                    intent.putExtra("type", "add");
                    getActivity().startActivityForResult(intent, REQUEST_CODE_FOR_ADD_GARDEDN_PLANT);
                }
            }
        });

        mPlantInGardenBeanList = obtainPlantsInGarden();
        GardenAdapter gardenAdapter = new GardenAdapter((AppCompatActivity) getActivity(), mPlantInGardenBeanList);
        mRcvPlants.setAdapter(gardenAdapter);

        return view;
    }

    private List<PlantInGardenBean> obtainPlantsInGarden() {
        List<PlantInGardenBean> plantInGardenBeanList = new ArrayList<>();
        // TODO: 2021/8/17 引入一个本地数据库，从本地数据库中去取值
        return plantInGardenBeanList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_ADD_GARDEDN_PLANT && resultCode == RESULT_OK) {
            mPlantInGardenBeanList.clear();
            mPlantInGardenBeanList.addAll(obtainPlantsInGarden());
            mGardenAdapter.notifyItemInserted(mPlantInGardenBeanList.size());
        }
    }
}