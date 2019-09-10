package com.TR.TRDashboard;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JiraItemRepository extends CrudRepository<DataModel, String> {
	
	@Query("select distinct d.project from DataModel d")
	 List<String> getAllProjects();
	
	@Query("select d from DataModel d where d.assignee is null")
	 List<DataModel> getUnAssigneedItems();
	
	@Query("select d  from DataModel d where d.timespent > d.timeestimate")
	 List<DataModel> getEffortSpillOverItems();
	
	
	@Query("select distinct d.sprint from DataModel d where d.project=:project order by d.sprint desc")
	List<String> getSprintByProject(String project);
	
	@Query("select d from DataModel d where d.project=:project and d.sprint=:sprint")
	List<DataModel> getDataModel(String project, String sprint);
	
	@Query("select d from DataModel d where d.project=:project and d.sprint=:sprint and d.assignee=:assignee")
	List<DataModel> getDataModelForAssignee(String project, String sprint,String assignee);
	
	
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
	@Query(value="select  project,sprint,issuetype,count(*) from trdata.jira_item where status in ('Open','Ready for Development','Developing') and created < Now() group by project,sprint,issuetype order by project",nativeQuery = true)
	List<Object[]> getPendnigPastDueDate();
	
	//query to find active sprint
	
	@Query(value="select max(d.sprint) from DataModel d group by d.project ")
	List<String> getActiveSprint();
	
	@Query(value="select d.project,d.sprint,d.priority as priority,count(d.priority) as count from DataModel d where d.priority='Critical' and d.sprint=:sprint group by d.project,d.sprint,d.priority")
	List<Object[]> getCriticalCategories(String sprint);
	
	@Query(value="select  SUM ( case WHEN status='Closed' and updated < now()- interval '1 day' then 1  else  0  END) AS Closed_before_DueDate,  SUM ( case WHEN status='Closed' and (updated = now() or updated = now()- interval '1 day' ) then 1 ELSE  0 end ) AS Closed_on_Duedate,"+
 " SUM ( CASE WHEN status='Closed' and (updated > now()  ) then 1 else 0  END ) AS Closed_after_duedate,  SUM ( case WHEN status != 'Closed' and (updated > now()  ) THEN 1 ELSE  0  end ) AS Pending_after_duedate from  trdata.jira_item ",nativeQuery = true)
	List<Object[]> getItemsVsDueDate();
	
	@Query(value="select d.project, count(d.issueid) as TotalCount, count(d.status) filter (where d.status='Closed') as statuscount from trdata.jira_item d where d.sprint=:sprint group by project",nativeQuery = true)
	List<Object[]> getCompletionPercentage(String sprint);
	
	@Query(value="select project,sprint,sprintenddate,status,count(project) from trdata.jira_item where sprintenddate < now() and status != 'Closed' group by project,sprint,sprintenddate,status",nativeQuery = true)
	List<Object[]> getPastEndDateItems();
	
}
