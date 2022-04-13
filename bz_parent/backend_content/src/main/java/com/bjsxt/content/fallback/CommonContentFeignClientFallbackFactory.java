package com.bjsxt.content.fallback;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonContentFeignClientFallbackFactory implements FallbackFactory<CommonContentFeignClient> {
    @Override
    public CommonContentFeignClient create(Throwable throwable) {
        return new CommonContentFeignClient() {
            @Override
            public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
                return null;
            }

            @Override
            public int insertContentCategory(TbContentCategory tbContentCategory) {
                return 0;
            }

            @Override
            public int deleteContentCategoryById(Long categoryId) {
                return 0;
            }

            @Override
            public int updateContentCategory(TbContentCategory tbContentCategory) {
                return 0;
            }

            @Override
            public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
                return null;
            }

            @Override
            public int insertTbContent(TbContent tbContent) {
                return 0;
            }

            @Override
            public int deleteContentByIds(Long id) {
                return 0;
            }

            @Override
            public TbContent selectContentById(Long id) {
                return null;
            }
        };
    }
}
