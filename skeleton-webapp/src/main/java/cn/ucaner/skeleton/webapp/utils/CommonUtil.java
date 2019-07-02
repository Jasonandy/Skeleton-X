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
package cn.ucaner.skeleton.webapp.utils;

import cn.ucaner.skeleton.webapp.entity.Message;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.utils
 * @Description： <p> CommonUtil  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:46
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class CommonUtil {

    private CommonUtil() { }

    /**
     * 对List集合中的数据按照时间顺序排序
     *
     * @param list List<Message>
     */
    public static void sort(List<Message> list) {
        list.sort(Comparator.comparing(Message::getTime));
    }

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
