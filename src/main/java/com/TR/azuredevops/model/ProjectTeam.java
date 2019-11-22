package com.TR.azuredevops.model;
import java.util.ArrayList;

public class ProjectTeam {

	private String id;
	private String name;
	private String teamURL;
	private String description;
	private ArrayList<Sprint> activeSprints = new ArrayList<Sprint>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamURL() {
		return teamURL;
	}

	public void setTeamURL(String teamURL) {
		this.teamURL = teamURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Sprint> getActiveSprints() {
		return activeSprints;
	}

	public void setActiveSprints(ArrayList<Sprint> activeSprints) {
		this.activeSprints = activeSprints;
	}

}
