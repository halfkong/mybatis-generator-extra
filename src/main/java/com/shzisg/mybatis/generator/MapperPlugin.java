package com.shzisg.mybatis.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

import java.util.List;

public class MapperPlugin extends PluginAdapter {

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
        commentConfig.setConfigurationType(EntityCommentGenerator.class.getCanonicalName());
        context.setCommentGeneratorConfiguration(commentConfig);
    }

    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return handleEntity(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return handleEntity(topLevelClass, introspectedTable);
    }

    private boolean handleEntity(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("javax.persistence.*");
        topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getFullyQualifiedTableNameAtRuntime() + "\")");
        return true;
    }
}
