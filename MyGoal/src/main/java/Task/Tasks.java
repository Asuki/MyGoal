/**
 * 
 */
package Task;

import java.time.LocalDate;
import java.util.*;

import Task.Task;

/**
 * @author Asuki
 *
 */
public class Tasks {

	/**
	 * The name of the task list.
	 */
	private String listName;
	/**
	 * The list of the active tasks.
	 */
	LinkedList<Task> tasks;
	/**
	 * The list of the finished tasks.
	 */
	LinkedList<Task> finishedTasks;
	/**
	 * The list of the category of priorities. It must be sync with the task list and priority list.
	 */
	LinkedList<String> priorCat;
	/**
	 * The list of the category. It must be sync with the task list and the priorCat list.
	 */
	LinkedList<Integer> priority;
	/**
	 * List of the possible priorities.
	 */
	LinkedList<String> priorityCategories;
	 
	/**
	 * @param listName The name of the task list.
	 */
	public Tasks(String listName){
		this.listName = listName;
		tasks = new LinkedList<Task>();
		finishedTasks = new LinkedList<Task>();
		priorCat = new LinkedList<String>();
		priority = new LinkedList<Integer>();
		priorityCategories = new LinkedList<String>();
		priorityCategories.add("A");
		priorityCategories.add("B");
		priorityCategories.add("C");
	}
	
	/**
	 * Removes the given name!'s task, puts in the finished task list and reorder the elements after the element in the same category.
	 * 
	 * @param taskName The name of the task what is finished.
	 * @return true if the remove was successful and the active list had contained the task. Return false otherwise. 
	 */
	public boolean FinishTask(String taskName){
		int index;
		if (-1 != (index = getTaskIndex(taskName))){
			String catTmp = this.priorCat.get(index);
			Integer priorTmp = this.priority.get(index);
			finishedTasks.add(tasks.get(index));
			tasks.remove(index);
			priorCat.remove(index);
			priority.remove(index);
			reorderTasksAfterRemove(catTmp, priorTmp);
			return true;
		}
		return false;
	}
	
	/**
	 * @param taskName The name of the task. It must be UNIQUE!
	 * @param deadLine The time when the task had to be finished.
	 * @param priorCat The category of priority. It can be A, B or C;
	 * @param priority The importance of the task.
	 */
	public void addTask(String taskName, LocalDate deadLine, String priorCat, Integer priority){
		if (!isContainsTaskName(taskName)){
			tasks.add(new Task(taskName, deadLine));
			this.priorCat.add(priorCat);
			addPriority(priorCat, priority);
		}
	}
	
	/**
	 * Adds a new priority to the active tasks list. The tasks priority will be decreased with lower priority in the same category.
	 * 
	 * @param priority The priority for the task.
	 */
	private void addPriority(String priorCat, Integer priority){
		for (int i = 0; i < this.priority.size(); i++) {
			Integer value = this.priority.get(i);
			if (value >= priority && this.priorCat.get(i) == priorCat)
				this.priority.set(i, value + 1);
		}
		this.priority.add(priority);
	}
	
	/**
	 * Adds the index of the task in the active task list.
	 * 
	 * @param taskName The name what's index we search for.
	 * @return the index of the task if it is in the active task list and -1 otherwise.
	 */
	public Integer getTaskIndex(String taskName){
		for (Integer result = 0; result < tasks.size(); result++) {
			if (this.tasks.get(result).taskName == taskName)
				return result;
		}
		return -1;
	}
	
	/**
	 * Reorder the active task list's priority. It can use after remove an element.
	 * 
	 * @param priorCat The category of the removed element's priority.
	 * @param priority The removed element's priority.
	 */
	protected void reorderTasksAfterRemove(String priorCat, Integer priority){
		for (int i = 0; i < this.priority.size(); i++) {
			Integer value = this.priority.get(i);
			if (value > priority && this.priorCat.get(i) == priorCat) 
				this.priority.set(i, value - 1);
		}
	}
	
