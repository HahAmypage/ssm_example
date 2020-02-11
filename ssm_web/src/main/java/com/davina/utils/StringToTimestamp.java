package com.davina.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
//注意如果这里类名为灰色说明没有创建对象，如果鼠标选中后提示类没有被使用，则检查配置文件的包扫描路径有没有扫描到这个类
public class StringToTimestamp implements Converter<String, Timestamp> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Timestamp convert(String source) {

        try {
            if (StringUtils.isEmpty(source)){
                return null;
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(source);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
