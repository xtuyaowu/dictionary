package cc.chenzhihao.dictionary.domain.base;

import javax.persistence.*;
import java.util.Date;

/**
 * Des : <dictionary> : BaseModel 基础模型
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/30 15:10
 */
@MappedSuperclass
public class BaseModel {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    protected Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    protected Date createTime;

    /**
     * 操作/修改时间
     */
    @Column(name = "modify_time")
    protected Date modifyTime = new Date();

    /**
     * 数据状态.可描述该条数据是否被删除或是否有效
     * true 有效, false 失效
     */
    @Column(name = "valid")
    protected boolean valid = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", valid=" + valid +
                '}';
    }
}
