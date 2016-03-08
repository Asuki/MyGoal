package Task;

import java.sql.Time;
import java.time.LocalDate;

public class DailyTask {
	/** The name of the task*/
	String taskName;
	/**The date, when the task have to finish*/
	LocalDate deadLine;
	/**The start date when the task well be started*/
	// ToDo: Check Date
	LocalDate startDate;
	LocalDate date;
	/**Exact time when the task have to do*/
	Time time;
	long remaningTime;
	String comment;
	/**What the user want to reach. It is a number, because a good goal is measurable*/
	long target;
	/**The value of performed target*/
	long performed;
	//ToDO: Remaining value get!
	/**The target point what the user wants to reach every day to finish his/her goal.*/
	long dailyTarget;
	/**It is true when the task is finished, otherwise it is false.*/
	boolean isFinished;
	
	/**@param taskName The name of the task.
	 * @param deadLine The date and time when the task have to finished.
	 * @param target The value what the user wants to achieve.*/
	public DailyTask(String taskName, LocalDate deadLine, long target)
	{
		this.taskName = taskName;
		this.deadLine = deadLine;
		this.target = target;
		comment = "";
		performed = 0;
		ReCalc(0);
	}
	
	public void ReCalc(long addPerfom)
	{
		performed += addPerfom;
		remaningTime = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), deadLine);
		dailyTarget = (target - performed)/remaningTime;
		isFinished = (performed == target) ? true : false;
	}
	//ToDo: Get + Set

}
