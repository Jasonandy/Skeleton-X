package cn.ucaner.skeleton.gateway.controller.test;

import cn.ucaner.skeleton.common.vo.RespBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.controller.test
 * @Description： <p> TestController  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 11:18
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
@RestController
@RequestMapping(value = "/v1/test")
public class TestController {

    @GetMapping("/token")
    public RespBody justForTest() {
        RespBody respBody = new RespBody();
        respBody.addOK(new Random().nextInt(24),"yes ok .");
        return respBody;
    }

}
