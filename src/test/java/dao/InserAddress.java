package dao;

import com.ymall.dao.AddressMapper;
import com.ymall.pojo.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zc on 2017/7/6.
 */
public class InserAddress {
    @Autowired
    private AddressMapper addressMapper;


    @Test
    public void test(){
        Address address=new Address();
        address.setUserId(1);
        address.setReceiverName("张三");
        int insert = addressMapper.insert(address);
        if(insert>0){
            System.out.printf(address.getId().toString());
        }
    }
}
