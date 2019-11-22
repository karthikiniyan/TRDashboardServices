package com.TR.azuredevops.service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.TR.azuredevops.model.DAGWorkItem;
import com.TR.azuredevops.model.Project;
import com.TR.azuredevops.model.ProjectTeam;
import com.TR.azuredevops.model.Sprint;
import com.TR.azuredevops.model.WorkItem;

/**
 * API Service for Azure Devops
 * 
 * @author SK18005
 *
 */
@Service
public class AzureDevopsAPIService {

    static final String TEAMS = "/_apis/teams";
    static final String SPRINTS = "/_apis/work/teamsettings/iterations?$timeframe=current";
    static final String WORKITEMIDS = "/workitems";
    
    int status = 0;
    String orgAzureDevopsURL = null;
    String personalAccessToken = null;
    String apiURLStr = null; 
    String encodedPAT = null;
    ArrayList<Project> projects = new ArrayList<Project>();
    ArrayList<WorkItem> allWorkItems = new ArrayList<WorkItem>();
    ArrayList<DAGWorkItem> dagWItems = new ArrayList<DAGWorkItem>(); 
    HashMap<String,ArrayList<WorkItem>> workItemRelations = new HashMap<String,ArrayList<WorkItem>>();
    
    URL apiURL;
    HttpURLConnection con;
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    /**
     * Service Exposed for getting the Projects
     * 
     * @param orgAzureDevopsURL java.lang.String
     * @param personalAccessToken java.lang.String
     * @return
     */
    public ArrayList<DAGWorkItem> getDAGWorkItems(String orgAzureDevopsURL,String personalAccessToken) {
    	this.orgAzureDevopsURL = orgAzureDevopsURL; // Sample orgAzureDevopsURL : https://dev.azure.com/{organization}
    	this.personalAccessToken = personalAccessToken;
    	logMessage("Process started for fetching the Projects : "+new Date());
    	populateProjectDetails();
    	logMessage("Process Ended for fetching the Projects : "+new Date());
    	logMessage("Total Work Items Fetched : "+dagWItems.size());
    	return dagWItems;
    }
    
    /**
     * Main method to populate the Project Details
     */
    private void populateProjectDetails(){
    	
    	String AuthStr = ":" + personalAccessToken;
        Base64 base64 = new Base64();
        encodedPAT = new String(base64.encode(AuthStr.getBytes()));
        
    	// Level1 - Populate the Projects and their Teams for the Organization
        populateProjects();
        
        logMessage("Project and Team Details Fetched");
        
        // Level2 - Populate the current Sprint details
        populateSprints(); 
        
        logMessage("Sprint Details Fetched");
        
        // Level3 - Populate WorkItem and their linked WorkItems for each sprint
        populateWorkItems();
        
        logMessage("Work Items ID's Fetched");
        
        // Level4 - Populate WorkItem Details
        populateWorkItemDetails();
        
        logMessage("Work Item Details Fetched");
        
        try {con.disconnect();}finally {}
    }

