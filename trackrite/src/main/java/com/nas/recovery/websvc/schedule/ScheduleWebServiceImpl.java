package com.nas.recovery.websvc.schedule;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.schedule.Schedule;

@WebService(endpointInterface = "com.nas.recovery.websvc.schedule.ScheduleWebService", serviceName = "ScheduleWebService")
@Name("scheduleWebService")
public class ScheduleWebServiceImpl implements ScheduleWebService {

	@In(create = true)
	com.nas.recovery.web.action.schedule.ScheduleAction scheduleAction;

	public Schedule loadById(Long id) {
		return scheduleAction.loadFromId(id);
	}

	public List<Schedule> findByExample(Schedule exampleSchedule) {
		return scheduleAction.search(exampleSchedule);
	}

	public void save(Schedule schedule) {
		scheduleAction.persist(schedule);
	}

}
