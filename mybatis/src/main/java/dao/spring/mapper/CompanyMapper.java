package dao.spring.mapper;

import domain.entity.Company;
import domain.vo.CompanyVO;

import java.util.List;

/**
 * @projectName: git
 * @package: dao.spring.mapper
 * @className: CompanyMapper
 * @author: 胡代伟
 * @description: 公司mapper
 * @date: 2023/12/8 19:51
 * @version: 1.0
 */
public interface CompanyMapper {
    public Company getById(int id);

    /*4.POJO中的属性可能会是一个集合对象，我们可以使用联合查询，并以级联属性的方式封装对象，使用collection标签定义对象的封装规则。*/
    public List<CompanyVO> getAllById(int id);

    /*5、collection分步查询*/
    public CompanyVO getAllByIdCompanyVo(int id);
}
