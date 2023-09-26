package in.fssa.jauntyrialto.model;

public abstract class Category implements Comparable<Category> {

	private int id;
	private String name;
	private boolean isActive;
	private String Img;

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

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
		return "Category [id=" + id + ", name=" + name + ", isActive=" + isActive + ", Img=" + Img + "]";
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