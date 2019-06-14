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
package cn.ucaner.skeleton.codegen.gen;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.codegen.gen
 * @Description： <p> CodeGeneratorMain 参考${url:https://mp.baomidou.com/guide/generator.html} </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 16:08
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 */
public class CodeGeneratorMain {

    /**
     * DB_URL
     */
    private static String DB_URL = "jdbc:mysql://47.106.125.14:3306/skeleton?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";

    /**
     * DB_DIVER
     */
    private static String DB_DIVER = "com.mysql.jdbc.Driver";

    /**
     * DB_USER_NAME
     */
    private static String DB_USER_NAME = "root";

    /**
     * DB_PASS_WORD
     */
    private static String DB_PASS_WORD = "123456";

    private static String PARENT_PACKAGE = "cn.ucaner.skeleton.codegen.test";

    private static String SUPER_ENTITY_CLASS = "cn.ucaner.skeleton.codegen.gen";

    private static String SUPER_CONTROLLER_CLASS = "cn.ucaner.skeleton.codegen.gen";

    /**
     * 如果模板引擎是 velocity
     */
    private static String templatePath = "/template/Mapper.xml.vm";

    /**
     * skeleton-codegen
     *
     */
    private static String PORJECT_NAME = "/skeleton-codegen/";

    /**
     * 读取控制台内容
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入:" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    /**
     * Just for test
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 代码生成器
         */
        AutoGenerator mpg = new AutoGenerator();
        /**
         * 全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+PORJECT_NAME;
        gc.setOutputDir(projectPath + "/src/test/java");
        gc.setAuthor("Jason");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        /**
         *  数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setSchemaName("public");
        dsc.setDriverName(DB_DIVER);
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASS_WORD);
        mpg.setDataSource(dsc);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);

        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };


        /**
         * 自定义配置会被优先输出
         */
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                /**
                 * 自定义输出文件名
                 * 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                 */
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        /**
         * 判断自定义文件夹是否需要创建
         */
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                //checkDir("调用默认方法创建的目录");
                return false;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         *
         * 自己定义的模板信息
         */
        TemplateConfig templateConfig = new TemplateConfig();

        /**
         *  配置自定义输出模板
         * 指定自定义模板路径,注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
         */
        templateConfig.setEntity("template/Entity.java");
        templateConfig.setService("/template/Service.java");
        templateConfig.setController("/template/Controller.java");

        templateConfig.setXml("/template/Mapper.xml");
        mpg.setTemplate(templateConfig);

        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        /**
         * baseEntity
         */
        strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        /**
         * BaseController
         */
        strategy.setSuperControllerClass(SUPER_CONTROLLER_CLASS);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        /**
         * 选择模板引擎
         */
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
