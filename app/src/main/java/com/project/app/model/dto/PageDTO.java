package com.project.app.model.dto;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分页信息")
public class PageDTO {

    @Schema(description = "当前页" , example = "1")
    private Integer pageNum = 1;

    @Schema(description = "每页记录数" , example = "10")
    private Integer pageSize = 10;

    private String search;

   private Map<String,Object> query;


   @Schema(hidden = true)
    public <E> Page<E> getMybatisPage() {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        return page;
    }


}
