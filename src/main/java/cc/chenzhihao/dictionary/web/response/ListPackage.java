package cc.chenzhihao.dictionary.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Des : <dictionary-scaffold> : ListPackage
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/12 22:59
 */
@ApiModel
public class ListPackage<T> {

	@ApiModelProperty(value = "总记录数")
	private int rows;

	@ApiModelProperty(value = "对象集合")
	private List<T> list;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
