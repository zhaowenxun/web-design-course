package com.staffmanage.dao;

import com.staffmanage.entity.staffxun;

import java.util.List;

public interface newHiresDao {
    public List<staffxun> getnewHires(String begin, String end , String option);
}
