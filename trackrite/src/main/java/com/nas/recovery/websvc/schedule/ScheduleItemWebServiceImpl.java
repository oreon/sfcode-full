package com.nas.recovery.websvc.schedule;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.schedule.ScheduleItem;

@WebService(endpointInterface = "com.nas.recovery.websvc.schedule.ScheduleItemWebService", serviceName = "ScheduleItemWebService")
@Name("scheduleItemWebService")
public class ScheduleItemWebServiceImpl implements ScheduleItemWebService {

	@In(create = true)
	com.nas.recovery.web.action.schedule.ScheduleItemAction scheduleItemAction;

	public ScheduleItem loadById(Long id) {
		return scheduleItemAction.loadFromId(id);
	}

	public List<ScheduleItem> findByExample(ScheduleItem exampleScheduleItem) {
		return scheduleItemAction.search(exampleScheduleItem);
	}

	public void save(ScheduleItem scheduleItem) {
		scheduleItemAction.persist(scheduleItem);
	}

}
