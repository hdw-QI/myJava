package domain.entity;

import exception.ValidationGroups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author 胡代伟
 * @description 测试hibernate-validator框架的参数校验
 * @date 2023-12-27 16:21
 */
@Data
public class Validation {
    //参数值必须不是 Null(作用于任意类型)
    //@NotNull(message = "姓名不能为空",groups = ValidationGroups.insert.class)
    //字符串不能为 null，而且字符串长度必须大于0，至少包含一个非空字符串（只能作用于字符串）
    @NotBlank(message = "姓名不能为空",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    //字符串的长度在min 和 max 之间（作用于字符串类型）
    @Length(min = 6,max = 15,message = "用户名长度大于等于6小于等于15",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    private String name;

    //参数值必须大于等于 最小值(作用于整型类型)
    //@Min(value = 18,message = "年龄必须大于等于18",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    //参数值必须小于等于 最大值(作用于整型类型)
    //@Max(value = 120,message = "年龄必须小于等于120",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    @Range(min = 18,max = 120,message = "年龄大于等于18小于等于120",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    private int age;

    @NotNull(message = "不能没有书", groups = {ValidationGroups.insert.class,ValidationGroups.update.class}) //如果加这个，则当数据没有这个属性时，不会进行校验
    //字符串：字符串长度必须在指定的范围内
    //Collection：集合大小必须在指定的范围内
    //Map：map的大小必须在指定的范围内
    //Array：数组长度必须在指定的范围内
    //作用于：
    // CharSequence
    //Collection
    //Map
    //Array
    @Size(min = 2, max = 10, message = "学生必须有两本书及以上，最多接10本书", groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    private List<Integer> bookCount;

    //验证字符串是否符合正则表达式(作用于字符串类型)
    @Pattern(regexp = "^[1-9]\\d*$", message = "编号必须是正整数(不包含0)",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    private String code;

    //参数值为正数(作用于数字类型)
    @Positive(message = "id必须为正数",groups = {ValidationGroups.update.class,ValidationGroups.delete.class})
    @NotNull(message = "不能id", groups = {ValidationGroups.update.class,ValidationGroups.delete.class}) //如果加这个，则当数据没有这个属性时，不会进行校验
    private int id;

    //参数值必须为 true(作用于布尔类型)
    @AssertTrue(message = "没同意隐私协议",groups = {ValidationGroups.insert.class})
    private boolean agree;

    //参数值为时间，且必须小于 当前时间（作用于时间类型(Date)）
    //@Past(message = "借书的时间必须是必须小于当前时间",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    //参数值为时间，且必须小于或等于 当前时间（作用于时间类型(Date)）
    @PastOrPresent(message = "借书的时间必须是今天及以前",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    @NotNull(message = "不能没有借书开始时间", groups = {ValidationGroups.insert.class,ValidationGroups.update.class}) //如果加这个，则当数据没有这个属性时，不会进行校验
    private Date begin;

    //参数值为时间，且必须大于 当前时间（作用于时间类型(Date)）
    //@Future(message = "还书的时间必须是必须大于当前时间",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    //参数值为时间，且必须大于或等于 当前时间（作用于时间类型(Date)）
    @FutureOrPresent(message = "还书的时间必须是今天及以前以后",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    @NotNull(message = "不能没有借书结束时间", groups = {ValidationGroups.insert.class,ValidationGroups.update.class}) //如果加这个，则当数据没有这个属性时，不会进行校验
    private Date end;

    //被注释的元素必须是电子邮箱地址(作用于字符串类型)
    @Email(message = "邮箱格式错误",groups = {ValidationGroups.insert.class,ValidationGroups.update.class})
    @NotNull(message = "不能没有邮箱", groups = {ValidationGroups.insert.class,ValidationGroups.update.class}) //如果加这个，则当数据没有这个属性时，不会进行校验
    private String email;

}
