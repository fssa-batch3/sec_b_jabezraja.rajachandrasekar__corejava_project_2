package in.fssa.jauntyrialto.model;

public abstract class Category implements Comparable<Category> {

	private int id;
	private String name;
	private boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsActive() {
		return isActive;
	}

	public void setIsActive(boolean is_active) {
		this.isActive = is_active;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", is_active=" + isActive + "]";
	}

	@Override
	public int compareTo(Category c) {

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