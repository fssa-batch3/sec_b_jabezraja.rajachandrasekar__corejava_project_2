package in.fssa.jauntyrialto.model;

public abstract class SubCategory implements Comparable<SubCategory> {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", category_id=" + category_id + ", name=" + name + ", is_active=" + is_active
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

	private int id;
	private int category_id;
	private String name;
	private boolean is_active;
}
