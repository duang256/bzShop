package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ContentService {
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);
    int insertTbContent(TbContent tbContent);
    int deleteContentByIds(Long id);
    List<TbContent> selectFrontendContentByAD();
    TbContent selectContentById(Long id);
}
