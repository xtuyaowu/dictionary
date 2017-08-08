package cc.chenzhihao.dictionary.dao.repository;

import cc.chenzhihao.dictionary.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/6 09:51
 */
@Repository
public interface CompanyDao extends JpaRepository<Company,Integer>{

    

}
