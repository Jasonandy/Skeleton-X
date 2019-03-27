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
package cn.ucaner.skeleton.service.biz.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.biz.task
 * @Description： <p> SkeletonTask  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/27 - 15:04
 * @Modify By：
 * @ModifyTime： 2019/3/27
 * @Modify marker：
 */
@JobHandler(value="skeletonTask")
@Component
public class SkeletonTask extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(SkeletonTask.class);

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        for (int i = 0; i < 10 ; i++) {
            logger.info("task任务正在执行中...执行参数{}",s);
        }
        return null;
    }
}
