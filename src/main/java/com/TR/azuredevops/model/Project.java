package com.TR.azuredevops.model;
import java.util.ArrayList;

public class Project {

	private String id;
	private String name;

	private ArrayList<ProjectTeam> teams = new ArrayList<ProjectTeam>();

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

	public ArrayList<ProjectTeam> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<ProjectTeam> teams) {
		this.teams = teams;
	}

}
