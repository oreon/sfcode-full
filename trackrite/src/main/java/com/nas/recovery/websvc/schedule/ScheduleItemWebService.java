package com.nas.recovery.websvc.schedule;

import javax.jws.WebService;
import org.wc.trackrite.schedule.ScheduleItem;
import java.util.List;

@WebService
public interface ScheduleItemWebService {

	public ScheduleItem loadById(Long id);

	public List<ScheduleItem> findByExample(ScheduleItem exampleScheduleItem);

	public void save(ScheduleItem scheduleItem);

}
