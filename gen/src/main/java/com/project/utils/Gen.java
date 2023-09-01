package com.project.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Gen {

    private static final String url = "jdbc:mysql://localhost:3306/common_basic";
    private static final String username = "root";
    private static final String password = "123456";
    private static final String filePath = "D:\\springwork\\work\\app\\src\\main";
    private static final String source = filePath+"\\java";
    private static final String mapper = filePath+"\\resources\\mapper";

    public static void main(String[] args) {

        Column createBy = new Column("create_by", FieldFill.INSERT);
        Column createTime = new Column("create_time", FieldFill.INSERT);


        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(url, username, password)
                .globalConfig((global) -> {
                    global.outputDir(source)
//                            .enableSwagger()
                            .disableOpenDir()
                            .author("admin" )
                            .commentDate("yyyy" )
                            .build();
                })
                .packageConfig((pck) -> {
                    pck.parent("com.project.app.work" )
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,mapper))
                            .build();
                })
                .strategyConfig(e -> {
                    e
                            .addInclude(
                                    "sys_shop")
                            .build()
                            .serviceBuilder()
                            .formatServiceFileName("%sService" )
                            .entityBuilder()
                            .disableSerialVersionUID()
//                            .enableChainModel()
                            .enableLombok()

                            .addTableFills(createBy, createTime).build();

                })
                .templateConfig(temp -> {

                    temp
                            .controller("/template/controller.java.vm" )
                            .service("/template/service.java.vm" )
                            .serviceImpl("/template/serviceImpl.java.vm" )
                            .entity("/template/entity.java.vm" )
                            .build();

                });

        Map<String, String> customFile = new HashMap<>();
        fastAutoGenerator.injectionConfig(consumer -> {
            consumer.customFile(customFile);
            consumer.beforeOutputFile((tableInfo, stringObjectMap) -> {
                customFile.clear();
                customFile.put(tableInfo.getEntityName() + ".vue", "/template/test2.java.vm" );
            }).build();
        });

        fastAutoGenerator.execute();

    }

}
