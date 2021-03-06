/******************************************************************************
 * ~ Copyright (c) 2018 [jasonandy@hotmail.com | https://github.com/Jasonandy] *
 * ~                                                                           *
 * ~ Licensed under the Apache License, Version 2.0 (the "License”);           *
 * ~ you may not use this file except in compliance with the License.          *
 * ~ You may obtain a copy of the License at                                   *
 * ~                                                                           *
 * ~    http://www.apache.org/licenses/LICENSE-2.0                             *
 * ~                                                                           *
 * ~ Unless required by applicable law or agreed to in writing, software       *
 * ~ distributed under the License is distributed on an "AS IS" BASIS,         *
 * ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * ~ See the License for the specific language governing permissions and       *
 * ~ limitations under the License.                                            *
 ******************************************************************************/
package cn.ucaner.skeleton.webapp.security.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName：SysUser
 * @Description： <p> SysUser  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 12:20
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 * @version V1.0
*/
public class SysUser {

    /**
     * userId
     */
    private Long userId;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * realName
     */
    private String realName;

    /**
     * tel
     */
    private String tel;

    /**
     * remark
     */
    private String remark;

    /**
     * deptId
     */
    private String deptId;

    /**
     * hasValid
     */
    private String hasValid;

    /**
     * hasDeptAdmin
     */
    private String hasDeptAdmin;

    /**
     * themes
     */
    private String themes;

    /**
     * createUserId
     */
    private Long createUserId;

    /**
     * createUserName
     */
    private String createUserName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * 当前用户 拥有哪些角色
     */
    private List<SysRole> roles = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getHasValid() {
        return hasValid;
    }

    public void setHasValid(String hasValid) {
        this.hasValid = hasValid == null ? null : hasValid.trim();
    }

    public String getHasDeptAdmin() {
        return hasDeptAdmin;
    }

    public void setHasDeptAdmin(String hasDeptAdmin) {
        this.hasDeptAdmin = hasDeptAdmin == null ? null : hasDeptAdmin.trim();
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes == null ? null : themes.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}