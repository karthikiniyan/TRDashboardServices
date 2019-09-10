package com.TR.service;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringJoiner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.TR.TRDashboard.DataModel;

@Service
public class TRService {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public String getDashBoardData() {
		
		
	final String uri = "http://ent.jira.int.thomsonreuters.com/rest/api/2/search?jql=project in (IAM,ASSETINS,APIG,DVPRT,IAPI,WCA,CTCP,CTPT) and sprint in openSprints()&maxResults=-1&fields=project,issuekey,issuetype,priority,reporter,assignee,resolution,labels,status,created,timeestimate,timespent,timeoriginalestimate,customfield_23700,customfield_10102,customfield_13500,customfield_11500,created,updated,duedate";
				 
				     //"http://ent.jira.int.thomsonreuters.com/rest/api/2/search?jql=project=IAM and sprint in openSprints()&fields=project,issuekey,issuetype,priority,reporter,assignee,resolution,labels,status,created,timeestimate,timespent,timeoriginalestimate,customfield_23700,customfield_10102,customfield_13500,customfield_11500,created,updated,duedate";
	     
		 
		    String result = restTemplate.getForObject(uri, String.class);
		     
		    System.out.println(result);
		
		return result;
		
		
	}
	

	public  String getSprintName(String details) {
		
	String name = null ;	
    String[] words = details.split(",");
        
        for(String  s  : words) {
         System.out.println(s);
         if(s.startsWith("name"))
         name= s.substring(5);
         
        }
        System.out.println(name);
		return name;
		
	}
	
	public  String getSprintStartDate(String details) {
		
		String startdate = null ;	
	    String[] words = details.split(",");
	        
	        for(String  s  : words) {
	         System.out.println(s);
	         if(s.startsWith("startDate"))
	        	 startdate= s.substring(10);
	        }
	        System.out.println("startdate : "+startdate);
			return startdate;
		}
	
public  String getSprintEndDate(String details) {
		
		String enddate = null ;	
	    String[] words = details.split(",");
	        
	        for(String  s  : words) {
	         System.out.println(s);
	         if(s.startsWith("endDate"))
	        	 enddate= s.substring(8);
	        }
	        System.out.println("enddate :"+enddate);
			return enddate;
		}
	
	public String getLabels(JSONArray labelArray) {
		
		StringJoiner rgbJoiner = new StringJoiner(
			      ",", "", "");
		for(int j=0;j< labelArray.size() ;j++) {
			
			// JSONObject jobj2 =   (JSONObject) labelArray.get(j);
			
			
			String label = (String) labelArray.get(j);
			
			System.out.println(label.toString());
			
			rgbJoiner.add(label.toString());
		}
		return  rgbJoiner.toString();
	}
	
	public void getDate(String dateString) {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse("2018-04-10T04:00:00.000Z", inputFormatter);
		String formattedDate = outputFormatter.format(date);
		System.out.println(formattedDate);
		 
	}
	
