package in.fssa.jauntyrialto.model;

public abstract class SubCategory implements Comparable<SubCategory> {

	private int id;
	private int categoryId;
	private String name;
	private boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", isActive=" + isActive
				+ "]";
	}

	@Override
	public int compareTo(SubCategory c) {

		if (this.getId() == c.getId()) {
			return 0;
		} else {
			if (this.id > getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