	/**
	 * Changes a task's priority category. If the new category is higher than the older, the task will have the worst (highest value) priority in the new category. If the new category is lower than the old one, the task will be the first in that.
	 * 
	 * @param taskName The name of the task what's priority category we want to change.
	 * @param newPriorCat The new priority category we want to use. It can be: "A", "B" or "C".
	 */
	public void changePriorCat(String taskName, String newPriorCat){
		Integer taskIndex = getTaskIndex(taskName);
		if (taskIndex == -1)
			return;
		Integer newCatIndex = priorityCategories.indexOf(newPriorCat);
		Integer oldCatIndex = priorityCategories.indexOf(priorCat.get(taskIndex));
		if (oldCatIndex == newCatIndex)
			return;
		if (newCatIndex > oldCatIndex) {
			for (int i = 0; i < this.priority.size(); i++) {
				if (this.priorCat.get(i) == newPriorCat)
					priority.set(i, priority.get(i) + 1);
			}
			priority.set(taskIndex, 1);
		}
		else
			priority.set(taskIndex, getLastPriorityInCategory(newPriorCat) + 1);
		priorCat.set(taskIndex, newPriorCat);
	}
	
	/**
	 * Gives the last priority value of the given priority category.
	 * 
	 * @param priorCat The priority category what we want to find the last priority.
	 * @return The last element of the priority category.
	 */
	public Integer getLastPriorityInCategory(String priorCat){
		Integer max = -1;
		Integer priorTmp;
		for (int i = 0; i < this.priorCat.size(); i++) {
			if (priorCat == this.priorCat.get(i) && max < (priorTmp = priority.get(i)))
				max = priorTmp;
		}
		return max;
	}
	
	/**
	 * Check if the active task list contains a task with the same name.
	 * 
	 * @param taskName The task name what we want to check
	 * @return true if the active task list contains the name and false otherwise
	 */
	private boolean isContainsTaskName(String taskName){
		for (Task task : tasks) {
			if (task.taskName == taskName)
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if the task is finished.
	 * 
	 * @param taskName The name of the task what we want to check if it is finished.
	 * @return true if the task is finished and false otherwise.
	 */
	public boolean isFinished(String taskName){
		for (Task task : finishedTasks) {
			if (task.taskName == taskName) 
				return true;
		}
		return false;
	}
	
	public void clearFinished(){
		finishedTasks.clear();
	}
	
	/**
	 * It gives the names of the active tasks.
	 * 
	 * @return the name of the tasks
	 */
	public LinkedList<String> getTaskNames(){
		LinkedList<String> result = new LinkedList<String>();
		for (Task task : tasks) {
			result.add(task.taskName);
		}
		return result;
	}
	
	/**
	 * It gives the names of the finished tasks.
	 * 
	 * @return the name of the tasks
	 */
	public LinkedList<String> getFinishedTaskNames(){
		LinkedList<String> result = new LinkedList<String>();
		for (Task task : finishedTasks) {
			result.add(task.taskName);
		}
		return result;
	}
	
	 /**
	  * Gives the name of the task list.
	  * 
	 * @return The name of the task list.
	 */
	public String getListName() {
		return listName;
	}
	 public void setListName(String listName) {
		this.listName = listName;
	}
	 
	 /**
	  * Renames the task if there is no other task with the new name
	  * 
	 * @param oldName The task name what we want to change.
	 * @param newName The name what we want to use in the future.
	 * @return True if the name change was successful and false if it is failed.
	 */
	public boolean setTaskName (String oldName, String newName){
		 int taskIndex;
		 if ((taskIndex = getTaskIndex(oldName)) != -1 && getTaskIndex(newName) == -1){
			 tasks.get(taskIndex).setTaskName(newName);
			 return true;
		 }
		 return false;
	 }
}
