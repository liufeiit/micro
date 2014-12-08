package me.andpay.ti.daf.dao.hibernate.simplequery;

/**
 * @description 宏变量格式化器接口定义类 
 * @author seabao
 * @project Jasine.WADK
 * @date 2005-9-13
 */
public interface MacroVariableFormatter {
    public String format(String pattern, Object value);
}
