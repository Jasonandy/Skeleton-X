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

import java.util.Date;

/**
 * @ClassName：SysModule
 * @Description： <p> SysModule </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:55
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 * @version V1.0
*/
public class SysModule {

    /**
     * moduleId
     */
    private Long moduleId;

    /**
     * parentId
     */
    private Long parentId;

    /**
     * moduleUrl
     */
    private String moduleUrl;

    /**
     * moduleState
     */
    private String moduleState;

    /**
     * hasVisible
     */
    private String hasVisible;

    /**
     * actions
     */
    private String actions;

    /**
     * remark
     */
    private String remark;

    /**
     * moduleLevel
     */
    private Long moduleLevel;

    /**
     * displayName
     */
    private String displayName;

    /**
     * moduleLeaf
     */
    private Long moduleLeaf;

    /**
     * moduleSeq
     */
    private Long moduleSeq;

    /**
     * moduleName
     */
    private String moduleName;

    /**
     * moduleNumber
     */
    private String moduleNumber;

    /**
     * moduleDescription
     */
    private String moduleDescription;

    /**
     * creatorId
     */
    private Long creatorId;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * lastUpdateUserId
     */
    private Long lastUpdateUserId;

    /**
     * lastUpdatedTime
     */
    private Date lastUpdatedTime;

    /**
     * departmentId
     */
    private String departmentId;

    /**
     * moduleIcon
     */
    private String moduleIcon;

    /**
     * hasDisplay
     */
    private String hasDisplay;

    /**
     * displayOrder
     */
    private Long displayOrder;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl == null ? null : moduleUrl.trim();
    }

    public String getModuleState() {
        return moduleState;
    }

    public void setModuleState(String moduleState) {
        this.moduleState = moduleState == null ? null : moduleState.trim();
    }

    public String getHasVisible() {
        return hasVisible;
    }

    public void setHasVisible(String hasVisible) {
        this.hasVisible = hasVisible == null ? null : hasVisible.trim();
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions == null ? null : actions.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getModuleLevel() {
        return moduleLevel;
    }

    public void setModuleLevel(Long moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Long getModuleLeaf() {
        return moduleLeaf;
    }

    public void setModuleLeaf(Long moduleLeaf) {
        this.moduleLeaf = moduleLeaf;
    }

    public Long getModuleSeq() {
        return moduleSeq;
    }

    public void setModuleSeq(Long moduleSeq) {
        this.moduleSeq = moduleSeq;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(String moduleNumber) {
        this.moduleNumber = moduleNumber == null ? null : moduleNumber.trim();
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription == null ? null : moduleDescription.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Long lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon == null ? null : moduleIcon.trim();
    }

    public String getHasDisplay() {
        return hasDisplay;
    }

    public void setHasDisplay(String hasDisplay) {
        this.hasDisplay = hasDisplay == null ? null : hasDisplay.trim();
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }
}