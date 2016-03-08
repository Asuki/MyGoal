package Task;

import java.time.LocalDate;

/**
 * A simple class to store task informations.
 * */
public class SimpleTask {
	/** The name of the task*/
	String taskName;
	/**The date, when the task have to finish*/
	LocalDate deadLine;
	/**It is optionally contains the comment about the task*/
	String comment;
	/**It is true when the task is finished, otherwise it is false.*/
	boolean isFinished;
	
	/**@param taskName The name of the task.
	 * The default dead-line is the current day.
	 * The default comment is empty.*/
	public SimpleTask(String taskName)
	{
		this.taskName = taskName;
		this.deadLine = LocalDate.now();
		this.comment = "";
		isFinished = false;
	}
	
	/**@param taskName The name of the task.
	 * @param taskName The date and time when the task have to finished.
	 * The default comment is empty.*/
	public SimpleTask (String taskName, LocalDate deadLine)
	{
		this.taskName = taskName;
		this.deadLine = deadLine;
		this.comment = "";
		isFinished = false;
	}
	
	/**@param taskName The name of the task.
	 * @param deadLine The date and time when the task have to finished.
	 * @param comment A short comment or description about the task.*/
	public SimpleTask (String taskName, LocalDate deadLine, String comment)
	{
		this.taskName = taskName;
		this.deadLine = deadLine;
		this.comment = comment;
		isFinished = false;
	}
	
	/**@return false if the task is active or true if we have finished it.*/
	public boolean GetIsFinished()
	{ return isFinished; }
	
	/**Set the task's status to finished */
	public void FinishTask()
	{ isFinished = true; }
	
}
