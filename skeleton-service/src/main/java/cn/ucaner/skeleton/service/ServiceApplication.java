package cn.ucaner.skeleton.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service
 * @Description： <p> ServiceApplication  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 9:23
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
@SpringBootApplication(scanBasePackages={"mapper.*"})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
