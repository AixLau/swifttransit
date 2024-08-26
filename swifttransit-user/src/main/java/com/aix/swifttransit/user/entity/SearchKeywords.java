package com.aix.swifttransit.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 搜索关键词表
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("search_keywords")
public class SearchKeywords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关键词唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 搜索次数
     */
    private Integer searchCount;

    /**
     * 最后搜索时间
     */
    private LocalDateTime lastSearched;


}
