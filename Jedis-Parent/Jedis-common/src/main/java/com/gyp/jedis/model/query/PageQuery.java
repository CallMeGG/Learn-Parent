package com.gyp.jedis.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {

    private static final Integer DEFAULT_PAGE_NUM = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageNum = DEFAULT_PAGE_NUM;

    private Integer pageSize = DEFAULT_PAGE_SIZE;
}