	/**
	 * This will populate the Project and Project Team details
	 */
    private void populateProjects() {
		
		apiURLStr = orgAzureDevopsURL + TEAMS;
		
        try {
        	apiURL = new URL(apiURLStr);
			con = (HttpURLConnection) apiURL.openConnection();
			con.setRequestProperty("Authorization", "Basic " + encodedPAT);
            con.setRequestMethod("GET");
            status = con.getResponseCode();
            if (status == 200){
                Scanner scanner = new Scanner(con.getInputStream());
                String responseBody = scanner.useDelimiter("\\A").next();
                scanner.close();
                Object obj = new JSONParser().parse(responseBody);
                JSONObject jo = (JSONObject) obj;
                JSONArray arr = (JSONArray)jo.get("value");
                HashMap<String,Project> pjs = new HashMap<String,Project>();
                for(int i=0;i<arr.size();i++) {
                	JSONObject jsObj = (JSONObject)arr.get(i);
                	String projectName = (String)jsObj.get("projectName");
                	if(true) {  // TODO : Check whether the project name is in the tracker as per DAG
                		Project proj = new Project();
                		if(pjs.get(projectName)!=null) {
                			proj = pjs.get(projectName);
                		}else {
                			proj.setName(projectName);
                    		proj.setId((String)jsObj.get("projectId"));
                		}
                		ProjectTeam pjTeam = new ProjectTeam();
                		pjTeam.setId((String)jsObj.get("id"));
                		pjTeam.setName((String)jsObj.get("name"));
                		pjTeam.setDescription((String)jsObj.get("description"));
                		pjTeam.setTeamURL((String)jsObj.get("url"));
                		proj.getTeams().add(pjTeam);
                		pjs.put(projectName, proj);
                	}
                }
                projects = new ArrayList<Project>(pjs.values());
            }else {
            	logMessage("Didn't get the response for Teams URL - "+apiURLStr+" ,PAT - "+personalAccessToken+" ,Http Response Code - "+status);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * This will populate the current sprint details for each Project Team
	 */
    private void populateSprints() {
    	for(Project proj : projects) {
    		for(ProjectTeam team : proj.getTeams()) {
    			apiURLStr = orgAzureDevopsURL + "/" + proj.getId() + "/" + team.getId() + SPRINTS;
    			try {
    	        	apiURL = new URL(apiURLStr);
    				con = (HttpURLConnection) apiURL.openConnection();
    				con.setRequestProperty("Authorization", "Basic " + encodedPAT);
    	            con.setRequestMethod("GET");
    	            status = con.getResponseCode();
    	            if (status == 200){
    	                Scanner scanner = new Scanner(con.getInputStream());
    	                String responseBody = scanner.useDelimiter("\\A").next();
    	                scanner.close();
    	                Object obj = new JSONParser().parse(responseBody);
    	                JSONObject jo = (JSONObject) obj;
    	                JSONArray arr = (JSONArray)jo.get("value");
    	                for(int i=0;i<arr.size();i++) {
    	                	JSONObject jsObj = (JSONObject)arr.get(i);
    	                	Sprint sprint = new Sprint();
    	                	sprint.setId((String)jsObj.get("id"));
    	                	sprint.setName((String)jsObj.get("name"));
    	                	sprint.setUrl((String)jsObj.get("url"));
    	                	JSONObject attr = (JSONObject)jsObj.get("attributes");
    	                	String startDate = (String)attr.get("startDate");
    	                	String endDate = (String)attr.get("finishDate");
    	                	if(startDate !=null && !"".equals(startDate)) {
    	                		sprint.setStartDate(sf.parse(startDate));
    	                	}
    	                	if(endDate !=null && !"".equals(endDate)) {
    	                		sprint.setEndDate(sf.parse(endDate));
    	                	}
    	                	team.getActiveSprints().add(sprint);
    	                }
    	            }else {
    	            	logMessage("Didn't get the response for Sprint URL - "+apiURLStr+" ,PAT - "+personalAccessToken+" ,Http Response Code - "+status);
    	            }
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}
    
	/**
	 * This will populate the WorkItem and their link WorkItems for each Active Sprint
	 */
    private void populateWorkItems() {
    	for(Project proj : projects) {
    		for(ProjectTeam team : proj.getTeams()) {
    			for(Sprint sprint : team.getActiveSprints()) {
    				apiURLStr = sprint.getUrl() + WORKITEMIDS;
        			try {
        	        	apiURL = new URL(apiURLStr);
        				con = (HttpURLConnection) apiURL.openConnection();
        				con.setRequestProperty("Authorization", "Basic " + encodedPAT);
        	            con.setRequestMethod("GET");
        	            status = con.getResponseCode();
        	            if (status == 200){
        	                Scanner scanner = new Scanner(con.getInputStream());
        	                String responseBody = scanner.useDelimiter("\\A").next();
        	                scanner.close();
        	                Object obj = new JSONParser().parse(responseBody);
        	                JSONObject jo = (JSONObject) obj;
        	                JSONArray arr = (JSONArray)jo.get("workItemRelations");
        	                for(int i=0;i<arr.size();i++) {
        	                	JSONObject jsObj = (JSONObject)arr.get(i);
        	                	JSONObject sourceObj = (JSONObject)jsObj.get("source");
        	                	JSONObject targetObj = (JSONObject)jsObj.get("target");
        	                	WorkItem wItem = new WorkItem();
        	                	wItem.setId((Long)targetObj.get("id"));
        	                	wItem.setUrl((String)targetObj.get("url"));
        	                	wItem.setSprintName(sprint.getName());
        	                	wItem.setProjectTeamName(team.getName());
        	                	wItem.setProjectName(proj.getName());
        	                	wItem.setSprintStartDate(sprint.getStartDate());
        	                	wItem.setSprintEndDate(sprint.getEndDate());
        	                	if(sourceObj==null) {
        	                		sourceObj = targetObj;
        	                	}
        	                	Long sourceID = (Long)sourceObj.get("id");
        	                	ArrayList<WorkItem> workItems = workItemRelations.get(sourceID.toString());
        	                	if(workItems==null) {
        	                		workItems = new ArrayList<WorkItem>();
        	                	}
        	                	workItems.add(wItem);
        	                	allWorkItems.add(wItem);
        	                	sprint.getWorkItems().add(wItem);
        	                	workItemRelations.put(sourceID.toString(), workItems);
        	                }
        	            }else {
        	            	logMessage("Didn't get the response for Work Item ID's URL - "+apiURLStr+" ,PAT - "+personalAccessToken+" ,Http Response Code - "+status);
        	            }
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
    			}
    		}
    	}
	}    
    
    /**
	 * This will populate the WorkItem Details
	 */
    private void populateWorkItemDetails() {
		for(WorkItem item : allWorkItems) {
			apiURLStr = item.getUrl();
			try {
	        	apiURL = new URL(apiURLStr);
				con = (HttpURLConnection) apiURL.openConnection();
				con.setRequestProperty("Authorization", "Basic " + encodedPAT);
	            con.setRequestMethod("GET");
	            status = con.getResponseCode();
	            if (status == 200){
	                Scanner scanner = new Scanner(con.getInputStream());
	                String responseBody = scanner.useDelimiter("\\A").next();
	                scanner.close();
	                Object obj = new JSONParser().parse(responseBody);
	                JSONObject jo = (JSONObject) obj;
	                JSONObject fields = (JSONObject)jo.get("fields");
	                item.setItemType((String)fields.get("System.WorkItemType"));
	                item.setStatus((String)fields.get("System.State"));
	                item.setTitle((String)fields.get("System.Title"));
	                item.setSeverity((String)fields.get("Microsoft.VSTS.Common.Severity"));
	                item.setClosedBy((String)fields.get("Microsoft.VSTS.Common.ClosedBy"));
	                item.setEffort((Double)fields.get("Microsoft.VSTS.Scheduling.Effort"));
	                item.setRemainingWork((Double)fields.get("Microsoft.VSTS.Scheduling.RemainingWork"));
	                item.setOriginalEstimate((Double)fields.get("Microsoft.VSTS.Scheduling.OriginalEstimate"));
	                JSONObject assignedTo = (JSONObject)fields.get("System.AssignedTo");
	                if(assignedTo != null) {
	                	item.setAssignedTo((String)assignedTo.get("displayName"));
	                }
	                JSONObject createdBy = (JSONObject)fields.get("System.CreatedBy");
	                if(createdBy!=null) {
	                	item.setCreatedBy((String)createdBy.get("displayName"));
	                }
	                JSONObject changedBy = (JSONObject)fields.get("System.ChangedBy");
	                if(changedBy!=null) {
	                	item.setChangedBy((String)changedBy.get("displayName"));
	                }
	                String createdDate = (String)fields.get("System.CreatedDate");
	            	String changedDate = (String)fields.get("System.ChangedDate");
	            	String closedDate = (String)fields.get("Microsoft.VSTS.Common.ClosedDate");
	            	if(createdDate !=null && !"".equals(createdDate)) {
	            		item.setCreatedDate(sf.parse(createdDate));
	            	}
	            	if(changedDate !=null && !"".equals(changedDate)) {
	            		item.setUpdatedDate(sf.parse(changedDate));
	            	}
	            	if(closedDate !=null && !"".equals(closedDate)) {
	            		item.setClosedDate(sf.parse(closedDate));
	            	}
	            	dagWItems.add(populateDataModelDTO(item));
	            }else {
	            	logMessage("Didn't get the response for Work Item detail URL - "+apiURLStr+" ,PAT - "+personalAccessToken+" ,Http Response Code - "+status);
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
	/**
	 * This method populates the DataModelDTO from the WorkItem objects
	 */
    private DAGWorkItem populateDataModelDTO(WorkItem wItem) {
    	DAGWorkItem dto = new DAGWorkItem();
    	dto.setIssueId(wItem.getId().toString());
    	dto.setProject(wItem.getProjectName());
    	dto.setSprint(wItem.getSprintName());
    	dto.setIssueType(wItem.getItemType());
    	dto.setStatus(wItem.getStatus());
    	dto.setAssignee(wItem.getAssignedTo());
    	dto.setPriority(wItem.getSeverity());
    	dto.setSprintstartdate(getTimeStamp(wItem.getSprintStartDate()));
    	dto.setSprintenddate(getTimeStamp(wItem.getSprintEndDate()));
    	dto.setCreated(getTimeStamp(wItem.getCreatedDate()));
    	dto.setUpdated(getTimeStamp(wItem.getUpdatedDate()));
    	dto.setDuedate(getTimeStamp(wItem.getClosedDate()));
    	dto.setTimeorignalEstimate(getString(wItem.getOriginalEstimate()));
    	dto.setTimespent(getString(wItem.getEffort()));
    	return dto;
	}
    
    private static String getString(Object obj) {
    	String res = null;
    	if(obj!=null) {
    		res = obj.toString();
    	}
    	return res;
    }
    
    /**
     * Returns the Timestamp of the specified date
     * @param date java.util.Date
     * @return java.sql.Timestamp
     */
    private Timestamp getTimeStamp(Date date) {
    	Timestamp ts = null;
    	if(date!=null) {
    		ts = new Timestamp(date.getTime());
    	}
		return ts;
	}

	private void logMessage(String message) {
    	System.out.println(message);
    }

    public HashMap<String, ArrayList<WorkItem>> getWorkItemRelations() {
		return workItemRelations;
	}
    
}