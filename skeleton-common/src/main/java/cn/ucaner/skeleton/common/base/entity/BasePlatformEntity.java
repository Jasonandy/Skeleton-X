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
package cn.ucaner.skeleton.common.base.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName：BasePlatformEntity
 * @Description： <p> BasePlatformEntity  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/18 - 16:39
 * @Modify By：
 * @ModifyTime： 2019/3/18
 * @Modify marker：
 * @version V1.0
*/
public class BasePlatformEntity implements Serializable {

    /**
     * 创建人id
     */
    protected String cp;

    /**
     * 创建人名称
     */
    protected String cpName;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date ct;

    /**
     * 修改人id
     */
    protected String ep;

    /**
     * 修改人名称
     */
    protected String epName;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date et;

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp
     *            the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp == null ? null : cp.trim();
    }

    /**
     * @return the ct
     */
    public Date getCt() {
        return ct;
    }

    /**
     * @param ct
     *            the ct to set
     */
    public void setCt(Date ct) {
        this.ct = ct;
    }

    /**
     * @return the ep
     */
    public String getEp() {
        return ep;
    }

    /**
     * @param ep
     *            the ep to set
     */
    public void setEp(String ep) {
        this.ep = ep == null ? null : ep.trim();
    }

    /**
     * @return the et
     */
    public Date getEt() {
        return et;
    }

    /**
     * @param et
     *            the et to set
     */
    public void setEt(Date et) {
        this.et = et;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName == null ? null : cpName.trim();
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName == null ? null : epName.trim();
    }

}
