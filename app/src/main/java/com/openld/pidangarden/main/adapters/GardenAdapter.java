package com.openld.pidangarden.main.adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.openld.pidangarden.R;
import com.openld.pidangarden.garden.modifyplantingarden.v.ModifyPlantInGardenActivity;
import com.openld.pidangarden.garden.showplantingarden.v.ShowPlantInGardenActivity;
import com.openld.pidangarden.main.beans.PlantInGardenBean;
import com.openld.toolkit.CollectionUtils;
import com.openld.toolkit.PreventContinueClickListener;

import java.util.List;

/**
 * author: lllddd
 * created on: 2021/8/10 9:29
 * description:花园的适配器
 */
public class GardenAdapter extends RecyclerView.Adapter<GardenAdapter.ShowViewHolder> {
    private final AppCompatActivity mActivity;

    private final List<PlantInGardenBean> mPlantInGardenBeanList;

    public GardenAdapter(@NonNull AppCompatActivity activity, @NonNull List<PlantInGardenBean> plantInGardenBeanList) {
        this.mActivity = activity;
        this.mPlantInGardenBeanList = plantInGardenBeanList;
    }

    @NonNull
    @Override
    public GardenAdapter.ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 展示植物
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_garden_plant_show, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        PlantInGardenBean plantInGardenBean = mPlantInGardenBeanList.get(position);
        if (plantInGardenBean == null) {
            return;
        }


        holder.imgPlant.setImageResource(0);
        holder.txtPlantName.setText(plantInGardenBean.getPlantName());
        holder.txtPlantDescription.setText(plantInGardenBean.getPlantDescription());

        holder.cardShow.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ShowPlantInGardenActivity.class);
                mActivity.startActivity(intent);
            }
        });

        holder.imgEdit.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ModifyPlantInGardenActivity.class);
                intent.putExtra("type", "modify");
                intent.putExtra("plant", (Parcelable) plantInGardenBean);
                mActivity.startActivity(intent);
            }
        });

        holder.cardShow.setOnLongClickListener(v -> {
            // TODO: 2021/8/10 删除
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.isEmpty(mPlantInGardenBeanList) ? 0 : mPlantInGardenBeanList.size();
    }

    /**
     * 展示的ViewHolder
     */
    public static class ShowViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardShow;

        private final AppCompatImageView imgEdit;

        private final AppCompatImageView imgPlant;

        private final AppCompatTextView txtPlantName;

        private final AppCompatTextView txtPlantDescription;

        private ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            cardShow = itemView.findViewById(R.id.card_show);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgPlant = itemView.findViewById(R.id.img_plant);
            txtPlantName = itemView.findViewById(R.id.txt_plant_name);
            txtPlantDescription = itemView.findViewById(R.id.txt_plant_description);
        }
    }
}
