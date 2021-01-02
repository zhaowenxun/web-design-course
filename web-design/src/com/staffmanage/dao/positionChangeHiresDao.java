package com.staffmanage.dao;

import com.staffmanage.entity.staffxun;

import java.util.List;

public interface positionChangeHiresDao {
    public List<staffxun> getpositionChangeHires(String begin, String end);
}
