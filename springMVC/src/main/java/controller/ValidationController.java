package controller;

import domain.entity.Validation;
import domain.vo.ResponseVo;
import exception.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡代伟
 * @description 测试hibernate-validator框架的参数校验
 * @date 2023-12-27 15:48
 */
@RestController
public class ValidationController {
    @PostMapping("/insert")
    public ResponseVo<String> insert(@Validated(ValidationGroups.insert.class) Validation validation){
        return ResponseVo.success("插入成功");
    }

}
