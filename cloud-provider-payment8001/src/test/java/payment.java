import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.service.PaymentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class payment {

    @Resource
    private PaymentService paymentService;
    @Resource
    private PaymentDao paymentDao;

    @Test
    public void create() {
        System.out.println(paymentDao.getPaymentById(1L));
    }
}
