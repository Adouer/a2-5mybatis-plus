package com.adouer.mybatisplus;

import com.adouer.mybatisplus.enums.GradeEnum;
import com.adouer.mybatisplus.mapper.UserMapper;
import com.adouer.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class CRUDTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数wraper，条件构造器，这里用null
        List list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void add () {
        User user = new User();
        user.setName("adouer");
        user.setAge(1);
        user.setEmail("email");
        userMapper.insert(user);
    }

    @Test
    public void update () {
        User user = new User();
        user.setId(2l);
        user.setAge(3);
        //设置为小学
        user.setGrade(GradeEnum.PRIMARY);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 乐观锁演示
     */
    @Test
    public void optimisticLocker1 () {
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.更新用户信息
        user.setAge(16);
        user.setEmail("xxx@qq.com");
        //3.执行更新
        userMapper.updateById(user);
    }

    /**
     * 乐观锁演示2
     */
    @Test
    public void optimisticLocker2 () {
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        User user2 = userMapper.selectById(1L);
        //2.更新用户信息
        user.setAge(16);
        user.setEmail("@qq.com");
        user2.setAge(6);
        user2.setEmail("@qqq.com");
        //3.执行更新
        userMapper.updateById(user2);   //user2更新后version变为3
        userMapper.updateById(user);    //此时user中，oldversion=2 现在的verison=3，不一致，不能改
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1638430154579574785L);
        System.out.println(user);
    }

    /**
     * 根据id批量查询
     */
    @Test
    public void selectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * selectByMap查询
     */
    @Test
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","zyf");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 测试分页
     */
    @Test
    public void pageTest(){
        //参数1：当前页，参数2：页面大小
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 根据id删除
     */
    @Test
    public void deleteById(){

        int i = userMapper.deleteById(1638430154579574785L);
    }
    /**
     * 根据id批量删除
     */
    @Test
    public void deleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1414058291897786373L,1414058291897786372L));
        System.out.println(i);
    }

    /**
     * 根据map按照条件删除
     */
    @Test
    public void deleteByMap(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("age",2);
        userMapper.deleteByMap(map);
    }
}
