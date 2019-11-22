package com.TR.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TR.TRDashboard.DataModel;
import com.TR.TRDashboard.DefectsDataModel;
import com.TR.TRDashboard.IssueTypeModel;
import com.TR.azuredevops.model.DAGWorkItem;
import com.TR.azuredevops.service.AzureDevopsAPIService;
import com.TR.model.AssetMasterModel;
import com.TR.service.TRService;
import com.TR.service.TrDataService;

@RestController
public class TRController {
	
	@Autowired
	public TRService service;
	
	@Autowired
	public AzureDevopsAPIService azureService;
	
	@Autowired
	private TrDataService dataService;
	
	@RequestMapping("/jira/getParentAsset")
	public HashMap<String,String> getParentAsset(){
		return service.getParentAsset();
	}
	
	
	@RequestMapping("/jira/getprojects")
	public List<String> getProjects(){
		return dataService.getProject();
	}
	
	@RequestMapping("/jira/getProjectsByParent/{parent}")
	public List<String> getProjectsByParent(@PathVariable ("parent") String parent){
		return dataService.getProjectsByParent(parent);
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
	
	@RequestMapping("/jira/getOverRunData/{project}/{sprint}/{status}")
	public List<DataModel> getOverRunData(@PathVariable ("project") String project, @PathVariable ("sprint") String sprint,@PathVariable("status") String status){
		return dataService.getOverRunData(project, sprint,status);
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
	
	@GetMapping("/jira/getPastDueDate/{parent}")
	public List<DefectsDataModel> getPendingPastDueDate(@PathVariable ("parent") String parent) {
		
		List<DefectsDataModel> list = dataService.getPendingPastDueDate(parent);
		return list;
	}
	
	@GetMapping("/jira/getActiveSprint/{parent}")
	public List<DefectsDataModel> getActiveSprint(@PathVariable ("parent") String parent) {
		
		List<DefectsDataModel> sprintlist =	dataService.getActiveSprint(parent);
		
		return sprintlist;	
	}
	
	@GetMapping("/jira/getEffortData")
	public HashMap<String,DefectsDataModel> getEffortData() {
		
		HashMap<String,DefectsDataModel> hm = new HashMap<String,DefectsDataModel>();
		
		hm =	dataService.getEffortData(hm);
		
		return hm;	
	}
	
	@GetMapping("/jira/getQualityData")
	public HashMap<String,DefectsDataModel> getQualityData() {
		
		HashMap<String,DefectsDataModel> hm = new HashMap<String,DefectsDataModel>();
		
		hm =	dataService.getQualityData(hm);
		
		return hm;	
	}
	
	@GetMapping("/jira/getPastSprintEndDateStatus")
	public HashMap<String,DefectsDataModel> getPastSprintEndDateStatus(){
		 HashMap<String,DefectsDataModel> hm = new  HashMap<String,DefectsDataModel>();
		try {
			hm= dataService.getPastSprintEndDateStatus(hm);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;
	}
	
	@GetMapping("/jira/getAssetStatus/{parent}")
	public List<DefectsDataModel> getAssetStatus(@PathVariable ("parent") String parent) {
		
		HashMap<String,DefectsDataModel> hm =	dataService.getAssetStatus(parent);
		
		List<DefectsDataModel> dataList = hm.values().stream().collect(Collectors.toList());
		
		return dataList;	
	}
	
	
	@GetMapping("/jira/getCriticalCategories/{parent}")
	public List<DefectsDataModel> getCriticalCategories(@PathVariable ("parent") String parent) {
		
		ArrayList<DefectsDataModel> modellist = new ArrayList<DefectsDataModel>();	
		
		List<DefectsDataModel> sprintlist =	dataService.getActiveSprint(parent);
		
		for(DefectsDataModel dm : sprintlist ) {
		
		DefectsDataModel data = dataService.getCriticalCategories(dm);
		
		if(data.getProject() != null)
		modellist.add(data);
		
		}
		
		return modellist;
	}
	
	@GetMapping("/jira/getItemsVsDueDate")
	public List<DefectsDataModel> getItemsVsDueDate(){
		
		List<DefectsDataModel> list = dataService.getItemsVsDueDate();
		return list;
	}
	
	
	@GetMapping("/jira/getCompletionPercentage/{parent}")
	public List<DefectsDataModel> getCompletionPercentage(@PathVariable ("parent") String parent) {
		
	ArrayList<DefectsDataModel> modellist = new ArrayList<DefectsDataModel>();	
	
	List<DefectsDataModel> sprintlist =	dataService.getActiveSprint(parent);
	
	for(DefectsDataModel dm : sprintlist ) {

	DefectsDataModel data= dataService.getCompletionPercentage(dm);
	modellist.add(data);
	}
	return modellist;
	}
	
	//getBugCompletionPercentage
	@GetMapping("/jira/getBugCompletionPercentage/{parent}")
	public List<DefectsDataModel> getBugCompletionPercentage(@PathVariable ("parent") String parent) {
		
	ArrayList<DefectsDataModel> modellist = new ArrayList<DefectsDataModel>();	
	List<DefectsDataModel> sprintlist =	dataService.getActiveSprint(parent);
	
	for(DefectsDataModel dm : sprintlist ) {

	DefectsDataModel data= dataService.getBugCompletionPercentage(dm);
	
	if (data.getNoofBugs() > 0)
	modellist.add(data);
	}
	return modellist;
	}
	
	@GetMapping("/jira/getUnAssigneedItems/{parent}")
	public List<DataModel> getUnAssigneedItems(@PathVariable ("parent") String parent){
		return dataService.getUnAssigneedItems(parent);
		
	}
	
	@GetMapping("/jira/getEffortSpillOverItems/{parent}")
	public List<DataModel> getEffortSpillOverItems(@PathVariable ("parent") String parent){
		return dataService.getEffortSpillOverItems(parent);
		
	}
	
	@GetMapping("/jira/getPastSprintEndDateItems/{parent}")
	public List<DefectsDataModel> getPastSprintEndDateItems(@PathVariable ("parent") String parent){
		List<DefectsDataModel> out=null;
		try {
			out= dataService.getPastSprintEndDateItems(parent);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}
	
	@GetMapping("/jira/getLastMonthBug/{parent}")
	public List<DefectsDataModel> getLastMonthBug(@PathVariable ("parent") String parent){
		return dataService.getLastMonthBug(parent);
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
	
	
	// get data from Azure and insertall into table 
		@GetMapping("/azure/getData")
		public ArrayList<DAGWorkItem> getAzureDashboardData() {
		String url = "https://dev.azure.com/tr-tax-gov-aumentum";
		String pwd = "tlwsjz7hsmbfmosnvokzsnp7ohnbeahg7bxmo5pnhqaiazcnkfsa";
			
		ArrayList<DAGWorkItem> dataList =	azureService.getDAGWorkItems(url, pwd);
		
		
		//dataService.SaveDataList(dataList);
//		}
			return dataList;
		}
	
}
