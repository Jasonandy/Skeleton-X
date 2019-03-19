package cn.ucaner.skeleton.service.common.mp;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.text.MessageFormat;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.common.mp
 * @Description： <p> SkeletonMyBatis3Impl  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/19 - 9:42
 * @Modify By：
 * @ModifyTime： 2019/3/19
 * @Modify marker：
 */
public class SkeletonMyBatis3Impl extends IntrospectedTableMyBatis3Impl {

    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        if (stringHasValue(tableConfiguration.getMapperName())) {
            String mapperName = tableConfiguration.getMapperName();
            int ind = mapperName.lastIndexOf('.');
            if (ind != -1) {
                mapperName = mapperName.substring(ind + 1);
            }
            //支持mapperName = "{0}Dao" 等用法
            sb.append(MessageFormat.format(mapperName, fullyQualifiedTable.getDomainObjectName()));
            sb.append(".xml");
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper.xml");
        }
        return sb.toString();
    }

    @Override
    protected void calculateJavaClientAttributes() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(calculateJavaClientImplementationPackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAOImpl");
        setDAOImplementationType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append("DAO");
        setDAOInterfaceType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getMapperName())) {
            //支持mapperName = "{0}Dao" 等用法
            sb.append(MessageFormat.format(tableConfiguration.getMapperName(), fullyQualifiedTable.getDomainObjectName()));
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper");
        }
        setMyBatis3JavaMapperType(sb.toString());

        sb.setLength(0);
        sb.append(calculateJavaClientInterfacePackage());
        sb.append('.');
        if (stringHasValue(tableConfiguration.getSqlProviderName())) {
            //支持mapperName = "{0}SqlProvider" 等用法
            sb.append(MessageFormat.format(tableConfiguration.getSqlProviderName(), fullyQualifiedTable.getDomainObjectName()));
        } else {
            sb.append(fullyQualifiedTable.getDomainObjectName());
            sb.append("SqlProvider");
        }
        setMyBatis3SqlProviderType(sb.toString());
    }
}
