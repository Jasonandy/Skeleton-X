package cn.ucaner.skeleton.crawler.vo;

import java.io.Serializable;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.vo
 * @Description： <p> BaseCrawlerVo </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 14:36
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class BaseCrawlerVo implements Serializable {

    /**
     * id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
