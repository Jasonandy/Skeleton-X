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

/**
 * @ClassName：SysPermission
 * @Description： <p> SysPermission </p>
 * @Author： - Jason
 * @CreatTime：2019/7/19 - 13:47
 * @Modify By：
 * @ModifyTime： 2019/7/19
 * @Modify marker：
 * @version V1.0
*/
public class SysPermission {

    /**
     * permissionId
     */
    private Long permissionId;

    /**
     * resourceId
     */
    private Long resourceId;

    /**
     * resourceName
     */
    private String resourceName;

    /**
     * resourceType
     */
    private Long resourceType;

    /**
     * actionId
     */
    private Long actionId;

    /**
     * actionName
     */
    private String actionName;

    /**
     * functionCode
     */
    private String functionCode;

    /**
     * permissionName
     */
    private String permissionName;

    /**
     * permissionSign
     */
    private String permissionSign;

    /**
     * description
     */
    private String description;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Long getResourceType() {
        return resourceType;
    }

    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode == null ? null : functionCode.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionSign() {
        return permissionSign;
    }

    public void setPermissionSign(String permissionSign) {
        this.permissionSign = permissionSign == null ? null : permissionSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}