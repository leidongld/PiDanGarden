package com.openld.pidangarden.main.v;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.openld.pidangarden.R;
import com.openld.pidangarden.main.fragments.GardenFragment;
import com.openld.pidangarden.main.fragments.MallFragment;
import com.openld.pidangarden.main.fragments.MyFragment;
import com.openld.pidangarden.main.fragments.ParamsFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    // 底部Tab组
    private RadioGroup mTabGroup;

    // 花园Tab
    private RadioButton mTabGarden;

    // 动态Tab
    private RadioButton mTabParams;

    // 商城Tab
    private RadioButton mTabMall;

    // 我的Tab
    private RadioButton mTabMy;

    // 花园碎片
    private GardenFragment mGardenFragment;

    // 动态碎片
    private ParamsFragment mParamsFragment;

    // 商城碎片
    private MallFragment mMallFragment;

    // 我的碎片
    private MyFragment mMyFragment;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    private void initWidgets() {
        mTabGroup = findViewById(R.id.rdgp_tabs);
        mTabGarden = findViewById(R.id.tab_garden);
        mTabParams = findViewById(R.id.tab_params);
        mTabMall = findViewById(R.id.tab_mall);
        mTabMy = findViewById(R.id.tab_my);

        mTabGroup.setId(0);
        mTabGroup.setOnCheckedChangeListener(this);

        mGardenFragment = GardenFragment.newInstance("garden1", "garden2");
        mParamsFragment = ParamsFragment.newInstance("params1", "params2");
        mMallFragment = MallFragment.newInstance("mall1", "mall2");
        mMyFragment = MyFragment.newInstance("my1", "my2");

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lly_container, mGardenFragment, "garden").commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tab_garden:
                // 切换到花园
                switch2GardenFragment();
                break;
            case R.id.tab_params:
                // 切换到动态
                switch2ParamsFragment();
                break;
            case R.id.tab_mall:
                // 切换到商城
                switch2MallFragment();
                break;
            case R.id.tab_my:
                // 切换到我的
                switch2MyFragment();
                break;
            default:
                break;
        }
    }

    /**
     * 切换到花园Fragment
     */
    private void switch2GardenFragment() {
        Toast.makeText(this, "切换到花园", Toast.LENGTH_SHORT).show();

        Fragment fragment = mFragmentManager.findFragmentByTag("garden");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (fragment == null) {
            mGardenFragment = GardenFragment.newInstance("garden1", "garden2");
        }
        fragmentTransaction.replace(R.id.lly_container, mGardenFragment, "garden").commit();
    }

    /**
     * 切换到动态Fragment
     */
    private void switch2ParamsFragment() {
        Toast.makeText(this, "切换到动态", Toast.LENGTH_SHORT).show();

        Fragment fragment = mFragmentManager.findFragmentByTag("params");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (fragment == null) {
            mParamsFragment = ParamsFragment.newInstance("params1", "params2");
        }
        fragmentTransaction.replace(R.id.lly_container, mParamsFragment, "params").commit();
    }

    /**
     * 切换到商城Fragment
     */
    private void switch2MallFragment() {
        Toast.makeText(this, "切换到商城", Toast.LENGTH_SHORT).show();

        Fragment fragment = mFragmentManager.findFragmentByTag("mall");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (fragment == null) {
            mMallFragment = MallFragment.newInstance("mall1", "mall2");
        }
        fragmentTransaction.replace(R.id.lly_container, mMallFragment, "mall").commit();
    }

    /**
     * 切换到我的Fragment
     */
    private void switch2MyFragment() {
        Toast.makeText(this, "切换到我的", Toast.LENGTH_SHORT).show();

        Fragment fragment = mFragmentManager.findFragmentByTag("my");
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (fragment == null) {
            mMyFragment = MyFragment.newInstance("my1", "my2");
        }
        fragmentTransaction.replace(R.id.lly_container, mMyFragment, "my").commit();
    }
}