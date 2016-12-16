package org.alex.helper.recycler;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.alex.helper.recycler.LayoutType.GridLayout;
import static org.alex.helper.recycler.LayoutType.HLinearLayout;
import static org.alex.helper.recycler.LayoutType.HStaggeredGridLayout;
import static org.alex.helper.recycler.LayoutType.VLinearLayout;
import static org.alex.helper.recycler.LayoutType.VStaggeredGridLayout;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@IntDef(value = {HLinearLayout, VLinearLayout, GridLayout, HStaggeredGridLayout, VStaggeredGridLayout})
public @interface LayoutType {
    /**
     * 线性的 - 垂直
     */
    int VLinearLayout = 0;
    /**
     * 线性的 - 水平
     */
    int HLinearLayout = 1;
    /**
     * 网格的
     */
    int GridLayout = 2;
    /**
     * 瀑布的 - 垂直
     */
    int VStaggeredGridLayout = 3;
    /**
     * 瀑布的 - 水平
     */
    int HStaggeredGridLayout = 4;
}