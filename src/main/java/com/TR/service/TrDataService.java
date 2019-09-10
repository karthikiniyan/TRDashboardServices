package com.TR.service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TR.TRDashboard.DataModel;
import com.TR.TRDashboard.DefectsDataModel;
import com.TR.TRDashboard.IssueTypeModel;
import com.TR.TRDashboard.JiraItemRepository;
import com.TR.TRDashboard.ResolutionModel;

@Service
public class TrDataService {

	@Autowired 
	private JiraItemRepository jiraRepo;
	
	public List<String> getProject(){
		List<String> projectList = jiraRepo.getAllProjects();
		return projectList;
	}
	
	public List<String> getSprintByProject(String project){
		List<String> sprintList = jiraRepo.getSprintByProject(project);
		return sprintList;
	}
	
	public List<DefectsDataModel> getDataModel(String project, String sprint) throws ParseException{
		List<DataModel> dataList = jiraRepo.getDataModel(project, sprint);
		List<DefectsDataModel> defectDataList =new ArrayList<DefectsDataModel>();;
	  
  	  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
  	  Date currentTime=new Date();
  	  String currentDate = formatter.format(currentTime);  
  	  Date current = formatter.parse(currentDate);  
  	
		
	for(DataModel dmobj:dataList) {
		Date createdTime=dmobj.getCreated();
		 String createdDate = formatter.format(createdTime);  
		Date created = formatter.parse(createdDate);  
		boolean flag=false;
		if((current.compareTo(created)> 0) && (dmobj.getStatus().equalsIgnoreCase("open")) ){
			flag=true;
		}
		DefectsDataModel obj = new DefectsDataModel(dmobj.getStatus(),dmobj.getIssueType(),dmobj.getPriority(),
				dmobj.getAssignee(),dmobj.getLabel(),dmobj.getCreated(),dmobj.getDuedate(),dmobj.getIssueId(),flag,dmobj.getProject(),dmobj.getSprint());
		defectDataList.add(obj);
	}
		return defectDataList;
	}
	
	public List<DataModel> getDataModelForAssignee(String project, String sprint,String assignee){
		List<DataModel> dataList = jiraRepo.getDataModelForAssignee(project, sprint,assignee);
		return dataList;
	}
	
	public List<IssueTypeModel> getAssigneeData(String project, String sprint){
		List<Object[]> dataList = jiraRepo.getAssigneeData(project, sprint);
		ArrayList<IssueTypeModel> issuelist = new ArrayList<IssueTypeModel>();
		
		for(Object[] obj: dataList) {
			
			IssueTypeModel model = new IssueTypeModel();
			
			String name = (String) obj[0];
			Long count =  (long) obj[1];
			
			model.setAssignee(name);
			model.setCount(count.intValue());
			System.out.println(name +" : "+ count.intValue());
			issuelist.add(model);
		}
		
		return issuelist;
	}
	
	
	public List<IssueTypeModel> getItemCategories(String project, String sprint){
		ArrayList<IssueTypeModel> issuelist = new ArrayList<IssueTypeModel>();
		List<Object[]> dataList = jiraRepo.getItemCategories(project, sprint);
		
		for(Object[] obj: dataList) {
			
			IssueTypeModel model = new IssueTypeModel();
			
			String name = (String) obj[0];
			Long count =  (long) obj[1];
			
			model.setName(name);
			model.setCount(count.intValue());
			
			issuelist.add(model);
		}
		return issuelist;
	}
	
	public List<IssueTypeModel> getItemStatusCategories(String project, String sprint){
		
		List<Object[]> dataList = jiraRepo.getItemStatusCategories(project, sprint);
		
		ArrayList<IssueTypeModel> issuelist = new ArrayList<IssueTypeModel>();
		
		String name = null;
		HashMap<String, IssueTypeModel> map = new HashMap<String, IssueTypeModel>();
		IssueTypeModel model = null;
		ArrayList<ResolutionModel> statuslist = null ;
		ResolutionModel resModel = null;
		
		for(Object[] obj: dataList) {
			
			name = (String) obj[0];
			String status = (String) obj[1];
			java.math.BigInteger count =  (java.math.BigInteger) obj[2];
				
				if(map.containsKey(name)){
					
					if(status != null) {
						resModel = new ResolutionModel();
						resModel.setName(status);
						resModel.setCount(count.intValue());
						statuslist.add(resModel);
					}
					else
					model.setCount(count.intValue());
				}
			else {
				model = new IssueTypeModel();
				
				 statuslist = new ArrayList<ResolutionModel>();
				 
				 model.setName(name);
				
				if(status != null) {
					resModel = new ResolutionModel();
					resModel.setName(status);
					resModel.setCount(count.intValue());
					statuslist.add(resModel);
				}
				else
				model.setCount(count.intValue());
			}
				
			model.setResolution(statuslist);
			if(name != null)
			map.put(name, model);
		}
		
		for (Map.Entry<String, IssueTypeModel> entry : map.entrySet()) {
			
			IssueTypeModel mmm = entry.getValue();
			System.out.println("key : " + entry.getKey() + " name : " +mmm.getName()+ " count : " +mmm.getCount() + " list : " +mmm.getResolution() );
			issuelist.add(entry.getValue());
		}	
		return issuelist;
	}
	
