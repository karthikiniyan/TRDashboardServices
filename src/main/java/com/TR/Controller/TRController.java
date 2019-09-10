package com.TR.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.TR.TRDashboard.DataModel;
import com.TR.TRDashboard.DefectsDataModel;
import com.TR.TRDashboard.IssueTypeModel;
import com.TR.service.TRService;
import com.TR.service.TrDataService;

@RestController
public class TRController {
	
	@Autowired
	public TRService service;
	
	@Autowired
	private TrDataService dataService;
	
	
	
	@RequestMapping("/jira/getprojects")
	public List<String> getProjects(){
		return dataService.getProject();
	}
	
	@RequestMapping("/jira/getSprints/{project}")
	public List<String> getSprint(@PathVariable ("project") String project){
		return dataService.getSprintByProject(project);
	}
	
	@RequestMapping("/jira/getData/{project}/{sprint}")
	public List<DefectsDataModel> getData(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint) throws java.text.ParseException{
		return dataService.getDataModel(project, sprint);
	}
	
	@RequestMapping("/jira/getDataForAssignee/{project}/{sprint}/{assignee}")
	public List<DataModel> getData(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint, @PathVariable ("assignee") String assignee){
		return dataService.getDataModelForAssignee(project, sprint, assignee);
	}
	
	@RequestMapping("/jira/getAssigneeData/{project}/{sprint}")
	public List<IssueTypeModel> getAssigneeData(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint){
		return dataService.getAssigneeData(project, sprint);
	}
	
	
	
	@RequestMapping("/jira/getItemCategories/{project}/{sprint}")
	public List<IssueTypeModel> getItemCategories(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint){
		return dataService.getItemCategories(project, sprint);
	}
	
	@RequestMapping("/jira/getPriorityCategories/{project}/{sprint}")
	public List<IssueTypeModel> getPriorityCategories(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint){
		return dataService.getPriorityCategories(project, sprint);
	}
	
	
	
	@RequestMapping("/jira/getItemStatusCategories/{project}/{sprint}")
	public List<IssueTypeModel> getItemStatusCategories(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint){
		return dataService.getItemStatusCategories(project, sprint);
	}
	
	@RequestMapping("/jira/getItemStatusForAssignee/{project}/{sprint}/{assignee}")
	public List<IssueTypeModel> getItemStatusForAssignee(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint,@PathVariable ("assignee") String assignee){
		return dataService.getItemStatusForAssignee(project, sprint,assignee);
	}
	
	@GetMapping("/jira/getPastDueDate")
	public List<DefectsDataModel> getPendingPastDueDate() {
		
		List<DefectsDataModel> list = dataService.getPendingPastDueDate();
		return list;
	}
	
	@GetMapping("/jira/getCriticalCategories")
	public List<DefectsDataModel> getCriticalCategories() {
		
		ArrayList<DefectsDataModel> modellist = new ArrayList<DefectsDataModel>();	
		
		List<String> sprintlist =	dataService.getActiveSprint();
		
		for(String sprint : sprintlist ) {
		
		DefectsDataModel data = dataService.getCriticalCategories(sprint);
		
		modellist.add(data);
		
		}
		
		return modellist;
	}
	
	@GetMapping("/jira/getItemsVsDueDate")
	public List<DefectsDataModel> getItemsVsDueDate(){
		
		List<DefectsDataModel> list = dataService.getItemsVsDueDate();
		return list;
	}
	
	
	@GetMapping("/jira/getCompletionPercentage")
	public List<DefectsDataModel> getCompletionPercentage() {
		
	ArrayList<DefectsDataModel> modellist = new ArrayList<DefectsDataModel>();	
		
	List<String> sprintlist =	dataService.getActiveSprint();
	
	for(String sprint : sprintlist ) {

	DefectsDataModel data= dataService.getCompletionPercentage(sprint);
	
	modellist.add(data);
	
	}
	
	return modellist;
	}
	
	@GetMapping("/jira/getUnAssigneedItems")
	public List<DataModel> getUnAssigneedItems(){
		return dataService.getUnAssigneedItems();
		
	}
	
	@GetMapping("/jira/getEffortSpillOverItems")
	public List<DataModel> getEffortSpillOverItems(){
		return dataService.getEffortSpillOverItems();
		
	}
	
	@GetMapping("/jira/getPastSprintEndDateItems")
	public ArrayList<DefectsDataModel> getPastSprintEndDateItems(){
		return dataService.getPastSprintEndDateItems();
	}
	
	// get data from jira and insertall into table 
	@GetMapping("/jira/getData")
	public String getDashboardData() {
		
	String response =	service.getDashBoardData();
	
	ArrayList<DataModel> dataList = service.responseParser(response);
//	for(DataModel data : dataList) {
		//System.out.println(data);
	dataService.SaveDataList(dataList);
//	}
		return response;
	}

}
