package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface ContentCategoryService {
    Result selectContentCategoryByParentId(Long id);
    Result insertContentCategory(TbContentCategory tbContentCategory);
    Result deleteContentCategoryById(Long categoryId);
    Result updateContentCategory(TbContentCategory tbContentCategory);
}