	//getItemStatusForAssignee
public List<IssueTypeModel> getItemStatusForAssignee(String project, String sprint,String assignee){
		
		List<Object[]> dataList = jiraRepo.getItemStatusForAssignee(project, sprint,assignee);
		
		ArrayList<IssueTypeModel> issuelist = new ArrayList<IssueTypeModel>();
		
		String name = null;
		HashMap<String, IssueTypeModel> map = new HashMap<String, IssueTypeModel>();
		IssueTypeModel model = null;
		ArrayList<ResolutionModel> statuslist = null ;
		ResolutionModel resModel = null;
		
		for(Object[] obj: dataList) {
			
			name = (String) obj[0];
			String status = (String) obj[1];
			java.math.BigInteger count =  (java.math.BigInteger) obj[2];
				
				if(map.containsKey(name)){
					
					if(status != null) {
						resModel = new ResolutionModel();
						resModel.setName(status);
						resModel.setCount(count.intValue());
						statuslist.add(resModel);
					}
					else
					model.setCount(count.intValue());
				}
			else {
				
				model = new IssueTypeModel();
				 statuslist = new ArrayList<ResolutionModel>();
				 model.setName(name);
				
				if(status != null) {
					resModel = new ResolutionModel();
					resModel.setName(status);
					resModel.setCount(count.intValue());
					statuslist.add(resModel);
				}
				else
				model.setCount(count.intValue());
			}
				
			model.setResolution(statuslist);
			if(name != null)
			map.put(name, model);
		}
		
		for (Map.Entry<String, IssueTypeModel> entry : map.entrySet()) {
			IssueTypeModel mmm = entry.getValue();
			System.out.println("key : " + entry.getKey() + " name : " +mmm.getName()+ " count : " +mmm.getCount() + " list : " +mmm.getResolution() );
			issuelist.add(entry.getValue());
		}	
		return issuelist;
	}
	
	
	
	public List<IssueTypeModel> getPriorityCategories(String project, String sprint){
		ArrayList<IssueTypeModel> issuelist = new ArrayList<IssueTypeModel>();
		List<Object[]> dataList = jiraRepo.getPriorityCategories(project, sprint);
		
		for(Object[] obj: dataList) {
			
			IssueTypeModel model = new IssueTypeModel();
			
			String priority = (String) obj[0];
			Long count =  (long) obj[1];
			
			model.setPriority(priority);
			model.setCount(count.intValue());
			
			issuelist.add(model);
		}
		return issuelist;
	}
	
	//getUnAssigneedItems
	public List<DataModel> getUnAssigneedItems(){
		List<DataModel> dataList = jiraRepo.getUnAssigneedItems();
		return dataList;
	}
	
	public List<DataModel> getEffortSpillOverItems(){
		List<DataModel> dataList = jiraRepo.getEffortSpillOverItems();
		return dataList;
	}
	
	
	public void SaveDataList (ArrayList<DataModel> data) {
		
		jiraRepo.saveAll(data);
		
	}
	
	public void SaveData(DataModel data) {
		
		jiraRepo.save(data);
		
	}
	
	public ArrayList<DefectsDataModel> getPendingPastDueDate(){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getPendnigPastDueDate();
		
		for(Object[] obj: dataList) {
			
			DefectsDataModel model = new DefectsDataModel();
			
			String project = (String) obj[0];
			String sprint = (String) obj[1];
			String issuetype = (String) obj[2];
			java.math.BigInteger count =  (java.math.BigInteger) obj[3];
			
			model.setProject(project);
			model.setSprint(sprint);
			model.setIssuetype(issuetype);			
			model.setCount(count.intValue());
			
			issuelist.add(model);
		}
		return issuelist;
	}
	
