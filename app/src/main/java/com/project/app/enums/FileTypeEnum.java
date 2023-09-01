package com.project.app.enums;

public enum FileTypeEnum {
    FILE("文件"),
    PIC("图片"),
    DOC("文档"),
    AUDIO("音频"),
    VIDEO("视频"),
    ZIP("压缩包");


    private final String type;


    FileTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
