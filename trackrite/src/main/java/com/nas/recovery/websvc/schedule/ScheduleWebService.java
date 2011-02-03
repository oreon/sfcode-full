package com.nas.recovery.websvc.schedule;

import javax.jws.WebService;
import org.wc.trackrite.schedule.Schedule;
import java.util.List;

@WebService
public interface ScheduleWebService {

	public Schedule loadById(Long id);

	public List<Schedule> findByExample(Schedule exampleSchedule);

	public void save(Schedule schedule);

}
