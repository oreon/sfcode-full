package com.nas.recovery.websvc.timetrack;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.timetrack.TimeSheet;

@WebService(endpointInterface = "com.nas.recovery.websvc.timetrack.TimeSheetWebService", serviceName = "TimeSheetWebService")
@Name("timeSheetWebService")
public class TimeSheetWebServiceImpl implements TimeSheetWebService {

	@In(create = true)
	com.nas.recovery.web.action.timetrack.TimeSheetAction timeSheetAction;

	public TimeSheet loadById(Long id) {
		return timeSheetAction.loadFromId(id);
	}

	public List<TimeSheet> findByExample(TimeSheet exampleTimeSheet) {
		return timeSheetAction.search(exampleTimeSheet);
	}

	public void save(TimeSheet timeSheet) {
		timeSheetAction.persist(timeSheet);
	}

}
