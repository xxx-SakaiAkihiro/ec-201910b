package jp.co.example.ecommerce_b.form;

public class SortForm {

	private Integer pageNumber;
	private String sort;
	private String name;

	@Override
	public String toString() {
		return "SortForm [pageNumber=" + pageNumber + ", sort=" + sort + ", name=" + name + "]";
	}

	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
