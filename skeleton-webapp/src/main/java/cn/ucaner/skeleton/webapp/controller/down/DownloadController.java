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
package cn.ucaner.skeleton.webapp.controller.down;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.ucaner.skeleton.webapp.utils.R;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.controller.down
 * @Description： <p> DownloadController  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/11 - 16:34
 * @Modify By：
 * @ModifyTime： 2019/7/11
 * @Modify marker：
 */
@Controller
@RequestMapping(value="/down")
public class DownloadController {

    private static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @GetMapping("/page")
    public String downImage(ModelMap map){
        map.addAttribute("pageName","通用下载器");
        return "biz/image/downLoadAndSave";
    }

    /**
     * 下载
     * @param downLoadUrl
     * @param storePath
     * @return
     */
    @ResponseBody
    @GetMapping("/save")
    public R downLoadFileSave(@Param("downLoadUrl") String downLoadUrl, @Param("storePath") String storePath){
        R r = new R();
        logger.info("== downLoadUrl:{} , storePath:{}",downLoadUrl,storePath);
        try {
            downLoadFile(downLoadUrl,storePath);
        } catch (Exception e) {
            r.setMsg("== error ==");
            r.setCode(500);
            logger.error("== downLoadFileSave:error:{} ==",e);
        }
        return  r;
    }


    /**
     * 文件下载
     * @param downLoadUrl
     * @param storePath
     */
    public static void downLoadFile(String downLoadUrl,String storePath){
        HttpUtil.downloadFile(downLoadUrl, FileUtil.file(storePath), new StreamProgress(){
            @Override
            public void start() {
                Console.log("开始下载。。。。");
            }
            @Override
            public void progress(long progressSize) {
                Console.log("已下载：{}", FileUtil.readableFileSize(progressSize));
            }
            @Override
            public void finish() {
                Console.log("下载完成！");
            }
        });
    }

}
