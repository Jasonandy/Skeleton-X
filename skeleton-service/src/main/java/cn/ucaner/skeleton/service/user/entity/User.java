package cn.ucaner.skeleton.service.user.entity;

import cn.ucaner.skeleton.common.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * @ClassName：User
 * @Description： <p> User  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 12:08
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 * @version V1.0
*/
public class User extends BaseEntity implements Serializable {

    private String id;

    private String name;

    private Integer age;

    private String desc;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}