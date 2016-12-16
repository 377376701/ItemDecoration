package com.alex.itemdecoration.ui.base;

import org.alex.baseui.AbsActivity;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 */

public abstract class IDActivity extends AbsActivity {
    @Override
    public int getBodyViewId() {
        return 0;
    }

    @Override
    public void showPreviewLayout() {

    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showBodyLayout() {

    }

    @Override
    public void showEmptyLayout() {

    }

    @Override
    public void showFailLayout() {

    }
}
