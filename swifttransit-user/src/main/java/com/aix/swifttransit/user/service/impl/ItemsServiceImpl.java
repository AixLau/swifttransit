package com.aix.swifttransit.user.service.impl;

import com.aix.swifttransit.user.entity.Items;
import com.aix.swifttransit.user.entity.RestrictedItems;
import com.aix.swifttransit.user.entity.SearchKeywords;
import com.aix.swifttransit.user.mapper.ItemsMapper;
import com.aix.swifttransit.user.service.ItemsService;
import com.aix.swifttransit.user.service.RestrictedItemsService;
import com.aix.swifttransit.user.service.SearchKeywordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 物品信息表 服务实现类
 * </p>
 *
 * @author aix
 * @since 2024-08-26
 */
@Service
@RequiredArgsConstructor
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements ItemsService {

    private final RestrictedItemsService restrictedItemsService;
    private final SearchKeywordsService searchKeywordsService;

    @Override
    @Cacheable(value = "itemsCache", key = "#keyword")
    public List<Items> searchItems(String keyword) {
        // 1. 检查并更新搜索关键词
        updateSearchKeyword(keyword);

        // 2. 查询禁寄物品的名称列表
        List<String> restrictedItemNames = restrictedItemsService.list()
                .stream()
                .map(RestrictedItems::getName)
                .toList();

        // 3. 执行模糊查询，并排除禁寄物品
        return this.lambdaQuery()
                .like(Items::getName, keyword)
                .notIn(Items::getName, restrictedItemNames)
                .list();
    }

    /**
     * 更新搜索关键词表，记录搜索次数
     *
     * @param keyword 用户输入的搜索关键词
     */
    private void updateSearchKeyword(String keyword) {
        SearchKeywords searchKeyword = searchKeywordsService.lambdaQuery().eq(SearchKeywords::getKeyword, keyword).one();
        if (ObjectUtils.isEmpty(searchKeyword)) {
            // 如果关键词不存在则插入新数据
            searchKeyword = new SearchKeywords();
            searchKeyword.setKeyword(keyword);
            searchKeyword.setSearchCount(1);
            searchKeyword.setLastSearched(LocalDateTime.now());
            this.searchKeywordsService.save(searchKeyword);
        } else {
            // 如果关键词已存在，则更新搜索次数和最后搜索时间
            searchKeyword.setSearchCount(searchKeyword.getSearchCount() + 1);
            searchKeyword.setLastSearched(LocalDateTime.now());
            this.searchKeywordsService.updateById(searchKeyword);
        }
    }
}
