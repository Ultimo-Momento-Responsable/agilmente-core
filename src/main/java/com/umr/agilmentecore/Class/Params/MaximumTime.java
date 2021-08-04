package com.umr.agilmentecore.Class.Params;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "maximum_time")
public class MaximumTime implements IParam {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "maximum_time")
	private float maximumTime;

	@Override
	public String getValue() {
		return Float.toString(this.maximumTime);
	}

	@Override
	public void setValue(String value) {
		this.maximumTime = Float.parseFloat(value);
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
