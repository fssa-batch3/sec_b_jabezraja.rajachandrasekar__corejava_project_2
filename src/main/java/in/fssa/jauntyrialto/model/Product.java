package in.fssa.jauntyrialto.model;

import javax.validation.constraints.NotNull;

public abstract class Product implements Comparable<Product> {

	private int id;
	// @NotNull(message = "name cannot be null")
	private String name;
	private String description;
	private int subCategoryId;
	private boolean isActive;
	private double price;
	private String mainImg;
	private String subImg1;
	private String subImg2;
	private String subImg3;

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getSubImg1() {
		return subImg1;
	}

	public void setSubImg1(String subImg1) {
		this.subImg1 = subImg1;
	}

	public String getSubImg2() {
		return subImg2;
	}

	public void setSubImg2(String subImg2) {
		this.subImg2 = subImg2;
	}

	public String getSubImg3() {
		return subImg3;
	}

	public void setSubImg3(String subImg3) {
		this.subImg3 = subImg3;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", subCategoryId="
				+ subCategoryId + ", isActive=" + isActive + ", price=" + price + ", mainImg=" + mainImg + ", subImg1="
				+ subImg1 + ", subImg2=" + subImg2 + ", subImg3=" + subImg3 + "]";
	}

	@Override
	public int compareTo(Product c) {

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
