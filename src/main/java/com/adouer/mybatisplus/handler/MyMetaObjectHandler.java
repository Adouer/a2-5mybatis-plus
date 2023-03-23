package com.adouer.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * MybatisPlus,时间日期自动填充处理器【老版本】
 */
//@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * “增加自动填充”
     * 新增一条数据，createTime，updateTime都要更新
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.........");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * “更新自动填充”
     *  更新一条数据的时候，只有updateTime字段更新
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update insert fill.........");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
