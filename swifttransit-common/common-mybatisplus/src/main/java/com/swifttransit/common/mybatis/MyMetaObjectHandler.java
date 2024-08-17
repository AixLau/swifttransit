package com.swifttransit.common.mybatis;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 插入时填充创建时间
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 插入时填充更新时间
        this.strictInsertFill(metaObject, "lastLogin", LocalDateTime.class, null); // 可选：初次创建时可以不填充最后登录时间
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 更新时填充更新时间
    }
}

