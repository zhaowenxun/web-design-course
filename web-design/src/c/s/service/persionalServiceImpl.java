package c.s.service;

import com.staffmanage.dao.persionalReport;
import com.staffmanage.dao.persionalServiceDao;
import com.staffmanage.dao.persionalServiceDaoImpl;

import java.util.List;
/*
    designer:zhaowenxun
 */
public class persionalServiceImpl implements persionalService{

    @Override
    public List<persionalReport> getStudentBySid(String beginTime) {
        persionalServiceDao psr= new persionalServiceDaoImpl();
        return psr.getStudentBySid(beginTime);
    }
}
