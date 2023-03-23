package com.adouer.mybatisplus.pojo;

import com.adouer.mybatisplus.enums.GradeEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT)//有新增操作时，数据库中该字段需要自动填充
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)//有新增和更新操作时，数据库中该字段需要自动填充
    private LocalDateTime updateTime;
    @Version //乐观锁添加version
    private Integer version;
    @TableLogic
    private Integer deleted;
    private GradeEnum grade;
}
