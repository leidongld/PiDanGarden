package com.openld.pidangarden.garden.modifyplantingarden.v;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatTextView;

import com.openld.pidangarden.R;
import com.openld.pidangarden.garden.modifyplantingarden.ModifyPlantInGardenContract;
import com.openld.pidangarden.main.beans.PlantInGardenBean;

/**
 * author: lllddd
 * created on: 2021/8/10 10:52
 * description:添加/修改花园中的植物页面
 */
public class ModifyPlantInGardenActivity extends AppCompatActivity implements ModifyPlantInGardenContract.IModifyPlantInGardenView {
    private AppCompatImageView mImgPlant;

    private AppCompatEditText mEdtPlantName;

    private AppCompatEditText mEdtPlantDesc;

    private String mType;

    private PlantInGardenBean mPlantInGardenBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_plant_in_garden_activity);

        obtainIntentData();
    }

    private void obtainIntentData() {
        Intent intent = getIntent();
        mType = intent.getStringExtra("type");
        mPlantInGardenBean = intent.getParcelableExtra("plant");
    }
}
