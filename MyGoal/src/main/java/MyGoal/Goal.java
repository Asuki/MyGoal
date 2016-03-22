package MyGoal;

import java.time.LocalDate;
import java.util.*;

import Task.Task;

public class Goal {
	
	String goalName;
	List<Task> tasks;
	List<Task> dailyTasks;
	List<Task> weeklyTasks;
	List<Task> monthlyTasks;
	
	public Goal(String goalName)
	{
		this.goalName = goalName;
		tasks = new LinkedList<Task>();
	}
	
	public void AddTask(String taskName, LocalDate deadLine, long target)
	{
		tasks.add(new Task(taskName, deadLine, target));
	}
	
	//ToDo: Daily tasks
	//ToDo: Tasks per week/month/year
	//ToDo: If a task late, recalculate
	//ToDo: Create groups and own group
	//ToDo: Set priority list and priority group.
	//ToDo: Move up/down priority.
}
