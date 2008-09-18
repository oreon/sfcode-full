package oaw4.demo.classic.uml.meta;

/** Represents a scheduled task that will be run by quartz
 * @author jsingh
 *
 */
public class ScheduledTask extends org.openarchitectureware.meta.uml.classifier.Class{
	
	private String cronExpression;

	/** The cron expression required to execute this task
	 * @return
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}
