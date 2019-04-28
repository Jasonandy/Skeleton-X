/*******************************************************************************
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
package cn.ucaner.skeleton.crawler.webmagic.downloader;

import cn.ucaner.skeleton.crawler.webmagic.utils.IPCheckUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.crawler.webmagic.downloader
 * @Description： <p> HupuProxyProvider  </p>
 * <doc>
 *      http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/proxy.html
 * </doc>
 * @Author： - Jason
 * @CreatTime：2019/4/28 - 9:54
 * @Modify By：
 * @ModifyTime： 2019/4/28
 * @Modify marker：
 */
public class HupuProxyProvider implements ProxyProvider {

    /**
     * 代理列表
     */
    private final List<Proxy> proxies;

    /**
     * pointer
     */
    private final AtomicInteger pointer;

    /**
     * HupuProxyProvider
     * @param proxies
     */
    public HupuProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    /**
     * HupuProxyProvider
     * @param proxies
     * @param pointer
     */
    private HupuProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies = proxies;
        this.pointer = pointer;
    }

    public static HupuProxyProvider from(Proxy... proxies) {
        ArrayList proxiesTemp = new ArrayList(proxies.length);
        Proxy[] var2 = proxies;
        int var3 = proxies.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Proxy proxy = var2[var4];
            if(IPCheckUtil.checkValidIP(proxy.getHost(), proxy.getPort())) {
                proxiesTemp.add(proxy);
            }
        }
        proxiesTemp.trimToSize();
        /**
         * 返回一个只读取的list
         */
        return new HupuProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
    }

    @Override
    public Proxy getProxy(Task task) {
        return this.proxies.get(this.incrForLoop());
    }

    /**
     * incrForLoop
     * @return
     */
    private int incrForLoop() {
        int p = this.pointer.incrementAndGet();
        int size = this.proxies.size();
        if(p < size) {
            return p;
        } else {
            while(!this.pointer.compareAndSet(p, p % size)) {
                p = this.pointer.get();
            }
            return p % size;
        }
    }

}
