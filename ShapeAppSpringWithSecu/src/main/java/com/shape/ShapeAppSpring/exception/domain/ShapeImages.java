package com.shape.ShapeAppSpring.exception.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="ShapeImage")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class ShapeImages {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	@Column(name="ID")
	private Long id;

	@Column(name="DESCRIPTIF")
	private String descriptif;

	@Column(name="URL")
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}