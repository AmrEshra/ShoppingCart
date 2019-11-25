package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false, length = 20)
	private String description;
	
//	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//	private List<Product> products = new ArrayList<Product>();

	@Builder
	public static Category of(String description) {
		Category category = new Category();
		category.setDescription(description);
		return category;
	}
}
