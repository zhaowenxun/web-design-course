package c.s.service;

import com.staffmanage.dao.persionalReport;
import java.util.List;

public interface persionalService{

    List<persionalReport> getStudentBySid(String beginTime);
    //获取当前时间间隔内的人事报表数据
}

