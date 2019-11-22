package com.TR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Asset_Master",schema="trdata")
public class AssetMasterModel {
	
	@Id
	@Column(name="id")
	private int Id ;
	@Column(name="parentasset")
	private String ParentAsset = null;
	@Column(name="childasset")
	private String ChildAsset = null;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getParentAsset() {
		return ParentAsset;
	}
	public void setParentAsset(String parentAsset) {
		ParentAsset = parentAsset;
	}
	public String getChildAsset() {
		return ChildAsset;
	}
	public void setChildAsset(String childAsset) {
		ChildAsset = childAsset;
	}
	

}
