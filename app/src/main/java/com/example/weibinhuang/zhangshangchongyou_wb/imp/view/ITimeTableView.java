package com.example.weibinhuang.zhangshangchongyou_wb.imp.view;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.SortCourse;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;

import java.util.List;

/**
 * Created by weibinhuang on 18-5-27.
 */

public interface ITimeTableView {
    void showTimeTable(List<SortCourse> list);
}
