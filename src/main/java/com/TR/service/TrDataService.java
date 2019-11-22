package com.TR.service;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TR.TRDashboard.AssetMasterRepository;
import com.TR.TRDashboard.DataModel;
import com.TR.TRDashboard.DefectsDataModel;
import com.TR.TRDashboard.IssueTypeModel;
import com.TR.TRDashboard.JiraItemRepository;
import com.TR.TRDashboard.ResolutionModel;
import com.TR.model.AssetMasterModel;

@Service
public class TrDataService {

	@Autowired 
	private JiraItemRepository jiraRepo;
	
	
	public List<String> getProject(){
		List<String> projectList = jiraRepo.getAllProjects();
		return projectList;
	}
	
	
	
	public List<String> getProjectsByParent(String parent){
		List<String> projectList = jiraRepo.getProjectsByParent(parent);
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
  	  boolean sprintEndFlag = false;
		
	for(DataModel dmobj:dataList) {
		Date createdTime=dmobj.getCreated();
		 String createdDate = formatter.format(createdTime);  
		Date created = formatter.parse(createdDate);  
		boolean flag=false;
		if((current.compareTo(created)> 0) && (dmobj.getStatus().equalsIgnoreCase("open")) ){
			flag=true;
		}
        Date d = new Date(dmobj.getSprintenddate().getTime());
        Date d1=new Date();
        long diff = d.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        
        if(diffDays<0) {
               sprintEndFlag = true;
        }
        else {
        	   sprintEndFlag = false;
        }
        DefectsDataModel obj = new DefectsDataModel(dmobj.getStatus(),dmobj.getIssueType(),dmobj.getPriority(),
               dmobj.getAssignee(),dmobj.getLabel(),dmobj.getCreated(),dmobj.getDuedate(),dmobj.getIssueId(),flag,dmobj.getProject(),dmobj.getSprint(),sprintEndFlag,dmobj.getSprintenddate(),dmobj.getSprintstartdate());
        defectDataList.add(obj);

	}
		return defectDataList;
	}
	
	public List<DataModel> getDataModelForAssignee(String project, String sprint,String assignee){
		List<DataModel> dataList = jiraRepo.getDataModelForAssignee(project, sprint,assignee);
		return dataList;
	}
	
