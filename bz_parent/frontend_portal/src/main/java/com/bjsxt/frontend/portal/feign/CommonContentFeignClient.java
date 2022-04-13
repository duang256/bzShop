package com.bjsxt.frontend.portal.feign;

import com.bjsxt.pojo.TbContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "common-content",fallbackFactory = CommonContentFeignClient.class)
public interface CommonContentFeignClient {

    @PostMapping("/service/content/selectFrontendContentByAD")
    List<TbContent> selectFrontendContentByAD();
}