	public ArrayList<DataModel> responseParser(String response) {
		
		JSONParser parser = new JSONParser();
		
		ArrayList<DataModel> dataList = new ArrayList<DataModel>();
	    
	    JSONObject jobj;
		try {
			jobj = (JSONObject)parser.parse(response);

			JSONArray jArr = (JSONArray)jobj.get("issues");
			
			for(int i=0;i<jArr.size();i++) {
				
				System.out.println(jArr.get(i));
				
				DataModel data = new DataModel();
			
			JSONObject obj1 = (JSONObject) jArr.get(i);
		    
			String issueId = (String) obj1.get("key");
			
			System.out.println("key " +issueId);
			
			data.setIssueId(issueId);
			
			Object obj = obj1.get("fields");
			
			JSONObject jobj1 = (JSONObject)parser.parse(obj.toString());
			
			String timeestimate = (String) String.valueOf( jobj1.get("timeestimate"));
			
			String timespent = (String)  String.valueOf(jobj1.get("timespent"));
			
			String Bussinessunit = (String)jobj1.get("customfield_23700");
			
			String Storypoints = (String) String.valueOf(jobj1.get("customfield_10102"));
			
			String EpicLink = (String)jobj1.get("customfield_13500");
			
			String createDate = (String)jobj1.get("created");
			
			String updateDate = (String)jobj1.get("updated");
			
			String dueDate = (String)jobj1.get("duedate");
			
			System.out.println(timeestimate +" "+ timespent +""+ Bussinessunit +" "+ Storypoints +" "+ EpicLink +" "+ createDate);
			
			Timestamp createTimestamp = new Timestamp(0);
			Timestamp updateTimestamp = new Timestamp(0);
			Timestamp duedateTimestamp = new Timestamp(0);
			Timestamp sprintSdateTimestamp = new Timestamp(0);
			Timestamp sprintEdateTimestamp = new Timestamp(0);
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			 // "2019-08-20T18:54:09.000+0800"
			 SimpleDateFormat duedateformat = new SimpleDateFormat("yyyy-MM-dd");
			//   2018-12-13
			 SimpleDateFormat sprintdateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			// 2019-08-12T18:05:35.299+08:00
			 java.util.Date parsedTimeStamp;
				try {
					
					if(createDate != null){
					parsedTimeStamp = (Date) dateFormat.parse(createDate);
					createTimestamp = new Timestamp(parsedTimeStamp.getTime());}
					if(updateDate != null) {
					parsedTimeStamp = (Date) dateFormat.parse(updateDate);
					updateTimestamp = new Timestamp(parsedTimeStamp.getTime());}
					if(dueDate != null) {
					parsedTimeStamp = (Date) duedateformat.parse(dueDate);
					duedateTimestamp = new Timestamp(parsedTimeStamp.getTime());}
					
					
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			data.setTimeestimate(timeestimate);
			data.setTimespent(timespent);
			data.setBussinessUnit(Bussinessunit);
			data.setStorypoints(Storypoints);
			data.setEpicLink(EpicLink);
			if(createTimestamp != null)
				data.setCreated(createTimestamp);
			if(updateTimestamp != null)
				data.setUpdated(updateTimestamp);
			if(duedateTimestamp != null)
				data.setDuedate(duedateTimestamp);
			
			//Sprint
			JSONArray SprintArray = (JSONArray)  jobj1.get("customfield_11500");
			
			if(SprintArray != null) {	
				
				String sprinthistory = "" ;
				
				int size = SprintArray.size()-1;
				
				System.out.println("Size " +size);
			
				for(int i1=0; i1 < SprintArray.size(); i1++ ) {
				
			String sprintdetails =   (String) SprintArray.get(i1);
			
			String sprintname  =  getSprintName(sprintdetails);
			
			if(size == i1) {
				
				data.setSprint(sprintname);
				
				String SprintStartDate = getSprintStartDate(sprintdetails);
				String SprintEndDate = getSprintEndDate(sprintdetails);
				
				if(SprintStartDate != null){
					parsedTimeStamp = (Date) sprintdateFormat.parse(SprintStartDate);
					sprintSdateTimestamp = new Timestamp(parsedTimeStamp.getTime());
					}
				if(SprintEndDate != null){
					parsedTimeStamp = (Date) sprintdateFormat.parse(SprintEndDate);
					sprintEdateTimestamp = new Timestamp(parsedTimeStamp.getTime());
					}
				
				data.setSprintstartdate(sprintSdateTimestamp);
				data.setSprintenddate(sprintEdateTimestamp);
			}
			sprinthistory = sprintname +","+  sprinthistory;
			
			System.out.println(sprintname   +"  "+ sprinthistory);
			}
				data.setSprinthistory(sprinthistory);
				
				
			}
		
			
			
			//labels
			JSONArray LabelArray = (JSONArray)  jobj1.get("labels");
			
			if(LabelArray != null) {
			
			String labels = getLabels(LabelArray);
			
			data.setLabel(labels);
			}
			
			// 
			Object obj2 = jobj1.get("project");
			
			JSONObject jobj3 = (JSONObject) parser.parse(obj2.toString());
			
			String Project = (String) jobj3.get("key");
			
			data.setProject(Project);
			
			//issuetype
			Object obj3 = jobj1.get("issuetype");
			
			JSONObject jobj4 = (JSONObject) parser.parse(obj3.toString());
			
			String IssueType = (String) jobj4.get("name");
			
			data.setIssueType(IssueType);
			
			//priority
			Object obj4 = jobj1.get("priority");
			
			JSONObject jobj5 = (JSONObject) parser.parse(obj4.toString());
			
			String priority = (String) jobj5.get("name");
			
			data.setPriority(priority);
			//reporter
			Object obj5 = jobj1.get("reporter");
			
			JSONObject jobj6 = (JSONObject) parser.parse(obj5.toString());
			
			String reporter = (String) jobj6.get("name");
			
			data.setReporter(reporter);
			//assignee
			Object obj6 = jobj1.get("assignee");
			
			if(obj6 != null) {
				
			JSONObject jobj7 = (JSONObject) parser.parse(obj6.toString());
			
			String assignee = (String) jobj7.get("name");
			
			data.setAssignee(assignee);
			}
			//resolution
			Object obj7 = jobj1.get("resolution");
			
			if(obj7 != null)  {
			
			JSONObject jobj8 = (JSONObject) parser.parse(obj7.toString());
			
			String resolution = (String) jobj8.get("name");
			
			data.setResolution(resolution);
			}
			//status
			Object obj8 = jobj1.get("status");
			
			if(obj8 != null) {
				
			JSONObject jobj9 = (JSONObject) parser.parse(obj8.toString());
			
			String status = (String) jobj9.get("name");
			
			data.setStatus(status);
			
			}
			
			dataList.add(data);
			
			}
			//labels
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return dataList;
		
	}
	

}
