package cn.ucaner.skeleton.service.controller;

import cn.ucaner.skeleton.common.vo.RespBody;
import cn.ucaner.skeleton.service.user.entity.User;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.controller
 * @Description： <p> TODO </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 13:20
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
@Controller
@RequestMapping("/es")
public class ElasticSearchController {

    private static Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    @Resource
    RestHighLevelClient restHighLevelClient;

    @ResponseBody
    @RequestMapping(value = "/test",method= RequestMethod.GET)
    public RespBody testElasticSearch() {
        RespBody respBody = new RespBody();
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("user", "Jason");
            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearch");
            IndexRequest indexRequest = new IndexRequest("index", "type", "1").source(jsonMap);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest,null);
            logger.info("测试-testElasticSearch:{}",indexResponse);
        } catch (Exception e) {
            respBody.addError(e.getMessage());
        }
        return respBody;
    }


}