	//getPastSprintEndDateItems
	public ArrayList<DefectsDataModel> getPastSprintEndDateItems(){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getPastEndDateItems();
		
		for(Object[] obj: dataList) {
			
			DefectsDataModel model = new DefectsDataModel();
			
			String project = (String) obj[0];
			String sprint = (String) obj[1];
			Timestamp sprintenddate = (Timestamp) obj[2];
			String status = (String) obj[3];
			java.math.BigInteger count =  (java.math.BigInteger) obj[4];
			
			model.setProject(project);
			model.setSprint(sprint);
			model.setSprintenddate(sprintenddate);		
			model.setStatus(status);
			model.setCount(count.intValue());
			
			issuelist.add(model);
		}
		return issuelist;
	}
	
	
	public List<String> getActiveSprint() {
		
	List<String> activeSprint =	jiraRepo.getActiveSprint();
		
		return activeSprint;
		
	}
	
	
	public DefectsDataModel getCriticalCategories(String sprint ){
		
		List<Object[]> dataList = jiraRepo.getCriticalCategories(sprint);
		
		DefectsDataModel model = new DefectsDataModel();
		
		for(Object[] obj: dataList) {
			
			String project = (String) obj[0];
			String sprnt = (String) obj[1];
			String priority = (String) obj[2];
			Long count =  (Long) obj[3];
			
			model.setProject(project);
			model.setSprint(sprnt);
			model.setPriority(priority);		
			model.setCount(count.intValue());
		
		}
		return model;
	}
	
	
	
	public ArrayList<DefectsDataModel> getItemsVsDueDate( ){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> objec = jiraRepo.getItemsVsDueDate();
		
		for(Object[] obj : objec) {
		
		System.out.println(obj[0].toString());
		java.math.BigInteger  Closed_before_DueDate_count =   (java.math.BigInteger) obj[0];
		if(Closed_before_DueDate_count != null) {
			System.out.println(Closed_before_DueDate_count);	
			DefectsDataModel model = new DefectsDataModel();
			model.setColumnName("Closed_before_DueDate");
			model.setCount(Closed_before_DueDate_count.intValue());
			issuelist.add(model);
		}
		
		
		java.math.BigInteger  Closed_on_Duedate_count =  (java.math.BigInteger) obj[1];
		if(Closed_on_Duedate_count != null) {
			
			DefectsDataModel model = new DefectsDataModel();
			model.setColumnName("Closed_on_Duedate_count");
			model.setCount(Closed_on_Duedate_count.intValue());
			issuelist.add(model);
		}
		
		java.math.BigInteger Closed_after_duedate =  (java.math.BigInteger) obj[2];
		if(Closed_after_duedate != null) {
			
			DefectsDataModel model = new DefectsDataModel();
			model.setColumnName("Closed_after_duedate");
			model.setCount(Closed_on_Duedate_count.intValue());
			issuelist.add(model);
		}
		
		java.math.BigInteger  Pending_after_duedate =  (java.math.BigInteger) obj[3];
		if(Closed_after_duedate != null) {
			
			DefectsDataModel model = new DefectsDataModel();
			model.setColumnName("Pending_after_duedate");
			model.setCount(Pending_after_duedate.intValue());
			issuelist.add(model);
		}
		
	
		}
		return issuelist;
	}
	
	
	// get completion percentage for a Active Sprint
	public DefectsDataModel getCompletionPercentage(String sprint ){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getCompletionPercentage(sprint);
		DefectsDataModel model = new DefectsDataModel();
		for(Object[] obj: dataList) {
			
			String project = (String) obj[0];
			
			 java.math.BigInteger   issue =  (java.math.BigInteger)  obj[1];
			 java.math.BigInteger completion=  (java.math.BigInteger)obj[2];
			
			System.out.print("issuecount "+ issue + "" + completion);
			int issueCount = issue.intValue();
			int completioncount = completion.intValue();
			
			    int completionpercentage =(int)((completioncount*100.0f)/issueCount);
			
			model.setProject(project);
			model.setCompletionpercentage(completionpercentage);
			model.setCount(issueCount);
			
		//	issuelist.add(model);
		}
		return model;
	}
	
	//getBugCompletionPercentage : use to get the bug completion range for 100 hours
	
	public DefectsDataModel getBugCompletionPercentage(String sprint ){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getBugCompletionPercentage(sprint);
		DefectsDataModel model = new DefectsDataModel();
		int counter =0;
		String project = null;
		int   totaltimespent = 0;
		
		for(Object[] obj: dataList) {
			
			 project = (String) obj[0];
			
			 int   timespent  =   Integer.parseInt((String) obj[2])  ;
			 
			 totaltimespent = totaltimespent + timespent ;
			 
			 counter++;
			
			System.out.print("totaltimespent "+ totaltimespent + "timespent :" + timespent);
		//	issuelist.add(model);
		}
		//no of bugs * 100 / totalhourspent = bugs fixed for 100 hours.
		if(totaltimespent > 0) {
			
		 int bugcompletionpercentage =(int)((counter*100)/totaltimespent);
		 System.out.print("counter "+ counter + "bugcompletionpercentage :" + bugcompletionpercentage);
			
		model.setProject(project);
		model.setSprint(sprint);
		model.setBugCompletionRange(bugcompletionpercentage);
		
		}
		
		return model;
	}
	
}
