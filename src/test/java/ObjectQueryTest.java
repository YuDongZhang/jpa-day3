import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkManDao;
import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    //could not initialize proxy - no Session
    //测试对象导航查询(查询一个对象的时候,通过此对象查询所有的关联对象)
    @Test
    @Transactional//没有事务报错 , 添加
    public void testQuery1(){
        //查询id为1的客户
        Customer customer = customerDao.getOne(1l);//延迟加载
        //对象导航查询,此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan :
                linkMans) {
            System.out.println( linkMan);
        }

    }

    /**
     * 对象导航查询:
     *      默认使用的是延迟加载的形式查询
     *          调用get方法并不会立即发送查询,而是在使用关联对象的时候才会查询
     *          延迟加载
     *
     *  修改配置将延迟加载改为立即加载:
     *          fetch,需要配置到多表映射关系的注解上
     *
     */

    @Test
    @Transactional//没有事务报错 , 添加
    public void testQuery2(){
        //查询id为1的客户
        Customer customer = customerDao.findOne(1l);
        //对象导航查询,此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan :
                linkMans) {
            System.out.println( linkMan);
        }

    }

}
