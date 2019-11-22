package com.TR.TRDashboard;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JiraItemRepository extends CrudRepository<DataModel, String> {
	
	@Query("select distinct d.project from DataModel d where sprintstatus='ACTIVE'")
	 List<String> getAllProjects();
	
	@Query("select distinct d.project from DataModel d where d.ParentAsset=:parent and sprintstatus='ACTIVE'")
	 List<String> getProjectsByParent(String parent);
	
	@Query("select d from DataModel d where d.ParentAsset=:parent and d.assignee is null")
	 List<DataModel> getUnAssigneedItems(String parent);
	
	@Query("select d  from DataModel d where d.ParentAsset=:parent and d.timespent > d.timeestimate and sprintstatus='ACTIVE'")
	 List<DataModel> getEffortSpillOverItems(String parent);
	
	
	@Query("select distinct d.sprint from DataModel d where d.project=:project and sprintstatus='ACTIVE' order by d.sprint desc")
	List<String> getSprintByProject(String project);
	
	@Query("select d from DataModel d where d.project=:project and d.sprint=:sprint")
	List<DataModel> getDataModel(String project, String sprint);
	
	@Query("select d from DataModel d where d.project=:project and d.sprint=:sprint and d.assignee=:assignee")
	List<DataModel> getDataModelForAssignee(String project, String sprint,String assignee);
	
	@Query("select d from DataModel d where d.project=:project and d.sprint=:sprint and d.status=:status")
	List<DataModel> getOverRunData(String project, String sprint,String status);
	
	
	@Query("select d.assignee as name,count(d.assignee) as count from DataModel d where d.project=:project and d.sprint=:sprint group by d.assignee")
	List<Object[]> getAssigneeData(String project, String sprint);
	
	@Query("select d.issueType as name,count(d.issueType) as count from DataModel d where d.project=:project and d.sprint=:sprint group by d.issueType")
	List<Object[]> getItemCategories(String project, String sprint);
	
	
	@Query("select d.priority as priority,count(d.priority) as count from DataModel d where d.project=:project and d.sprint=:sprint group by d.priority")
	List<Object[]> getPriorityCategories(String project, String sprint);
	
	
	@Query(value = "select d.issuetype,d.status,count(d.issuetype) as count from trdata.jira_item d where d.project=:project and d.sprint=:sprint group by rollup ( d.issuetype,d.status) order by d.issuetype,d.status", 
			  nativeQuery = true)
	List<Object[]> getItemStatusCategories(String project, String sprint);
	
	@Query(value = "select d.issuetype,d.status,count(d.issuetype) as count from trdata.jira_item d where d.project=:project and d.sprint=:sprint and d.assignee=:assignee group by rollup ( d.issuetype,d.status) order by d.issuetype,d.status", 
			  nativeQuery = true)
	List<Object[]> getItemStatusForAssignee(String project, String sprint,String assignee);
	
	//Quries to get defects data
	
	//item which pending past due date.
	@Query(value="select  project,sprint,issuetype,count(*) from trdata.jira_item where ParentAsset=:parent and status in ('Open','Ready for Development','Developing') and created < Now() and sprintstatus='ACTIVE' group by project,sprint,issuetype order by project",nativeQuery = true)
	List<Object[]> getPendnigPastDueDate(String parent);
	
	//query to find active sprint
	
	@Query(value="select distinct d.sprint,d.project from DataModel d where d.ParentAsset=:parent and d.sprintstatus='ACTIVE' order by d.project")
	List<Object[]> getActiveSprint(String parent);
	
	@Query(value="select sum(timeestimate) totaltimeestimate,sum(timespent) totaltimespent,project,sprint from trdata.jira_item ji where (project,sprint) in (select project,sprint from trdata.jira_item where sprintstatus='ACTIVE')\r\n" + 
			"group by ji.project,ji.sprint order by project",nativeQuery = true)
	List<Object[]>getEffortData();
	
	@Query(value="select d.project,d.sprint,d.priority as priority,d.sprintenddate,count(d.priority) as count from DataModel d where d.priority='Critical' and d.sprint=:sprint and d.project=:project  group by d.project,d.sprint,d.priority,d.sprintenddate")
	List<Object[]> getCriticalCategories(String sprint,String project);
	
	@Query(value="select  SUM ( case WHEN status='Closed' and updated < now()- interval '1 day' then 1  else  0  END) AS Closed_before_DueDate,  SUM ( case WHEN status='Closed' and (updated = now() or updated = now()- interval '1 day' ) then 1 ELSE  0 end ) AS Closed_on_Duedate,"+
 " SUM ( CASE WHEN status='Closed' and (updated > now()  ) then 1 else 0  END ) AS Closed_after_duedate,  SUM ( case WHEN status != 'Closed' and (updated > now()  ) THEN 1 ELSE  0  end ) AS Pending_after_duedate from  trdata.jira_item ",nativeQuery = true)
	List<Object[]> getItemsVsDueDate();
	
	@Query(value="select d.project,d.sprintenddate, count(d.issueid) as TotalCount, count(d.status) filter (where d.status='Closed') as statuscount from trdata.jira_item d where d.sprint=:sprint and d.project=:project group by project,d.sprintenddate",nativeQuery = true)
	List<Object[]> getCompletionPercentage(String sprint,String project);
	
	@Query(value="select d.project,d.sprint,d.timespent  from trdata.jira_item d where d.issuetype='Bug' and d.sprint=:sprint and d.project=:project",nativeQuery = true)
	List<Object[]> getBugCompletionPercentage(String sprint,String project);
	
	@Query(value="select project,sprint,sprintenddate,status,count(project) from trdata.jira_item where ParentAsset=:parent and sprintenddate < now() and status != 'Closed' and sprintstatus='ACTIVE' group by project,sprint,sprintenddate,status",nativeQuery = true)
	List<Object[]> getPastEndDateItems(String parent);
	
	@Query(value="select project,sprint,sprintstartdate,sprintenddate,count(project) from trdata.jira_item where sprintenddate < now() and status != 'Closed' and sprintstatus='ACTIVE' group by project,sprint,sprintstartdate,sprintenddate",nativeQuery = true)
	List<Object[]> getPastEndDateStatus();
	
	@Query(value="select project,count(*) as count from trdata.jira_item where ParentAsset=:parent and  issuetype='Bug' and created >=now()-interval '30 day' group by project order by count desc",nativeQuery=true)
	List<Object[]> getLastMonthBug(String parent);
	
	@Query(value="select d.project,d.sprint,count(*) as TotalCount, count(d.issuetype) filter (where d.issuetype='Bug') as bugcount from trdata.jira_item d where (project,sprint) in (select distinct project,sprint from trdata.jira_item where sprintstatus='ACTIVE') and d.sprintstatus='ACTIVE' group by project,d.sprint order by project",nativeQuery = true)
	List<Object[]> getQualityData();
	
}
