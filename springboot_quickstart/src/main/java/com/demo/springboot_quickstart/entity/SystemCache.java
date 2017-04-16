package com.demo.springboot_quickstart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by luyan on 2017/4/16.
 */
@Entity
@Table(name = "syscache")
public class SystemCache {
	@Id
	private String id = UUID.randomUUID().toString().replace("-", "");

	private String prop;

	@Column(name = "start_number")
	private int startNumber;

	@Column(name = "end_number")
	private int endNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}
}
