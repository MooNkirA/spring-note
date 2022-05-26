package com.moon.crm.po;

/**
 * 综合查询条件与分页的实体类
 * @author MoonZero
 */
public class QueryVo {

	// ==== 封装页面提交查询表单 ====
	// 封装客户名称
	private String custName;
	// 封装客户来源
	private String custSource;
	// 封装所属行业
	private String custIndustry;
	// 封装客户级别
	private String custLevel;

	// ====用于封装分页信息 ====
	// 分页属性：当前页，每一页显示大小，当前页开始索引
	// 封装当前页(默认为第1页)
	private Integer page = 1;
	// 封装每页显示数据大小（默认每页显示10条数据）
	private Integer rows = 10;
	// 封装当前页开始索引
	private Integer start;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustSource() {
		return custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	public String getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "QueryVo [custName=" + custName + ", custSource=" + custSource + ", custIndustry=" + custIndustry
				+ ", custLevel=" + custLevel + ", page=" + page + ", rows=" + rows + ", start=" + start + "]";
	}

}