	public List<DataModel> getOverRunData(String project, String sprint,String status){
		List<DataModel> dataList = jiraRepo.getOverRunData(project, sprint,status);
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
	public List<DataModel> getUnAssigneedItems(String parent){
		List<DataModel> dataList = jiraRepo.getUnAssigneedItems(parent);
		return dataList;
	}
	
	public List<DataModel> getEffortSpillOverItems(String parent){
		List<DataModel> dataList = jiraRepo.getEffortSpillOverItems(parent);
		for (DataModel dataModel : dataList) {
			if(dataModel.getIssueId().equals("CTCP-15207")) {
				System.out.println("sa");
			}
			/*
			 * String timeEstimate = null; if(dataModel.getTimeestimate()==null ||
			 * dataModel.getTimeestimate().equals("null")) { timeEstimate="0"; } else {
			 * timeEstimate=dataModel.getTimeestimate(); }
			 */
			dataModel.setTimeestimate(((dataModel.getTimeestimate())/3600));
			/*
			 * String timeSpent = null; if(dataModel.getTimespent()==null ||
			 * dataModel.getTimespent().equals("null")) { timeSpent="0"; }else {
			 * timeSpent=dataModel.getTimespent(); }
			 */
			dataModel.setTimespent(((dataModel.getTimespent())/3600));
		}
		
		return dataList;
	}
	
	
	public void SaveDataList (ArrayList<DataModel> data) {
		
		jiraRepo.saveAll(data);
		
	}
	
	public void SaveData(DataModel data) {
		
		jiraRepo.save(data);
		
	}
	
	public ArrayList<DefectsDataModel> getPendingPastDueDate(String parent){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getPendnigPastDueDate(parent);
		
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
	public ArrayList<DefectsDataModel> getPastSprintEndDateItems(String parent) throws ParseException{
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getPastEndDateItems(parent);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	  	  Date currentTime=new Date();
	  	  String currentDate = formatter.format(currentTime);  
	  	  Date current = formatter.parse(currentDate);
	  	  
	  	  
		for(Object[] obj: dataList) {
			
			DefectsDataModel model = new DefectsDataModel();
			String sprintstatus = "Green";
			String project = (String) obj[0];
			String sprint = (String) obj[1];
			Timestamp sprintenddate = (Timestamp) obj[2];
			String status = (String) obj[3];
			java.math.BigInteger count =  (java.math.BigInteger) obj[4];
			
			Date endDate=sprintenddate;
			 String endDateString = formatter.format(endDate);  
			Date endDateSprint = formatter.parse(endDateString);  
			long diff = current.getTime() - endDateSprint.getTime();
		   // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			model.setProject(project);
			model.setSprint(sprint);
			model.setActiveSprint(sprint);
			model.setSprintenddate(sprintenddate);		
			model.setStatus(status);
			model.setCount(count.intValue());
			long daysExcceded = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			model.setSprintoverRunDays(daysExcceded);
			
			if(daysExcceded > 0 && daysExcceded < 14)
				sprintstatus="Amber";
			else if(daysExcceded > 0 && daysExcceded > 14)
				sprintstatus="Red";
		
			
			model.setSprintstatus(sprintstatus);
			
			issuelist.add(model);
		}
		return issuelist;
	}
	
	public HashMap<String,DefectsDataModel> getPastSprintEndDateStatus(HashMap<String,DefectsDataModel> hm) throws ParseException{
		
		List<Object[]> dataList = jiraRepo.getPastEndDateStatus();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	  	  Date currentTime=new Date();
	  	  String currentDate = formatter.format(currentTime);  
	  	  Date current = formatter.parse(currentDate);
	  	  
	  	  
		for(Object[] obj: dataList) {
			
			DefectsDataModel model ;
			String sprintstatus = "Green";
			String project = (String) obj[0];
			String sprint = (String) obj[1];
			Timestamp sprintstartdate = (Timestamp) obj[2];
			Timestamp sprintenddate = (Timestamp) obj[3];
			
			java.math.BigInteger count =  (java.math.BigInteger) obj[4];
			
			Date endDate=sprintenddate;
			 String endDateString = formatter.format(endDate);  
			Date endDateSprint = formatter.parse(endDateString);  
			long diff = current.getTime() - endDateSprint.getTime();
		   // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			
			String key = project+"_"+sprint;
			
			if(hm.get(key) != null) {
				
				model = hm.get(key);
			
//			else {
//				model = new DefectsDataModel();
//			}
			
			
			model.setProject(project);
			model.setSprint(sprint);
			model.setActiveSprint(sprint);
			model.setSprintstartdate(sprintstartdate);
			model.setSprintenddate(sprintenddate);		
			model.setCount(count.intValue());
			long daysExcceded = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			model.setSprintoverRunDays(daysExcceded);
			
			if(daysExcceded > 0 && daysExcceded < 14)
				sprintstatus="Amber";
			else if(daysExcceded > 0 && daysExcceded > 14)
				sprintstatus="Red";
		
			model.setSprintstatus(sprintstatus);
			
			hm.put(key,model);
			}
		}
		return hm;
	}
	
	
	public List<DefectsDataModel> getActiveSprint(String parent) {
		
	List<Object[]> activeSprintlist =	jiraRepo.getActiveSprint(parent);
	ArrayList<DefectsDataModel> datalist = new ArrayList<DefectsDataModel>();
	
	for(Object[] obj: activeSprintlist) {
		
		DefectsDataModel model = new DefectsDataModel();
		
		String activesprint = (String) obj[0];
		String project = (String) obj[1];
		
		model.setProject(project);
		model.setActiveSprint(activesprint);
		
		
		datalist.add(model);
	}
		
		return datalist;
		
	}
	
	//getEffortData
	public HashMap<String,DefectsDataModel> getEffortData(HashMap<String,DefectsDataModel> hm) {
		
		List<Object[]> effortlist =	jiraRepo.getEffortData();
		
		DefectsDataModel model;
		
		for(Object[] obj: effortlist) {
			
			
			//BigDecimal
			
			BigDecimal BDtimeestimated = (BigDecimal) obj[0];
			int timeestimated =BDtimeestimated.intValue();
			
			BigDecimal BDtimespent = (BigDecimal)   obj[1];
			int timespent = BDtimespent.intValue();
			String project = (String) obj[2];
			String sprint = (String) obj[3];
			String effortStatus = null;
				
			if(timespent > timeestimated) {
				
				int difference = timespent - timeestimated;
				if(timeestimated > 0) {
				int percentage = 100*difference/timeestimated;
				
				if(percentage <= 10)
					effortStatus="Amber";
				if(percentage > 10)
					effortStatus="Red";
				}else {
					effortStatus="Red";
				}
				
			}else {
				effortStatus="Green";
			}
			
	String key = project+"_"+sprint;
			
			if(hm.get(key) != null) {
				
				model = hm.get(key);
//			}
//			else {
//				model = new DefectsDataModel();
//			}
			
			model.setProject(project);
			model.setActiveSprint(sprint);
			model.setTimeestimate(timeestimated);
			model.setTimespent(timespent);
			model.setEffortStatus(effortStatus);
			
			hm.put(key,model);
			}
		}
			return hm;
		}
	
	//getQualityData
		public HashMap<String,DefectsDataModel> getQualityData(HashMap<String,DefectsDataModel> hm) {
			
			List<Object[]> effortlist =	jiraRepo.getQualityData();
			ArrayList<DefectsDataModel> datalist = new ArrayList<DefectsDataModel>();
			
			for(Object[] obj: effortlist) {
				
				DefectsDataModel model ;
				//BigDecimal
				
				String project = (String) obj[0];
				String sprint = (String) obj[1];
				
				BigInteger BDtotalcount = (BigInteger) obj[2];
				int totalcount =BDtotalcount.intValue();
				
				BigInteger BDbugcount = (BigInteger)   obj[3];
				int bugcount = BDbugcount.intValue();
				
				
				String qualityStatus = "Green";
					
				if(totalcount > 0) {
				
					int percentage = 100*bugcount/totalcount;
					
					if(percentage <= 10)
						qualityStatus="Amber";
					if(percentage > 10)
						qualityStatus="Red";
					}else {
						qualityStatus="Red";
					}
				
				String key = project+"_"+sprint;
				
				if(hm.get(key) != null) {
					
					model = hm.get(key);
//				}
//				else {
//					model = new DefectsDataModel();
//				}
					
				model.setProject(project);
				model.setActiveSprint(sprint);
				model.setQualityStatus(qualityStatus);
				
				hm.put(key,model);
				}
			}
				
				return hm;
				
			}
		
		public HashMap<String,DefectsDataModel> getAssetStatus(String parent) {
			
			HashMap<String,DefectsDataModel> hm = new HashMap<String,DefectsDataModel>();
			
			ArrayList<DefectsDataModel> list = (ArrayList<DefectsDataModel>) getActiveSprint(parent);
			
			for(DefectsDataModel dm : list) {
				
				String key = dm.getProject()+"_"+dm.getActiveSprint();
				
			System.out.println("inside AssetStatus" +  dm.getProject()+"_"+dm.getActiveSprint());
				
				hm.put(key,dm);
			}
			
			try {
				hm = getPastSprintEndDateStatus(hm);
				hm = getEffortData(hm); 
				hm = getQualityData(hm);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return hm;
		}
		
	
	public DefectsDataModel getCriticalCategories(DefectsDataModel dm ){
		
		List<Object[]> dataList = jiraRepo.getCriticalCategories(dm.getActiveSprint(),dm.getProject());
		
		DefectsDataModel model = new DefectsDataModel();
		
		for(Object[] obj: dataList) {
			
			String project = (String) obj[0];
			String sprnt = (String) obj[1];
			String priority = (String) obj[2];
			Timestamp sprintenddate = (Timestamp) obj[3];
			Long count =  (Long) obj[4];
			
			model.setProject(project);
			model.setSprint(sprnt);
			model.setPriority(priority);	
			model.setSprintenddate(sprintenddate);
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
	public DefectsDataModel getCompletionPercentage(DefectsDataModel dm ){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getCompletionPercentage(dm.getActiveSprint(),dm.getProject());
		DefectsDataModel model = new DefectsDataModel();
		for(Object[] obj: dataList) {
			
			String project = (String) obj[0];
			Timestamp sprintenddate = (Timestamp) obj[1];
			 java.math.BigInteger   issue =  (java.math.BigInteger)  obj[2];
			 java.math.BigInteger completion=  (java.math.BigInteger)obj[3];
			
			System.out.print("issuecount "+ issue + "" + completion);
			int issueCount = issue.intValue();
			int completioncount = completion.intValue();
			
			    int completionpercentage =(int)((completioncount*100.0f)/issueCount);
			
			model.setProject(project);
			model.setCompletionpercentage(completionpercentage);
			model.setCount(issueCount);
			model.setSprintenddate(sprintenddate);
			
		//	issuelist.add(model);
		}
		return model;
	}
	
	
	public DefectsDataModel getBugPercentage() {
		
		DefectsDataModel model = new DefectsDataModel();
		
		return model;
	}
	
	//getBugCompletionPercentage : use to get the max bug from the asset
	
	public DefectsDataModel getBugCompletionPercentage(DefectsDataModel dm ){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getBugCompletionPercentage(dm.getActiveSprint(),dm.getProject());
		DefectsDataModel model = new DefectsDataModel();
		int counter =0;
		String project = null;
		int   totaltimespent = 0;
		
		for(Object[] obj: dataList) {
		 	
			 project = (String) obj[0];
			 int   timespent  =0;
			/*
			 * if(obj[2]==null ||obj[2].equals("null")) { System.out.println("null"); }else
			 * { timespent=Integer.parseInt((String)obj[2]); }
			 */
			/*
			 * Object count=obj[2]==null?0:obj[2]; if (count==null || count.equals("null")){
			 * count=0; System.out.println("null"); } int timespent =
			 * count=Integer.parseInt((String) count ) ;
			 */
		//	 totaltimespent = totaltimespent + timespent ;
			 
			 counter++;
			
		//	System.out.print("totaltimespent "+ totaltimespent + "timespent :" + timespent);
		//	issuelist.add(model);
		}
		//no of bugs * 100 / totalhourspent = bugs fixed for 100 hours.
	//	if(totaltimespent > 0) {
			
	//	 int bugcompletionpercentage =(int)((counter*100)/totaltimespent);
	//	 System.out.print("counter "+ counter + "bugcompletionpercentage :" + bugcompletionpercentage);
			
		model.setProject(project);
		model.setSprint(dm.getActiveSprint());
	//	model.setBugCompletionRange(bugcompletionpercentage);
		model.setNoofBugs(counter);
		
	//	}
		
		return model;
	}
	
	public List<DefectsDataModel> getLastMonthBug(String parent){
		ArrayList<DefectsDataModel> issuelist = new ArrayList<DefectsDataModel>();
		List<Object[]> dataList = jiraRepo.getLastMonthBug(parent);
		DefectsDataModel model=null;
		for(Object[] obj: dataList) {
			model = new DefectsDataModel();
			System.out.println(obj[0]+"->"+Integer.parseInt(obj[1].toString()));
			model.setProject((String) obj[0]);
			model.setNoofBugs(Integer.parseInt(obj[1].toString()));
			issuelist.add(model);
		}
		return issuelist;
	}	
	
}
