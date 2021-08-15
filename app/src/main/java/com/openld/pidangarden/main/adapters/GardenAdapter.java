package com.openld.pidangarden.main.adapters;

import android.content.Intent;
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
import com.openld.pidangarden.main.beans.PlantInGardenBean;
import com.openld.pidangarden.garden.addplantingarden.v.AddPlantInGardenActivity;
import com.openld.pidangarden.garden.modifyplantingarden.v.ModifyPlantInGardenActivity;
import com.openld.pidangarden.garden.showplantingarden.v.ShowPlantInGardenActivity;
import com.openld.toolkit.CollectionUtils;
import com.openld.toolkit.PreventContinueClickListener;

import java.util.List;

/**
 * author: lllddd
 * created on: 2021/8/10 9:29
 * description:花园的适配器
 */
public class GardenAdapter extends RecyclerView.Adapter<GardenAdapter.AbsGardenViewHolder> {
    private final AppCompatActivity mActivity;

    private final List<PlantInGardenBean> mPlantInGardenBeanList;

    /**
     * ViewHolder的类型
     */
    private interface ViewHolderType {
        int ADD = 0;
        int SHOW = 1;
    }

    public GardenAdapter(@NonNull AppCompatActivity activity, @NonNull List<PlantInGardenBean> plantInGardenBeanList) {
        this.mActivity = activity;
        this.mPlantInGardenBeanList = plantInGardenBeanList;
    }

    @NonNull
    @Override
    public GardenAdapter.AbsGardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 添加植物
        if (ViewHolderType.ADD == viewType) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_garden_plant_add, parent, false);
            return new AddViewHolder(view);
        }
        // 展示植物
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_garden_plant_show, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GardenAdapter.AbsGardenViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (ViewHolderType.ADD == viewType) {
            onBindAddViewHolder((AddViewHolder) holder);
        } else if (ViewHolderType.SHOW == viewType) {
            onBindShowViewHolder((ShowViewHolder) holder, position);
        }
    }

    /**
     * 绑定展示ViewHolder
     *
     * @param holder   展示ViewHolder
     * @param position 位置
     */
    private void onBindShowViewHolder(@NonNull ShowViewHolder holder, int position) {
        PlantInGardenBean plantInGardenBean = mPlantInGardenBeanList.get(position);
        if (plantInGardenBean == null) {
            return;
        }
// TODO: 2021/8/10
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
                mActivity.startActivity(intent);
            }
        });

        holder.cardShow.setOnLongClickListener(v -> {
            // TODO: 2021/8/10 删除
            return true;
        });
    }

    /**
     * 绑定添加ViewHolder
     *
     * @param holder   添加ViewHolder
     */
    private void onBindAddViewHolder(@NonNull AddViewHolder holder) {
        holder.cardAdd.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AddPlantInGardenActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.isEmpty(mPlantInGardenBeanList) ? 1 : mPlantInGardenBeanList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewHolderType.ADD;
        }
        return ViewHolderType.SHOW;
    }

    /**
     * 抽象ViewHolder
     */
    abstract static class AbsGardenViewHolder extends RecyclerView.ViewHolder {
        public AbsGardenViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 添加的ViewHolder
     */
    private static class AddViewHolder extends AbsGardenViewHolder {
        private final CardView cardAdd;

        private AddViewHolder(@NonNull View itemView) {
            super(itemView);

            cardAdd = itemView.findViewById(R.id.card_add);
        }
    }

    /**
     * 展示的ViewHolder
     */
    private static class ShowViewHolder extends AbsGardenViewHolder {
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
