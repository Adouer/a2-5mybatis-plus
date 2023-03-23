package com.adouer.mybatisplus;

import com.adouer.mybatisplus.mapper.UserMapper;
import com.adouer.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * 测试wrapper
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectList(){

        //查name不为空，email不为空，年龄大于等于6
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",6);
         userMapper.selectList(wrapper).forEach(System.out::println);   //对比map
    }
    @Test
    public void selectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Jone");
        System.out.println(userMapper.selectOne(wrapper));
    }

    /**
     *  查询年龄在20-30之间的人
     */
    @Test
    public void selectBetweenAnd(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);   //区间
        System.out.println(userMapper.selectCount(wrapper));    //查询结果数
    }

    /**
     * 模糊查询，查询邮箱中不包含e,并且以“t”开头的
     */
    @Test
    public void selectMaps(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("email","e")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);

    }
    /**
     * 查询，子查询
     */
    @Test
    public void selectObjects(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id <3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);

    }
    /**
     * 通过id降序排序
     */
    @Test
    public void desc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
