package com.staffmanage.dao;

import com.staffmanage.entity.staffxun;

import java.util.List;

public interface separatedHiresDao {
    public List<staffxun> getSeparatedHires(String begin, String end, String option);
}
