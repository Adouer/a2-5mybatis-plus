package com.adouer.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MybatisPlus,时间日期自动填充处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler1 implements MetaObjectHandler {
    /**
     * “增加自动填充”【表示新增时，哪些字段需要自动填充】
     * 新增一条数据，createTime，updateTime都要更新
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.........");
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }

    /**
     * “更新自动填充”【表示更新时，哪些字段需要自动填充】
     *  更新一条数据的时候，只有updateTime字段更新
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update insert fill.........");
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
    }
}
